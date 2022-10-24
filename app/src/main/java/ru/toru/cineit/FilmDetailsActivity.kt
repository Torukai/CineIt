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
            .setTitle("Dialog")
            .setMessage("Share with your friend!")
            .setNegativeButton("Cancel") { dialog, which -> }
            .setPositiveButton("Send") { dialog, which ->
                edit = editText.text.toString()

                try {
                    val smsManager = SmsManager.getDefault()
                    smsManager.sendTextMessage(edit, null, "Hey! Check out this cool film I found.", null, null)
                    Toast.makeText(applicationContext, "Message Sent", Toast.LENGTH_LONG).show()

                } catch (e: Exception) {
                    Toast.makeText(applicationContext, "Please enter all the data.."+e.message.toString(), Toast.LENGTH_LONG)
                        .show()
                }
            }
            .setView(dialogLayout)
            .create()

        builder.show()
    }
}

