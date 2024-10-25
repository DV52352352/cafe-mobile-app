package com.dhrutikvala.coursework.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import com.dhrutikvala.coursework.Controller.CafeController
import com.dhrutikvala.coursework.Model.CustomAdapter
import com.dhrutikvala.coursework.Model.Product
import com.dhrutikvala.coursework.R

class ShoppingCart : AppCompatActivity() {

    val cc: CafeController = CafeController(this)
    var userId: Int = 0
    lateinit var shoppingCart: ListView
    var i = 0
    var x = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)

        userId = intent.getIntExtra("uId", 0)

        var usersShoppingCart = cc.getCustomerShoppingList(userId)
        var usersShoppingCartProducts: MutableList<Product>
        var productsId = mutableListOf<Int>()
        var productNames = mutableListOf<String>()
        var productTypes =  mutableListOf<Int>()
        var productTypesString = mutableListOf<String>()
        var productPrices =  mutableListOf<Double>()
        var productAvailability =  mutableListOf<Int>()
        var productImages =  mutableListOf<ByteArray>()
        while(i < usersShoppingCart.size) {
            usersShoppingCartProducts = cc.getProductById(usersShoppingCart.get(i).ProdId)
            productsId.add(usersShoppingCartProducts.get(0).ProdId)
            productNames.add(usersShoppingCartProducts.get(0).ProdName)
            productTypes.add(usersShoppingCartProducts.get(0).ProdType)
            if(productTypes.get(i) == 1) {
                productTypesString.add("Cold Drink")
            } else if(productTypes.get(i) == 2) {
                productTypesString.add("Hot Drink")
            } else if(productTypes.get(i) == 3) {
                productTypesString.add("Cake")
            } else if(productTypes.get(i) == 4) {
                productTypesString.add("Snack")
            }
            productPrices.add(usersShoppingCartProducts.get(0).ProdPrice)
            productImages.add(usersShoppingCartProducts.get(0).ProdImage!!)
            productAvailability.add(usersShoppingCartProducts.get(0).ProdIsAvailable)
            i++
        }

        shoppingCart = findViewById<ListView>(R.id.listShoppingCart)
        val customAdapter = CustomAdapter(this, productsId, productNames, productImages, productPrices, productAvailability, productTypesString)
        shoppingCart!!.adapter = customAdapter

        shoppingCart.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this, ProductPage::class.java)
            intent.putExtra("delete", 1)
            intent.putExtra("cId", usersShoppingCart.get(i).CartId)
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

    fun btnGoToPayment(view: View) {
        val intent = Intent(this, Payment::class.java)
        intent.putExtra("uId", userId)
        startActivity(intent)
    }
}