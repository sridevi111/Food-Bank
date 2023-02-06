package com.foodbank.app.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.foodbank.app.R
import com.foodbank.app.utils.FBUtils
import com.foodbank.app.utils.FireBaseManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText

    private lateinit var etPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<View>(R.id.rlSignUp).setOnClickListener { onClickOnSignUp() }
        findViewById<View>(R.id.btnLogin).setOnClickListener {

            GlobalScope.launch(Dispatchers.Main) {
                onClickOnLogin()
            }
        }
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        findViewById<View>(R.id.btnForgotPassword).setOnClickListener { onClickOnResetPassword() }
        findViewById<View>(R.id.ivShowPassword).setOnClickListener { onClickOnShowPassword(it) }
    }

    private fun onClickOnSignUp() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    private fun onClickOnShowPassword(view: View) {
        val imageView = view as ImageView
        if (etPassword.inputType == 129) {
            etPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            imageView.setImageDrawable(resources.getDrawable(R.drawable.ic_hide_password, null))
        } else {
            etPassword.inputType = 129
            imageView.setImageDrawable(resources.getDrawable(R.drawable.ic_show_password, null))
        }
        etPassword.setSelection(etPassword.length())
    }

    private suspend fun onClickOnLogin() {
        if (!FBUtils.isValidEmail(etEmail.text.toString())) {
            Toast.makeText(this, "Enter a valid Email Address", Toast.LENGTH_LONG).show()
            return
        }

        if (etPassword.text.toString().length < 4) {
            Toast.makeText(
                this,
                "Password should contains at least one uppercase, lowercase, special character and number",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        FireBaseManager.getInstance().login(this, etEmail.text.toString().lowercase(),etPassword.text.toString());
    }

    private fun onClickOnResetPassword() {
        //startActivity(Intent(this, ForgotPasswordActivity::class.java))
    }

}