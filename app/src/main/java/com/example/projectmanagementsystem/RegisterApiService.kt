package com.example.projectmanagementsystem

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RegisterApiService {
    @FormUrlEncoded
    @POST("pms_reg.php")
    fun registerUser(
        @Field("first_name") first:String,
        @Field("last_name") last:String,
        @Field("email") email:String,
        @Field("mobile") mobile:String,
        @Field("company_size") company_size:Int,
        @Field("your_role") your_role:String
    ) : Observable<String>

    companion object{
        fun create(): RegisterApiService {

            val BASE_URL = "http://rjtmobile.com/aamir/pms/android-app/"
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(RegisterApiService::class.java)
        }
    }
}