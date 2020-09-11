package ru.maxim.vesdecod.donations.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import ru.maxim.vesdecod.donations.R

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    title = getString(R.string.fundraisings)
    createFundraisingBtn.setOnClickListener {
      startActivity(Intent(this, FundraisingTypeActivity::class.java))
    }
  }
}