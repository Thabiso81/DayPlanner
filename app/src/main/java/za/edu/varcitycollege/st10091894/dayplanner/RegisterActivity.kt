package za.edu.varcitycollege.st10091894.dayplanner


import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var backToLoginLink: TextView
    private lateinit var btnRegister: Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)


        username = findViewById<EditText>(R.id.edtPersonEmail)
        password = findViewById<EditText>(R.id.edtPassword)
        confirmPassword = findViewById<EditText>(R.id.edtConfirmPassword)
        backToLoginLink = findViewById<TextView>(R.id.goToRegister)
        btnRegister = findViewById<Button>(R.id.btnRegister)
        auth = Firebase.auth

        //register the user
        btnRegister.setOnClickListener(){
            if (inputValid(username, password, confirmPassword)){

                val email: String = username.text.toString().trim(){it <= ' '}
                val password: String = password.text.toString().trim(){it <= ' '}
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val user = auth.currentUser
                            val userIsRegistered = true
                            val goToLogin = Intent(this, LoginActivity::class.java)



                            goToLogin.putExtra("username", email)
                            goToLogin.putExtra("password", password)
                            goToLogin.putExtra("hasRegistered", userIsRegistered)
                            startActivity(goToLogin)
                            finish()
                            Toast.makeText(
                                baseContext,
                                "Registration successful",
                                Toast.LENGTH_SHORT,
                            ).show()

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext,
                                "${task.exception}",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }


            }
        }

        //take user back to Login
        backToLoginLink.setOnClickListener(){
            val backToLogin = Intent(this, LoginActivity::class.java)

            startActivity(backToLogin)
            finish()
        }


    }

    private fun inputValid(email: EditText, password: EditText, confirmPassword: EditText): Boolean{
        var isValid = true
        if (email.text.toString().isEmpty()){
            isValid = false
            email.error = "Please enter an email"
        }
        if (password.text.toString().isEmpty()){
            isValid = false
            password.error = "Please enter a password"
        }
        if (confirmPassword.text.toString().isEmpty()){
            isValid = false
            confirmPassword.error = "please confirm your password"
        }
        if (confirmPassword.text.toString() != password.text.toString()){
            isValid = false
            confirmPassword.error = "passwords do not match"
        }


        return isValid
    }
}