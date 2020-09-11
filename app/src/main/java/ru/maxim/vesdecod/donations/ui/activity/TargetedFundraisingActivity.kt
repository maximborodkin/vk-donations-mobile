package ru.maxim.vesdecod.donations.ui.activity

import android.R.attr
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ListAdapter
import kotlinx.android.synthetic.main.activity_targeted_fundraising.*
import ru.maxim.vesdecod.donations.R
import java.io.FileNotFoundException
import java.io.InputStream


class TargetedFundraisingActivity : AppCompatActivity() {

    private var isImageUploaded = false
    private val imageLoadResult = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_targeted_fundraising)
        title = getString(R.string.targeted_fundraising)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        targetedFundraisingRemoveCoverBtn.visibility = View.GONE
        targetedFundraisingRemoveCoverBtn.setOnClickListener {
            targetedFundraisingCover.setImageDrawable(null)
            it.visibility = GONE
        }
        targetedFundraisingCover.setOnClickListener {
            if (!isImageUploaded) {
                val photoPickerIntent = Intent(Intent.ACTION_PICK)
                photoPickerIntent.type = "image/*"
                startActivityForResult(photoPickerIntent, imageLoadResult)
            }
        }
        val mockAccounts =
            arrayListOf("Vk Pay Account *1234", "Vk Pay Account *4321", "Vk Pay Account *1111")
        val accountsSpinnerAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mockAccounts)
        targetedFundraisingAccount.adapter = accountsSpinnerAdapter
        targetedFundraisingOkBtn.setOnClickListener {
            if (checkFields()) {
                startActivity(Intent(this, TargetedFundraisingExtrasActivity::class.java))
            } else {
                Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkFields(): Boolean{
        if (targetedFundraisingName.text.isNullOrBlank()) return false
        if (targetedFundraisingSum.text.isNullOrBlank()) return false
        if (targetedFundraisingTarget.text.isNullOrBlank()) return false
        if (targetedFundraisingDescription.text.isNullOrBlank()) return false
        if (targetedFundraisingAccount.selectedItemPosition == -1) return false
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == imageLoadResult && resultCode == RESULT_OK) {
            try {
                val imageUri: Uri = data?.data?:return
                val imageStream: InputStream? = contentResolver.openInputStream(imageUri)
                val selectedImage = BitmapFactory.decodeStream(imageStream)
                targetedFundraisingCover.setImageBitmap(selectedImage)
                targetedFundraisingRemoveCoverBtn.visibility = VISIBLE
                isImageUploaded = true
            } catch (ignored: Exception) { }
        }
    }
}