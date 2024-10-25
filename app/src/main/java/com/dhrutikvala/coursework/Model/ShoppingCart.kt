package com.dhrutikvala.coursework.Model

import java.io.Serializable

data class ShoppingCart(var CartId: Int, var CusId: Int, var ProdId: Int){

    override fun toString(): String {
        return "ShoppingCart(CartId='$CartId', CusId='$CusId', ProdId='$ProdId')"
    }

}