package com.tymex.interview.user_data.usecase

import com.tymex.interview.core.local.Prefs
import com.tymex.interview.shared_test.base.BaseUnitTest
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class GetLoginUserUseCaseImplTest: BaseUnitTest() {

    private lateinit var useCase: GetLoginUserUseCaseImpl
    private val prefs = mockk<Prefs>()

    override fun setUp() {
        super.setUp()
        useCase = GetLoginUserUseCaseImpl(prefs)
    }

    @Test
    fun `invoke called returns isLoggedIn value true`() = runTest {
        // Given
        every { prefs.isLoggedIn } returns flowOf(true)

        // When
        val actual = useCase.invoke().first()

        // Then
        assertTrue(actual)
    }

    @Test
    fun `invoke called returns isLoggedIn value false`() = runTest {
        // Given
        every { prefs.isLoggedIn } returns flowOf(false)

        // When
        val actual = useCase.invoke().first()

        // Then
        assertFalse(actual)
    }
}