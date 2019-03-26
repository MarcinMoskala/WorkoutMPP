package sample

import android.databinding.DataBindingUtil
import android.databinding.ObservableField
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import sample.databinding.ActivityMainBinding

actual typealias ViewModel = android.arch.lifecycle.ViewModel
actual typealias MutableProp<T> = ObservableField<T>

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        WorkoutViewModel(AndroidTimer(), AndroidSpeaker(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .vm = viewModel
        viewModel.onStart()
    }
}

