package com.example.projectmanagementsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.util.*

class RegisterActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener {

    lateinit var edtFirstName : EditText
    lateinit var edtLastName : EditText
    lateinit var edtEmailId : EditText
    lateinit var edtPhoneNo : EditText
    lateinit var edtPassword : EditText
    lateinit var spinRole : Spinner
    lateinit var btnRegister : Button
    var role:String = "emp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initVar()

        btnRegister.setOnClickListener(View.OnClickListener {
            val apiService = RegisterApiService.create()
            val compositeDisposable = CompositeDisposable()
            val userObservable =
                apiService.registerUser(edtFirstName.text.toString(),edtLastName.text.toString(),edtEmailId.text.toString(),edtPhoneNo.text.toString(),500,role)
                    .subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe({ result -> registerSuccess(result) }, { error -> showError(error.message) })
            compositeDisposable.add(userObservable)
        })
    }

    private fun registerSuccess(response: String) {
        Toast.makeText(this,response,Toast.LENGTH_LONG).show()
    }
    private fun showError(message: String?){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    private fun initVar() {
        edtFirstName = findViewById(R.id.edit_first_name)
        edtLastName = findViewById(R.id.edit_last_name)
        edtEmailId = findViewById(R.id.edit_email_id)
        edtPhoneNo = findViewById(R.id.edit_mobile_no)
        edtPassword = findViewById(R.id.edit_password)
        spinRole = findViewById(R.id.spinner_role)
        btnRegister = findViewById(R.id.button_register)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(this, R.array.roles, android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinRole.adapter = adapter
            }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(this,"You must select a role",Toast.LENGTH_LONG).show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        role = parent?.getItemAtPosition(position).toString()
        when(role){
            "Employee" -> role = "user"
            "Manager" -> role = "TL"
            "Admin" -> role = "admin"
        }
    }
}