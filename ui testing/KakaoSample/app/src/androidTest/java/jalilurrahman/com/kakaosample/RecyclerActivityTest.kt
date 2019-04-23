package jalilurrahman.com.kakaosample

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.screen.Screen.Companion.onScreen
import jalilurrahman.com.kakaosample.screen.RecyclerActivityScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Murtuza Rahman on 2019-04-23.
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class RecyclerActivityTest {
    @Rule
    @JvmField
    val rule = ActivityTestRule(RecyclerActivity::class.java)

    @Test
    fun testContentItemsRecyclerView(){
        onScreen<RecyclerActivityScreen>{
            recycler{
                isVisible()
                hasSize(10)

                firstChild<RecyclerActivityScreen.Item> {
                    isVisible()
                    title{
                        hasText("Title 1")
                    }
                }

                lastChild<RecyclerActivityScreen.Item> {
                    isVisible()
                    title {
                        hasText("Final Title")
                    }
                }

                children<RecyclerActivityScreen.Item> {
                    subtitle { hasText("This is a test subtitle character sequence") }
                }

                childWith<RecyclerActivityScreen.Item> { withDescendant { withText("Final Title") } } perform {
                    title {
                        isDisplayed()
                        hasText("Final Title")
                    }
                }
            }
        }
    }
}