package `in`.edu.cambridge.mediaplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var playBtn: ImageButton
    private lateinit var pauseBtn: ImageButton
    private lateinit var forwardBtn: ImageButton
    private lateinit var backwardBtn: ImageButton
    private lateinit var seekBar: SeekBar

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var runnable:Runnable
    private var handler: Handler = Handler()
    private var pause:Boolean = false

    val MediaPlayer.seconds:Int
        get() {
            return this.duration / 1000
        }
    val MediaPlayer.currentSeconds:Int
        get() {
            return this.currentPosition/1000
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playBtn = findViewById(R.id.play)
        pauseBtn = findViewById(R.id.pause)
        forwardBtn = findViewById(R.id.forward)
        backwardBtn = findViewById(R.id.backward)
        seekBar= findViewById(R.id.audio_seekbar)

        playBtn.setOnClickListener{
            if(pause){
                mediaPlayer.seekTo(mediaPlayer.currentPosition)
                mediaPlayer.start()
                pause = false
                val contextView = findViewById<View>(R.id.root)
                val snack = Snackbar.make(contextView, "Media Playing", Snackbar.LENGTH_SHORT)
                snack.show()
            }else{
                mediaPlayer = MediaPlayer.create(applicationContext,R.raw.shape_of_you)
                mediaPlayer.start()
                val contextView = findViewById<View>(R.id.root)
                val snack = Snackbar.make(contextView, "Media Playing", Snackbar.LENGTH_SHORT)
                snack.show()
            }
            initializeSeekBar()
            playBtn.isEnabled = false
            pauseBtn.isEnabled = true
            mediaPlayer.setOnCompletionListener {
                playBtn.isEnabled = true
                pauseBtn.isEnabled = false
                val contextView = findViewById<View>(R.id.root)
                val snack = Snackbar.make(contextView, "Media Ended", Snackbar.LENGTH_SHORT)
                snack.show()
            }
        }

        pauseBtn.setOnClickListener {
            if(mediaPlayer.isPlaying){
                mediaPlayer.pause()
                pause = true
                playBtn.isEnabled = true
                pauseBtn.isEnabled = false
                val contextView = findViewById<View>(R.id.root)
                val snack = Snackbar.make(contextView, "Media Paused", Snackbar.LENGTH_SHORT)
                snack.show()
            }
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                if (b) {
                    mediaPlayer.seekTo(i * 1000)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })

        forwardBtn.setOnClickListener {
            var i: Int = mediaPlayer.currentPosition
            mediaPlayer.seekTo(i+10000)
            val contextView = findViewById<View>(R.id.root)
            val snack = Snackbar.make(contextView, "Media Forwarded 10 Seconds", Snackbar.LENGTH_SHORT)
            snack.show()
        }

        backwardBtn.setOnClickListener {
            var i: Int = mediaPlayer.currentPosition
            mediaPlayer.seekTo(i-10000)
            val contextView = findViewById<View>(R.id.root)
            val snack = Snackbar.make(contextView, "Media Backwarded 10 Seconds", Snackbar.LENGTH_SHORT)
            snack.show()
        }
    }

    private fun initializeSeekBar() {
        seekBar.max = mediaPlayer.seconds

        runnable = Runnable {
            seekBar.progress = mediaPlayer.currentSeconds
            val diff = mediaPlayer.seconds - mediaPlayer.currentSeconds
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
    }
}



