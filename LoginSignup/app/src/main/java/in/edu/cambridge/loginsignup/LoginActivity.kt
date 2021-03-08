package `in`.edu.cambridge.loginsignup

import `in`.edu.cambridge.loginsignup.data.AppDatabase
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room

class LoginActivity : AppCompatActivity() {

    private lateinit var logInUsername: EditText
    private lateinit var logInPassword: EditText
    private lateinit var logInBtn: Button
    private lateinit var logInToSignup: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.setTitle("Log In")

        logInUsername = findViewById(R.id.login_username)
        logInPassword = findViewById(R.id.login_password)
        logInToSignup = findViewById(R.id.login_to_signup)
        logInBtn = findViewById(R.id.logInButton)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "app-database"
        ).allowMainThreadQueries().build()

        val userDao = db.userDao()

        logInBtn.setOnClickListener {

            val tempUser = userDao.getUser(logInUsername.text.toString())

            if(tempUser.password.equals(logInPassword.text.toString())){
                Toast.makeText(baseContext,"Login Sucessful",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(baseContext,"Invalid Username Or Password",Toast.LENGTH_SHORT).show()
            }

        }

        logInToSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}