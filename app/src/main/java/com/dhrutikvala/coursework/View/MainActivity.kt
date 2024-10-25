package com.dhrutikvala.coursework.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.dhrutikvala.coursework.Controller.CafeController
import com.dhrutikvala.coursework.Model.CafeDatabase
import com.dhrutikvala.coursework.R


class MainActivity : AppCompatActivity() {

    val cc: CafeController = CafeController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnRegister(view: View) {
        val intent = Intent(this, Register::class.java)
        startActivity(intent)
    }

    fun btnLogin(view: View) {
        val username = findViewById<EditText>(R.id.editMainUsername).text.toString()
        val password = findViewById<EditText>(R.id.editMainPassword).text.toString()

        val admin = cc.loginAdmin(username, password)
        val customer = cc.loginCustomer(username, password)

        if(admin != -1) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, AdminPanel::class.java).apply {
                putExtra("uId", admin)
            }
            startActivity(intent)
        }
        else if(customer != -1) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CafeMenu::class.java).apply {
                putExtra("uId", customer)
            }
            startActivity(intent)
        }
        else {
              Toast.makeText(this, "Account not found", Toast.LENGTH_SHORT).show()
        }
    }

}