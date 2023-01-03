package petros.efthymiou.groovy.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import petros.efthymiou.groovy.utils.BaseUnitTest

class PlayListRepositoryShould:BaseUnitTest() {

    private val service:PlayListService = mock()
    private val mapper: PlaylistMapper = mock()
    private val playlists = mock<List<PlayList>>()
    private val playlistRaw = mock<List<PlayListRaw>>()
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getPlayListFromService() = runBlockingTest {

        val repository=PlayListRepository(service)
        repository.getPlaylists()

        verify(service, times(1)).fetchPlayLists()
    }

    @Test
    fun emitPlayListsFromService() = runBlockingTest {
        val repository = mockSucessfulCase()

        assertEquals(playlists,repository.getPlaylists().first().getOrNull())
    }

    @Test
    fun propagateErrors()= runBlockingTest{
        val repository = mockFailureCase()

        assertEquals(exception,repository.getPlaylists().first().exceptionOrNull())

    }

    @Test
    fun delegateBusinessLogicToMapper()= runBlockingTest {
        val repository = mockSucessfulCase()

        repository.getPlaylists().first()
        verify(mapper, times(1)).invoke(playlistRaw)
    }


    private suspend fun mockFailureCase(): PlayListRepository {
        whenever(service.fetchPlayLists()).thenReturn(
            flow {
                emit(Result.failure<List<PlayListRaw>>(exception))
            }
        )

        val repository = PlayListRepository(service,mapper)
        return repository
    }

    private suspend fun mockSucessfulCase(): PlayListRepository {
        whenever(service.fetchPlayLists()).thenReturn(
            flow {
                emit(Result.success(playlistRaw))
            }
        )

        val repository = PlayListRepository(service,mapper)
        return repository
    }
}