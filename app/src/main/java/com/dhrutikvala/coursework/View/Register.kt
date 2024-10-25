package com.dhrutikvala.coursework.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.dhrutikvala.coursework.R
import com.dhrutikvala.coursework.Controller.CafeController

class Register : AppCompatActivity() {

    val cc: CafeController = CafeController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun btnAddCustomer(view: View) {

        val cusFullName = (findViewById<EditText>(R.id.editFirstName).text.toString() + " " +
                findViewById<EditText>(R.id.editLastName).text.toString())
        val cusEmail = findViewById<EditText>(R.id.editEmail).text.toString()
        val cusPhoneNo = findViewById<EditText>(R.id.editPhoneNo).text.toString()
        val cusUserName = findViewById<EditText>(R.id.editUsername).text.toString()
        val cusPassword = findViewById<EditText>(R.id.editPassword).text.toString()
        val cusIsActive = 0

        val customer = cc.registerAddCustomer(cusFullName, cusEmail, cusPhoneNo,
            cusUserName, cusPassword)
        Toast.makeText(this, customer, Toast.LENGTH_SHORT).show()

        if(customer == "Account has been created") {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}