package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlayListRepository @Inject constructor(private val service: PlayListService) {


   suspend fun getPlaylists():Flow<Result<List<PlayList>>> = service.fetchPlayLists()



    }


