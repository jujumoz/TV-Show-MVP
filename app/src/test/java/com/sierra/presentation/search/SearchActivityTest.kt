package com.sierra.presentation.search

import android.os.Build
import android.os.Looper.getMainLooper
import androidx.test.core.app.ActivityScenario
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.view_tv_show_list.*
import org.hamcrest.CoreMatchers.nullValue
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import org.robolectric.shadows.ShadowToast
import java.util.concurrent.TimeUnit


private const val SEARCH = "star wars"

@RunWith(RobolectricTestRunner::class)
@LooperMode(LooperMode.Mode.PAUSED)
@Config(sdk= [Build.VERSION_CODES.P])
internal class SearchActivityTest {

    @Test
    fun `should show shows when search click`() {
        val scenario = ActivityScenario.launch(SearchActivity::class.java)
        scenario.onActivity { activity ->
            //given
            activity.edit_text_search.setText(SEARCH)

            //when
            activity.button_search.performClick()
            shadowOf(getMainLooper()).idleFor(5000, TimeUnit.MILLISECONDS)

            //then
            assertThat(ShadowToast.getTextOfLatestToast(), nullValue())
            assertEquals(5, activity.recycler_view_tv_show_list.adapter?.itemCount)
        }
    }

    @Test
    fun `should show shows when start`() {
        val scenario = ActivityScenario.launch(SearchActivity::class.java)
        scenario.onActivity { activity ->
            //given
            //INIT

            //when
            //START

            //then
            assertThat(ShadowToast.getTextOfLatestToast(), nullValue())
            assertEquals(3, activity.recycler_view_tv_show_list.adapter?.itemCount)
        }
    }
}