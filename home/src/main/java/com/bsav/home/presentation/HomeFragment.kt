package com.bsav.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bsav.core.presentation.snackbar.showErrorMessage
import com.bsav.core.presentation.snackbar.showInternetNotAvailableMessage
import com.bsav.home.databinding.FragmentHomeBinding
import com.bsav.home.domain.model.Destination
import com.bsav.home.domain.model.Program
import com.bsav.home.domain.model.ProgramType
import com.bsav.home.presentation.HomeViewModel.State.LoadPopularMovies
import com.bsav.home.presentation.HomeViewModel.State.LoadPopularTvShows
import com.bsav.home.presentation.HomeViewModel.State.LoadTopRatedMovies
import com.bsav.home.presentation.HomeViewModel.State.LoadTopRatedTvShows
import com.bsav.home.presentation.HomeViewModel.State.NoInternetAvailable
import com.bsav.home.presentation.HomeViewModel.State.UnexpectedError
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), OnClickProgram {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getPrograms()
            binding.swipeRefresh.isRefreshing = false
        }
        initializeObservers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPrograms()
        with(binding) {
            recyclerPopularMovies.showSkeleton()
            recyclerTopRatedMovies.showSkeleton()
            recyclerPopularTvShows.showSkeleton()
            recyclerTopRatedTvShows.showSkeleton()
        }
    }

    private fun RecyclerView.showSkeleton(): SkeletonScreen {
        return Skeleton.bind(this)
            .adapter(ProgramAdapter(this@HomeFragment))
            .frozen(false)
            .count(5)
            .shimmer(true)
            .angle(20)
            .duration(1000)
            .color(android.R.color.white)
            .load(com.bsav.core.R.layout.item_program_skeleton)
            .build()
            .show()
    }

    private fun initializeObservers() {
        viewModel.run {
            state.observe(viewLifecycleOwner) {
                when (it) {
                    is LoadPopularMovies -> loadPopularMovies(it.programs)
                    is LoadPopularTvShows -> loadPopularTvShows(it.programs)
                    is LoadTopRatedMovies -> loadTopRatedMovies(it.programs)
                    is LoadTopRatedTvShows -> loadTopRatedTvShows(it.programs)
                    is UnexpectedError -> handleError()
                    is NoInternetAvailable -> showNoInternetMessage()
                }
            }
            navigate.observe(viewLifecycleOwner) {
                it.getContentIfNotHandled()?.let { destination ->
                    navigateTo(destination)
                }
            }
        }
    }

    private fun handleError() {
        view?.showErrorMessage()
    }

    private fun showNoInternetMessage() {
        view?.showInternetNotAvailableMessage { viewModel.getPrograms() }
    }

    private fun loadPopularMovies(programs: List<Program>) {
        binding.recyclerPopularMovies.setProgramsAdapter(programs)
    }

    private fun loadTopRatedMovies(programs: List<Program>) {
        binding.recyclerTopRatedMovies.setProgramsAdapter(programs)
    }

    private fun loadPopularTvShows(programs: List<Program>) {
        binding.recyclerPopularTvShows.setProgramsAdapter(programs)
    }

    private fun loadTopRatedTvShows(programs: List<Program>) {
        binding.recyclerTopRatedTvShows.setProgramsAdapter(programs)
    }

    private fun RecyclerView.setProgramsAdapter(programs: List<Program>) {
        if (programs.isNotEmpty()) {
            adapter = ProgramAdapter(this@HomeFragment, programs)
        }
    }

    override fun goToDetails(programId: Int, programType: ProgramType) {
        viewModel.goToDetail(programId, programType)
    }

    private fun navigateTo(destination: Destination) {
        findNavController().navigate(destination.deepLink)
    }
}
