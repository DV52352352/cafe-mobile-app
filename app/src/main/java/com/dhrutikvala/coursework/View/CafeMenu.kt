package com.dhrutikvala.coursework.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.dhrutikvala.coursework.Controller.CafeController
import com.dhrutikvala.coursework.Model.Product
import com.dhrutikvala.coursework.R
import com.dhrutikvala.coursework.View.ShoppingCart
import java.lang.NullPointerException

class CafeMenu : AppCompatActivity() {

    val cc: CafeController = CafeController(this)

    public var userId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cafe_menu)
        userId = intent.getIntExtra("uId", 0)

        var shoppingListCount = cc.getCustomerShoppingListCount(userId)
        findViewById<TextView>(R.id.txtOrders).apply {
            text = "You have $shoppingListCount items in your basket"
        }
    }

    fun btnCakes(view: View) {
        val intent = Intent(this, Cakes::class.java).apply {
            putExtra("uId", userId)
        }
        startActivity(intent)
    }

    fun btnColdDrinks(view: View) {
        val intent = Intent(this, ColdDrinks::class.java).apply {
            putExtra("uId", userId)
        }
        startActivity(intent)
    }

    fun btnHotDrinks(view: View) {
        val intent = Intent(this, HotDrinks::class.java).apply {
            putExtra("uId", userId)
        }
        startActivity(intent)
    }

    fun btnSnacks(view: View) {
        val intent = Intent(this, Snacks::class.java).apply {
            putExtra("uId", userId)
        }
        startActivity(intent)
    }

    fun btnShoppingCart(view: View) {
        val intent = Intent(this, ShoppingCart::class.java).apply {
            putExtra("uId", userId)
        }
        startActivity(intent)
    }

    fun btnLogOut(view: View) {
        cc.logoutCustomer(userId)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}