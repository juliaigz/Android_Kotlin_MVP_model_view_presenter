package com.example.myapplication.model

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