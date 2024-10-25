package com.dhrutikvala.coursework.Model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

private val DatabaseName = "CourseWorkDB.db"
private val ver: Int = 1

class CafeDatabase(context: Context) : SQLiteOpenHelper(context, DatabaseName, null, ver) {

    //Admin Table
    public val adminTableName = "TAdmin"
    public val admin_ID = "AdminId"
    public val admin_FullName = "AdminFullName"
    public val admin_Email = "AdminEmail"
    public val admin_PhoneNo = "AdminPhoneNo"
    public val admin_UserName = "AdminUserName"
    public val admin_Password = "AdminPassword"
    public val admin_IsActive = "AdminIsActive"

    //Customer Table
    public val customerTableName = "TCustomer"
    public val customer_ID = "CusId"
    public val customer_FullName = "CusFullName"
    public val customer_Email = "CusEmail"
    public val customer_PhoneNo = "CusPhoneNo"
    public val customer_UserName = "CusUserName"
    public val customer_Password = "CusPassword"
    public val customer_IsActive = "CusIsActive"

    //Order Table
    public val orderTableName = "TOrder"
    public val order_ID = "OrderId"
    public val order_Date = "OrderDate"
    public val order_Time = "OrderTime"
    public val order_Status = "OrderStatus"

    //Order Details Table
    public val orderDetailsTableName = "TOrderDetails"
    public val order_DetailsID = "OrderDetailsId"

    //Payment Table
    public val paymentTableName = "TPayment"
    public val payment_ID = "PaymentId"
    public val payment_Type = "PaymentType"
    public val amount = "Amount"
    public val payment_Date = "PaymentDate"

    //Product Table
    public val productTableName = "TProduct"
    public val product_ID = "ProdId"
    public val product_Name = "ProdName"
    public val product_Image = "ProdImage"
    public val product_Price = "ProdPrice"
    public val product_IsAvailable = "ProdIsAvailable"
    public val product_Type = "ProdType"

    //Shopping Cart Table
    public val shoppingCartTableName = "TShoppingCart"
    public val shoppingCart_Id = "CartId"


    override fun onCreate(db: SQLiteDatabase?) {
        try {
            var sqlCreateStatement: String = "CREATE TABLE " + adminTableName + " ( " +
                    admin_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    admin_FullName + " TEXT NOT NULL, " +
                    admin_Email + " TEXT NOT NULL, " +
                    admin_PhoneNo + " TEXT NOT NULL, " +
                    admin_UserName + " TEXT NOT NULL UNIQUE, " +
                    admin_Password + " TEXT NOT NULL, " +
                    admin_IsActive + " INTEGER NOT NULL )"

            db?.execSQL(sqlCreateStatement)

            sqlCreateStatement = "CREATE TABLE " + customerTableName + " ( " +
                    customer_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    customer_FullName + " TEXT NOT NULL, " +
                    customer_Email + " TEXT NOT NULL, " +
                    customer_PhoneNo + " TEXT NOT NULL, " +
                    customer_UserName + " TEXT NOT NULL UNIQUE, " +
                    customer_Password + " TEXT NOT NULL, " +
                    customer_IsActive + " INTEGER NOT NULL )"

            db?.execSQL(sqlCreateStatement)

            sqlCreateStatement = "CREATE TABLE " + orderTableName + " ( " +
                    order_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    customer_ID + " INTEGER NOT NULL, " +
                    order_Date + " TEXT NOT NULL, " +
                    order_Time + " TEXT NOT NULL, " +
                    order_Status + " INTEGER NOT NULL DEFAULT 0)"

            db?.execSQL(sqlCreateStatement)

            sqlCreateStatement = "CREATE TABLE " + orderDetailsTableName + " ( " +
                    order_DetailsID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    order_ID + " INTEGER NOT NULL, " +
                    product_ID + " TEXT NOT NULL )"

            db?.execSQL(sqlCreateStatement)

            sqlCreateStatement = "CREATE TABLE " + paymentTableName + " ( " +
                    payment_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    order_ID + " INTEGER NOT NULL, " +
                    payment_Type + " INTEGER NOT NULL, " +
                    amount + " REAL NOT NULL DEFAULT 0.0, " +
                    payment_Date + " TEXT NOT NULL )"

            db?.execSQL(sqlCreateStatement)

            sqlCreateStatement = "CREATE TABLE " + productTableName + " ( " +
                    product_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    product_Name + " TEXT NOT NULL, " +
                    product_Image + " BLOB, " +
                    product_Price + " REAL NOT NULL, " +
                    product_IsAvailable + " INT NOT NULL, " +
                    product_Type + " INTEGER NOT NULL DEFAULT 1 )"

            db?.execSQL(sqlCreateStatement)

            sqlCreateStatement = "CREATE TABLE " + shoppingCartTableName + " ( " +
                    shoppingCart_Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    customer_ID + " INTEGER NOT NULL, " +
                    product_ID + " INTEGER NOT NULL )"

            db?.execSQL(sqlCreateStatement)

        } catch (e: SQLException) {
        }
    }

