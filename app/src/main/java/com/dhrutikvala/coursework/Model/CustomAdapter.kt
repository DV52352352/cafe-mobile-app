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

class CustomAdapter(private val appContext: Context, private val prodId: MutableList<Int>, private val prodName: MutableList<String>, private val prodImage: MutableList<ByteArray>,
                    private val prodPrice: MutableList<Double>, private val prodIsAvailable: MutableList<Int>, private val prodType: MutableList<String>) : BaseAdapter() {

    private val infalter: LayoutInflater = appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return prodName.size
    }

    override fun getItem(i: Int): Any? {
        return i
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var view: View? = view
        view = infalter.inflate(R.layout.managecafelistview, parent, false)

        val pName = view.findViewById<TextView>(R.id.txtListProductName)
        val pImage = view.findViewById<ImageView>(R.id.txtListProductImage)
        val pPrice = view.findViewById<TextView>(R.id.txtListProductPrice)
        val pIsAvailable = view.findViewById<TextView>(R.id.txtListProductIsAvailable)
        val pType = view.findViewById<TextView>(R.id.txtListProductType)

        pName.text = prodName[position]

        var i = 0
        var imagesBmp = mutableListOf<Bitmap>()
        while(prodImage.size > i) {
            imagesBmp.add(BitmapFactory.decodeByteArray(prodImage.get(i), 0, prodImage.get(i)!!.size))
            i++
        }
        pImage.setImageBitmap(imagesBmp[position])
        pPrice.text = "£" + prodPrice[position].toString()

        var prodIsAvailableString = mutableListOf<String>()
        i = 0
        while(prodIsAvailable.size > i) {
            if(prodIsAvailable.get(i) == 1) {
                prodIsAvailableString.add("Available")
            } else {
                prodIsAvailableString.add("Unavailable")
            }
            i++
        }

        pIsAvailable.text = prodIsAvailableString[position]
        pType.text = prodType[position]
        return view
    }

}