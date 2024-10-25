package com.dhrutikvala.coursework.Model

data class Product(
    val ProdId: Int, var ProdName: String, var ProdImage: ByteArray?,
    val ProdPrice: Double, val ProdIsAvailable: Int, val ProdType: Int) {

    override fun toString(): String {
        return "Product(ProdId='$ProdId', ProdName='$ProdName', ProdImage='$ProdImage', " +
                "ProdPrice='$ProdPrice', ProdIsActive='$ProdIsAvailable', ProdType='$ProdType')"
    }
}


