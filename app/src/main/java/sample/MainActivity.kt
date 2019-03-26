package sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), WorkoutView {

    private val presenter by lazy { WorkoutPresenter(this, AndroidTimer(), AndroidSpeaker(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nextButton.setOnClickListener { presenter.onNext() }
        prevButton.setOnClickListener { presenter.onPrevious() }
        presenter.onStart()
    }

    override fun setUpWorkoutDisplay(title: String, imgApiName: String) {
        textView.text = title
        imageView.loadImage(url = "$BASE_URL/images/$imgApiName")
    }

    override fun hideTimer() {
        timerView.text = ""
        progressBar.progress = 0
    }

    override fun updateTimer(secLeft: Int, progress: Int) {
        timerView.text = "$secLeft"
        progressBar.progress = progress
    }
}

