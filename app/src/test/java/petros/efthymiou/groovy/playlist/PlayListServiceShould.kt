package petros.efthymiou.groovy.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.Assert;
import org.junit.Assert.assertEquals
import petros.efthymiou.groovy.utils.BaseUnitTest


class PlayListServiceShould:BaseUnitTest() {

    private lateinit var service:PlayListService
    private val api:PlayListAPI = mock()
    private val playlists:List<PlayListRaw> = mock()


    @Test
    fun fetchPlayListsFromAPI() = runBlockingTest {
        service = PlayListService(api)
        service.fetchPlayLists().first()

        verify(api,times(1)).fetchAllPlayLists()
    }

    @Test
    fun convertValuesToFlowResultAndEmitThem()= runBlockingTest {

        mockSuccessfulCase()

        assertEquals(Result.success(playlists),service.fetchPlayLists().first())

    }


    @Test
    fun emitsErrorResultWhenNetworkFails() = runBlockingTest {
        mockErrorCase()

        assertEquals("Something went wrong",service.fetchPlayLists().first().exceptionOrNull()?.message)

    }

    private suspend fun mockErrorCase() {
        whenever(api.fetchAllPlayLists()).thenThrow(RuntimeException("Damn backen developers"))
        service = PlayListService(api)
    }

    private suspend fun mockSuccessfulCase() {
        whenever(api.fetchAllPlayLists()).thenReturn(playlists)

        service = PlayListService(api)
    }

}