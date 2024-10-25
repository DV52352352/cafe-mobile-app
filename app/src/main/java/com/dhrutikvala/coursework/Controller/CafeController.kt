package com.dhrutikvala.coursework.Controller

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.dhrutikvala.coursework.Model.CafeDatabase
import com.dhrutikvala.coursework.Model.Customer
import com.dhrutikvala.coursework.Model.Order
import com.dhrutikvala.coursework.Model.OrderDetails
import com.dhrutikvala.coursework.Model.Product
import com.dhrutikvala.coursework.Model.ShoppingCart
import com.dhrutikvala.coursework.Model.Payment


class CafeController(context: Context) {

    private val cafeDatabase: CafeDatabase = CafeDatabase(context)

    fun registerAddCustomer(CusFullName: String, CusEmail: String,
                            CusPhoneNo: String, CusUserName: String, CusPassword: String) : String {

        val CusIsActive = 0
        val customer = Customer(0, CusFullName, CusEmail, CusPhoneNo,
            CusUserName, CusPassword, CusIsActive)

        if(cafeDatabase.addCustomer(customer)) {
            return "Account has been created"
        } else {
            return "Error has occured while creating your account"
        }
    }

    fun loginAdmin(username: String, password: String) : Int {
        var a = cafeDatabase.getAdminAccount(username, password)
        if(a.AdminFullName != "Account not found") {
            cafeDatabase.updateAdminIsActive(a)
            return a.AdminId
        } else {
            return -1
        }
    }

    fun loginCustomer(username: String, password: String) : Int {
        var a = cafeDatabase.getCustomerAccount(username, password)
        if(a.CusFullName != "Account not found") {
            cafeDatabase.updateCustomerIsActive(a)
            return a.CusId
        } else {
            return -1
        }
    }

    fun logoutAdmin(id: Int) {
        var a = cafeDatabase.getAdmin(id)
        if(a.AdminFullName != "Account not found") {
            cafeDatabase.updateAdminIsActive(a)
        }
    }

    fun logoutCustomer(id: Int) {
        var a = cafeDatabase.getCustomer(id)
        if(a.CusFullName != "Account not found") {
            cafeDatabase.updateCustomerIsActive(a)
        }
    }

    fun getCustomerId(customer: Customer) : Int {
        return customer.CusId
    }

    fun getCustomerName(id: Int) : String {
        return cafeDatabase.getCustomer(id).CusFullName
    }

    fun getAllProductId() : MutableList<Int> {
        var productId: MutableList<Int>
        productId = cafeDatabase.getAllProductId()

        return productId
    }

    fun getAllProductNames(): MutableList<String> {
        var productNames: MutableList<String>
        productNames = cafeDatabase.getAllProductNames()

        return productNames

    }

    fun getAllProductImages() : MutableList<ByteArray> {
        var productImages: MutableList<ByteArray>
        productImages = cafeDatabase.getAllProductImage()

        return productImages
    }

    fun getAllProductPrices() : MutableList<Double> {
        var productPrices: MutableList<Double>
        productPrices = cafeDatabase.getAllProductPrice()

        return productPrices
    }

    fun getAllProductIsAvailable() : MutableList<Int> {
        var productsIsAvailable: MutableList<Int>
        productsIsAvailable = cafeDatabase.getAllProductIsAvailable()

        return productsIsAvailable
    }

    fun getAllProductTypes() : MutableList<String> {
        var productTypes: MutableList<String>
        productTypes = cafeDatabase.getAllProductType()

        return productTypes
    }

    fun getAllColdDrinks() : MutableList<Product> {
        var productColdDrinks: MutableList<Product>
        productColdDrinks = cafeDatabase.getAllColdDrinks()

        return productColdDrinks
    }

    fun getAllHotDrinks() : MutableList<Product> {
        var productHotDrinks: MutableList<Product>
        productHotDrinks = cafeDatabase.getAllHotDrinks()

        return productHotDrinks
    }

    fun getAllCakes() : MutableList<Product> {
        var productCakes: MutableList<Product>
        productCakes = cafeDatabase.getAllCakes()

        return productCakes
    }

    fun getAllSnacks() : MutableList<Product> {
        var productSnacks: MutableList<Product>
        productSnacks = cafeDatabase.getAllSnacks()

        return productSnacks
    }

    fun getProductById(productId: Int): MutableList<Product> {
        var product: MutableList<Product>
        product = cafeDatabase.getProductById(productId)

        return product
    }

    fun updateOrderStatus(order: Order): Boolean {
        var orderChanged: Boolean
        orderChanged = cafeDatabase.updateOrderToPrepared(order)

        return orderChanged
    }

    fun updateProduct(product: Product): Boolean {
        var productUpdated: Boolean
        productUpdated = cafeDatabase.updateProduct(product)
        return productUpdated

    }

    fun deleteProduct(id: Int): Boolean {
        var productDeleted: Boolean
        productDeleted = cafeDatabase.deleteProduct(id)
        return productDeleted
    }

    fun getCustomerShoppingList(customerId: Int): MutableList<ShoppingCart> {
        var shoppingCart: MutableList<ShoppingCart>
        shoppingCart = cafeDatabase.getCustomerShoppingList(customerId)

        return shoppingCart
    }

    fun addItemToShoppingList(customerId: Int, productId: Int): Boolean {
        var shoppingList: Boolean = false
        shoppingList = cafeDatabase.addShoppingItem(ShoppingCart(0, customerId, productId))
        return shoppingList
    }

    fun removeCustomerShoppingItem(shoppingCartId: Int, customerId: Int, productId: Int): Boolean {
        var shoppingList: Boolean = false
        shoppingList = cafeDatabase.deleteCustomerShoppingItem(ShoppingCart(shoppingCartId, customerId, productId))
        return shoppingList
    }

    fun removeCustomerShoppingList(customerId: Int): Boolean {
        var shoppingItem: Boolean

        shoppingItem = cafeDatabase.deleteCustomerShoppingList(customerId)
        return shoppingItem

    }

    fun getCustomerShoppingListCount(customerId: Int): Int {
        var shoppingCartCount: Int = 0
        shoppingCartCount = cafeDatabase.getCustomerShoppingList(customerId).size

        return shoppingCartCount
    }

    fun getOrderId(customerId: Int, orderTime: String): Int {
        var orderId: Int = 0
        orderId = cafeDatabase.getOrderId(customerId, orderTime)

        return orderId
    }

    fun getOrders(): MutableList<Order> {
        var orderList = mutableListOf<Order>()
        orderList = cafeDatabase.getAllOrders()

        return orderList
    }

    fun getOrderDetailsByOrderId(id: Int): MutableList<OrderDetails> {
        var orderDetails = mutableListOf<OrderDetails>()
        orderDetails = cafeDatabase.getOrderDetailsByOrderId(id)

        return orderDetails
    }

    fun addOrder(order: Order): Boolean {
        var orderDetails: Boolean = false
        orderDetails = cafeDatabase.addOrder(order)

        return orderDetails
    }

    fun addPayment(payment: Payment): Boolean {
        var paymentDetails: Boolean = false
        paymentDetails = cafeDatabase.addPayment(payment)

        return paymentDetails
    }

    fun addOrderDetails(orderDetails: OrderDetails): Boolean {
        var orderDetailsList: Boolean = false
        orderDetailsList = cafeDatabase.addOrderDetails(orderDetails)

        return orderDetailsList
    }

    fun addProduct(product: Product): Boolean {
        var productDetails: Boolean = false
        productDetails = cafeDatabase.addProduct(product)

        return productDetails
    }


}