package sample

data class Exercise(
    val key: String,
    val imgUrlName: String,
    val nameText: String,
    val time: Int = 30,
    val prepareTime: Int = 5
)