package com.example.wordle

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.wordle.FourLetterWordList.FourLetterWordList.getRandomFourLetterWord
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var guessButton = findViewById<Button>(R.id.button)
        var textInput = findViewById<EditText>(R.id.editTextText)
        var result1 = findViewById<TextView>(R.id.guess1)
        var result2 = findViewById<TextView>(R.id.guess2)
         var result3 = findViewById<TextView>(R.id.guess3)
         var resetButton = findViewById<Button>(R.id.button2)
        var targetWord = findViewById<TextView>(R.id.textView)

        var guessCount= 0
        guessButton.setOnClickListener {

            val guess = textInput.text.toString().uppercase()
            guessCount++
            when (guessCount) {
                1 -> appendColoredText(result1,checkGuess(guess))
                2 -> appendColoredText(result2,checkGuess(guess))
                3 -> appendColoredText(result3, checkGuess(guess))
            }
            if (guessCount >= 3) {
                targetWord.text = "$wordToGuess"
                resetButton.visibility = View.VISIBLE
                guessButton.isEnabled = false
                Toast.makeText(this, "You exceeded the number of guesses!", Toast.LENGTH_SHORT)
                    .show()

                guessButton.setBackgroundColor(Color.GRAY)
            }
    }
        resetButton.setOnClickListener{

            finish();
            startActivity(intent)


        }


    }
    var wordToGuess = getRandomFourLetterWord()
    private fun checkGuess(guess: String) : String {
        val result = StringBuilder()
        for (i in 0..3) {
            val color: String = when{
            guess[i] == wordToGuess[i]
            -> "#00FF00"

           guess[i] in wordToGuess
             ->  "#FFFF00"

            else
                ->"#FF0000"
            }
            result.append("<font color='$color'>${guess[i]}</font>")
        }
        return result.toString()
    }
    private fun appendColoredText(textView: TextView, result: String) {
        val styledResult = android.text.Html.fromHtml(result, android.text.Html.FROM_HTML_MODE_LEGACY)
        textView.append(styledResult)
        textView.append("\n")
    }

}