package com.dhrutikvala.coursework.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.dhrutikvala.coursework.Controller.CafeController
import com.dhrutikvala.coursework.R

class AdminPanel : AppCompatActivity() {

    val cc: CafeController = CafeController(this)
    var userId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_panel)
        if(intent.getIntExtra("uId", 0) != 0) {
            userId = intent.getIntExtra("uId", 0)
        }

    }

    fun btnCafeMenu(view: View) {
        val intent = Intent(this, ManageCafeMenu::class.java)
        startActivity(intent)
    }

    fun btnOrderMenu(view: View) {
        val intent = Intent(this, ManageOrders::class.java)
        startActivity(intent)
    }

    fun btnAddItem(view: View) {
        val intent = Intent(this, AddItem::class.java)
        startActivity(intent)
    }

    fun btnLogOut(view: View) {
        cc.logoutAdmin(userId)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}