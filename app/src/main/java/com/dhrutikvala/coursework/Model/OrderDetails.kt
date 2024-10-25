package com.dhrutikvala.coursework.Model

data class OrderDetails(val OrderDetailsId: Int, var OrderId: Int, var ProductId: String) {

    override fun toString(): String {
        return "OrderDetails(OrderDetailsId='$OrderDetailsId', OrderId='$OrderId', ProductId='$ProductId)"
    }

}


