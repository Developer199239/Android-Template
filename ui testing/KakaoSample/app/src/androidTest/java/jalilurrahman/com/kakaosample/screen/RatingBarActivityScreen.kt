package jalilurrahman.com.kakaosample.screen

import com.agoda.kakao.progress.KSeekBar
import com.agoda.kakao.rating.KRatingBar
import com.agoda.kakao.screen.Screen
import jalilurrahman.com.kakaosample.R

class RatingBarActivityScreen: Screen<RatingBarActivityScreen>() {
    val ratingBar: KRatingBar = KRatingBar {
        withId(R.id.ratingBar)
    }

    val seekBar: KSeekBar = KSeekBar{
        withId(R.id.seekBar)
    }
}