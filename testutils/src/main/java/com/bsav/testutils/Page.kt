package com.bsav.testutils

open class Page {
    companion object {
        inline fun <reified T : Page> on(): T {
            return Page().on()
        }
    }

    inline fun <reified T : Page> on(): T {
        return T::class.constructors.first().call()
    }
}
