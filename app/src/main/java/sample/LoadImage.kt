package sample

import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .fitCenter()
        .into(this)
}