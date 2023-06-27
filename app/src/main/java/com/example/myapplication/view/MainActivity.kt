package com.example.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.myapplication.R
import com.example.myapplication.contract.PasswordStrengthContract
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.presenter.PasswordStrengthPresenter


class MainActivity : AppCompatActivity(), PasswordStrengthContract.View {

    private lateinit var presenter: PasswordStrengthContract.Presenter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        presenter = PasswordStrengthPresenter(this)

        binding.passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No se necesita implementar
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val password = s.toString()
                presenter.onPasswordChanged(password)
            }
            override fun afterTextChanged(s: Editable?) {
                // No se necesita implementar
            }
        })

    }

    override fun updatePasswordStrength(strength: Int) {
        val strengthText = when (strength) {
            0 -> "Débil"
            1 -> "Media"
            2 -> "Fuerte"
            else -> "Desconocida"
        }
        binding.strengthTextView.text = "Fortaleza: $strengthText"
    }


}