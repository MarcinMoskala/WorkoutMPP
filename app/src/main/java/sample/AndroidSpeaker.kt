package sample

import android.content.*
import android.media.*
import android.os.*
import android.speech.tts.*
import android.util.*
import java.util.*

class AndroidSpeaker(context: Context): Speaker {

    private val whistle by lazy { MediaPlayer.create(context, R.raw.whistle_blow_cc0) }
    private val endSound by lazy { MediaPlayer.create(context, R.raw.victory) }

    private var isLoaded = false
    private val onInitListener = TextToSpeech.OnInitListener { status ->
        if (status == TextToSpeech.SUCCESS) {
            mTts.language = Locale.getDefault()
            isLoaded = true
        }
    }

    private var mTts: TextToSpeech = TextToSpeech(context, onInitListener)

    override fun speak(text: String) {
        if (isLoaded) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mTts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
            } else {
                @Suppress("DEPRECATION")
                mTts.speak(text, TextToSpeech.QUEUE_FLUSH, null)
            }
        } else
            Log.e("error", "TTS Not Initialized")
    }

    override fun playWhistle() {
        whistle.start()
    }

    override fun playEndSound() {
        endSound.start()
    }

    fun release() {
        whistle.release()
        endSound.release()
    }
}