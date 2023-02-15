package petros.efthymiou.groovy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import petros.efthymiou.groovy.playlist.PlayList

class PlayListDetailViewmodel:ViewModel() {


    val playListDetails:MutableLiveData<Result<PlayListDetail>> = MutableLiveData()

    fun getPlaylistDetails(id:String){

    }


}
