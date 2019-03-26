package sample

import android.os.*

class AndroidTimer : Timer {
    var timer: CountDownTimer? = null

    override fun start(seconds: Int, onTick: (secondsUntilFinished: Int) -> Unit, onFinish: () -> Unit) {
        stop()
        onTick(seconds)
        timer = object : CountDownTimer(seconds * 1000L, 100) {
            override fun onTick(millisUntilFinished: Long) {
                onTick((millisUntilFinished / 1000).toInt())
            }

            override fun onFinish() {
                onFinish.invoke()
            }
        }.apply { start() }
    }

    override fun stop() {
        timer?.cancel()
    }
}