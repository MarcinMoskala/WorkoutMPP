package sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), WorkoutView {

    private val speaker by lazy { AndroidSpeaker(this) }
    private val presenter by lazy { WorkoutPresenter(this, AndroidTimer(), speaker) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nextButton.setOnClickListener { presenter.onNext() }
        prevButton.setOnClickListener { presenter.onPrevious() }
        presenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onStop() {
        super.onStop();
        presenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        speaker.release()
    }

    override fun setUpWorkoutDisplay(title: String, imageApiName: String) {
        textView.text = title
        Glide.with(this)
            .load("${ExercisesRepository.BASE_URL}/images/$imageApiName")
            .fitCenter()
            .into(imageView)
    }

    override fun hideTimer() {
        progressBar.visibility = View.GONE
        timerView.text = ""
    }

    override fun updateTimer(nowSec: Int, endSec: Int) {
        progressBar.visibility = View.VISIBLE
        timerView.text = "${endSec - nowSec}"
        progressBar.progress = nowSec * 100 / endSec
    }
}

