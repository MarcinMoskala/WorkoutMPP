package sample

internal sealed class WorkoutState

internal object DoneState : WorkoutState()

internal class ExerciseState(val exercise: Exercise) : WorkoutState()

internal class PrepareState(val exercise: Exercise) : WorkoutState()

internal fun List<Exercise>.toStates(): List<WorkoutState> =
    flatMap { exercise -> listOf(PrepareState(exercise), ExerciseState(exercise)) } + DoneState