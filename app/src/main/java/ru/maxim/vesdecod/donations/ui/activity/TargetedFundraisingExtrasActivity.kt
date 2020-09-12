package ru.maxim.vesdecod.donations.ui.activity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_targeted_fundraising_extras.*
import ru.maxim.vesdecod.donations.R
import java.text.SimpleDateFormat
import java.util.*

class TargetedFundraisingExtrasActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
        add(Calendar.HOUR_OF_DAY, 3)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_targeted_fundraising_extras)
        title = getString(R.string.extra)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val mockAuthors =
            arrayListOf("Maxim Borodkin", "Matvei Pravosudov")
        val authorsSpinnerAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mockAuthors)
        targetedFundraisingAuthor.adapter = authorsSpinnerAdapter
        targetedFundraisingEndingDate.setOnClickListener {
            DatePickerDialog(this, this, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        targetedFundraisingEndsMode.check(targetedFundraisingEndsOnCollect.id)
        targetedFundraisingEndingDate.isEnabled = false
        targetedFundraisingEndsOnDate.setOnCheckedChangeListener { _, isChecked ->
            targetedFundraisingEndingDate.isEnabled = isChecked
        }
        targetedFundraisingExtraOkBtn.setOnClickListener {
            startActivity(Intent(this, CreatePostActivity::class.java))
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT)
        targetedFundraisingEndingDate.setText(dateFormat.format(Date(calendar.timeInMillis)))
    }
}