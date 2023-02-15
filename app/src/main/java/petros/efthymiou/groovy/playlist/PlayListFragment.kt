package petros.efthymiou.groovy.playlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_playlist.*
import kotlinx.android.synthetic.main.fragment_playlist.view.*
import petros.efthymiou.groovy.R
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class PlayListFragment : Fragment() {

    lateinit var viewModel: PlayListViewModel
    @Inject
    lateinit var viewmodelFactory: PlayListViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)

        setupViewModel()
        observeLoader()
        observePlayList(view)
        return view
    }

    private fun observeLoader() {
        viewModel.loader.observe(this as LifecycleOwner) { loading ->
            when (loading) {
                true -> loader.visibility = View.VISIBLE

                else -> loader.visibility = View.GONE
            }

        }
    }

    private fun observePlayList(view: View) {
        viewModel.playList.observe(this as LifecycleOwner) { playlists ->
            if (playlists.getOrNull() != null)
                setupList(view.playlist_list, playlists.getOrNull()!!)
            else {
                //TODO
            }
        }
    }

    private fun setupList(
        view: View?,
        playlists: List<PlayList>
    ) {
        with(view as RecyclerView) {
            layoutManager = LinearLayoutManager(context)

            adapter = MyPlayListRecyclerViewAdapter(playlists) { id ->
               // val action =
                //    PlaylistFragmentDirections.actionPlaylistFragmentToPlaylistDetailFragment(id)

               // findNavController().navigate(action)
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewmodelFactory).get(PlayListViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PlayListFragment().apply {


                }
            }
    }
