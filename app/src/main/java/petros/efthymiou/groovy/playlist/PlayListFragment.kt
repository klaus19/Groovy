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
import petros.efthymiou.groovy.R

/**
 * A fragment representing a list of Items.
 */
class PlayListFragment : Fragment() {

    lateinit var viewModel: PlayListViewModel
    lateinit var viewmodelFactory: PlayListViewModelFactory
    private val repository= PlayListRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)

        setupViewModel()

        viewModel.playList.observe( this as LifecycleOwner) { playlists ->
            if (playlists.getOrNull()!=null)
            setupList(view, playlists.getOrNull()!!)
            else{
                //TODO
            }
        }
        return view
    }

    private fun setupList(
        view: View?,
        playlists: List<PlayList>
    ) {
        with(view as RecyclerView) {
            layoutManager = LinearLayoutManager(context)

            adapter = MyPlayListRecyclerViewAdapter(playlists)
        }
    }

    private fun setupViewModel() {
        viewmodelFactory = PlayListViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewmodelFactory).get(PlayListViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PlayListFragment().apply {


                }
            }
    }
