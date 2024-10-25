package com.dhrutikvala.coursework.View

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.drawToBitmap
import com.dhrutikvala.coursework.Controller.CafeController
import com.dhrutikvala.coursework.Model.Product
import com.dhrutikvala.coursework.R
import java.io.ByteArrayOutputStream

class EditMenu : AppCompatActivity() {

    val cc: CafeController = CafeController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_menu)

        val pName = findViewById<TextView>(R.id.editProductName)
        val spinnerProducts = arrayOf("Cake", "Hot Drink", "Cold Drink", "Snack")
        val pType = findViewById<Spinner>(R.id.spinner)
        pType.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,spinnerProducts)
        val pImage = findViewById<ImageView>(R.id.editProductImage)
        val pPrice = findViewById<TextView>(R.id.editProductPrice)
        val pIsAvailable = findViewById<Switch>(R.id.switchProductAvailable)

        pName.text = intent.getStringExtra("pName")
        val prodType = intent.getStringExtra("pType")
        if(prodType == "Cake") {
            pType.setSelection(0)
        } else if(prodType == "Hot Drink") {
            pType.setSelection(1)
        } else if(prodType == "Cold Drink") {
            pType.setSelection(2)
        } else if(prodType == "Snack") {
            pType.setSelection(3)
        }
        val prodIsAvailable = intent.getIntExtra("pIsAvailable", 0)
        if(prodIsAvailable == 1) {
            pIsAvailable.toggle()
        }
        val prodImage = intent.getByteArrayExtra("pImage")
        val prodImageBitmap: Bitmap = BitmapFactory.decodeByteArray(prodImage, 0, prodImage!!.size)
        pImage.setImageBitmap(prodImageBitmap)
        pPrice.text = intent.getDoubleExtra("pPrice", 0.0).toString()
    }

    fun btnUpdateProduct(view: View) {
        val prodId = intent.getIntExtra("pId", 0)
        val prodName = findViewById<TextView>(R.id.editProductName).text.toString()
        val prodImage = intent.getByteArrayExtra("pImage")
        val prodType = findViewById<Spinner>(R.id.spinner)
        val prodTypeString = prodType.selectedItem.toString()
        var prodTypeInt: Int = 0
        if(prodTypeString == "Cake") {
            prodTypeInt = 3
        } else if(prodTypeString == "Hot Drink") {
            prodTypeInt = 2
        } else if(prodTypeString == "Cold Drink") {
            prodTypeInt = 1
        } else if(prodTypeString == "Snack") {
            prodTypeInt = 4
        }

        var prodPrice = findViewById<TextView>(R.id.editProductPrice).text.toString().toDouble()
        val prodIsAvailable = findViewById<Switch>(R.id.switchProductAvailable).isChecked
        var prodIsAvailableInt = 0
        if(prodIsAvailable) {
            prodIsAvailableInt = 1
        }

        if(cc.updateProduct(Product(prodId, prodName, prodImage,
                prodPrice, prodIsAvailableInt, prodTypeInt))) {
            Toast.makeText(this, "The product is updated successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ManageCafeMenu::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "The product was not updated", Toast.LENGTH_SHORT).show()
        }
    }

    fun btnDeleteProduct(view: View) {
        val prodId = intent.getIntExtra("pId", 0)

        if(cc.deleteProduct(prodId)) {
            Toast.makeText(this, "The product has been removed", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ManageCafeMenu::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "The product can not be removed", Toast.LENGTH_SHORT).show()
        }
    }
}