package com.tymex.interview.shared_test.base

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.rules.ExternalResource

class MockWebServerRule : ExternalResource() {
    companion object {
        private const val TAG = "MockWebServerRule"
    }

    val mockedServer = MockWebServer()

    @Before
    public override fun before() {
        super.before()
        mockedServer.start()
    }

    @After
    public override fun after() {
        super.after()
        try {
            mockedServer.shutdown()
        } catch (e: Exception) {
            println("MockWebServerRule: exception when shutdown mock server: ${e.printStackTrace()}")
        }
    }
}
