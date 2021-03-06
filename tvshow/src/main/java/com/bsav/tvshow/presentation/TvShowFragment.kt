package com.bsav.tvshow.presentation

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
import com.bsav.tvshow.databinding.FragmentTvShowBinding
import com.bsav.tvshow.domain.model.TvShow
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowFragment : Fragment() {

    private lateinit var binding: FragmentTvShowBinding
    private val args: TvShowFragmentArgs by navArgs()
    private val viewModel: TvShowViewModel by viewModels()
    private lateinit var skeleton: SkeletonScreen

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTvShowBinding.inflate(inflater, container, false)
        setObserver()
        viewModel.getTvShow(args.programId)
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
                is TvShowViewModel.State.UnexpectedError -> handleError()
                is TvShowViewModel.State.LoadTvShow -> showTvShow(it.tvShow)
                is TvShowViewModel.State.NoInternetAvailable -> showNoInternetMessage()
            }
        }
    }

    private fun showTvShow(tvShow: TvShow) {
        with(binding) {
            tvShow.backdropPath?.let { imgHeader.loadImageFromPathWithBaseUrl(it) }
            textTvShowTitle.text = tvShow.name
            textTvShowDescription.text = tvShow.overview
            if (tvShow.adult) {
                textIsAdult.visibility = View.VISIBLE
            }
            textReleaseYear.text = tvShow.releaseDate.substring(0, 4)
            "${tvShow.voteAverage}\u2605".also { textScore.text = it }
            "Episodes: ${tvShow.episodes}".also { textEpisodes.text = it }
            "Seasons: ${tvShow.seasons}".also { textSeasons.text = it }
            skeleton.hide()
        }
    }

    private fun handleError() {
        view?.showErrorMessage()
    }

    private fun showNoInternetMessage() {
        view?.showInternetNotAvailableMessage(indefinite = true) {
            viewModel.getTvShow(args.programId)
        }
    }
}
