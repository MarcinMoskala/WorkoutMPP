package sample

import kotlin.test.Test
import kotlin.test.assertEquals
import io.mockk.*

class WorkoutViewModelTest {

    private val firstExercise = getExercises().first()

    @Test
    fun singleExerciseTimerTest() {
        val timer = FakeTimer()
        val speaker = FakeSpeaker()
        val vm = WorkoutViewModel(timer, speaker)

        vm.onStart()
        val prepareText = "Prepare for " + firstExercise.nameText
        assertEquals(prepareText, vm.titleProp.get())
        assertEquals(prepareText, speaker.spokenTexts.last())
        assertEquals(0, vm.progressProp.get())
        assertEquals(firstExercise.time.toString(), vm.timerTextProp.get())

        // ...
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

    class FakeSpeaker: Speaker {
        var spokenTexts = listOf<String>()
            private set

        override fun speak(text: String) {
            spokenTexts = spokenTexts + text
        }
    }
}