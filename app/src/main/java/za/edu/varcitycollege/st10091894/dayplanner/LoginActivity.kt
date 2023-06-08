package za.edu.varcitycollege.st10091894.dayplanner

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var signupLink: TextView
    private lateinit var loginButton: Button


    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        username = findViewById<EditText>(R.id.edtPersonEmail)
        password = findViewById<EditText>(R.id.edtPassword)
        signupLink = findViewById<TextView>(R.id.tvGoToRegister)
        loginButton = findViewById<Button>(R.id.btnLogin)
        auth = Firebase.auth

        val user_id = intent.getStringExtra("user_id")
        val email_id = intent.getStringExtra("email_id")
        val _username = intent.getStringExtra("username")
        val _password = intent.getStringExtra("password")
        val userHasRegistered = intent.getBooleanExtra("hasRegistered", false)

        if(userHasRegistered) {
            username.setText(_username.toString())
            password.setText(_password.toString())
        }
        //navigate to register activity
        signupLink.setOnClickListener{
            val register = Intent(this, RegisterActivity::class.java)
            startActivity(register)
            finish()
        }
        //Sign the user into the app
        loginButton.setOnClickListener(){
            if (inputValid(username, password)){
                val email: String = username.text.toString().trim(){it <= ' '}
                val password: String = password.text.toString().trim(){it <= ' '}

                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            val user = auth.currentUser
                            val mainActivity = Intent(this, MainActivity::class.java)
                            mainActivity.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(mainActivity)
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext,
                                "Username or password is incorrect",
                                Toast.LENGTH_SHORT,
                            ).show()

                        }
                    }

            }



        }
    }

    private fun inputValid(email: EditText, password: EditText): Boolean{
        var isValid = true
        if (email.text.toString().isEmpty()){
            isValid = false
            email.error = "Required"
        }
        if (password.text.toString().isEmpty()){
            isValid = false
            password.error = "Required"
        }



        return isValid
    }
}