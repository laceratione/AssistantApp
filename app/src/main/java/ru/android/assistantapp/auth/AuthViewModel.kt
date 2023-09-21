package ru.android.assistantapp.auth

import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {
    private var _login: String = ""

    fun setLogin(login: String) {
        _login = login
    }
}