package com.example.loginexample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.loginexample.R
import com.example.loginexample.databinding.ActivityHomeBinding
import com.example.loginexample.databinding.ActivityLoginBinding
import com.example.loginexample.viewmodel.LoginViewModel

class HomeActivity : AppCompatActivity() {
    private var loginViewModel: LoginViewModel? = null
    private var binding: ActivityHomeBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        loginViewModel = ViewModelProviders.of(this, LoginViewModel.Factory(applicationContext))
            .get(LoginViewModel::class.java)
        binding?.loginViewModel = loginViewModel
    }
}