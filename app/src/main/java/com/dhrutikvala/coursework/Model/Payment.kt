package com.dhrutikvala.coursework.Model

data class Payment(val PaymentId: Int, var OrderId: Int, var PaymentType: Int,
                 val Amount: Double, val PaymentDate: String) {

    override fun toString(): String {
        return "Payment(PaymentId='$PaymentId', OrderId='$OrderId, PaymentType='$PaymentType', " +
                "Amount='$Amount', PaymentDate='$PaymentType')"
    }

}


