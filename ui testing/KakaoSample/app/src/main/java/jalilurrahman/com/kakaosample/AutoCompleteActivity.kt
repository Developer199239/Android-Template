package jalilurrahman.com.kakaosample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView

class AutoCompleteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_complete)

        val adapter = ArrayAdapter<String>(this, R.layout.item_autocomplete, R.id.text,
            listOf("Title 1", "Title 2", "Title 3", "Title 4", "Title 5",
                "Title 6", "Title 7", "Title 8", "Title 9", "Title 10"))

        val autocomplete = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        autocomplete.setAdapter(adapter)
    }
}
