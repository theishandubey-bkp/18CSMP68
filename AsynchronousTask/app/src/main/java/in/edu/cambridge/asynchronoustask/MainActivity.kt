package `in`.edu.cambridge.asynchronoustask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var startBtn : Button
    private lateinit var stopBtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var task = LongOperation()
        startBtn = findViewById(R.id.start)
        stopBtn = findViewById(R.id.end)

        startBtn.setOnClickListener {
            val contextView = findViewById<View>(R.id.root)
            val snack = Snackbar.make(contextView, "Demonstration of Asynchronous Task", Snackbar.LENGTH_SHORT)
            snack.show()
            task.execute()
        }
        stopBtn.setOnClickListener {
            task.cancel(true)
            val contextView = findViewById<View>(R.id.root)
            val snack = Snackbar.make(contextView, "Asynchronous Task Interrupted", Snackbar.LENGTH_SHORT)
            snack.show()
        }
    }


}