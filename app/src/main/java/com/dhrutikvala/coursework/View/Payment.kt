package com.dhrutikvala.coursework.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.dhrutikvala.coursework.Controller.CafeController
import com.dhrutikvala.coursework.Model.CustomAdapter
import com.dhrutikvala.coursework.Model.Order
import com.dhrutikvala.coursework.Model.Product
import com.dhrutikvala.coursework.Model.Payment
import com.dhrutikvala.coursework.Model.OrderDetails
import com.dhrutikvala.coursework.R
import org.w3c.dom.Text
import java.time.LocalDate
import java.util.Calendar

class Payment : AppCompatActivity() {

    val cc: CafeController = CafeController(this)
    var userId: Int = 0
    lateinit var shoppingCart: ListView
    var i = 0
    var x = 0
    var totalPrice: Double = 0.0
    var allProductIds: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val spinnerProducts = arrayOf("Debit Card", "Paypal", "Google Pay")
        val pType = findViewById<Spinner>(R.id.paymentTypeSpinner)
        pType.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,spinnerProducts)

        userId = intent.getIntExtra("uId", 0)

        var usersShoppingCart = cc.getCustomerShoppingList(userId)
        var usersShoppingCartProducts: MutableList<Product>
        var productNames = mutableListOf<String>()
        var productTypes =  mutableListOf<Int>()
        var productTypesString = mutableListOf<String>()
        var productPrices =  mutableListOf<Double>()
        var productAvailability =  mutableListOf<Int>()
        var productImages =  mutableListOf<ByteArray>()
        var productsId = mutableListOf<Int>()

        while(i < usersShoppingCart.size) {
            usersShoppingCartProducts = cc.getProductById(usersShoppingCart.get(i).ProdId)
            productsId.add(usersShoppingCartProducts.get(0).ProdId)
            allProductIds += "${productsId.get(i)},"
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
            totalPrice += productPrices.get(i)
            productImages.add(usersShoppingCartProducts.get(0).ProdImage!!)
            productAvailability.add(usersShoppingCartProducts.get(0).ProdIsAvailable)
            i++
        }

        findViewById<TextView>(R.id.txtPrice).apply {
            text = "Total: £" + totalPrice
        }
        shoppingCart = findViewById<ListView>(R.id.listPaymentShoppingList)
        val customAdapter = CustomAdapter(this, productsId, productNames, productImages, productPrices, productAvailability, productTypesString)
        shoppingCart!!.adapter = customAdapter
    }

    fun btnGoBack(view: View) {
        val intent = Intent(this, ShoppingCart::class.java).apply {
            putExtra("uId", userId)
        }
        startActivity(intent)
    }

    fun btnPayNow(view: View) {
        val payType = findViewById<Spinner>(R.id.paymentTypeSpinner)
        val paymentMethod = payType.selectedItem.toString()
        var paymentMethodInt: Int = 0
        if(paymentMethod == "Debit Card") {
            paymentMethodInt = 1
        } else if(paymentMethod == "Paypal") {
            paymentMethodInt = 2
        } else if(paymentMethod == "Google Play") {
            paymentMethodInt = 3
        }

        var i: Int = 0
        var orderId = 0
        val calendar = Calendar.getInstance()
        val orderTime = "" + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH + 1)
        val year = calendar.get(Calendar.YEAR)
        val orderDate = "" + day + "/" + month + "/" + year
        val orderStatus = 1
        //orderId = cc.getOrderId(customerId, orderTime)
        //Toast.makeText(this, "$orderId", Toast.LENGTH_LONG).show()


        if(cc.addOrder(Order(0, userId, orderDate, orderTime, orderStatus))) {
            orderId = cc.getOrderId(userId, orderTime)
            if(cc.addPayment(Payment(0, orderId, paymentMethodInt, totalPrice, orderDate))) {
                if(cc.addOrderDetails(OrderDetails(0, orderId, allProductIds))) {
                    cc.removeCustomerShoppingList(userId)
                    Toast.makeText(this, "Order has been successfully placed", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, CafeMenu::class.java).apply {
                        putExtra("uId", userId)
                    }
                    startActivity(intent)
                }
            }
        } else {
            Toast.makeText(this, "Order failed to process", Toast.LENGTH_LONG).show()
        }



    }
}