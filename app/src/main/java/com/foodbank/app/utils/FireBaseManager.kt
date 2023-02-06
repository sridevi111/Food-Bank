package com.foodbank.app.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.foodbank.app.models.AllModels
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FireBaseManager private constructor() {

    private val db = FirebaseFirestore.getInstance()

    companion object {
        private var instance: FireBaseManager? = null

        fun getInstance(): FireBaseManager {
            if (instance == null) {
                instance = FireBaseManager()
            }
            return instance!!
        }
    }

    suspend fun login(context: Context, email: String, password: String) {

        Loader.getInstance().showLoading(context)

        val usersRef = FirebaseFirestore.getInstance().collection("Users")

        val result = usersRef.whereEqualTo("email", email).get().await()

        Loader.getInstance().hideLoading()

        if (result.isEmpty) {
            // Email not found, show error message
            Toast.makeText(context, "Email not found", Toast.LENGTH_SHORT).show()
            return
        }

        val user = result.documents[0].toObject(AllModels.User::class.java)

        if (user?.password != password) {
            // Password doesn't match, show error message
            Toast.makeText(context, "Incorrect password", Toast.LENGTH_SHORT).show()
            return
        }

        // Login success
        Toast.makeText(context, "Login successful" + result.documents[0].id, Toast.LENGTH_SHORT).show()



        PreferenceManager.getInstance(context).saveUser(result.documents[0].id,user);

        PreferenceManager.getInstance(context).restart();

    }




    suspend fun creteAccount(context: Context, userName: String, email: String, password: String, userType:String, restaurantName:String) {

        Loader.getInstance().showLoading(context)

        val usersRef = FirebaseFirestore.getInstance().collection("Users")


        // Check if email already exists
        val emailExists = usersRef.whereEqualTo("email", email.lowercase()).get().await()
        if (!emailExists.isEmpty) {
            Loader.getInstance().hideLoading()
            // Email already exists, show error message
            Toast.makeText(context, "Email already exists", Toast.LENGTH_SHORT).show()
            return
        }
    // Save the data if email doesn't exist
        val data = hashMapOf(
            "userName" to userName,
            "email" to email,
            "password" to password,
            "userType" to userType,
            "restaurantName" to restaurantName
        )

        usersRef.add(data).await()
        Loader.getInstance().hideLoading()
        // Show success message
        Toast.makeText(context, "Account created successfully, Login with same email & password", Toast.LENGTH_SHORT).show()
         (context as Activity).finish()
    }




}
