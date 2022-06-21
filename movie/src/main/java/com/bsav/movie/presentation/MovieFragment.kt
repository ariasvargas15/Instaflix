package com.bsav.movie.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bsav.core.utils.loadImageFromPathWithBaseUrl
import com.bsav.core.utils.showErrorMessage
import com.bsav.core.utils.showInternetNotAvailableMessage
import com.bsav.movie.databinding.FragmentMovieBinding
import com.bsav.movie.domain.model.Movie
import com.bsav.movie.presentation.MovieViewModel.State.LoadMovie
import com.bsav.movie.presentation.MovieViewModel.State.NoInternetAvailable
import com.bsav.movie.presentation.MovieViewModel.State.UnexpectedError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private val args: MovieFragmentArgs by navArgs()
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        setObserver()
        viewModel.getMovie(args.programId)
        return binding.root
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
