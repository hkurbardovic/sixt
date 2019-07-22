package com.hariskurbardovic.sixt

import android.widget.FrameLayout
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.rule.ActivityTestRule
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hariskurbardovic.sixt.map.MapsActivity
import com.hariskurbardovic.sixt.map.fragments.MapFragment
import org.hamcrest.Matchers.instanceOf
import org.hamcrest.Matchers.notNullValue
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MapsActivity::class.java)

    @Test
    fun ensureViewsNotNullAndPresentMapsActivity() {
        val activity = activityTestRule.activity
        val bottomNavigationView =
            activity.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        assertThat(bottomNavigationView, notNullValue())
        assertThat(bottomNavigationView, instanceOf(BottomNavigationView::class.java))

        val navHostFragment = activity.findViewById<FrameLayout>(R.id.nav_host_fragment)

        assertThat(navHostFragment, notNullValue())
        assertThat(navHostFragment, instanceOf(FrameLayout::class.java))
    }

    @Test
    fun recreateMapFragment() {
        val scenario = launchFragmentInContainer<MapFragment>(null, R.layout.fragment_map)
        scenario.recreate()
    }

    @Test
    fun recreateListFragment() {
        val scenario = launchFragmentInContainer<MapFragment>(null, R.layout.fragment_list)
        scenario.recreate()
    }
}