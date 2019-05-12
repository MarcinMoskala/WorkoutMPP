package sample

actual class MutableProp<T> actual constructor(current: T) {
    private var current = current
    private var listeners = listOf<(T)->Unit>()

    actual fun set(elem: T) {
        current = elem
        listeners.forEach { it(elem) }
    }

    fun addListener(listener: (T)->Unit) {
        listeners += listener
        listener(current)
    }

    actual fun get(): T? = current
}