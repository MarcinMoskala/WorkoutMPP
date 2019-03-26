package sample

class WorkoutViewModel(
    private val timer: Timer,
    private val speaker: Speaker
) {
    private val states: List<WorkoutState> = getExercises().toStates()
    private var state: WorkoutState = states.first()

    val titleProp = MutableProp("")
    val imgApiUrlProp = MutableProp("")
    val progressProp = MutableProp(0)
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
        imgApiUrlProp.set(imgApiName)
        speaker.speak(titleText)
        setUpTimer(state)
    }

    private fun setUpTimer(state: WorkoutState) {
        val durationSeconds = when (state) {
            DoneState -> {
                progressProp.set(0)
                timerTextProp.set("")
                timer.stop()
                return
            }
            is ExerciseState -> state.exercise.time
            is PrepareState -> EXERCISE_PREPARE_TIME
        }

        view.updateTimer(secLeft = durationSeconds, progress = 0F)
        timer.start(
            durationSeconds,
            onTick = { secondsLeft ->
                val progress = (durationSeconds - secondsLeft).toFloat() / durationSeconds
                view.updateTimer(secLeft = seconds, progress = durationSeconds)
            },
            onFinish = this::onNext
        )
    }

    private fun updateTimer(secLeft: Int, progress: Float) {
        progressProp.set(progress)
        timerTextProp.set("$secLeft")
    }

    companion object {
        private const val EXERCISE_PREPARE_TIME = 8
    }
}