package com.example.projectmanagementsystem

data class RegisterDetails (val first_name:String,val last_name:String,val email_id:String,val phone:String,val password:String,val company_size:Int,val your_role:String)
/*
    @SerializedName("first_name") val name : String,
    @SerializedName("last_name") val username : String,
    @SerializedName("email_id") val email : String,
    @SerializedName("phone") val phone : String,
    @SerializedName("password") val password : String,
    @SerializedName("company_size") val size : Int,
    @SerializedName("your_role") val role : String*/