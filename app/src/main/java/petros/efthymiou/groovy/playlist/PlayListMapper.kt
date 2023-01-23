package petros.efthymiou.groovy.playlist


import petros.efthymiou.groovy.R
import javax.inject.Inject

class PlaylistMapper @Inject constructor(): Function1<List<PlayListRaw>, List<PlayList>> {

    override fun invoke(playlistsRaw: List<PlayListRaw>): List<PlayList> {
      return playlistsRaw.map {
          val image = when(it.category){
              "rock" ->R.mipmap.rock
              else ->R.mipmap.playlist
          }

          PlayList(it.id,it.type,it.category,image)
      }
    }
}