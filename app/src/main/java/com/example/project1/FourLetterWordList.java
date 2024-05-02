package com.example.project1;

import java.util.Random;

public class FourLetterWordList {
    private static final String[] words = {"star", "dogs", "most", "book"};

    public static String getRandomFourLetterWord() {
        Random random = new Random();
        int index = random.nextInt(words.length);
        return words[index];
    }
}