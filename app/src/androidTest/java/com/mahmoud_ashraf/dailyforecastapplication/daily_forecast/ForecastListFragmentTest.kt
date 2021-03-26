package com.mahmoud_ashraf.dailyforecastapplication.daily_forecast

import android.widget.EditText
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.mahmoud_ashraf.dailyforecastapplication.R
import org.junit.Rule
import org.junit.Test


class ForecastListFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(DailyForecastActivity::class.java)

    @Test
    fun test_isForecastListVisible_onAppLaunch() {
        Espresso.onView(withId(R.id.rv_forecasts))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_isSearchViewEnabled_onAppLaunch() {
        Espresso.onView(withId(R.id.sv_main))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))

    }

    @Test
    fun test_when_submitSearch_then_progress_start_load() {
        Espresso.onView(withId(R.id.progressBar))
            .check(ViewAssertions.matches(withEffectiveVisibility(Visibility.GONE)))

        Espresso. onView(withId(R.id.sv_main)).perform(click())
        // Type the text in the search field and submit the query
        Espresso.onView(isAssignableFrom(EditText::class.java)).perform(
            typeText("cairo"),
            pressImeActionButton()
        )
    }


}