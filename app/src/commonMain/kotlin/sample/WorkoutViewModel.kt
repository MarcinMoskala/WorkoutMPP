package sample

class WorkoutViewModel constructor(
    private val timer: Timer,
    private val speaker: Speaker,
    exercises: List<Exercise> = getExercises()
) : ViewModel() {
    // Overloading for Obj-C as well as default parameters are not supported yet
    constructor(timer: Timer, speaker: Speaker) : this(timer, speaker, getExercises())

    private val states: List<WorkoutState> = exercises.toStates()
    private var state: WorkoutState = states.first()

    val titleProp = MutableProp("")
    val imgUrlProp = MutableProp("")
    val progressProp = MutableProp(0)
    val timerTextProp = MutableProp("")

    fun onStart() {
        showState(state)
    }

    fun onNext() {
        val nextState = states.getOrNull(states.indexOf(state) + 1)
        if (nextState != null) {
            state = nextState
            showState(state)
        }
    }

    fun onPrevious() {
        val nextState = states.getOrNull(states.indexOf(state) - 1)
        if (nextState != null) {
            state = nextState
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
                progressProp.set(0)
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
                val progress = 100 * (durationSeconds - secondsLeft) / durationSeconds
                progressProp.set(progress)
            },
            onFinish = this::onNext
        )
    }

    companion object {
        const val EXERCISE_PREPARE_TIME = 8
    }
}