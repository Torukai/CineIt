package ru.toru.cineit

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.telephony.SmsManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class FilmDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_details)

        findViewById<FloatingActionButton>(R.id.share).setOnClickListener { view ->
            showShareDialog()
        }

        val films = (application as App).films
        val id = intent.getIntExtra("id", -1)
        val isFavorite = intent.getBooleanExtra("isFavorite", false)

        findViewById<ImageView>(R.id.cover).setBackgroundResource(films[id].coverId)
        findViewById<TextView>(R.id.description).text = getString(films[id].description)
        findViewById<Switch>(R.id.isFavorite).isChecked = isFavorite

        findViewById<Switch>(R.id.isFavorite).setOnCheckedChangeListener { _, isChecked ->
            films[id].isFavorite = isChecked
        }
    }

    private fun showShareDialog()
    {
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.share_layout, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.sms_edit)
        var edit : String

        val builder = AlertDialog.Builder(this)
            .setTitle("Hi!")
            .setMessage("Share with your friend!")
            .setNegativeButton("Cancel") { dialog, which -> }
            .setNeutralButton("Later") { dialog, which -> }
            .setPositiveButton("Send") { dialog, which ->
                edit = editText.text.toString()

                try {
                    // on below line we are initializing sms manager.
                    //as after android 10 the getDefault function no longer works
                    //so we have to check that if our android version is greater
                    //than or equal toandroid version 6.0 i.e SDK 23
                    val smsManager: SmsManager

                        //if user's SDK is less than 23 then
                        //SmsManager will be initialized like this
                        smsManager = SmsManager.getDefault()


                    // on below line we are sending text message.
                    smsManager.sendTextMessage(edit, null, "Hey! Check out this cool film I found.", null, null)

                    // on below line we are displaying a toast message for message send,
                    Toast.makeText(applicationContext, "Message Sent", Toast.LENGTH_LONG).show()

                } catch (e: Exception) {

                    // on catch block we are displaying toast message for error.
                    Toast.makeText(applicationContext, "Please enter all the data.."+e.message.toString(), Toast.LENGTH_LONG)
                        .show()
                }
            }
            .setView(dialogLayout)
            .create()

        builder.show()
    }
}

