package com.example.wordleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val upgradeButton = findViewById<Button>(R.id.button)
        val word = findViewById<TextView>(R.id.textView)
        button.setOnClickListener {
            Toast.makeText(it.context, "Clicked Button!", Toast.LENGTH_SHORT).show()
            counter++
            if(counter == 1) {
                FirstGuess.text = enterWord.text
            }
            if(counter == 2) {
                SecondGuess.text = enterWord.text
            }
            if(counter == 2) {
                ThirdGuess.text = enterWord.text
            }
            if(counter == 2) {
                word.text = wordToGuess
                buttonRestart.visibility = View.VISIBLE
            }
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
    private val wordToGuess = Array<Char>(4)
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
