package sample

fun getExercises(): List<Exercise> = listOf(
    Exercise("chest_expander.png", "Chest expanders"),
    Exercise("rotating_toe_touches.jpg", "Rotating Toe Touches"),
    Exercise("hips_rotation.png", "Hips rotation", time = 20),
    Exercise("forward_bend.png", "Dynamic forward bend"),
    Exercise("wrist_warm_up.png", "Wrist warm up"),
    Exercise("wall_push_ups.jpg", "Wall push ups"),
    Exercise("leg_rise.gif", "Leg rise", time = 35),
    Exercise("superman.png", "Superman hold"),
    Exercise("forward_bend.png", "Forward bend"),
    Exercise("downward_dog.jpg", "Downward-Facing Dog"),
    Exercise("biceps_stretch.jpg", "Biceps and Wrist Stretch"),
    Exercise("wrist_stretch_2.jpg", "Other site Wrist Stretch"),
    Exercise("shoulder_stretch.jpg", "Shoulder Stretch"),
    Exercise("back_stretch.png", "Back Stretch")
    //...
)

const val BASE_URL = "https://raw.githubusercontent.com/MarcinMoskala/WorkoutMPP/master/api/"