package sample

import kotlin.test.Test
import kotlin.test.assertEquals

class WorkoutViewModelTest {

    @Test
    fun singleExerciseTimerTest() {
        val firstExercise = Exercise("chest_expander.png", "Chest expanders", time = 30)
        val timer = FakeTimer()
        val speaker = FakeSpeaker()
        val vm = WorkoutViewModel(timer, speaker, exercises = listOf(firstExercise))

        vm.onStart()
        val prepareText = "Prepare for " + firstExercise.nameText
        assertEquals(prepareText, vm.titleProp.get())
        assertEquals(prepareText, speaker.spokenTexts.last())
        assertEquals(0, vm.progressProp.get())
        val prepareTime = WorkoutViewModel.EXERCISE_PREPARE_TIME
        val preparationTime = prepareTime.toString()
        assertEquals(preparationTime, vm.timerTextProp.get())

        timer.tick()
        assertEquals(prepareText, vm.titleProp.get())
        assertEquals(prepareText, speaker.spokenTexts.last())
        assertEquals(100 / prepareTime, vm.progressProp.get())
        assertEquals((prepareTime - 1).toString(), vm.timerTextProp.get())

        val exerciseTime = firstExercise.time
        vm.onNext()
        assertEquals(firstExercise.nameText, vm.titleProp.get())
        assertEquals(firstExercise.nameText, speaker.spokenTexts.last())
        assertEquals(0, vm.progressProp.get())
        assertEquals(exerciseTime.toString(), vm.timerTextProp.get())

        timer.tick()
        assertEquals(firstExercise.nameText, vm.titleProp.get())
        assertEquals(firstExercise.nameText, speaker.spokenTexts.last())
        assertEquals(100 / exerciseTime, vm.progressProp.get())
        assertEquals((exerciseTime - 1).toString(), vm.timerTextProp.get())

        timer.tick()
        assertEquals(200 / exerciseTime, vm.progressProp.get())
        assertEquals((exerciseTime - 2).toString(), vm.timerTextProp.get())

        vm.onNext()
        assertEquals("Done", vm.titleProp.get())
        assertEquals("Done", speaker.spokenTexts.last())
        assertEquals(0, vm.progressProp.get())
        assertEquals("", vm.timerTextProp.get())
    }

    class FakeTimer: Timer {
        private var seconds = 0
        private var onTick: (Int) -> Unit = {}
        private var onFinish: () -> Unit = {}

        override fun start(seconds: Int, onTick: (Int) -> Unit, onFinish: () -> Unit) {
            this.seconds = seconds
            this.onTick = onTick
            this.onFinish = onFinish
            onTick(seconds)
        }

        override fun stop() {
            // no-op
        }

        fun tick() {
            seconds--
            if(seconds == 0) {
                onFinish()
            } else {
                onTick(seconds)
            }
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