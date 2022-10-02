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
import petros.efthymiou.groovy.utils.getValueForTest


class PlayListViewModelShould:BaseUnitTest() {



    private val repository: PlayListRepository = mock()
    private val playlists = mock<List<PlayList>>()
    private val expected = Result.success(playlists)




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

    private fun mockSuccessfulCase(): PlayListViewModel {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(expected)
                }
            )

        }
        val viewModel = PlayListViewModel(repository)
        return viewModel
    }
}