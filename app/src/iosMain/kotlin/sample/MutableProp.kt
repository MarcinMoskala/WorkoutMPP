@file:Suppress("unused") // Used from Swift

package sample

actual class MutableProp<T> actual constructor(private var current: T) {
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