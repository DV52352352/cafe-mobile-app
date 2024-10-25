package com.dhrutikvala.coursework.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import com.dhrutikvala.coursework.Controller.CafeController
import com.dhrutikvala.coursework.Model.CustomAdapter
import com.dhrutikvala.coursework.Model.OrdersCustomAdapter
import com.dhrutikvala.coursework.R

class ManageOrders : AppCompatActivity() {

    var cc: CafeController = CafeController(this)
    lateinit var cafeMenuList: ListView
    var i: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_orders)

        var allOrders = cc.getOrders()
        var orderIds = mutableListOf<Int>()
        var customerIds = mutableListOf<Int>()
        var customerNames = mutableListOf<String>()
        var orderDates = mutableListOf<String>()
        var orderTimes = mutableListOf<String>()
        var orderStatus = mutableListOf<Int>()
        var orderStatusString = mutableListOf<String>()

        while(i < allOrders.size) {
            orderIds.add(allOrders.get(i).OrderId)
            customerIds.add(allOrders.get(i).CusId)
            customerNames.add(cc.getCustomerName(customerIds.get(i)))
            orderDates.add(allOrders.get(i).OrderDate)
            orderTimes.add(allOrders.get(i).OrderTime)
            orderStatus.add(allOrders.get(i).OrderStatus)
            if(orderStatus.get(i) == 1) {
                orderStatusString.add("Unprepared")
            } else if(orderStatus.get(i) == 2) {
                orderStatusString.add("Prepared")
            }
            i++
        }

        cafeMenuList = findViewById<ListView>(R.id.listOrders)
        val customAdapter = OrdersCustomAdapter(this, orderIds, customerNames, orderDates, orderTimes, orderStatusString)
        cafeMenuList!!.adapter = customAdapter

        cafeMenuList.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this, ManageOrderDetails::class.java)
            intent.putExtra("cId", customerIds.get(i))
            intent.putExtra("oId", orderIds.get(i))
            intent.putExtra("cName", customerNames.get(i))
            intent.putExtra("oDate", orderDates.get(i))
            intent.putExtra("oTime", orderTimes.get(i))
            intent.putExtra("oStatus", orderStatus.get(i))
            intent.putExtra("oStatusString", orderStatusString.get(i))
            startActivity(intent)
        }
    }

    fun btnGoBack(view: View) {
        val intent = Intent(this, AdminPanel::class.java)
        startActivity(intent)
    }
}