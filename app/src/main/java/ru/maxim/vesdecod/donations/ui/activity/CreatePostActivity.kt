package ru.maxim.vesdecod.donations.ui.activity

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_create_post.*
import ru.maxim.vesdecod.donations.R
import java.io.InputStream

class CreatePostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
        title = getString(R.string.create_post)
        val name = "Good boys helps to kittens"
        val author = "Maxim Borodkin"
        val cover = R.drawable.kitten

        createPostName.text = name
        createPostAuthor.text = author
        createPostCover.setImageResource(cover)

    }
}