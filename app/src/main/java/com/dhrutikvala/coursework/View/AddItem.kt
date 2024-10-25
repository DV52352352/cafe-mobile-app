package com.dhrutikvala.coursework.View

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import com.dhrutikvala.coursework.Controller.CafeController
import com.dhrutikvala.coursework.Model.Product
import com.dhrutikvala.coursework.R

class AddItem : AppCompatActivity() {

    val cc: CafeController = CafeController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        val spinnerProducts = arrayOf("Cake", "Hot Drink", "Cold Drink", "Snack")
        val pType = findViewById<Spinner>(R.id.addSpinnerType)
        pType.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,spinnerProducts)

    }

    fun btnAdd(view: View) {
        val addProductName = findViewById<TextView>(R.id.addProductName).text.toString()
        val addProductPrice = findViewById<TextView>(R.id.addProductPrice).text.toString().toDouble()
        val addProdType = findViewById<Spinner>(R.id.addSpinnerType)
        val addProdTypeString = addProdType.selectedItem.toString()
        var prodTypeInt: Int = 0
        if(addProdTypeString == "Cake") {
            prodTypeInt = 3
        } else if(addProdTypeString == "Hot Drink") {
            prodTypeInt = 2
        } else if(addProdTypeString == "Cold Drink") {
            prodTypeInt = 1
        } else if(addProdTypeString == "Snack") {
            prodTypeInt = 4
        }
        val addProdIsAvailable = findViewById<Switch>(R.id.switchProductAvailable2).isChecked
        var addProdIsAvailableInt = 0
        if(addProdIsAvailable) {
            addProdIsAvailableInt = 1
        }
        val addProductImage = cc.getProductById(1).get(0).ProdImage

        val product = Product(0, addProductName, addProductImage, addProductPrice, addProdIsAvailableInt, prodTypeInt)
        if(cc.addProduct(product)) {
            Toast.makeText(this, "The product was added successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ManageCafeMenu::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "The product can not be added", Toast.LENGTH_SHORT).show()
        }
    }

    fun btnGoBack(view: View) {
        val intent = Intent(this, AdminPanel::class.java)
        startActivity(intent)
    }
}