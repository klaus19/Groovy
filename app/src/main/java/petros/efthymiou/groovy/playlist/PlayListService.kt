package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class PlayListService(
    private val api: PlayListAPI
)
 {
   suspend fun fetchPlayLists() :Flow<Result<List<PlayList>>> {

       return flow {
           emit(Result.success(api.fetchAllPlayLists()))
       }
    }

}
