package com.bsav.movie.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bsav.core.presentation.glide.loadImageFromPathWithBaseUrl
import com.bsav.core.presentation.snackbar.showErrorMessage
import com.bsav.core.presentation.snackbar.showInternetNotAvailableMessage
import com.bsav.movie.databinding.FragmentMovieBinding
import com.bsav.movie.domain.model.Movie
import com.bsav.movie.presentation.MovieViewModel.State.LoadMovie
import com.bsav.movie.presentation.MovieViewModel.State.NoInternetAvailable
import com.bsav.movie.presentation.MovieViewModel.State.UnexpectedError
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private val args: MovieFragmentArgs by navArgs()
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var skeleton: SkeletonScreen

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        setObserver()
        viewModel.getMovie(args.programId)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        skeleton = binding.constraintBody.showSkeleton()
    }

    private fun View.showSkeleton(): SkeletonScreen {
        return Skeleton.bind(this)
            .shimmer(true)
            .angle(20)
            .color(android.R.color.white)
            .load(com.bsav.core.R.layout.item_body_skeleton)
            .build()
            .show()
    }

    private fun setObserver() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is UnexpectedError -> handleError()
                is LoadMovie -> showMovie(it.movie)
                is NoInternetAvailable -> showNoInternetMessage()
            }
        }
    }

    private fun showMovie(movie: Movie) {
        with(binding) {
            movie.backdropPath?.let { imgHeader.loadImageFromPathWithBaseUrl(it) }
            textMovieTitle.text = movie.title
            textMovieDescription.text = movie.overview
            if (movie.adult) {
                textIsAdult.visibility = View.VISIBLE
            }
            textReleaseYear.text = movie.releaseDate.substring(0, 4)
            "${movie.voteAverage}\u2605".also { textScore.text = it }
            skeleton.hide()
        }
    }

    private fun handleError() {
        view?.showErrorMessage()
    }

    private fun showNoInternetMessage() {
        view?.showInternetNotAvailableMessage(indefinite = true) {
            viewModel.getMovie(args.programId)
        }
    }
}
