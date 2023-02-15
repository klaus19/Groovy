package petros.efthymiou.groovy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class PlayDetailsViewmodelFactory @Inject constructor(
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlayListDetailViewmodel() as T
    }

}
