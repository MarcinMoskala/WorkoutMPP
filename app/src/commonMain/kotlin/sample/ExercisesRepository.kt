package sample

class ExercisesRepository {
    fun getExercises(): List<Exercise> = warmup + exercises + stretch

    private val warmup = listOf(
        Exercise("jumping_jacks", "jumping_jacks.png", "Jumping jacks"),
        Exercise("chest_expanders", "chest_expander.png", "Chest expanders"),
        Exercise("rotating_toe_touches", "rotating_toe_touches.jpg", "Rotating Toe Touches"),
        Exercise("hips_rotation", "hips_rotation.png", "Hips rotation", time = 20),
        Exercise("forward_bend", "forward_bend.png", "Dynamic forward bend"),
        Exercise("wrist", "wrist_warm_up.png", "Wrist warm up"),
        Exercise("wall_push_up", "wall_push_ups.jpg", "Wall push ups", prepareTime = 8)
    )

    private val exercises = listOf(
        Exercise("leg_rise", "leg_rise.gif", "Leg rise", time = 35, prepareTime = 8),
        Exercise("superman_hold", "superman.png", "Superman hold", prepareTime = 8),
        Exercise("squats", "squat.png", "Squats", time = 50, prepareTime = 8),
        Exercise("push_ups", "push_ups.png", "Push ups", time = 35, prepareTime = 8),
        Exercise("handstand", "handstand.jpg", "Handstand", prepareTime = 8),
        Exercise("bridge", "bridge.jpg", "Bridge", prepareTime = 10),
        Exercise("plank", "plank.png", "Plank")
    )

    private val stretch = listOf(
        Exercise("forward_bend", "forward_bend.png", "Forward bend"),
        Exercise("downward_dog", "downward_dog.jpg", "Downward-Facing Dog"),
        Exercise("biceps_and_wrist_stretch", "biceps_stretch.jpg", "Biceps and Wrist Stretch", prepareTime = 8),
        Exercise("other_site_wrist_stretch", "wrist_stretch_2.jpg", "Other site Wrist Stretch"),
        Exercise("chest_stretch", "chest_stretch.jpg", "Chest Stretch"),
        Exercise("shoulder_streatch", "shoulder_stretch.jpg", "Shoulder Stretch"),
        Exercise("upper_trap_stretch", "upper_trap_stretch.gif", "Upper Trap Stretch"),
        Exercise("back_stretch", "back_stretch.png", "Back Stretch")
    )

    companion object {
        val BASE_URL = "https://raw.githubusercontent.com/MarcinMoskala/WorkoutMPP/master/api/"
    }
}