    fun getAllAdmins(): ArrayList<Admin> {

        val adminList = ArrayList<Admin>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $adminTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val fullName: String = cursor.getString(1)
                val email: String = cursor.getString(2)
                val phoneNo: String = cursor.getString(3)
                val userName: String = cursor.getString(4)
                val password: String = cursor.getString(5)
                val isActive: Int = cursor.getInt(6)
                val a = Admin(id, fullName, email, phoneNo, userName, password, isActive)
                adminList.add(a)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return adminList

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun getShoppingList(): ArrayList<ShoppingCart> {

        val shoppingList = ArrayList<ShoppingCart>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $shoppingCartTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val cusId: Int = cursor.getInt(1)
                val prodId: Int = cursor.getInt(2)
                val a = ShoppingCart(id, cusId, prodId)
                shoppingList.add(a)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return shoppingList

    }

    fun getAllCustomers(): ArrayList<Customer> {

        val customerList = ArrayList<Customer>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $customerTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val fullName: String = cursor.getString(1)
                val email: String = cursor.getString(2)
                val phoneNo: String = cursor.getString(3)
                val userName: String = cursor.getString(4)
                val password: String = cursor.getString(5)
                val isActive: Int = cursor.getInt(6)
                val a = Customer(id, fullName, email, phoneNo, userName, password, isActive)
                customerList.add(a)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return customerList

    }

    fun getAllOrders(): MutableList<Order> {

        val orderList = mutableListOf<Order>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $orderTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val cusId: Int = cursor.getInt(1)
                val orderDate: String = cursor.getString(2)
                val orderTime: String = cursor.getString(3)
                val orderStatus: Int = cursor.getInt(4)
                val a = Order(id, cusId, orderDate, orderTime, orderStatus)
                orderList.add(a)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return orderList

    }

     fun getAllOrderDetails(): ArrayList<OrderDetails> {

         val orderDetailsList = ArrayList<OrderDetails>()
         val db: SQLiteDatabase = this.readableDatabase
         val sqlStatement = "SELECT * FROM $orderDetailsTableName"

         val cursor: Cursor = db.rawQuery(sqlStatement, null)

         if (cursor.moveToFirst())
             do {
                 val id: Int = cursor.getInt(0)
                 val orderId: Int = cursor.getInt(1)
                 val productId: String = cursor.getString(2)
                 val a = OrderDetails(id, orderId, productId)
                 orderDetailsList.add(a)
             } while (cursor.moveToNext())

         cursor.close()
         db.close()

         return orderDetailsList

     }

     fun getAllPayments(): ArrayList<Payment> {

         val paymentList = ArrayList<Payment>()
         val db: SQLiteDatabase = this.readableDatabase
         val sqlStatement = "SELECT * FROM $paymentTableName"

         val cursor: Cursor = db.rawQuery(sqlStatement, null)

         if (cursor.moveToFirst())
             do {
                 val id: Int = cursor.getInt(0)
                 val orderId: Int = cursor.getInt(1)
                 val paymentType: Int = cursor.getInt(2)
                 val amount: Double = cursor.getDouble(3)
                 val paymentDate: String = cursor.getString(4)
                 val a = Payment(id, orderId, paymentType, amount, paymentDate)
                 paymentList.add(a)
             } while (cursor.moveToNext())

         cursor.close()
         db.close()

         return paymentList

     }

     fun getAllProducts(): ArrayList<Product> {

         val productList = ArrayList<Product>()
         val db: SQLiteDatabase = this.readableDatabase
         val sqlStatement = "SELECT * FROM $productTableName"

         val cursor: Cursor = db.rawQuery(sqlStatement, null)

         if (cursor.moveToFirst())
             do {
                 val id: Int = cursor.getInt(0)
                 val prodName: String = cursor.getString(1)
                 val prodImage: ByteArray? = cursor.getBlob(2)
                 val prodPrice: Double = cursor.getDouble(3)
                 val prodIsAvailable: Int = cursor.getInt(4)
                 val prodType: Int = cursor.getInt(5)
                 val a = Product(id, prodName, prodImage, prodPrice, prodIsAvailable, prodType)
                 productList.add(a)
             } while (cursor.moveToNext())

         cursor.close()
         db.close()

         return productList
     }

    fun getAllColdDrinks(): MutableList<Product> {

        val productColdDrinkList = mutableListOf<Product>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $productTableName WHERE $product_Type=1 AND $product_IsAvailable=1"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)
        if(cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val prodName: String = cursor.getString(1)
                val prodImage: ByteArray? = cursor.getBlob(2)
                val prodPrice: Double = cursor.getDouble(3)
                val prodIsAvailable: Int = cursor.getInt(4)
                val prodType: Int = cursor.getInt(5)
                val a = Product(id, prodName, prodImage, prodPrice, prodIsAvailable, prodType)
                productColdDrinkList.add(a)
            } while(cursor.moveToNext())

        cursor.close()
        db.close()

        return productColdDrinkList
    }

    fun getCustomerShoppingList(id: Int): MutableList<ShoppingCart> {

        val customerShoppingList = mutableListOf<ShoppingCart>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $shoppingCartTableName WHERE $customer_ID=$id"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)
        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val cusId: Int = cursor.getInt(1)
                val prodId: Int = cursor.getInt(2)
                val a = ShoppingCart(id, cusId, prodId)
                customerShoppingList.add(a)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return customerShoppingList
    }

    fun getProductById(id: Int): MutableList<Product> {
        val product = mutableListOf<Product>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $productTableName WHERE $product_ID=$id"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val prodName: String = cursor.getString(1)
                val prodImage: ByteArray? = cursor.getBlob(2)
                val prodPrice: Double = cursor.getDouble(3)
                val prodIsAvailable: Int = cursor.getInt(4)
                val prodType: Int = cursor.getInt(5)
                val a = Product(id, prodName, prodImage, prodPrice, prodIsAvailable, prodType)
                product.add(a)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return product
    }

    fun getAllProductId(): MutableList<Int> {

        val productIdList = mutableListOf<Int>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $productTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val prodId: Int = cursor.getInt(0)
                productIdList.add(prodId)

            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return productIdList
    }

    fun getAllProductNames(): MutableList<String> {

        val productNameList = mutableListOf<String>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $productTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val prodName: String = cursor.getString(1)
                productNameList.add(prodName)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return productNameList
    }

    fun getAllProductPrice(): MutableList<Double> {

        val productPriceList = mutableListOf<Double>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $productTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val prodPrice: Double = cursor.getDouble(3)
                productPriceList.add(prodPrice)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return productPriceList
    }

    fun getAllProductIsAvailable(): MutableList<Int> {

        val productIsAvailableList = mutableListOf<Int>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $productTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val prodIsAvailable: Int = cursor.getInt(4)
                productIsAvailableList.add(prodIsAvailable)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return productIsAvailableList
    }

    fun getAllProductType(): MutableList<String> {

        val productTypesList = mutableListOf<String>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $productTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                if(cursor.getInt(5) == 1) {
                    productTypesList.add("Cold Drink")
                } else if(cursor.getInt(5) == 2) {
                    productTypesList.add("Hot Drink")
                } else if(cursor.getInt(5) == 3) {
                    productTypesList.add("Cake")
                } else if(cursor.getInt(5) == 4) {
                    productTypesList.add("Snack")
                }
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return productTypesList
    }

    fun getAllProductImage(): MutableList<ByteArray> {

        val productImageList = mutableListOf<ByteArray>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $productTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val prodImage: ByteArray? = cursor.getBlob(2)
                if (prodImage != null) {
                    productImageList.add(prodImage)
                }
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return productImageList
    }

    fun getCustomerShoppingItem(shoppingListId: Int, customerId: Int, productId: Int): MutableList<ShoppingCart> {

        val customerShoppingItem = mutableListOf<ShoppingCart>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $productTableName WHERE $customer_ID=$customerId AND $product_ID=$productId AND $shoppingCart_Id=$shoppingListId"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)
        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val cusId: Int = cursor.getInt(1)
                val prodId: Int = cursor.getInt(2)
                val a = ShoppingCart(id, cusId, prodId)
                customerShoppingItem.add(a)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return customerShoppingItem
    }

    fun getCustomerProductDetails(customerId: Int, productId: Int): MutableList<ShoppingCart> {

        val customerShoppingItem = mutableListOf<ShoppingCart>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $productTableName WHERE $customer_ID=$customerId AND $product_ID=$productId"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)
        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val cusId: Int = cursor.getInt(1)
                val prodId: Int = cursor.getInt(2)
                val a = ShoppingCart(id, cusId, prodId)
                customerShoppingItem.add(a)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return customerShoppingItem
    }

    fun getOrderId(customerId: Int, orderTime: String): Int {

        var orderId = 0
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $orderTableName WHERE $customer_ID=$customerId AND $order_Time = \"$orderTime\""

        val cursor: Cursor = db.rawQuery(sqlStatement, null)
        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                orderId = id
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return orderId

    }

    fun getOrderDetailsByOrderId(id: Int): MutableList<OrderDetails> {

        var orderDetails = mutableListOf<OrderDetails>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $orderDetailsTableName WHERE $order_ID=$id"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)
        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val orderId: Int = cursor.getInt(1)
                val productId: String = cursor.getString(2)
                val a = OrderDetails(id, orderId, productId)
                orderDetails.add(a)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return orderDetails

    }

    fun updateOrderToPrepared(order: Order): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(order_Status, 2)

        val result = db.update(orderTableName, cv, "$order_ID = ${order.OrderId}", null) == 1
        db.close()
        return result
    }

