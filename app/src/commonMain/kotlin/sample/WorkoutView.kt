package sample

interface WorkoutView {
    fun setUpWorkoutDisplay(title: String, imageApiName: String)
    fun hideTimer()
    fun updateTimer(nowSec: Int, endSec: Int)
}