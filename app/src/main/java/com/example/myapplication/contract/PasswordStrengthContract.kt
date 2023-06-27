package com.example.myapplication.contract

interface PasswordStrengthContract {

    interface View {
        fun updatePasswordStrength(strength: Int)
    }

    interface Presenter {
        fun onPasswordChanged(password: String)
    }


}