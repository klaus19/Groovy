package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlayListRepository(private val service: PlayListService) {


   suspend fun getPlaylists():Flow<Result<List<PlayList>>> {

       return service.fetchPlayLists()



    }

}
