package sample

interface WorkoutView {
    fun setUpWorkoutDisplay(title: String, imgApiName: String)
    fun hideTimer()
    fun updateTimer(nowSec: Int, endSec: Int)
}