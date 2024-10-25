package com.dhrutikvala.coursework.View

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import com.dhrutikvala.coursework.Controller.CafeController
import com.dhrutikvala.coursework.Model.CustomAdapter
import com.dhrutikvala.coursework.R

class ManageCafeMenu : AppCompatActivity() {

    val cc: CafeController = CafeController(this)
    lateinit var cafeMenuList: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_cafe_menu)

        var productsId = cc.getAllProductId()
        var productsName = cc.getAllProductNames()
        var productsType = cc.getAllProductTypes()
        var productsPrice = cc.getAllProductPrices()
        var productsIsAvailable = cc.getAllProductIsAvailable()
        var productsImage = cc.getAllProductImages()

        cafeMenuList = findViewById<ListView>(R.id.manageCafeMenuList)
        val customAdapter = CustomAdapter(this, productsId, productsName, productsImage, productsPrice, productsIsAvailable, productsType)
        cafeMenuList!!.adapter = customAdapter

        cafeMenuList.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this, EditMenu::class.java)
            intent.putExtra("pId", productsId.get(i))
            intent.putExtra("pName", productsName.get(i))
            intent.putExtra("pType", productsType.get(i))
            intent.putExtra("pPrice", productsPrice.get(i))
            intent.putExtra("pIsAvailable", productsIsAvailable.get(i))
            intent.putExtra("pImage", productsImage.get(i))
            startActivity(intent)
        }

    }

    fun btnGoBack(view: View) {
        val intent = Intent(this, AdminPanel::class.java)
        startActivity(intent)
    }
}