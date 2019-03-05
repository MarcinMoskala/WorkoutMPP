package sample

internal sealed class WorkoutState {
    abstract val time: Int?
    abstract val imageApiName: String
}

internal object DoneState : WorkoutState() {
    override val time: Int? = null
    override val imageApiName: String = "done.jpg"
}

internal class ExerciseState(val exercise: Exercise) : WorkoutState() {
    override val time: Int? get() = exercise.time
    override val imageApiName: String get() = exercise.imgUrlName
}

internal class PrepareState(val exercise: Exercise) : WorkoutState() {
    override val time: Int? get() = exercise.prepareTime
    override val imageApiName: String get() = exercise.imgUrlName
}

internal fun List<Exercise>.toStates(): List<WorkoutState> =
    flatMap { exercise -> listOf(PrepareState(exercise), ExerciseState(exercise)) } + DoneState