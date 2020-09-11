package ru.maxim.vesdecod.donations.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_fundraising_type.*
import ru.maxim.vesdecod.donations.R

class FundraisingTypeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fundraising_type)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.fundraising_type)
        fundraisingTypeTargeted.setOnClickListener {
            startActivity(Intent(this, TargetedFundraisingActivity::class.java))
        }
        fundraisingTypeMonth.setOnClickListener {
            startActivity(Intent(this, RegularFundraisingActivity::class.java))
        }
    }
}