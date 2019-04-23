package jalilurrahman.com.kakaosample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_snack_bar.*

class SnackBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snack_bar)

        snackbar_button.setOnClickListener {
            val snackbar = Snackbar.make(findViewById(android.R.id.content),
                "This is snackbar!", Snackbar.LENGTH_LONG)

            snackbar.setAction("DISMISS") {
                snackbar.dismiss()
            }

            snackbar.show()
        }
    }
}
