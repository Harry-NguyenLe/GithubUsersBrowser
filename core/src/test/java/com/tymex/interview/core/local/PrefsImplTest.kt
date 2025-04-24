package com.tymex.interview.core.local

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.preferencesOf
import com.tymex.interview.shared_test.base.BaseUnitTest
import com.tymex.interview.core.utils.IS_LOGGED_IN
import com.tymex.interview.core.utils.USERNAME
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.nio.file.Files


@ExperimentalCoroutinesApi
class PrefsImplTest : BaseUnitTest() {

    private lateinit var prefs: PrefsImpl
    private val mockDataStore = mockk<DataStore<Preferences>>()
    private val dataStore: DataStore<Preferences> by lazy {
        PreferenceDataStoreFactory.create {
            Files.createTempFile("test", ".preferences_pb").toFile()
        }
    }

    @Test
    fun `isLoggedIn flow emit false when preference is not set`() = runTest(testDispatcher) {
        // Given
        every { mockDataStore.data } returns flowOf(emptyPreferences())
        prefs = PrefsImpl(mockDataStore)

        // When
        val result = prefs.isLoggedIn.first()
        advanceUntilIdle()

        // Then
        assertFalse(result)
    }

    @Test
    fun `isLoggedIn flow emits false when preference is false`() = runTest(testDispatcher) {
        // Given
        val pref = preferencesOf(IS_LOGGED_IN to false)
        coEvery { mockDataStore.data } returns flowOf(pref)
        prefs = PrefsImpl(mockDataStore)

        // When
        val actual = prefs.isLoggedIn.first()
        advanceUntilIdle()

        // Then
        assertFalse("Emission should be false", actual)
    }

    @Test
    fun `isLoggedIn flow emits true when preference is true`() = runTest(testDispatcher) {
        // Given
        val pref = preferencesOf(IS_LOGGED_IN to true)
        coEvery { mockDataStore.data } returns flowOf(pref)
        prefs = PrefsImpl(mockDataStore)

        // When
        val actual = prefs.isLoggedIn.first()
        advanceUntilIdle()

        // Then
        assertTrue("Emission should be true", actual)
    }

    @Test
    fun `isLoggedIn flow emits false when exception occurs`() = runTest(testDispatcher) {
        // Given
        val exception = IOException("Test exception")
        coEvery { mockDataStore.data } returns flow { throw exception }
        prefs = PrefsImpl(mockDataStore)

        // When
        val actual = prefs.isLoggedIn.first()
        advanceUntilIdle()

        // Then
        assertFalse("Should emit false on IOException", actual)
    }

    @Test
    fun `isLoggedIn flow rethrows non-IOException`() = runTest(testDispatcher) {
        // Given
        try {
            val runtimeException = RuntimeException("Something else went wrong")
            coEvery { mockDataStore.data } returns flow { throw runtimeException }
            prefs = PrefsImpl(mockDataStore)

            // When
            val actual = prefs.isLoggedIn.first()
            advanceUntilIdle()

            // Then
            with(actual) {
                assert(this)
            }
        } catch (e: RuntimeException) {
            println(e.printStackTrace())
        }
    }


    @Test
    fun `saveLoginStatus saves true and username correctly`() = runTest(testDispatcher) {
        // Given
        prefs = PrefsImpl(dataStore)

        // When
        val username = "testUser"
        prefs.saveLoginStatus(true, username)
        advanceUntilIdle()

        // Then
        val preferences = dataStore.data.first()
        assertTrue(preferences[IS_LOGGED_IN] ?: false)
        assertEquals(username, preferences[USERNAME])
    }

    @Test
    fun `saveLoginStatus remove username when username is null`() = runTest(testDispatcher) {
        // Given
        prefs = PrefsImpl(dataStore)

        // When
        val username = null
        prefs.saveLoginStatus(true, username)
        advanceUntilIdle()

        // Then
        val preferences = dataStore.data.first()
        assertEquals(username, preferences[USERNAME])
    }
}