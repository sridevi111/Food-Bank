package com.foodbank.app.ui.auth

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.foodbank.app.R
import com.foodbank.app.utils.FBUtils
import com.foodbank.app.utils.FireBaseManager
import com.foodbank.app.utils.Loader
import com.foodbank.app.utils.LocationHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private lateinit var locationHelper: LocationHelper

    private lateinit var etEmail: TextView
    private lateinit var etPassword: EditText
    private lateinit var etUserName: TextView
    private lateinit var etRepeatPassword: EditText
    private lateinit var restaurantName: TextView
    private lateinit var address: TextView

    private lateinit var radioGroup: RadioGroup
    private lateinit var radioDonner: RadioButton
    private lateinit var radioReceiver: RadioButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        findViewById<View>(R.id.ivBack).setOnClickListener { finish() }
        findViewById<View>(R.id.llLogin).setOnClickListener { onClickOnLogin() }
        findViewById<View>(R.id.btnLogin).setOnClickListener {


            GlobalScope.launch(Dispatchers.Main) {
                onClickOnSignup()
            }

        }
        findViewById<ImageView>(R.id.ivShowPassword).setOnClickListener {
            onClickOnShowPassword(
                etPassword,
                it as ImageView
            )
        }
        findViewById<ImageView>(R.id.ivShowRepeatPassword).setOnClickListener {
            onClickOnShowPassword(
                etRepeatPassword,
                it as ImageView
            )
        }

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etRepeatPassword = findViewById(R.id.etRepeatPassword)
        etUserName = findViewById(R.id.etUserName)
        restaurantName = findViewById(R.id.restaurantName)
        address = findViewById(R.id.address)

        radioGroup = findViewById(R.id.radio_group)
        radioDonner = findViewById(R.id.radio_donner)
        radioReceiver = findViewById(R.id.radio_receiver)


        radioDonner.isChecked = true

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_donner -> {
                    restaurantName.visibility  = View.VISIBLE
                }
                R.id.radio_receiver -> {
                    restaurantName.visibility  = View.GONE
                }
            }
        }

        val colorStateList = ColorStateList.valueOf(Color.parseColor("#F1947C"))
        radioDonner.buttonTintList = colorStateList
        radioReceiver.buttonTintList = colorStateList
    }

    private fun onClickOnShowPassword(editText: EditText, imageView: ImageView) {
        if (editText.inputType == 129) {
            editText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            imageView.setImageDrawable(resources.getDrawable(R.drawable.ic_hide_password))
        } else {
            editText.inputType = 129
            imageView.setImageDrawable(resources.getDrawable(R.drawable.ic_show_password))
        }
        editText.setSelection(editText.length())
    }

    private fun onClickOnLogin() {
        finish()
    }

    private suspend fun onClickOnSignup() {

        if (etUserName.text.isEmpty()) {
            Toast.makeText(this, "Enter Valid Name", Toast.LENGTH_LONG).show()
            return
        }

        if (!FBUtils.isValidEmail(etEmail.text.toString())) {
            Toast.makeText(this, "Enter Valid Email Address", Toast.LENGTH_LONG).show()
            return
        }

        if (etPassword.text.length <= 3 || etPassword.text.length >= 16) {
            Toast.makeText(
                this,
                "Password length should be minimum 4 and maximum 16 characters long",
                Toast.LENGTH_LONG
            ).show()
            return
        }
//        if (!FBUtils.isValidPassword(etPassword.text.toString())) {
//            Toast.makeText(
//                this,
//                "Password should contains at least one uppercase, lowercase, special character and number",
//                Toast.LENGTH_LONG
//            ).show()
//            return
//        }

        if (etPassword.text.toString() != etRepeatPassword.text.toString()) {
            Toast.makeText(this, "Password and Confirm password should match", Toast.LENGTH_LONG)
                .show()
            return
        }

        if(restaurantName.visibility  != View.GONE) {

            if (restaurantName.text.toString() == "" || restaurantName.text.isEmpty()) {
                Toast.makeText(this, "Password enter restaurant name", Toast.LENGTH_LONG)
                    .show()
                return
            }

        }


        createUser();


//
//        if (address.text.isEmpty() || address.text == "Add Your Location") {
//            Toast.makeText(this, "Please add Address", Toast.LENGTH_LONG).show()
//            return
//        }


    }

    private suspend fun createUser() {

        val name = etUserName.text.toString()
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        var userType = "Donner"
        var resName = restaurantName.text.toString()


        userType = if(restaurantName.visibility  != View.GONE) {
          "Donner"

        }else {
            "Receiver"
        }

        FireBaseManager.getInstance().creteAccount(this,name,email,password,userType,resName)


    }

    fun onAddLocation(view: View) {
        locationHelper = LocationHelper(this)

        Loader.getInstance().showLoading(this)

        locationHelper.getAddress { address, lat, lng ->

            Loader.getInstance().hideLoading()

            Log.e("ADDDDDresss" , address);

        }


    }

}