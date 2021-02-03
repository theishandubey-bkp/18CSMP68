package `in`.edu.cambridge.clipboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var copyText: EditText
    private lateinit var pasteText: TextView

    private lateinit var copyBtn: Button
    private lateinit var pasteBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        copyText = findViewById(R.id.copy_this_text)
        copyBtn = findViewById(R.id.copy_text)
        copyBtn.setOnClickListener { copyText() }

        pasteText = findViewById(R.id.paste_here)
        pasteBtn = findViewById(R.id.paste_text)
        pasteBtn.setOnClickListener { pasteText() }

    }

    fun copyText(){
        val contextView = findViewById<View>(R.id.root)
        val snack = Snackbar.make(contextView, "Text Copied", Snackbar.LENGTH_INDEFINITE)
        snack.show()

    }

    fun pasteText(){
        pasteText.text = copyText.text.toString()
        val contextView = findViewById<View>(R.id.root)
        val snack = Snackbar.make(contextView, "Text Pasted", Snackbar.LENGTH_INDEFINITE)
        snack.show()
    }
}