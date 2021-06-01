package com.plete.otokko.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.plete.otokko.MainActivity
import com.plete.otokko.R
import com.plete.otokko.api.ApiClient
import com.plete.otokko.api.apiInterface
import com.plete.otokko.model.DefaultResponse
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar!!.setTitle("Register Now")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        btnSignUp.setOnClickListener {
            register()
        }
    }

    fun register(){
        val name = etFullName.text.toString()
        val email = etEmail.text.toString()
        val pass = etPassword.text.toString()
        val repass = etRepass.text.toString()

        if(name.isEmpty() || email.isEmpty() || pass.isEmpty() || repass.isEmpty()){
            Toast.makeText(
                this, "Please complete the Field first!", Toast.LENGTH_LONG
            ).show()
        }
        if (repass != pass){
            Toast.makeText(
                this, "Password doesn't match!", Toast.LENGTH_LONG
            ).show()
        }

        var apiService: apiInterface = ApiClient().getApiClient()!!.create(apiInterface::class.java)
        var requestCall: Call<DefaultResponse> = apiService.register(name, email, pass, repass)

        requestCall.enqueue(
            object : Callback<DefaultResponse>{
                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity,
                    "Register Failed!", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<DefaultResponse>,
                    response: Response<DefaultResponse>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(this@RegisterActivity,
                        "Register Successful!", Toast.LENGTH_LONG).show()
                        Log.d("log" , response.body()?.success.toString())
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@RegisterActivity,
                        "Register Failed!", Toast.LENGTH_LONG).show()
                    }
                }
            }
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}