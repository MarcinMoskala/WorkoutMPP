package sample

interface Timer {
    fun start(seconds: Int, onTick: (secondsLeft: Int)->Unit, onFinish: ()->Unit)
    fun stop()
}