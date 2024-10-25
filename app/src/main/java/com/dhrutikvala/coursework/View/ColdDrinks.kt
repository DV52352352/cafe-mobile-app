package com.dhrutikvala.coursework.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import com.dhrutikvala.coursework.Controller.CafeController
import com.dhrutikvala.coursework.Model.CustomAdapter
import com.dhrutikvala.coursework.R

class ColdDrinks : AppCompatActivity() {

    public var userId = 0
    var cc: CafeController = CafeController(this)
    lateinit var cafeMenuList: ListView
    var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cold_drinks)

        userId = intent.getIntExtra("uId", 0)

        var allColdDrinks = cc.getAllColdDrinks()
        var productsId = mutableListOf<Int>()
        var productNames = mutableListOf<String>()
        var productTypes =  mutableListOf<Int>()
        var productTypesString = mutableListOf<String>()
        var productPrices =  mutableListOf<Double>()
        var productAvailability =  mutableListOf<Int>()
        var productImages =  mutableListOf<ByteArray>()
        while(i < allColdDrinks.size) {
            productsId.add(allColdDrinks.get(i).ProdId)
            productNames.add(allColdDrinks.get(i).ProdName)
            productTypes.add(allColdDrinks.get(i).ProdType)
            if(productTypes.get(i) == 1) {
                productTypesString.add("Cold Drink")
            } else if(productTypes.get(i) == 2) {
                productTypesString.add("Hot Drink")
            } else if(productTypes.get(i) == 3) {
                productTypesString.add("Cake")
            } else if(productTypes.get(i) == 4) {
                productTypesString.add("Snack")
            }
            productPrices.add(allColdDrinks.get(i).ProdPrice)
            productImages.add(allColdDrinks.get(i).ProdImage!!)
            productAvailability.add(allColdDrinks.get(i).ProdIsAvailable)
            i++
        }

        cafeMenuList = findViewById<ListView>(R.id.listColdDrinks)
        val customAdapter = CustomAdapter(this, productsId, productNames, productImages, productPrices, productAvailability, productTypesString)
        cafeMenuList!!.adapter = customAdapter

        cafeMenuList.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this, ProductPage::class.java)
            intent.putExtra("uId", userId)
            intent.putExtra("pId", productsId.get(i))
            intent.putExtra("pName", productNames.get(i))
            intent.putExtra("pType", productTypesString.get(i))
            intent.putExtra("pPrice", productPrices.get(i))
            intent.putExtra("pIsAvailable", productAvailability.get(i))
            intent.putExtra("pImage", productImages.get(i))
            startActivity(intent)

        }
    }

    fun btnGoBack(view: View) {
        val intent = Intent(this, CafeMenu::class.java).apply {
            putExtra("uId", userId)
        }
        startActivity(intent)
    }
}