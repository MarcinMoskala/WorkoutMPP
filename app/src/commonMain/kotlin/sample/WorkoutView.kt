package sample

interface WorkoutView {
    fun setUpWorkoutDisplay(title: String, imgApiName: String)
    fun hideTimer()
    fun updateTimer(secLeft: Int, progress: Int)
}