package com.dhrutikvala.coursework.Model

data class Admin(val AdminId: Int, var AdminFullName: String, var AdminEmail: String,
    val AdminPhoneNo: String, val AdminUserName: String, val AdminPassword: String, val AdminIsActive: Int) {

    override fun toString(): String {
        return "Admin(fullName='$AdminFullName', email='$AdminEmail', phoneNo='$AdminPhoneNo', userName='$AdminUserName, " +
                "password='$AdminPassword', isActive=$AdminIsActive)"
    }

}


