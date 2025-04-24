import androidx.paging.AsyncPagingDataDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.tymex.interview.shared_test.base.BaseUnitTest
import com.tymex.interview.user_data.dummy.dummyUserResponse
import com.tymex.interview.user_data.remote.ApiService
import com.tymex.interview.user_data.repository.ListUserRepositoryImpl
import com.tymex.interview.user_domain.model.User
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ListUserRepositoryImplTest: BaseUnitTest() {

    private lateinit var repository: ListUserRepositoryImpl
    private lateinit var apiService: ApiService

    @Before
    override fun setUp() {
        super.setUp()
        apiService = mockk(relaxed = true)
        repository = ListUserRepositoryImpl(apiService, testDispatcher)
    }

    @Test
    fun `getUsers emit PagingData with expected users`() = runTest(testDispatcher) {
        // Given
        val testUser = dummyUserResponse
        coEvery { apiService.getUsers(any(), any()) } returns testUser

        val differ = AsyncPagingDataDiffer(
            diffCallback = diffCallback,
            updateCallback = noopListCallback,
            workerDispatcher = testDispatcher,
            mainDispatcher = testDispatcher
        )

        // When
        val job = launch {
            repository.getUsers().collectLatest { pagingData ->
                differ.submitData(pagingData)
            }
        }

        advanceUntilIdle()

        // Then
        val snapshot = differ.snapshot()
        assertEquals(2, snapshot.size)
        assertEquals("user1", snapshot[0]?.login)
        assertEquals("user2", snapshot[1]?.login)

        job.cancel()

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
