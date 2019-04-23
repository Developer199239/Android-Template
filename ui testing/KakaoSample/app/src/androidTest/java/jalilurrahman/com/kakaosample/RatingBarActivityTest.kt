package jalilurrahman.com.kakaosample

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.screen.Screen.Companion.onScreen
import jalilurrahman.com.kakaosample.screen.RatingBarActivityScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class RatingBarActivityTest {
    @Rule
    @JvmField
    val rule = ActivityTestRule(RatingBarActivity::class.java)

    @Test
    fun testContentScreen() {
        onScreen<RatingBarActivityScreen> {
            ratingBar {
                hasRating(0f)
                setRatingAt(3f)
                hasRating(3f)
            }
        }
    }

    @Test
    fun testSeekBar(){
        onScreen<RatingBarActivityScreen> {
            seekBar {
                hasProgress(70)
                dragProgressTo(30)
                hasProgress(30)
            }
        }
    }
}