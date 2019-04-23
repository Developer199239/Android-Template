package jalilurrahman.com.kakaosample.screen

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.web.KWebView
import jalilurrahman.com.kakaosample.R
import kotlinx.android.synthetic.main.activity_web.view.*

/**
 * Created by Murtuza Rahman on 2019-04-23.
 */
class WebAcitivtyScreen: Screen<WebAcitivtyScreen>() {

    val webView:KWebView = KWebView{
        withId(R.id.webView)
    }
}