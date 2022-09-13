package com.example.wordleapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private var wordToGuess : String = FourLetterWordList.getRandomFourLetterWord()
    var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                
        fun View.hideKeyboard() {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(windowToken, 0)
        }
        
        setContentView(R.layout.activity_main)
        
        var editText = findViewById<EditText>(R.id.keyboard)
        val buttonRestart = findViewById<Button>(R.id.guess)
        val word = findViewById<TextView>(R.id.word)
        val textViewGuessOutput1 = findViewById<TextView>(R.id.FirstGuessOutput)
        val textViewGuessOutput2 = findViewById<TextView>(R.id.SecondGuessOutput)
        val textViewGuessOutput3 = findViewById<TextView>(R.id.ThirdGuessOutput)
        val checkViewForGuessOutput1 = findViewById<TextView>(R.id.FirstGuessOutputCheck)
        val checkViewForGuessOutput2 = findViewById<TextView>(R.id.SecondGuessOutputCheck)
        val checkViewForGuessOutput3 = findViewById<TextView>(R.id.ThirdGuessOutputCheck)

        fun markButtonDisable(button: Button) {
            button.isEnabled = false
            button.isClickable = false
            button.setTextColor(ContextCompat.getColor(buttonRestart.context, R.color.white))
            button.setBackgroundColor(ContextCompat.getColor(buttonRestart.context, R.color.grey))
        }

        var counter = 0
        word.visibility = View.GONE
        
        buttonRestart.setOnClickListener {
            Toast.makeText(it.context, "Clicked Button!", Toast.LENGTH_SHORT).show()
            it.hideKeyboard()
            counter++
            when (counter) {
                1 -> {
                    textViewGuessOutput1.text = editText.text.toString().uppercase()
                    var guess1 = checkGuess(textViewGuessOutput1.text as String)
                    it.hideKeyboard()
                    checkViewForGuessOutput1.text = guess1
                    editText.text.clear()
                }
                2 -> {
                    textViewGuessOutput2.text = editText.text.toString().uppercase()
                    var guess2 = checkGuess(textViewGuessOutput2.text as String)
                    it.hideKeyboard()
                    checkViewForGuessOutput2.text = guess2
                    editText.text.clear()
                }
                3 -> {
                    textViewGuessOutput3.text = editText.text.toString().uppercase()
                    var guess3 = checkGuess(textViewGuessOutput3.text as String)
                    it.hideKeyboard()
                    checkViewForGuessOutput3.text = guess3
                    editText.text.clear()
                }
            }
            markButtonDisable(buttonRestart)
        }
        
    }
    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            result += if (guess[i] == wordToGuess[i]) {
                "O"
            } else if (guess[i] in wordToGuess) {
                "+"
            } else {
                "X"
            }
        }
        return result
    }
}
