package sample

import android.os.CountDownTimer

class AndroidTimer : Timer {
    private var timer: CountDownTimer? = null

    override fun start(seconds: Int, onTick: (secondsUntilFinished: Int) -> Unit, onFinish: () -> Unit) {
        stop()
        onTick(seconds)
        timer = object : CountDownTimer(seconds * SEC_IN_MILLIS, WAKE_UP_EVERY_MILLIS) {
            override fun onTick(millisUntilFinished: Long) {
                onTick((millisUntilFinished / SEC_IN_MILLIS).toInt())
            }

            override fun onFinish() {
                onFinish.invoke()
            }
        }.apply { start() }
    }

    override fun stop() {
        timer?.cancel()
        timer = null
    }

    companion object {
        const val SEC_IN_MILLIS = 1000L
        const val WAKE_UP_EVERY_MILLIS = 100L
    }
}
