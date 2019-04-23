package jalilurrahman.com.kakaosample.screen

import android.content.ClipData.Item
import android.view.View
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import jalilurrahman.com.kakaosample.R
import org.hamcrest.Matcher

/**
 * Created by Murtuza Rahman on 2019-04-23.
 */
class RecyclerActivityScreen:Screen<RecyclerActivityScreen>() {
    val recycler: KRecyclerView = KRecyclerView({
        withId(R.id.recycler_view)
    }, itemTypeBuilder = {
        itemType(::Item)
    })

    class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {
        val title: KTextView = KTextView(parent) { withId(R.id.title) }
        val subtitle: KTextView = KTextView(parent) { withId(R.id.subtitle) }
    }
}