package com.dhrutikvala.coursework.Model

data class Customer(val CusId: Int, var CusFullName: String, var CusEmail: String,
                 val CusPhoneNo: String, val CusUserName: String, val CusPassword: String, val CusIsActive: Int) {

    override fun toString(): String {
        return "Customer(fullName='$CusFullName', email='$CusEmail', phoneNo='$CusPhoneNo', userName='$CusUserName, " +
                "password='$CusPassword', isActive=$CusIsActive)"
    }

}


