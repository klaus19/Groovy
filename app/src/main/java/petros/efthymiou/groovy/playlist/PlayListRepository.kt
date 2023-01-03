package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlayListRepository @Inject constructor(
    private val service: PlayListService,
    private val mapper: PlaylistMapper
    ) {


   suspend fun getPlaylists(): Flow<Result<List<PlayListRaw>>> = service.fetchPlayLists()



    }


