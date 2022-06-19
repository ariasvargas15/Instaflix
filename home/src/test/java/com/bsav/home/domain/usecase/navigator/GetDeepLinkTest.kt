package com.bsav.home.domain.usecase.navigator

import android.net.Uri
import com.bsav.home.domain.model.Destination
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import org.junit.Assert
import org.junit.Test


class GetDeepLinkTest {

    private val getDeepLink = GetDeepLink()
    private val uri = mockk<Uri>()
    private val expected = Destination(uri)

    init {
        mockkStatic(Uri::class)
    }

    @Test
    fun `when params are empty should not concat query`() {
        every { Uri.parse("myapp://com.bsav.instaflix/path") } answers { uri }

        val response = getDeepLink(emptyMap(), "path")

        Assert.assertEquals(expected, response)
    }

    @Test
    fun `when params are not empty should concat query`() {
        every { Uri.parse("myapp://com.bsav.instaflix/path?id=1") } answers { uri }

        val response = getDeepLink(mapOf("id" to 1), "path")

        Assert.assertEquals(expected, response)
    }

    @Test
    fun `when params are more than two should concat query with ampersand`() {
        every { Uri.parse("myapp://com.bsav.instaflix/path?id=1&name=Stranger Things&released=true") } answers { uri }
        val params = mapOf(
            "id" to 1,
            "name" to "Stranger Things",
            "released" to true
        )

        val response = getDeepLink(params, "path")

        Assert.assertEquals(expected, response)
    }
}