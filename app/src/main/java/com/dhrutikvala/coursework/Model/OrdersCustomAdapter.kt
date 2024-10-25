package com.dhrutikvala.coursework.Model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.dhrutikvala.coursework.R

class OrdersCustomAdapter(private val appContext: Context, private val orderId: MutableList<Int>, private val CusName: MutableList<String>,
                            private val orderDate: MutableList<String>, private val orderTime: MutableList<String>,
                            private val orderStatus: MutableList<String>): BaseAdapter() {

    private val infalter: LayoutInflater = appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return orderId.size
    }

    override fun getItem(i: Int): Any? {
        return i
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var view: View? = view
        view = infalter.inflate(R.layout.manageorderslist, parent, false)

        val txtOrderId = view.findViewById<TextView>(R.id.txtOrderId)
        val txtOrderDate = view.findViewById<TextView>(R.id.txtOrderDate)
        val txtOrderTime = view.findViewById<TextView>(R.id.txtOrderTime)
        val txtOrderStatus = view.findViewById<TextView>(R.id.txtOrderStatus)

        txtOrderId.text = "" + orderId[position] + ": " + CusName[position]
        txtOrderDate.text = orderDate[position]
        txtOrderTime.text = orderTime[position]
        txtOrderStatus.text = orderStatus[position]

        return view
    }

}