package com.foodbank.app.utils

import android.content.Context
import android.content.Intent
import android.util.Log
import com.foodbank.app.SplashScreen
import com.foodbank.app.models.AllModels


class PreferenceManager private constructor(private val context: Context){
    companion object{
          var instance: PreferenceManager? = null

        fun getInstance(context: Context): PreferenceManager{
            if(instance == null){
                instance = PreferenceManager(context)
            }
            return instance as PreferenceManager
        }
    }

    private val prefs = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)

    fun restart() {
        val intent = Intent(context, SplashScreen::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        context.startActivity(intent)
    }

    fun saveUser(documentId:String,user: AllModels.User) {
        val prefsEditor = prefs.edit()
        prefsEditor.putString("email", user.userName)
        prefsEditor.putString("userName", user.email)
        prefsEditor.putString("password", user.password)
        prefsEditor.putString("userType", user.userType)
        prefsEditor.putString("restaurantName", user.restaurantName)
        prefsEditor.putString("documentId", documentId)
        prefsEditor.apply()
    }
    fun isLoggedIn(): Boolean? {
        return getEmail()?.let { it.isNotEmpty() } ?: false
    }

    fun isDonner(): Boolean? {
        Log.e("JJJJ", prefs.getString("userType",null).toString());

        return prefs.getString("userType", null) == "Donner"

    }


    fun getEmail(): String? {
        return prefs.getString("email", null)
    }
    fun getName(): String? {
        return prefs.getString("userName", null)
    }

    fun getUserType(): String? {
        return prefs.getString("userType", null)
    }


    fun clearUserDefaults() {
        prefs.edit().clear().apply()
    }
}

//class PreferenceManager private constructor() {
//
//
//    companion object{
//        private var instance: PreferenceManager? = null
//        val  pref = PreferenceManager(context.getSharedPreferences("prefs", Context.MODE_PRIVATE))
//        fun getInstance(): PreferenceManager{
//            if(instance == null){
//                instance = PreferenceManager()
//            }
//            return instance as PreferenceManager
//        }
//    }
//
//
//
//
////        var instance: PreferenceManager? = null
////        fun getInstance(context: Context): PreferenceManager {
////            if (instance == null) {
////                instance = PreferenceManager(context.getSharedPreferences("prefs", Context.MODE_PRIVATE))
////            }
////            return instance!!
////        }
//
//
//    fun isLoggedIn(): Boolean? {
//        return getEmail()?.let { it.isNotEmpty() } ?: false
//    }
//    fun saveEmail(email: String) {
//        prefs.edit().putString("EMAIL", email).apply()
//    }
//
//    fun getEmail(): String? {
//        return prefs.getString("EMAIL", null)
//    }
//
//    fun saveData(documentID: String, name: String, email: String, userType: String) {
//        prefs.edit()
//            .putString("NAME", name)
//            .putString("EMAIL", email)
//            .putString("USER_TYPE", userType)
//            .putString("DOCUMENT_ID", documentID)
//            .apply()
//    }
//
//    fun clearUserDefaults() {
//        prefs.edit().clear().apply()
//    }
//
//}
