package com.example.myapplication.presenter

import com.example.myapplication.contract.PasswordStrengthContract
import com.example.myapplication.model.PasswordStrengthChecker

class PasswordStrengthPresenter(private val view: PasswordStrengthContract.View) : PasswordStrengthContract.Presenter {

    private val passwordStrengthChecker = PasswordStrengthChecker()

    override fun onPasswordChanged(password: String) {

        val strength = passwordStrengthChecker.calculateStrength(password)
        view.updatePasswordStrength(strength)

    }

}