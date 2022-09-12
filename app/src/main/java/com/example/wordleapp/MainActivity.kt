package com.example.wordleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    val wordToGuess = Array<Char>(4)
    wordToGuess = getRandomFourLetterWord()
    var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                
        funView.hideKeyboard() {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(windowToken, 0)
        }
        
        setContentView(R.layout.activity_main)
        
        var editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)
        val textView = findViewById<Button>(R.id.textView)
        val textViewforGuess1 = findViewById<TextView>(R.id.textView)
        val textViewforGuess2 = findViewById<TextView>(R.id.textView)
        val textViewforGuess3 = findViewById<TextView>(R.id.textView)
        val textViewOutput1 = findViewById<TextView>(R.id.textView)
        val textViewGuessOutput2 = findViewById<TextView>(R.id.textView)
        val textViewGuessOutput3 = findViewById<TextView>(R.id.textView)
        val checkViewforGuess1 = findViewById<TextView>(R.id.textView)
        val checkViewforGuess2 = findViewById<TextView>(R.id.textView)
        val checkViewforGuess3 = findViewById<TextView>(R.id.textView)
        val word = findViewById<TextView>(R.id.word)
        
        var counter = 0
        word.visibility = View.GONE
        
        button.setOnClickListener {
            Toast.makeText(it.context, "Clicked Button!", Toast.LENGTH_SHORT).show()
            it.hideKeyboard()
            counter++
            if(counter == 1) {
                FirstGuess.text = editText.text.toString()
                val guess1 = checkGuess(FirstGuess.text)
                it.hideKeyBoard()
                checkViewforGuess1.text = guess1
            }
            else if(counter == 2) {
                SecondGuess.text = editText.text.toString()
                val guess2 = checkGuess(SecondGuess.text)
                it.hideKeyBoard()
                checkViewforGuess2.text = guess2
            }
            else if(counter == 3) {
                ThirdGuess.text = editText.text.toString()
                val guess3 = checkGuess(ThirdGuess.text)
                it.hideKeyBoard()
                checkViewforGuess3.text = guess3
            }
            buttonRestart.visibility = View.VISIBLE
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
