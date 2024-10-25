package com.dhrutikvala.coursework.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.dhrutikvala.coursework.Controller.CafeController
import com.dhrutikvala.coursework.Model.CustomAdapter
import com.dhrutikvala.coursework.Model.Order
import com.dhrutikvala.coursework.Model.Product
import com.dhrutikvala.coursework.R

class ManageOrderDetails : AppCompatActivity() {

    val cc: CafeController = CafeController(this)
    lateinit var orderProductList: ListView
    var userId = 0
    var orderId = 0
    var customerId = 0
    var customerName = ""
    var orderDate = ""
    var orderTime = ""
    var orderStatusString = ""
    var orderStatus = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_order_details)

        val txtOrderNumber = findViewById<TextView>(R.id.txtOrderDetails)
        val txtCustomerName = findViewById<TextView>(R.id.txtOrderCustomerName)
        val txtOrderDate = findViewById<TextView>(R.id.txtOrderDate)
        val txtOrderStatus = findViewById<TextView>(R.id.txtOrderStatus)

        orderId = intent.getIntExtra("oId", 0)
        customerId = intent.getIntExtra("cId", 0)
        customerName = intent.getStringExtra("cName").toString()
        orderDate = intent.getStringExtra("oDate").toString()
        orderTime = intent.getStringExtra("cName").toString()
        orderStatusString = intent.getStringExtra("oStatusString").toString()
        orderStatus = intent.getIntExtra("oStatus", 0)

        txtOrderNumber.text = "Order Number: " + orderId
        txtCustomerName.text = "Customer Name: " + customerName
        txtOrderDate.text = "Order Date: " + orderDate
        txtOrderStatus.text = "Order Status: " + orderStatusString

        var orderDetails = cc.getOrderDetailsByOrderId(orderId)

        var i = 0
        var productIdArray = mutableListOf<Int>()
        var productIds = orderDetails.get(0).ProductId
        while(i < productIds.length) {
            if(productIds.get(i) != ',') {
                var productId = productIds.get(i).toString().toInt()
                productIdArray.add(productId)
            }
            i++
        }

        var productsId = mutableListOf<Int>()
        var productNames = mutableListOf<String>()
        var productTypes =  mutableListOf<Int>()
        var productTypesString = mutableListOf<String>()
        var productPrices =  mutableListOf<Double>()
        var productAvailability =  mutableListOf<Int>()
        var productImages =  mutableListOf<ByteArray>()

        i = 0
        while(i < productIdArray.size) {
            var product = cc.getProductById(productIdArray.get(i))
            productsId.add(product.get(0).ProdId)
            productNames.add(product.get(0).ProdName)
            productTypes.add(product.get(0).ProdType)
            if(productTypes.get(i) == 1) {
                productTypesString.add("Cold Drink")
            } else if(productTypes.get(i) == 2) {
                productTypesString.add("Hot Drink")
            } else if(productTypes.get(i) == 3) {
                productTypesString.add("Cake")
            } else if(productTypes.get(i) == 4) {
                productTypesString.add("Snack")
            }
            productPrices.add(product.get(0).ProdPrice)
            productAvailability.add(product.get(0).ProdIsAvailable)
            productImages.add(product.get(0).ProdImage!!)
            i++
        }

        orderProductList = findViewById<ListView>(R.id.listProductOrderDetails)
        val customAdapter = CustomAdapter(this, productsId, productNames, productImages, productPrices, productAvailability, productTypesString)
        orderProductList!!.adapter = customAdapter
    }

    fun btnSetToPrepared(view: View) {
        var order = Order(orderId, customerId, orderDate, orderTime, orderStatus)
        if(orderStatus == 2) {
            Toast.makeText(this, "The order is already prepared", Toast.LENGTH_SHORT).show()
        } else {
            if(cc.updateOrderStatus(order)) {
                Toast.makeText(this, "The order is set to prepared", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ManageOrders::class.java)
                startActivity(intent)
            }
        }
    }

    fun btnGoBack(view: View) {
        val intent = Intent(this, ManageOrders::class.java)
        startActivity(intent)
    }
}