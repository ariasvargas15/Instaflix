package com.bsav.home

import com.bsav.home.data.remote.di.ServiceModule
import com.bsav.home.data.remote.model.MovieNetwork
import com.bsav.home.data.remote.model.PageMovieNetwork
import com.bsav.home.data.remote.model.PageTvShowNetwork
import com.bsav.home.data.remote.model.TvShowNetwork
import com.bsav.home.data.remote.service.MovieService
import com.bsav.home.data.remote.service.TvShowService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.coEvery
import io.mockk.mockk

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ServiceModule::class]
)
object FakeServiceModule {

    @Provides
    @Reusable
    fun provideTvShowServiceModule(): TvShowService = mockk()

    @Provides
    @Reusable
    fun provideMovieServiceModule(): MovieService = mockk()
}

fun TvShowService.stubPopularTvShows() {
    coEvery { getPopularTvShows() } answers { popularShows }
}

fun TvShowService.stubTopRatedTvShows() {
    coEvery { getTopRatedTvShows() } answers { topShows }
}

fun MovieService.stubPopularMovies() {
    coEvery { getPopularMovies() } answers { popularMovies }
}

fun MovieService.stubTopRatedMovies() {
    coEvery { getTopRatedMovies() } answers { topMovies }
}

val topMovies = PageMovieNetwork(
    results = listOf(
        MovieNetwork(id = 278, posterPath = "/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg", title = "The Shawshank Redemption"),
        MovieNetwork(id = 19404, posterPath = "/2CAL2433ZeIihfX1Hb2139CX0pW.jpg", title = "Dilwale Dulhania Le Jayenge"),
        MovieNetwork(id = 238, posterPath = "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg", title = "The Godfather"),
        MovieNetwork(id = 424, posterPath = "/sF1U4EUQS8YHUYjNl3pMGNIQyr0.jpg", title = "Schindler's List"),
        MovieNetwork(id = 240, posterPath = "/hek3koDUyRQk7FIhPXsa6mT2Zc3.jpg", title = "The Godfather: Part II"),
        MovieNetwork(id = 372754, posterPath = "/cIfRCA5wEvj9tApca4UDUagQEiM.jpg", title = "Dou kyu sei – Classmates")
    )
)
val popularMovies = PageMovieNetwork(
    results = listOf(
        MovieNetwork(id = 338953, posterPath = "/jrgifaYeUtTnaH7NF5Drkgjg2MB.jpg", title = "Fantastic Beasts: The Secrets of Dumbledore"),
        MovieNetwork(id = 752623, posterPath = "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Lost City"),
        MovieNetwork(id = 675353, posterPath = "/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg", title = "Sonic the Hedgehog 2"),
        MovieNetwork(id = 818397, posterPath = "/QaNLpq3Wuu2yp5ESsXYcQCOpUk.jpg", title = "Memory"),
        MovieNetwork(id = 526896, posterPath = "/6JjfSchsU6daXk2AKX8EEBjO3Fm.jpg", title = "Morbius"),
        MovieNetwork(id = 453395, posterPath = "/9Gtg2DzBhmYamXBS1hKAhiwbBKS.jpg", title = "Doctor Strange in the Multiverse of Madness"),
    )
)

val topShows = PageTvShowNetwork(
    results = listOf(
        TvShowNetwork(id = 130392, name = "The D'Amelio Show", posterPath = "/z0iCS5Znx7TeRwlYSd4c01Z0lFx.jpg"),
        TvShowNetwork(id = 100, name = "I Am Not an Animal", posterPath = "/qG59J1Q7rpBc1dvku4azbzcqo8h.jpg"),
        TvShowNetwork(id = 94605, name = "Arcane", posterPath = "/ohGz4HDYGTite1GmRhRuBMVAn03.jpg"),
        TvShowNetwork(id = 124834, name = "Heartstopper", posterPath = "/wJJt1HG62h3WoGnLcRIbO2nNNkg.jpg"),
        TvShowNetwork(id = 19239, name = "Conan, the Boy in Future", posterPath = "/tmlhwdTBA264iQF2Us5vWdKz1fE.jpg"),
        TvShowNetwork(id = 88040, name = "Given", posterPath = "/lTP5yFKt2wzv8vM4EheNgIejEVu.jpg")
    )
)

val popularShows = PageTvShowNetwork(
    results = listOf(
        TvShowNetwork(id = 76479, name = "The Boys", posterPath = "/stTEycfG9928HYGEISBFaG1ngjM.jpg"),
        TvShowNetwork(id = 66732, name = "Stranger Things", posterPath = "/49WJfeN0moxb9IPfGn8AIqMGskD.jpg"),
        TvShowNetwork(id = 92782, name = "Ms. Marvel", posterPath = "/cdkyMYdu8ao26XOBvilNzLneUg1.jpg"),
        TvShowNetwork(id = 52814, name = "Halo", posterPath = "/nJUHX3XL1jMkk8honUZnUmudFb9.jpg"),
        TvShowNetwork(id = 92830, name = "Obi-Wan Kenobi", posterPath = "/qJRB789ceLryrLvOKrZqLKr2CGf.jpg"),
        TvShowNetwork(id = 60574, name = "Peaky Blinders", posterPath = "/vUUqzWa2LnHIVqkaKVlVGkVcZIW.jpg")
    )
)
