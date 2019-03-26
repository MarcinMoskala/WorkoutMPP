package sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { WorkoutViewModel(AndroidTimer(), AndroidSpeaker(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.apply {
            titleProp.addListener { titleView.text = it }
            imgApiUrlProp.addListener {
                imageView.loadImage(url = "$BASE_URL/images/$it")
            }
            progressProp.addListener { progressBar.progress = (100 * it).toInt() }
            timerTextProp.addListener { timerView.text = it }
        }

        nextButton.setOnClickListener { viewModel.onNext() }
        prevButton.setOnClickListener { viewModel.onPrevious() }
        viewModel.onStart()
    }
}

