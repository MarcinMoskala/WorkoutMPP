package sample

expect abstract class ViewModel()

expect class MutableProp<T>(current: T) {
    fun set(elem: T)
}