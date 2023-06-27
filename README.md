# Android_Kotlin_MVP_model_view_presenter
### This is a project made in android studio, using kotlin,to check the strenght of a password using the Structure of MVP , Model, View, Presenter 

### STEP 1: the design in xml 

![image](https://github.com/juliaigz/Android_Kotlin_MVP_model_view_presenter/assets/40221707/2d40f806-7324-46b1-af81-3b67a4587414)


### STEP 2 : Create te package MODEL and the kotlin class PasswordStrengthChecker


´´´bash

class PasswordStrengthChecker {

    // Lógica para calcular la fortaleza de la contraseña
    // Retorna un valor numérico que representa la fortaleza (por ejemplo, 0 = débil, 1 = media, 2 = fuerte)

    fun calculateStrength(password: String): Int {
        val length = password.length

        if (length < 6) {
            return 0 // Contraseña débil
        }

        var hasUpperCase = false
        var hasLowerCase = false
        var hasNumber = false

        for (char in password) {
            when {
                char.isUpperCase() -> hasUpperCase = true
                char.isLowerCase() -> hasLowerCase = true
                char.isDigit() -> hasNumber = true
            }
        }

        if (hasUpperCase && hasLowerCase && hasNumber) {
            return 2 // Contraseña fuerte
        }

        return 1 // Contraseña media
    }

}

´´´

### STEP 3 : Create te package INTERFACE contract and the kotlin interface PasswordStrengthContract

´´´bash

interface PasswordStrengthContract {
    interface View {
        fun updatePasswordStrength(strength: Int)
    }

    interface Presenter {
        fun onPasswordChanged(password: String)
    }
}

´´´


### STEP 4 : Create te package PRESENTER and the kotlin class PasswordStrengthPresenter that implements the interface

´´´bash

import com.example.myapplication.contract.PasswordStrengthContract
import com.example.myapplication.model.PasswordStrengthChecker

class PasswordStrengthPresenter(private val view: PasswordStrengthContract.View) : PasswordStrengthContract.Presenter {

    private val passwordStrengthChecker = PasswordStrengthChecker()

    override fun onPasswordChanged(password: String) {

        val strength = passwordStrengthChecker.calculateStrength(password)
        view.updatePasswordStrength(strength)

    }
}

´´´

### STEP 5 : Create te package VIEW and the kotlin class MainActivity that implements the interface PasswordStrengthContract.View

´´´bash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

´´´





