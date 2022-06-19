package com.bsav.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bsav.home.databinding.FragmentHomeBinding
import com.bsav.home.domain.model.Program
import com.bsav.home.domain.model.ProgramType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), OnClickProgram {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel.getPrograms()
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getPrograms()
            binding.swipeRefresh.isRefreshing = false
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeObservers()
    }

    private fun initializeObservers() {
        viewModel.run {
            state.observe(viewLifecycleOwner) {
                when (it) {
                    is HomeViewModel.State.LoadPopularMovies -> loadPopularMovies(it.programs)
                    is HomeViewModel.State.LoadPopularTvShows -> loadPopularTvShows(it.programs)
                    is HomeViewModel.State.LoadTopRatedMovies -> loadTopRatedMovies(it.programs)
                    is HomeViewModel.State.LoadTopRatedTvShows -> loadTopRatedTvShows(it.programs)
                }
            }
        }
    }

    private fun loadPopularMovies(programs: List<Program>) {
        val adapter = ProgramAdapter(programs, this)
        binding.recyclerPopularMovies.adapter = adapter
    }

    private fun loadTopRatedMovies(programs: List<Program>) {
        val adapter = ProgramAdapter(programs, this)
        binding.recyclerTopRatedMovies.adapter = adapter
    }

    private fun loadPopularTvShows(programs: List<Program>) {
        val adapter = ProgramAdapter(programs, this)
        binding.recyclerPopularTvShows.adapter = adapter
    }

    private fun loadTopRatedTvShows(programs: List<Program>) {
        val adapter = ProgramAdapter(programs, this)
        binding.recyclerTopRatedTvShows.adapter = adapter
    }

    override fun goToDetails(programId: Int, programType: ProgramType) {
        TODO("Not yet implemented")
    }
}