package petros.efthymiou.groovy.playlist

import androidx.lifecycle.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PlayListViewModel(
    private val repository: PlayListRepository
) :ViewModel() {


    val loader = MutableLiveData<Boolean>()


    //usage of lIVEDATA Builder
    val playList = liveData {
        loader.postValue(true)

        emitSource(repository.getPlaylists()
            .onEach {
                loader.postValue(false)
            }
            .asLiveData())
    }



}
