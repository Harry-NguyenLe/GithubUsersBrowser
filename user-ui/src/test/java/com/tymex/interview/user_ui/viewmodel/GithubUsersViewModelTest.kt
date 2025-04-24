package com.tymex.interview.user_ui.viewmodel

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.tymex.interview.shared_test.base.BaseUnitTest
import com.tymex.interview.user_domain.model.User
import com.tymex.interview.user_domain.usecase.GetUserPagingUseCase
import com.tymex.interview.user_ui.dummy.dummyUser
import com.tymex.interview.user_ui.screen.list.GithubUsersViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class GithubUsersViewModelTest : BaseUnitTest() {

    private lateinit var viewModel: GithubUsersViewModel
    private val mockUseCase = mockk<GetUserPagingUseCase>()

    @Test
    fun `usersFlow emit expected PagingData`() = runTest {
        // Given
        val user = dummyUser
        val expectedPagingData = PagingData.from(listOf(user))
        every { mockUseCase.invoke() } returns flowOf(expectedPagingData)
        val differ = AsyncPagingDataDiffer(
            diffCallback = diffCallback,
            updateCallback = noopListCallback,
            workerDispatcher = testDispatcher,
            mainDispatcher = testDispatcher
        )

        // When
        viewModel = GithubUsersViewModel(mockUseCase)
        val actual = viewModel.usersFlow.first()
        differ.submitData(actual)
        advanceUntilIdle()

        // Then
        assertEquals(1, differ.itemCount)
        assertEquals("user1", differ.snapshot()[0]?.login)

    }


    private val diffCallback = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem == newItem
    }

    private val noopListCallback = object : ListUpdateCallback {
        override fun onInserted(position: Int, count: Int) {}
        override fun onRemoved(position: Int, count: Int) {}
        override fun onMoved(fromPosition: Int, toPosition: Int) {}
        override fun onChanged(position: Int, count: Int, payload: Any?) {}
    }
}