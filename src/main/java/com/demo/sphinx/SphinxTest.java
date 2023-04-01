package com.demo.sphinx;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;

import java.io.IOException;

public class SphinxTest {

    public static void main(String[] args) throws IOException {

        // Set up configuration for Sphinx4
        Configuration configuration = new Configuration();
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

        //Decode audio in telephone quality with a sample rate of 8000 Hz
        //configuration.setSampleRate(8000);

        // Create a live speech recognizer with the configured settings
        LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
        recognizer.startRecognition(true);

        // Loop indefinitely, capturing voice input and processing commands
        while (true) {

            // Capture the next utterance from the user
            System.out.println("Say something...");
            String utterance = recognizer.getResult().getHypothesis();

            // Process the utterance and perform the appropriate action
            if (utterance.equals("open file")) {
                // Code to open a file goes here
                System.out.println("Opening file...");
            } else if (utterance.equals("save document")) {
                // Code to save a document goes here
                System.out.println("Saving document...");
            } else if (utterance.equals("play music")) {
                // Code to play music goes here
                System.out.println("Playing music...");
            } else {
                // Command not recognized - provide feedback to the user
                System.out.println("Command recognized: " + utterance);
            }

        }

    }

}
