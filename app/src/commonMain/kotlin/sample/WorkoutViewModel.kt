package sample

class WorkoutViewModel(
    private val timer: Timer,
    private val speaker: Speaker
) : ViewModel() {
    private val states: List<WorkoutState> = getExercises().toStates()
    private var state: WorkoutState = states.first()

    val titleProp = MutableProp("")
    val imgUrlProp = MutableProp("")
    val progressProp = MutableProp(0F)
    val timerTextProp = MutableProp("")

    fun onStart() {
        showState(state)
    }

    fun onNext() {
        val nextIndex = states.indexOf(state) + 1
        if (nextIndex in states.indices) {
            state = states[nextIndex]
            showState(state)
        }
    }

    fun onPrevious() {
        val prevIndex = states.indexOf(state) - 1
        if (prevIndex in states.indices) {
            state = states[prevIndex]
            showState(state)
        }
    }

    private fun showState(state: WorkoutState) {
        val titleText = when (state) {
            is PrepareState -> "Prepare for " + state.exercise.nameText
            is ExerciseState -> state.exercise.nameText
            is DoneState -> "Done"
        }
        val imgApiName = when (state) {
            is PrepareState -> state.exercise.imgUrlName
            is ExerciseState -> state.exercise.imgUrlName
            is DoneState -> "done.jpg"
        }
        titleProp.set(titleText)
        imgUrlProp.set("$BASE_URL/images/$imgApiName")
        speaker.speak(titleText)
        setUpTimer(state)
    }

    private fun setUpTimer(state: WorkoutState) {
        val durationSeconds = when (state) {
            DoneState -> {
                progressProp.set(0F)
                timerTextProp.set("")
                timer.stop()
                return
            }
            is ExerciseState -> state.exercise.time
            is PrepareState -> EXERCISE_PREPARE_TIME
        }

        timer.start(
            durationSeconds,
            onTick = { secondsLeft ->
                timerTextProp.set("$secondsLeft")
                val progress = (durationSeconds - secondsLeft).toFloat() / durationSeconds
                progressProp.set(progress)
            },
            onFinish = this::onNext
        )
    }

    companion object {
        const val EXERCISE_PREPARE_TIME = 8
    }
}