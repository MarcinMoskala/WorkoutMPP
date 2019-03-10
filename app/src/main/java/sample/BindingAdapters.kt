package sample

import android.databinding.BindingAdapter
import android.view.*
import android.widget.*
import com.bumptech.glide.Glide

@BindingAdapter("android:visibility")
fun View.setVisible(value: Boolean) {
    visibility = if (value) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .fitCenter()
        .into(this)
}