package com.dhrutikvala.coursework.Model

data class Order(val OrderId: Int, var CusId: Int, var OrderDate: String,
                 val OrderTime: String, val OrderStatus: Int) {

    override fun toString(): String {
        return "Order(OrderId = '$OrderId', CusId='$CusId', OrderDate='$OrderDate', " +
                "OrderTime='$OrderTime, OrderStatus='$OrderStatus')"
    }

}


