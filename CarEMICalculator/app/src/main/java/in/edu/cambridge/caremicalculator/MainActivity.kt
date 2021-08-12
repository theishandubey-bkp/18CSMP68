package `in`.edu.cambridge.caremicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.Math.pow
class MainActivity : AppCompatActivity() {

    private lateinit var resultText: TextView
    private lateinit var principalAmount : EditText
    private lateinit var downPayment : EditText
    private lateinit var interestRate : EditText
    private lateinit var loanTerm : EditText
    private lateinit var calculateBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultText = findViewById(R.id.resultTextView)
        principalAmount = findViewById(R.id.principalAmount)
        downPayment = findViewById(R.id.downPayment)
        interestRate = findViewById(R.id.interestRate)
        loanTerm = findViewById(R.id.loanTerm)
        calculateBtn = findViewById(R.id.calculateEMIButton)

        calculateBtn.setOnClickListener {
            var emi : Int
            var p : Int
            var principal = principalAmount.text.toString().toInt()
            var r = interestRate.text.toString().toInt()
            var n = loanTerm.text.toString().toInt()
            var downPaymentAmt =downPayment.text.toString().toInt()
            p = if ((downPaymentAmt != 0)){
                principal - downPaymentAmt
            } else{
                principal
            }
            emi = (p*(r*pow(((1+r).toDouble()), n.toDouble())) /(pow(((1+r).toDouble()), n.toDouble()) -1)).toInt()
            resultText.text = emi.toString()
        }
    }
}