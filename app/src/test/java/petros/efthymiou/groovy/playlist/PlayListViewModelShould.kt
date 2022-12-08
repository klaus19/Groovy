package petros.efthymiou.groovy.playlist


import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.Assert.*
import petros.efthymiou.groovy.utils.BaseUnitTest
import petros.efthymiou.groovy.utils.captureValues
import petros.efthymiou.groovy.utils.getValueForTest


class PlayListViewModelShould:BaseUnitTest() {



    private val repository: PlayListRepository = mock()
    private val playlists = mock<List<PlayList>>()
    private val expected = Result.success(playlists)
    private val exception = RuntimeException("Something went wrong")




    @Test
    fun getPlayListFromRepository() = runBlockingTest{
        val viewModel = mockSuccessfulCase()

        viewModel.playList.getValueForTest()

        verify(repository, times(1)).getPlaylists()
    }

    @Test
    fun emitsPlayListFromRepository() = runBlockingTest{
        val viewModel = mockSuccessfulCase()

        assertEquals(expected,viewModel.playList.getValueForTest())

    }

    @Test
    fun emitErrorWhenReceivedError() {
        val viewModel = mockErrorCase()
        assertEquals(exception,viewModel.playList.getValueForTest()?.exceptionOrNull())
    }

    @Test
    fun showSpinnerWhileLoading() = runBlockingTest {
        val viewModel = mockSuccessfulCase()

        viewModel.loader.captureValues{
            viewModel.playList.getValueForTest()

            assertEquals(true,values[0])
        }
    }

    @Test
    fun closeLoaderAfterPlayListLoad() = runBlockingTest {
        val viewModel = mockSuccessfulCase()

        viewModel.loader.captureValues {
            viewModel.playList.getValueForTest()

            assertEquals(false,values.last())
    }
        }

    @Test
    fun closeLoaderAfterError() = runBlockingTest {
        val viewModel = mockErrorCase()

        viewModel.loader.captureValues {
            viewModel.playList.getValueForTest()

            assertEquals(false,values.last())
        }
    }


    private fun mockSuccessfulCase(): PlayListViewModel {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(expected)
                }
            )

        }
        return PlayListViewModel(repository)
    }

    private fun mockErrorCase(): PlayListViewModel {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(Result.failure<List<PlayList>>(exception))
                }
            )
        }
        return PlayListViewModel(repository)
    }
}