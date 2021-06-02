package com.plete.otokko.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.plete.otokko.MainActivity
import com.plete.otokko.R
import com.plete.otokko.api.ApiClient
import com.plete.otokko.api.ApiInterface
import com.plete.otokko.model.LoginResponse
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnSignIn.setOnClickListener {
            login()
        }

        tvRegis.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    fun login(){
        val email = etEmail.text.toString()
        val pass = etPassword.text.toString()

        if (email.isEmpty() || pass.isEmpty()){
            Toast.makeText(this,
            "Field cannnot be Empty!", Toast.LENGTH_LONG).show()
        }

        var apiService: ApiInterface = ApiClient().getApiClient()!!.create(ApiInterface::class.java)
        var requestCall: Call<LoginResponse> = apiService.login(email, pass)

        requestCall.enqueue(
            object : Callback<LoginResponse>{
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@LoginActivity,
                    "Login Failed", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(this@LoginActivity,
                        "Login Successful", Toast.LENGTH_SHORT).show()
                        Log.d("log" , response.body()?.token.toString())
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    } else{
                        Toast.makeText(this@LoginActivity,
                        "Login Failed", Toast.LENGTH_LONG).show()
                    }
                }

            }
        )
    }
}