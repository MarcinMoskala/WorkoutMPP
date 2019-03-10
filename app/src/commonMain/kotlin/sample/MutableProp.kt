package sample

class MutableProp<T>(current: T) {
    var current = current
        private set
    var listeners = listOf<(T)->Unit>()

    fun set(elem: T) {
        current = elem
        listeners.forEach { it(elem) }
    }

    fun addListener(listener: (T)->Unit) {
        listeners = listeners + listener
        listener(current)
    }
}