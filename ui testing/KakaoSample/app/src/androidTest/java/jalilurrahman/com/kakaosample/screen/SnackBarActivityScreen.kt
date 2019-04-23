package jalilurrahman.com.kakaosample.screen

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KSnackbar
import jalilurrahman.com.kakaosample.R

/**
 * Created by Murtuza Rahman on 2019-04-23.
 */
class SnackBarActivityScreen: Screen<SnackBarActivityScreen>() {
    val snackBarBtn: KButton = KButton {
        withId(R.id.snackbar_button)
    }

    val snackbar: KSnackbar = KSnackbar()
}