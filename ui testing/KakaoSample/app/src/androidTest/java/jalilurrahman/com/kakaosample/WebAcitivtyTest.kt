package jalilurrahman.com.kakaosample

import androidx.test.espresso.web.webdriver.Locator
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.screen.Screen.Companion.onScreen
import jalilurrahman.com.kakaosample.screen.WebAcitivtyScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Murtuza Rahman on 2019-04-23.
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class WebAcitivtyTest {
    @Rule
    @JvmField
    val rule = ActivityTestRule(WebAcitivty::class.java)

    @Test
    fun testWebViewHasTextHelloAndClickLink() {
        Screen.onScreen<WebAcitivtyScreen> {
            webView{
                withElement(Locator.ID, "text") {
                    hasText("Hello")
                }
                withElement(Locator.LINK_TEXT, "My Home") {
                    click()
                }
            }
        }
    }
}