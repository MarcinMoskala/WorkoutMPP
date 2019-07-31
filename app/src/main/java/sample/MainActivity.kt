package sample

import android.databinding.DataBindingUtil
import android.databinding.ObservableField
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.koin.android.viewmodel.ext.android.viewModel
import sample.databinding.ActivityMainBinding

actual typealias ViewModel = android.arch.lifecycle.ViewModel
actual typealias MutableProp<T> = ObservableField<T>

class MainActivity : AppCompatActivity() {

    private val vm by viewModel<WorkoutViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .vm = vm

        if (savedInstanceState == null) {
            vm.onStart()
        }
    }
}
