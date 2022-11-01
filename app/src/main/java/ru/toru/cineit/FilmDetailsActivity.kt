package ru.toru.cineit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.telephony.SmsManager
import android.widget.Toast

class FilmDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_details)

        findViewById<FloatingActionButton>(R.id.share).setOnClickListener { _ ->
            showShareDialog()
        }

        val cover = intent.getIntExtra("cover", -1)
        val description = intent.getIntExtra("description", -1)
        val isFavorite = intent.getBooleanExtra("isFavorite", false)

        findViewById<ImageView>(R.id.cover).setBackgroundResource(cover)
        findViewById<TextView>(R.id.description).text = getString(description)
        findViewById<ImageView>(R.id.isFavorite).setBackgroundResource(if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border)
    }

    private fun showShareDialog()
    {
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.share_layout, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.sms_edit)
        var edit : String

        val builder = AlertDialog.Builder(this)
            .setTitle(R.string.share_dialog_title)
            .setMessage(R.string.share_dialog_message)
            .setNegativeButton(android.R.string.cancel) { dialog, which -> }
            .setPositiveButton(android.R.string.ok) { dialog, which ->
                edit = editText.text.toString()

                try {
                    val smsManager = SmsManager.getDefault()
                    smsManager.sendTextMessage(edit, null, getString(R.string.share_sms_message), null, null)
                    Toast.makeText(applicationContext, getString(R.string.share_sent_toast_confirm_message), Toast.LENGTH_LONG).show()

                } catch (e: Exception) {
                    Toast.makeText(applicationContext, getString(R.string.share_sent_error_toast_message) + e.message.toString(), Toast.LENGTH_LONG)
                        .show()
                }
            }
            .setView(dialogLayout)
            .create()

        builder.show()
    }
}

