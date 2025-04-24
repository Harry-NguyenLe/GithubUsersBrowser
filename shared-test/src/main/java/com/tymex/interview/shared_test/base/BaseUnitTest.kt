package com.tymex.interview.shared_test.base

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.test.StandardTestDispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.HttpURLConnection

fun provideMoshi(): Moshi =
    Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()


open class BaseUnitTest {

    @get:Rule
    val mockWebServerRule = MockWebServerRule()

    val mockedServer: MockWebServer
        get() = mockWebServerRule.mockedServer

    val testDispatcher = StandardTestDispatcher()

    // Set the main coroutines dispatcher for unit testing.
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule(testDispatcher)

    @Before
    open fun setUp() {
    }

    @After
    open fun tearDown() {
    }

    private fun response(code: Int, block: MockResponse.() -> Unit = {}) =
        MockResponse().setResponseCode(code).apply(block)

    fun enqueueResponse(
        code: Int = HttpURLConnection.HTTP_OK,
        block: MockResponse.() -> Unit = {}
    ) {
        mockedServer.enqueue(response(code, block))
    }

    inline fun <reified T> MockWebServer.enqueueService(): T =
        Retrofit.Builder()
            .baseUrl(this.url("/"))
            .addConverterFactory(
                MoshiConverterFactory.create(provideMoshi())
            ).build()
            .create(T::class.java)

}