package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class PlayListService @Inject constructor(
    private val api: PlayListAPI
)
 {
   suspend fun fetchPlayLists() :Flow<Result<List<PlayListRaw>>> {

       return flow {
           emit(Result.success(api.fetchAllPlayLists()))
       }.catch {
           emit(Result.failure(RuntimeException("Something went wrong")))
       }
    }

}
