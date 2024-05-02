package com.example.project1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView targetWordTextView;
    private EditText guessEditText;
    private Button submitButton;
    private TextView guessResultTextView;

    private String targetWord;
    private int remainingGuesses = 3;
    private boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        targetWordTextView = findViewById(R.id.targetWordTextView);
        guessEditText = findViewById(R.id.guessEditText);
        submitButton = findViewById(R.id.submitButton);
        guessResultTextView = findViewById(R.id.guessResultTextView);

        targetWord = FourLetterWordList.getRandomFourLetterWord();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gameOver) {
                    String guess = guessEditText.getText().toString().toLowerCase();
                    String result = checkGuess(guess);
                    guessResultTextView.append("Guess #" + (4 - remainingGuesses) + " Check: " + result + "\n");
                    remainingGuesses--;

                    if (remainingGuesses == 0 || result.equals("OOOO")) {
                        disableInput();
                        gameOver = true;
                        if (result.equals("OOOO")) {
                            targetWordTextView.setText("Congratulations! You've guessed the word: " + targetWord);
                        } else {
                            targetWordTextView.setText("Out of guesses. The word was: " + targetWord);
                        }
                    }
                }
            }
        });
    }

    private String checkGuess(String guess) {
        StringBuilder correctness = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            char guessChar = guess.charAt(i);
            char targetChar = targetWord.charAt(i);
            if (guessChar == targetChar) {
                correctness.append("O");
            } else if (targetWord.contains(String.valueOf(guessChar))) {
                correctness.append("+");
            } else {
                correctness.append("X");
            }
        }
        return correctness.toString();
    }

    private void disableInput() {
        guessEditText.setEnabled(false);
        submitButton.setEnabled(false);
    }
}
