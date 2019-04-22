package jalilurrahman.com.kakao_espresso_sample

import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class KakaoActivityKakaoTest {

    @JvmField
    @Rule
    val testRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    private val kakaoScreen = KakaoScreen()

    @Test
    fun initialViewDisplayedProperly() {
        kakaoScreen {
            button { isDisplayed() }
            helloTextView {
                isDisplayed()
                hasText("Hello World!")
            }

            imageView { isNotDisplayed() }
        }
    }

    @Test
    fun textShouldBeUpdateAndImageDisplayedOnButtonClick() {
        kakaoScreen {
            button { click() }
            helloTextView {
                isDisplayed()
                hasText("Hello Kakao World!")
            }
            imageView {
                isDisplayed()
            }
        }
    }

}