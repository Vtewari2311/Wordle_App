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
        
        fun markButtonDisable(button: Button) {
            button?.isEnabled = false
            button.isClickable = false
            button?.setTextColor(ContextCompat.getColor(textView.context, R.color.white))
            button?.setBackgroundColor(ContextCompat.getColor(textView.context, R.color.greyish))
        }
        
        setContentView(R.layout.activity_main)
        
        var editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)
        val buttonRestart = findViewbyId<Button>(R.id.guess)
        val word = findViewById<TextView>(R.id.word)
        val textView = findViewById<Button>(R.id.textView)
        val textViewforGuess1 = findViewById<TextView>(R.id.FirstGuess)
        val textViewforGuess2 = findViewById<TextView>(R.id.SecondGuess)
        val textViewforGuess3 = findViewById<TextView>(R.id.ThirdGuess)
        val textViewGuessOutput1 = findViewById<TextView>(R.id.FirstGuessOutput)
        val textViewGuessOutput2 = findViewById<TextView>(R.id.SecondGuessOutput)
        val textViewGuessOutput3 = findViewById<TextView>(R.id.ThirdGuessOutput)
        val checkViewforGuess1 = findViewById<TextView>(R.id.FirstGuessCheck)
        val checkViewforGuess2 = findViewById<TextView>(R.id.SecondGuessCheck)
        val checkViewforGuess3 = findViewById<TextView>(R.id.ThirdGuessCheck)
        
        var counter = 0
        word.visibility = View.GONE
        
        button.setOnClickListener {
            Toast.makeText(it.context, "Clicked Button!", Toast.LENGTH_SHORT).show()
            it.hideKeyboard()
            counter++
            if(counter == 1) {
                textViewGuessOutput1.text = editText.text.toString().uppercase()
                var guess1 = checkGuess(textViewGuessOutput1.text)
                it.hideKeyBoard()
                checkViewforGuess1.text = guess1
                editText.getText().clear()
            }
            else if(counter == 2) {
                textViewGuessOutput2.text = editText.text.toString().uppercase()
                var guess2 = checkGuess(textViewGuessOutput2.text)
                it.hideKeyBoard()
                checkViewforGuess1.text = guess1
                editText.getText().clear()
            }
            else if(counter == 3) {
                textViewGuessOutput1.text = editText.text.toString().uppercase()
                var guess3 = checkGuess(textViewGuessOutput3.text)
                it.hideKeyBoard()
                checkViewforGuess3.text = guess3
                editText.getText().clear()
            }
            buttonRestart.visibility = markButtonDisable(buttonRestart)
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
