package sample

import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertEquals
import io.mockk.*
import org.junit.*

class WorkoutViewModelTest {

    val firstExercise = getExercises().first()
    val prepTime = WorkoutPresenter.EXERCISE_PREPARE_TIME

    @Test
    fun singleExerciseTimerTest() {
        val timer = FakeTimer()
        val speaker: Speaker = mockk(relaxed = true)
        val view: WorkoutView = mockk(relaxed = true)
        val presenter = WorkoutPresenter(view, timer, speaker)

        presenter.onStart()
        timer.onTick(prepTime - 1)
        timer.onTick(prepTime - 2)
        presenter.onNext()
        timer.onTick(firstExercise.time - 1)

        verify {
            val prepareText = "Prepare for " + firstExercise.nameText
            view.setUpWorkoutDisplay(prepareText, firstExercise.imgUrlName)
            speaker.speak(prepareText)
            view.updateTimer(prepTime, 0F)
            view.updateTimer(prepTime - 1, 1F / prepTime)
            view.updateTimer(prepTime - 2, 2F / prepTime)

            view.setUpWorkoutDisplay(firstExercise.nameText, firstExercise.imgUrlName)
            speaker.speak(firstExercise.nameText)
            view.updateTimer(firstExercise.time, 0F)
            view.updateTimer(firstExercise.time - 1, 1F / firstExercise.time)
        }
    }

    class FakeTimer(): Timer {
        var seconds = 0
        var onTick: (Int) -> Unit = {}
        var onFinish: () -> Unit = {}

        override fun start(seconds: Int, onTick: (Int) -> Unit, onFinish: () -> Unit) {
            this.seconds = seconds
            this.onTick = onTick
            this.onFinish = onFinish
            onTick(seconds)
        }

        override fun stop() {
            // no-op
        }
    }
}