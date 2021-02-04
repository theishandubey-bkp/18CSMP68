package `in`.edu.cambridge.fileapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import java.io.*

class MainActivity : AppCompatActivity() {

    private lateinit var text_input: EditText
    private lateinit var saveBtn : Button
    private lateinit var openBtn : Button
    private lateinit var open_text : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text_input = findViewById(R.id.text_input)
        open_text = findViewById(R.id.open_file_text)

        openBtn = findViewById(R.id.open_file)
        saveBtn = findViewById(R.id.save_file)

        saveBtn.setOnClickListener {
            var data : String = text_input.text.toString()
            writeToFile(data, this)
            Toast.makeText(this, "File Saved !", Toast.LENGTH_SHORT).show()
        }

        openBtn.setOnClickListener {
            var data = readFromFile(this)
            open_text.text = data
        }

    }

    private fun writeToFile(data: String, context: Context) {
        try {
            val outputStreamWriter =
                OutputStreamWriter(context.openFileOutput("file.txt", Context.MODE_PRIVATE))
            outputStreamWriter.write(data)
            outputStreamWriter.close()
        } catch (e: IOException) {
            Log.e("Exception", "File write failed: " + e.toString())
        }
    }

    private fun readFromFile(context: Context): String? {
        var str = ""
        try {
            val inputStream: InputStream = context.openFileInput("file.txt")
            if (inputStream != null) {
                val inputStreamReader = InputStreamReader(inputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                var receiveString: String? = ""
                val stringBuilder = StringBuilder()
                while (bufferedReader.readLine().also { receiveString = it } != null) {
                    stringBuilder.append("\n").append(receiveString)
                }
                inputStream.close()
                str = stringBuilder.toString()
            }
        } catch (e: FileNotFoundException) {
            Log.e("Main Activity", "File not found: " + e.toString())
        } catch (e: IOException) {
            Log.e("Main Activity", "Can not read file: $e")
        }
        return str
    }
}