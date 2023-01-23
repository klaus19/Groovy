package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlayListRepository @Inject constructor(
    private val service: PlayListService,
    private val mapper: PlaylistMapper
    ) {


   suspend fun getPlaylists(): Flow<Result<List<PlayList>>> =
       service.fetchPlayLists().map {
           if (it.isSuccess)
           Result.success(mapper(it.getOrNull()!!))
           else (it.isFailure)
               Result.failure(it.exceptionOrNull()!!)
       }



    }


