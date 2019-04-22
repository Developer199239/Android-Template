package jalilurrahman.com.kakao_espresso_sample

import com.agoda.kakao.image.KImageView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView

class KakaoScreen : Screen<KakaoScreen>() {
    val helloTextView: KTextView = KTextView{
        withId(R.id.text_view_hello)
    }

    val button: KButton = KButton{
       withId(R.id.button_update)
    }

    val imageView: KImageView = KImageView{
        withId(R.id.image_view_kakao)
    }
}