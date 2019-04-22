package jalilurrahman.com.kakaosample.screen

import android.content.ClipData.Item
import android.widget.ListView
import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.list.KAdapterItem
import com.agoda.kakao.list.KAdapterItemTypeBuilder
import com.agoda.kakao.list.KListView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import jalilurrahman.com.kakaosample.R

class AutoCompleteActivityScreen : Screen<AutoCompleteActivityScreen>() {
    val input = KEditText {
        withId(R.id.autoCompleteTextView)
    }

//    val list = KListView(
//        builder = { isInstanceOf(ListView::class.java) },
//        itemTypeBuilder = KAdapterItemTypeBuilder
//    )
}