package jalilurrahman.com.kakaosample.screen

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import jalilurrahman.com.kakaosample.R
import org.hamcrest.Matcher
import kotlinx.android.synthetic.main.activity_recycler.view.*

/**
 * Created by Murtuza Rahman on 2019-04-23.
 */
class NestedRecyclerActivityScreen:Screen<NestedRecyclerActivityScreen>() {
    val recycler = KRecyclerView({withId(R.id.recycler_view)},


    )
    
}