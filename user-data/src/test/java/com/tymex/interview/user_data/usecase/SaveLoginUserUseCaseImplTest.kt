package com.tymex.interview.user_data.usecase

import com.tymex.interview.core.local.Prefs
import com.tymex.interview.shared_test.base.BaseUnitTest
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SaveLoginUserUseCaseImplTest : BaseUnitTest() {
    private lateinit var useCase: SaveLoginUserUseCaseImpl
    private val mockPrefs = mockk<Prefs>(relaxed = true)

    override fun setUp() {
        super.setUp()
        useCase = SaveLoginUserUseCaseImpl(mockPrefs)
    }

    @Test
    fun `invoke called saveLoginStatus with true and provide username`() = runTest {
        // Given
        val username = "test username"

        // When
        useCase.invoke(username)

        // Then
        coVerify(exactly = 1) { mockPrefs.saveLoginStatus(true, username) }
    }
}