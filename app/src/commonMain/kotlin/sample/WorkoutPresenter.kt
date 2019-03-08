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

    fun onNext() {
        val position = states.indexOf(state)
        if (position < states.lastIndex) {
            state = states[position + 1]
            showState()
        }
    }

    fun onPrevious() {
        val position = states.indexOf(state)
        if (position > 0) {
            state = states[position - 1]
            showState()
        }
    }

    private fun showState() {
        val titleText: String
        val state = state
        titleText = when (state) {
            is PrepareState -> "Prepare for " + state.exercise.nameText
            is ExerciseState -> state.exercise.nameText
            is DoneState -> "Done"
        }
        view.setUpWorkoutDisplay(titleText, state.imageApiName)
        speaker.speak(titleText)
        timer.stop()
        if(state is DoneState) {
            view.hideTimer()
        } else {
            setUpTimer(state.time)
        }
    }

    private fun setUpTimer(durationSeconds: Int?) {
        durationSeconds ?: return
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