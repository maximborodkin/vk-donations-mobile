package ru.maxim.vesdecod.donations.ui.activity

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_regular_fundraising.*
import ru.maxim.vesdecod.donations.R
import java.io.InputStream

class RegularFundraisingActivity : AppCompatActivity() {

    private var isImageUploaded = false
    private val imageLoadResult = 124

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regular_fundraising)
        title = getString(R.string.regular_fundraising)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        regularFundraisingRemoveCoverBtn.visibility = View.GONE
        regularFundraisingRemoveCoverBtn.setOnClickListener {
            regularFundraisingCover.setImageDrawable(null)
            it.visibility = View.GONE
        }
        regularFundraisingCover.setOnClickListener {
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
        regularFundraisingAccount.adapter = accountsSpinnerAdapter

        val mockAuthors =
            arrayListOf("Maxim Borodkin", "Matvei Pravosudov")
        val authorsSpinnerAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mockAuthors)
        regularFundraisingAuthor.adapter = authorsSpinnerAdapter
        regularFundraisingOkBtn.setOnClickListener {
            if (checkFields()) {

            } else {
                Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkFields(): Boolean{
        if (regularFundraisingName.text.isNullOrBlank()) return false
        if (regularFundraisingSum.text.isNullOrBlank()) return false
        if (regularFundraisingTarget.text.isNullOrBlank()) return false
        if (regularFundraisingDescription.text.isNullOrBlank()) return false
        if (regularFundraisingAccount.selectedItemPosition == -1) return false
        if (regularFundraisingAuthor.selectedItemPosition == -1) return false
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == imageLoadResult && resultCode == RESULT_OK) {
            try {
                val imageUri: Uri = data?.data?:return
                val imageStream: InputStream? = contentResolver.openInputStream(imageUri)
                val selectedImage = BitmapFactory.decodeStream(imageStream)
                regularFundraisingCover.setImageBitmap(selectedImage)
                regularFundraisingRemoveCoverBtn.visibility = View.VISIBLE
                isImageUploaded = true
            } catch (ignored: Exception) { }
        }
    }
}