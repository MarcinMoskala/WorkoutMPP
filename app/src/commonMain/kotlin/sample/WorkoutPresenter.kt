package sample

class WorkoutPresenter(
    private val view: WorkoutView,
    private val timer: Timer,
    private val speaker: Speaker
) {
    private val repo = ExercisesRepository()
    private lateinit var states: List<WorkoutState>
    private lateinit var state: WorkoutState

    fun onStart() {
        states = repo.getExercises().toStates()
        state = states.first()
        showState()
    }

    fun onResume() {
        timer.start()
    }

    fun onStop() {
        timer.stop()
    }

    fun onNext() {
        val position = states.indexOf(state)
        if (position < states.size - 1) {
            state = states[position + 1]
        }
        showState()
    }

    fun onPrevious() {
        val position = states.indexOf(state)
        if (position > 0) {
            state = states[position - 1]
        }
        showState()
    }

    private fun showState() {
        val titleText: String
        val state = state
        when (state) {
            is PrepareState -> {
                speaker.playWhistle()
                titleText = "Prepare for " + state.exercise.nameText
//                setExercisesCountDisplay(state.index)
            }
            is ExerciseState -> {
                speaker.playWhistle()
                titleText = state.exercise.nameText
//                setExercisesCountDisplay(state.index)
            }
            is DoneState -> {
                speaker.playEndSound()
                titleText = "Done"
//                setExercisesCountDisplay(null)
            }
        }
        view.setUpWorkoutDisplay(titleText, state.imageApiName)
        speaker.speak(titleText)
        setUpTimer(state.time)
    }

//    private fun setExercisesCountDisplay(index: Int?) {
//        val text = if (index == null) "" else "${index + 1}/${exercises.size}"
//        exercisesCounterDisplay.set(text)
//    }

    private fun setUpTimer(durationSeconds: Int?) {
        if (durationSeconds == null) {
            view.hideTimer()
            return
        }
        view.updateTimer(nowSec = 0, endSec = durationSeconds)
        timer.start(durationSeconds,
            onTick = { secondsUntilFinished ->
                val seconds = durationSeconds - secondsUntilFinished
                view.updateTimer(nowSec = seconds, endSec = durationSeconds)
            },
            onFinish = this::onNext
        )
    }
}