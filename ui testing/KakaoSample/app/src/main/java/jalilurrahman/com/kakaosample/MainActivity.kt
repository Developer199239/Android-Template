package jalilurrahman.com.kakaosample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        autocompleteBtn.setOnClickListener {
            var intent = Intent(this, AutoCompleteActivity::class.java)
            startActivity(intent)
        }

        ratingbarBtn.setOnClickListener {
            var intent = Intent(this, RatingBarActivity::class.java)
            startActivity(intent)
        }

        snackbarBtn.setOnClickListener {
            var intent = Intent(this, SnackBarActivity::class.java)
            startActivity(intent)
        }

        webBtn.setOnClickListener {
            var intent = Intent(this, WebAcitivty::class.java)
            startActivity(intent)
        }

        recyclerBtn.setOnClickListener {
            var intent = Intent(this, RecyclerActivity::class.java)
            startActivity(intent)
        }

        nextRecyclerViewBtn.setOnClickListener {
            var intent = Intent(this, NestedRecyclerActivity::class.java)
            startActivity(intent)
        }
    }
}
