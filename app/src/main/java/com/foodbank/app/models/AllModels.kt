package com.foodbank.app.models

class AllModels {

    class User(
        var userType: String,
        var email: String,
        var password: String,
        var restaurantName: String,
        var userName: String
    ) {

        constructor() : this("", "", "", "", "")
    }

}