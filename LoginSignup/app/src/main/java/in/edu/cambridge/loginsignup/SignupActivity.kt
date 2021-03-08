package `in`.edu.cambridge.loginsignup

import `in`.edu.cambridge.loginsignup.data.AppDatabase
import `in`.edu.cambridge.loginsignup.data.User
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import java.util.regex.Matcher
import java.util.regex.Pattern

class SignupActivity : AppCompatActivity() {

    private lateinit var signUpUsername: EditText
    private lateinit var signUpPassword: EditText
    private lateinit var signUpBtn: Button
    private lateinit var signUpToLogIn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        supportActionBar?.setTitle("Sign Up")

        signUpUsername = findViewById(R.id.signUp_username)
        signUpPassword = findViewById(R.id.signUp_password)
        signUpToLogIn = findViewById(R.id.signUp_to_logIn)
        signUpBtn = findViewById(R.id.signUpButton)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "app-database"
        ).allowMainThreadQueries().build()

        val userDao = db.userDao()

        signUpBtn.setOnClickListener {

            if(signUpPassword.text.toString().length<8 && !isValidPassword(signUpPassword.text.toString())){
                Toast.makeText(baseContext,"Invalid Password", Toast.LENGTH_SHORT).show()
            }else{
                val newUser = User(signUpUsername.text.toString(), signUpPassword.text.toString())
                userDao.insert(newUser)
                Toast.makeText(baseContext,"Sign Up Sucessful", Toast.LENGTH_SHORT).show()
            }


        }

        signUpToLogIn.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }


   /*  */


    fun isValidPassword(password: String?): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        matcher = pattern.matcher(password)
        return matcher.matches()
    }

}