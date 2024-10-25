package com.dhrutikvala.coursework.View

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isInvisible
import com.dhrutikvala.coursework.Controller.CafeController
import com.dhrutikvala.coursework.Model.Product
import com.dhrutikvala.coursework.Model.ShoppingCart
import com.dhrutikvala.coursework.R

class ProductPage : AppCompatActivity() {

    val cc: CafeController = CafeController(this)
    public var pId = 0;
    public var uId = 0;
    public var cId = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_page)
        val pName = findViewById<TextView>(R.id.productPageName)
        val pTitle = findViewById<TextView>(R.id.txtProductPageTitle)
        val pType = findViewById<TextView>(R.id.productPageType)
        val pImage = findViewById<ImageView>(R.id.productPageImage)
        val pPrice = findViewById<TextView>(R.id.productPagePrice)
        val pIsAvailable = findViewById<Switch>(R.id.productPageAvailability)

        val deleteButton = intent.getIntExtra("delete", 0)
        cId = intent.getIntExtra("cId", 0)
        pId = intent.getIntExtra("pId", 0)
        uId = intent.getIntExtra("uId", 0)
        pName.text = "Name: " + intent.getStringExtra("pName")
        pTitle.text = intent.getStringExtra("pName")
        pPrice.text = "Price: £" + intent.getDoubleExtra("pPrice", 0.0).toString()
        pType.text = "Type: " + intent.getStringExtra("pType")

        val prodIsAvailable = intent.getIntExtra("pIsAvailable", 0)
        if(prodIsAvailable == 1) {
            pIsAvailable.toggle()
        }
        val prodImage = intent.getByteArrayExtra("pImage")
        val prodImageBitmap: Bitmap = BitmapFactory.decodeByteArray(prodImage, 0, prodImage!!.size)
        pImage.setImageBitmap(prodImageBitmap)

        if(deleteButton == 1) {
            findViewById<Button>(R.id.btnAddToBasket).isInvisible = true
            findViewById<Button>(R.id.btnRemoveItem).isInvisible = false
        }
    }

    fun btnGoBack(view: View) {
        val intent = Intent(this, CafeMenu::class.java).apply {
            putExtra("uId", uId)
        }
        startActivity(intent)
    }

    fun btnAddToBasket(view: View) {
        if(cc.addItemToShoppingList(uId, pId)) {
            Toast.makeText(this, "Added to basket", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CafeMenu::class.java).apply {
                putExtra("uId", uId)
            }
            startActivity(intent)
        } else {
            Toast.makeText(this, "Failed to add to basket", Toast.LENGTH_SHORT).show()
        }

        /*val prodName = intent.getStringExtra("pName")!!
        val prodType = intent.getIntExtra("pType", 0)
        val prodPrice = intent.getDoubleExtra("pPrice", 0.0)
        val prodIsAvailable = intent.getIntExtra("pIsAvailable", 0)
        val prodImage = intent.getByteArrayExtra("pImage")*/

        /*intent.putExtra("pName", prodName)
        intent.putExtra("pType", prodType)
        intent.putExtra("pPrice", prodPrice)
        intent.putExtra("pIsAvailable", prodIsAvailable)
        intent.putExtra("pImage", prodImage)*/

    }

    fun btnRemoveProduct(view: View) {
        if(cc.removeCustomerShoppingItem(cId, uId, pId)) {
            Toast.makeText(this, "Removed the item from the basket", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, com.dhrutikvala.coursework.View.ShoppingCart::class.java).apply {
                putExtra("uId", uId)
            }
            startActivity(intent)
        } else {
            Toast.makeText(this, "Can not remove the item from the basket", Toast.LENGTH_SHORT).show()
        }
    }
}