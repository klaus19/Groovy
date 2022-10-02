package petros.efthymiou.groovy.playlist

import androidx.lifecycle.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PlayListViewModel(
    private val repository: PlayListRepository
) :ViewModel() {


    //usage of lIVEDATA Builder
    val playList = liveData {
        emitSource(repository.getPlaylists().asLiveData())
    }


}
