package petros.efthymiou.groovy.playlist
import junit.framework.TestCase.assertEquals
import org.junit.Test
import petros.efthymiou.groovy.utils.BaseUnitTest

class PlayListMapperShould:BaseUnitTest() {

    private val playlistRaw = PlayListRaw("1","da,name","da,category")
    private val playlistRawRock = PlayListRaw("1","da,name","rock")

    private val mapper=PlaylistMapper()

    private val playlists = mapper(listOf(playlistRaw))
    private val playlist = playlists[0]
    private val playlistRock = mapper(listOf(playlistRawRock))[0]

    @Test
    fun keepSameId(){
        assertEquals(playlistRaw.id,playlist.id)
    }

    @Test
    fun keepSameName(){
        assertEquals(playlistRaw.type,playlist.name)
    }

    @Test
    fun keepSameCategory(){
        assertEquals(playlistRaw.category,playlist.category)
    }

    @Test
    fun mapDefaultImageWhenNotRock(){
        assertEquals(petros.efthymiou.groovy.R.mipmap.playlist,playlist.image)
    }

    @Test
    fun mapRockImageWhenRockCategory(){
        assertEquals(petros.efthymiou.groovy.R.mipmap.rock,playlistRock.image)
    }

}