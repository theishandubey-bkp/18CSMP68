package `in`.edu.cambridge.asynchronoustask

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlin.coroutines.coroutineContext

class LongOperation : AsyncTask<String?, Void?, String>() {
    protected override fun doInBackground(vararg params: String?): String? {
        try {
            Thread.sleep(100000)
        } catch (e: InterruptedException) {
            Log.e("LongOperation", "Interrupted", e)
            return "Interrupted"
        }
        return "Executed"
    }
    override fun onPostExecute(result: String) {}
    companion object {
        fun execute() {}
    }
}