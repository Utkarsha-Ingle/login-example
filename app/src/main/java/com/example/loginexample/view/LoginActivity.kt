package com.example.loginexample.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.loginexample.R
import com.example.loginexample.databinding.ActivityLoginBinding
import com.example.loginexample.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private var loginViewModel: LoginViewModel? = null
    private var binding: ActivityLoginBinding? = null
    private var signup:Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        loginViewModel = ViewModelProviders.of(this, LoginViewModel.Factory(applicationContext))
            .get(LoginViewModel::class.java)
        binding?.loginViewModel = loginViewModel

        setListeners()
    }

    private fun setListeners() {
        binding?.bCreateUser?.setOnClickListener(this)
        binding?.bSubmit?.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.bSubmit -> {
                val isValidUser = loginViewModel?.checkValidLogin()
                isValidUser?.let {
                    if (it) {
                        Toast.makeText(this, "Successfully Logged In!", Toast.LENGTH_LONG).show()
                        val homeIntent = Intent(this, HomeActivity::class.java)
                        startActivity(homeIntent)
                    } else {
                        Toast.makeText(this, "Invalid Login!", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            R.id.bCreateUser -> {
                if(!TextUtils.isEmpty(binding?.etUserId?.text.toString()) && !TextUtils.isEmpty(binding?.etPassword?.text.toString())) {
                    loginViewModel?.createUser(
                        binding?.etUserId?.text.toString(),
                        binding?.etPassword?.text.toString()
                    )
                    Toast.makeText(this, "User created successfully!", Toast.LENGTH_LONG)
                        .show()
                }
                else{
                    Toast.makeText(this, "User Id/Password cannot be empty", Toast.LENGTH_LONG)
                        .show()
                }

            }
        }
    }
}