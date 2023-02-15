package petros.efthymiou.groovy

import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import org.hamcrest.CoreMatchers
import org.junit.Test

class PlayListDetailsFeature:BaseUITest(){

    @Test
    fun displayPlaylistAndDetails(){

        Espresso.onView(
            CoreMatchers.allOf(
                ViewMatchers.withId(R.id.playlist_image),
                ViewMatchers.isDescendantOfA(nthChildOf(ViewMatchers.withId(R.id.playlist_list), 0))
            )
        )
            .perform()

        assertDisplayed("Hard Rock Cafe")
        assertDisplayed("Tejas")

    }

}