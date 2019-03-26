package sample

import sample.Directions.*

private val BOTTOM_HEIGHT = 100
private val BUTTONS_WIDTH = 50

fun makeWorkoutView(viewModel: WorkoutViewModel) = viewBuilder {
    val titleView = textView(textProp = viewModel.titleProp) {
        toParent(Left, Top, Right)
        height = 100
    }
    val prevView = view {
        toParent(Left, Bottom)
        height = BOTTOM_HEIGHT
        width = BUTTONS_WIDTH
        imageView(resName = "prev") {
            centerHorizontally()
            centerVertically()
            addOnClick { viewModel.onNext() }
        }
    }
    val nextView = view {
        toParent(Right, Bottom)
        height = BOTTOM_HEIGHT
        width = BUTTONS_WIDTH
        imageView(resName = "next") {
            centerHorizontally()
            centerVertically()
            addOnClick { viewModel.onPrevious() }
        }
    }
    val timerView = textView(textProp = viewModel.timerTextProp) {
        toParent(Bottom)
        height = BOTTOM_HEIGHT
        constraint(Right, nextView, Left)
        constraint(Left, prevView, Right)
    }
    val progressView = progress(progressProp = viewModel.progressProp) {
        constraint(Bottom, timerView, Top)
        toParent(Left, Right)
    }
    val imageView = imageView(imgUrlProp = viewModel.imgUrlProp) {
        constraint(Top, titleView, Bottom)
        constraint(Bottom, progressView, Top)
        toParent(Left, Right)

    }
}



enum class Directions {
    Left, Right, Top, Bottom
}

internal fun viewBuilder(init: ViewBuilder.() -> Unit): View {
    return ViewBuilder().apply(init).build()
}

internal fun ViewBuilder.view(init: ViewBuilder.() -> Unit): View {
    return ViewBuilder().apply(init).build().also { subviews += it }
}

fun ViewBuilder.textView(textProp: MutableProp<String>, init: ViewBuilder.() -> Unit): View = TODO()

fun ViewBuilder.progress(progressProp: MutableProp<Int>, init: ViewBuilder.() -> Unit): View = TODO()

fun ViewBuilder.imageView(resName: String? = null, imgUrlProp: MutableProp<String>? = null, init: ViewBuilder.() -> Unit): View
        = TODO()

class ViewBuilder {
    val subviews = mutableListOf<View>()
    val clickListeners = mutableListOf<() -> Unit>()
    var height: Int? = null
    var width: Int? = null

    fun addOnClick(listener: () -> Unit) {
        clickListeners += listener
    }

    fun build(): View {
        TODO()
    }

    fun toParent(vararg dirs: Directions) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun constraint(ownDir: Directions, view: View, anotherDir: Directions) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun centerHorizontally() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun centerVertically() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class View