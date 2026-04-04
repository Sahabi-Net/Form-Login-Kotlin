package com.example.formlogin

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var signupTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupSignupLink()
        setupLoginButton()
    }

    private fun initViews() {
        usernameEditText = findViewById(R.id.Username)
        passwordEditText = findViewById(R.id.Password)
        loginButton = findViewById(R.id.LoginButton)
        signupTextView = findViewById(R.id.signup)
    }

    private fun setupLoginButton() {
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (validateInput(username, password)) {
                performLogin(username, password)
            }
        }
    }

    private fun validateInput(username: String, password: String): Boolean {
        if (username.isEmpty()) {
            usernameEditText.error = "Username tidak boleh kosong"
            usernameEditText.requestFocus()
            return false
        }

        if (password.isEmpty()) {
            passwordEditText.error = "Password tidak boleh kosong"
            passwordEditText.requestFocus()
            return false
        }

        if (password.length < 6) {
            passwordEditText.error = "Password minimal 6 karakter"
            passwordEditText.requestFocus()
            return false
        }

        return true
    }

    private fun performLogin(username: String, password: String) {
        if (username == "admin" && password == "123456") {
            Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Username atau password salah", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupSignupLink() {
        val fullText = "Apakah Sudah Memiliki Akun ? Buat Akun"
        val spannableString = SpannableString(fullText)

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(this@MainActivity, "Navigasi ke halaman Buat Akun", Toast.LENGTH_SHORT).show()
            }
        }

        val startIndex = fullText.indexOf("Buat Akun")
        val endIndex = startIndex + "Buat Akun".length
        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        signupTextView.text = spannableString
        signupTextView.movementMethod = LinkMovementMethod.getInstance()
    }
}