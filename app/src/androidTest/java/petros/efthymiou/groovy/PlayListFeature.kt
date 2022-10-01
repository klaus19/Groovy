package petros.efthymiou.groovy

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class PlayListFeature {

    val mActivityTestRule = ActivityTestRule(MainActivity::class.java)
    @Rule get



    @Test
    fun displayScreenTitle() {
        assertDisplayed("PlayLists")
    }
}