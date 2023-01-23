package petros.efthymiou.groovy

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import petros.efthymiou.groovy.playlist.idlingResource


@RunWith(AndroidJUnit4::class)
class PlayListFeature :BaseUITest() {

    val mActivityTestRule = ActivityTestRule(MainActivity::class.java)
    @Rule get



    @Test
    fun displayScreenTitle() {
        assertDisplayed("PlayLists")
    }

    @Test
    fun displayPlayLists(){
        assertRecyclerViewItemCount(R.id.playlist_list,10)

        onView(allOf(withId(R.id.playlist_name), isDescendantOfA(nthChildOf(withId( R.id.playlist_list),
            0))))
            .check(matches(withText("Hard Rock Cafe")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.playlist_category), isDescendantOfA(nthChildOf(withId( R.id.playlist_list),
            0))))
            .check(matches(withText("rock")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.playlist_image), isDescendantOfA(nthChildOf(withId( R.id.playlist_list),
            1))))
            .check(matches(withDrawable(R.mipmap.playlist)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun displayLoaderWhileFetchingPlayLists(){
        IdlingRegistry.getInstance().register(idlingResource)
        assertDisplayed(R.id.loader)
    }

    @Test
    fun hidesLoader(){

        assertNotDisplayed(R.id.loader)
    }

    @Test
    fun displaysRockForRockList(){
        onView(allOf(withId(R.id.playlist_image), isDescendantOfA(nthChildOf(withId( R.id.playlist_list),0))))
            .check(matches(withDrawable(R.mipmap.rock)))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.playlist_image), isDescendantOfA(nthChildOf(withId( R.id.playlist_list), 3))))
            .check(matches(withDrawable(R.mipmap.rock)))
            .check(matches(isDisplayed()))
    }


    @Test
    fun navigateToDetailsScreen(){
        onView(allOf(withId(R.id.playlist_image), isDescendantOfA(nthChildOf(withId( R.id.playlist_list),0))))
            .perform()
        assertDisplayed(R.id.playlists_details_root)
    }




}