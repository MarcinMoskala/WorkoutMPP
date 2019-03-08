package sample

interface Timer {
    fun start(seconds: Int, onTick: (Int)->Unit, onFinish: ()->Unit)
    fun stop()
}