    fun updateProduct(product: Product) : Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(product_Name, product.ProdName)
        cv.put(product_Type, product.ProdType)
        cv.put(product_IsAvailable, product.ProdIsAvailable)
        cv.put(product_Price, product.ProdPrice)

        val result = db.update(productTableName, cv, "$product_ID = ${product.ProdId}", null) == 1
        db.close()
        return result

    }

    fun updateCustomerIsActive(customer: Customer): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()
        if(customer.CusIsActive == 0) {
            cv.put(customer_IsActive, 1)
        } else {
            cv.put(customer_IsActive, 0)
        }

        val result = db.update(customerTableName, cv, "$customer_ID = ${customer.CusId}", null) == 1
        db.close()
        return result
    }

    fun updateAdminIsActive(admin: Admin): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        if(admin.AdminIsActive == 0) {
            cv.put(admin_IsActive, 1)
        } else {
            cv.put(admin_IsActive, 0)
        }

        val result = db.update(adminTableName, cv, "$admin_ID = ${admin.AdminId}", null) == 1
        db.close()
        return result
    }

    fun deleteProduct(id: Int): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val result = db.delete(productTableName, "$product_ID = ${id}", null) == 1

        db.close()
        return result
    }

    fun deleteShoppingItem(id: Int): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val result = db.delete(shoppingCartTableName, "$shoppingCart_Id = ${id}", null) == 1

        db.close()
        return result
    }

    fun deleteCustomerShoppingItem(shoppingList: ShoppingCart): Boolean {
        val shoppingListId = shoppingList.CartId
        val db: SQLiteDatabase = this.writableDatabase
        val result = db.delete(shoppingCartTableName, "$shoppingCart_Id=$shoppingListId", null) == 1

        db.close()
        return result
    }

    fun deleteCustomerShoppingList(customerId: Int): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        var i = 0
        val result = db.delete(shoppingCartTableName, "$customer_ID=$customerId", null) == 1

        db.close()
        return result
    }

    fun addProduct(product: Product): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(product_Name, product.ProdName)
        cv.put(product_Image, product.ProdImage)
        cv.put(product_Type, product.ProdType)
        cv.put(product_Price, product.ProdPrice)
        cv.put(product_IsAvailable, product.ProdIsAvailable)

        val success = db.insert(productTableName, null, cv)
        db.close()
        return success != -1L
    }

    fun addOrder(order: Order): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(customer_ID, order.CusId)
        cv.put(order_Date, order.OrderDate)
        cv.put(order_Time, order.OrderTime)
        cv.put(order_Status, order.OrderStatus)

        val success = db.insert(orderTableName, null, cv)
        db.close()
        return success != -1L
    }

    fun addPayment(payment: Payment): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(order_ID, payment.OrderId)
        cv.put(payment_Type, payment.PaymentType)
        cv.put(amount, payment.Amount)
        cv.put(payment_Date, payment.PaymentDate)

        val success = db.insert(paymentTableName, null, cv)
        db.close()
        return success != -1L
    }

    fun addOrderDetails(orderDetails: OrderDetails): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(order_ID, orderDetails.OrderId)
        cv.put(product_ID, orderDetails.ProductId)

        val success = db.insert(orderDetailsTableName, null, cv)
        db.close()
        return success != -1L
    }

    fun addShoppingItem(shoppingCart: ShoppingCart): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(customer_ID, shoppingCart.CusId)
        cv.put(product_ID, shoppingCart.ProdId)

        val success = db.insert(shoppingCartTableName, null, cv)
        db.close()
        return success != -1L
    }

    fun addCustomer(customer: Customer): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(customer_FullName, customer.CusFullName)
        cv.put(customer_Email, customer.CusEmail)
        cv.put(customer_PhoneNo, customer.CusPhoneNo)
        cv.put(customer_UserName, customer.CusUserName)
        cv.put(customer_Password, customer.CusPassword)
        cv.put(customer_IsActive, customer.CusIsActive)

        val success = db.insert(customerTableName, null, cv)
        db.close()
        return success != -1L
    }

    fun getCustomerAccount(username: String, password: String): Customer {
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement =
            "SELECT * FROM $customerTableName WHERE $customer_UserName = \"$username\" " +
                    "AND $customer_Password = \"$password\""

        val cursor: Cursor = db.rawQuery(sqlStatement, null)
        if (cursor.moveToFirst()) {
            db.close()
            return Customer(
                cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6)
            )
        } else {
            db.close()
            return Customer(
                0, "Account not found", "0", "0",
                "0", "0", 0
            )
        }
    }

    fun getCustomer(id: Int): Customer {
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $customerTableName WHERE $customer_ID = $id"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)
        if (cursor.moveToFirst()) {
            db.close()
            return Customer(
                cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6)
            )
        } else {
            db.close()
            return Customer(
                0, "Customer not found", "0", "0",
                "0", "0", 0
            )
        }
    }

    fun getAdmin(id: Int): Admin {
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $adminTableName WHERE $admin_ID = $id"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)
        if (cursor.moveToFirst()) {
            db.close()
            return Admin(
                cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6)
            )
        } else {
            db.close()
            return Admin(
                0, "Admin not found", "0", "0",
                "0", "0", 0
            )
        }
    }

    fun getAdminAccount(username: String, password: String): Admin {
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $adminTableName WHERE $admin_UserName = \"$username\" " +
                "AND $admin_Password = \"$password\""

        val cursor: Cursor = db.rawQuery(sqlStatement, null)
        if (cursor.moveToFirst()) {
            db.close()
            return Admin(
                cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6)
            )
        } else {
            db.close()
            return Admin(
                0, "Account not found", "0", "0",
                "0", "0", 0
            )
        }
    }

    fun getAllCakes(): MutableList<Product> {
        val productList = mutableListOf<Product>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $productTableName WHERE $product_Type = 3 AND $product_IsAvailable=1"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val prodName: String = cursor.getString(1)
                val prodImage: ByteArray? = cursor.getBlob(2)
                val prodPrice: Double = cursor.getDouble(3)
                val prodIsAvailable: Int = cursor.getInt(4)
                val prodType: Int = cursor.getInt(5)
                val a = Product(id, prodName, prodImage, prodPrice, prodIsAvailable, prodType)
                productList.add(a)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return productList
    }

    fun getAllHotDrinks(): MutableList<Product> {
        val productList = mutableListOf<Product>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $productTableName WHERE $product_Type = 2 AND $product_IsAvailable=1"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val prodName: String = cursor.getString(1)
                val prodImage: ByteArray? = cursor.getBlob(2)
                val prodPrice: Double = cursor.getDouble(3)
                val prodIsAvailable: Int = cursor.getInt(4)
                val prodType: Int = cursor.getInt(5)
                val a = Product(id, prodName, prodImage, prodPrice, prodIsAvailable, prodType)
                productList.add(a)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return productList
    }

    fun getAllSnacks(): MutableList<Product> {
        val productList = mutableListOf<Product>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $productTableName WHERE $product_Type = 4 AND $product_IsAvailable=1"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val prodName: String = cursor.getString(1)
                val prodImage: ByteArray? = cursor.getBlob(2)
                val prodPrice: Double = cursor.getDouble(3)
                val prodIsAvailable: Int = cursor.getInt(4)
                val prodType: Int = cursor.getInt(5)
                val a = Product(id, prodName, prodImage, prodPrice, prodIsAvailable, prodType)
                productList.add(a)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return productList
    }


}