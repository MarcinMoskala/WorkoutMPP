package sample

import android.content.Context
import android.os.Build
import android.speech.tts.TextToSpeech

class AndroidSpeaker(context: Context) : Speaker {

    private var tts: TextToSpeech = TextToSpeech(context, null)

    override fun speak(text: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        } else {
            @Suppress("DEPRECATION")
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null)
        }
    }
}