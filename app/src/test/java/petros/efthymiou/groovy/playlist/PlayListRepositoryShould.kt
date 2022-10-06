package petros.efthymiou.groovy.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import petros.efthymiou.groovy.utils.BaseUnitTest

class PlayListRepositoryShould:BaseUnitTest() {

    private val service:PlayListService = mock()

    @Test
    fun getPlayListFromService() = runBlockingTest {

        val repository=PlayListRepository(service)
        repository.getPlaylists()

        verify(service, times(1)).fetchPlayLists()


    }
}