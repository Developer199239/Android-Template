package jalilurrahman.com.kakaosample

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.screen.Screen.Companion.idle
import jalilurrahman.com.kakaosample.screen.RatingBarActivityScreen
import jalilurrahman.com.kakaosample.screen.SnackBarActivityScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Murtuza Rahman on 2019-04-23.
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class SnackBarActivityTest {
    @Rule
    @JvmField
    val rule = ActivityTestRule(SnackBarActivity::class.java)

    @Test
    fun testSnackBar() {
        Screen.onScreen<SnackBarActivityScreen> {
            snackBarBtn{
                click()
            }

            snackbar {
                isDisplayed()

                text {
                    hasText("This is snackbar!")
                }

                action {
                    hasText("DISMISS")
                    click()
                    idle(500)
                }
                doesNotExist()
            }
        }
    }

}