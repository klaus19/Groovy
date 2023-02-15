package petros.efthymiou.groovy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_play_list_detail.*
import kotlinx.android.synthetic.main.fragment_playlist.view.*
import kotlinx.android.synthetic.main.playlist_item.*
import kotlinx.android.synthetic.main.playlist_item.playlist_name
import petros.efthymiou.groovy.playlist.PlayListViewModel
import javax.inject.Inject


@AndroidEntryPoint
class PlayListDetailFragment : Fragment() {
    @Inject
    lateinit var viewModel:PlayListDetailViewmodel
    @Inject
    lateinit var viewModelFactory:PlayDetailsViewmodelFactory


    val args:PlayListDetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_play_list_detail, container, false)
        val id = args.playlistId


        setupViewmodel()

        viewModel.getPlaylistDetails(id)
        observeLiveData()
        return view
    }

    private fun observeLiveData() {
        viewModel.playListDetails.observe(this as LifecycleOwner) { playlistDetails ->
            if (playlistDetails.getOrNull() != null) {
                setupUI(playlistDetails)
            } else {

            }
        }
    }

    private fun setupViewmodel() {
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(PlayListDetailViewmodel::class.java)
    }

    private fun setupUI(playlistDetails: Result<PlayListDetail>) {
        playlist_name.text = playlistDetails.getOrNull()!!.name
        playlists_details.text = playlistDetails.getOrNull()!!.details
    }

    companion object {
        @JvmStatic
        fun newInstance()=
            PlayListDetailFragment()
    }
}