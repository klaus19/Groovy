package petros.efthymiou.groovy.playlist


import javax.inject.Inject

class PlaylistMapper @Inject constructor(): Function1<List<PlayListRaw>, List<PlayList>> {

    override fun invoke(playlistsRaw: List<PlayListRaw>): List<PlayList> {

    }
}