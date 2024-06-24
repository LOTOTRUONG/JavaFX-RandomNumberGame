package loto.vn.numbergame;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    private Button btnGuess, btnRandom, btnReset;

    @FXML
    private TextField fieldNumberGuess, fieldNumberMax, fieldNumberMin, fieldScore, fieldTimesPlaying;

    @FXML
    private Label labelGame, labelNotification;

    private Integer numberToGuess;
    private Integer attemptsLeft;
    private Integer score;
    private Integer timesPlaying;
    private Integer totalScore;

    private Random random = new Random();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fieldNumberMax.setText("100");
        fieldNumberMin.setText("1");
        timesPlaying = 0;
        btnRandom.setOnAction(event ->  startNewGame());
        btnGuess.setOnAction(event -> {handleGuess();});
        btnReset.setOnAction(event -> resetGame());
        totalScore = 0;
        fieldScore.setText(String.valueOf(totalScore));

    }

    private void startNewGame(){
        try {
            Integer min = Integer.parseInt(fieldNumberMin.getText().trim());
            Integer max = Integer.parseInt(fieldNumberMax.getText().trim());

            if (min >= max) {
                labelNotification.setText("Invalid range: Min must be less than Max.");
                labelNotification.getStyleClass().add("error-message");
                return;
            }

            numberToGuess = random.nextInt(max - min + 1) + min;
            attemptsLeft = 10; // Maximum 10 attempts allowed
            score = 0;
            fieldScore.setText(String.valueOf(totalScore));
            fieldNumberGuess.setEditable(true);
            labelNotification.setText("Guess a number between " + min + " and " + max + "!");
            labelNotification.getStyleClass().remove("error-message");

            btnGuess.setDisable(false);
            btnRandom.setDisable(true);
            btnReset.setDisable(false);
        } catch (NumberFormatException e) {
            labelNotification.setText("Please enter valid numbers for Min and Max.");
            labelNotification.getStyleClass().add("error-message");
        }
    }

    private void handleGuess(){
        try {
            Integer min = Integer.parseInt(fieldNumberMin.getText().trim());
            Integer max = Integer.parseInt(fieldNumberMax.getText().trim());
            Integer guess = Integer.parseInt(fieldNumberGuess.getText().trim());

            if (guess < min || guess > max){
                labelNotification.setText("Please enter a number between " + min + " and " + max);
                labelNotification.getStyleClass().add("error-message");
                return;
            }
            if (guess == numberToGuess) {
                labelNotification.setText("Congratulations! You guessed it!! The number is " + numberToGuess);
                labelNotification.getStyleClass().add("sucess-message");

                score += attemptsLeft;
                totalScore += score;
                fieldScore.setText(totalScore.toString());
                btnGuess.setDisable(true);
                btnRandom.setDisable(false);
                timesPlaying ++;
            } else if (guess < numberToGuess) {
                labelNotification.setText("The number is greater than " + guess);
                labelNotification.getStyleClass().remove("sucess-message");
                labelNotification.getStyleClass().remove("error-message");

            } else {
                labelNotification.setText("The number is less than " + guess);
                labelNotification.getStyleClass().remove("sucess-message");
                labelNotification.getStyleClass().remove("error-message");

            }

            attemptsLeft--;

            if (attemptsLeft == 0 && guess != numberToGuess) {
                labelNotification.setText("Out of attempts. The number was " + numberToGuess);
                labelNotification.getStyleClass().add("error-message");
                btnGuess.setDisable(true);
                btnRandom.setDisable(false);
                timesPlaying ++;
            }
            fieldTimesPlaying.setText(timesPlaying + " times playing game");

        } catch (NumberFormatException e) {
            labelNotification.setText("Please enter a valid number.");
            labelNotification.getStyleClass().add("error-message");

        }
    }

    private void resetGame() {
        btnRandom.setDisable(false);
        btnGuess.setDisable(false);
        fieldNumberGuess.setEditable(false);
        fieldNumberMin.setText("1");
        fieldNumberMax.setText("100");
        labelNotification.setText("Please enter a number, enter it, and presse 'Guess' ");
        labelNotification.getStyleClass().remove("sucess-message");
        labelNotification.getStyleClass().remove("error-message");
        fieldScore.clear();
        fieldNumberGuess.clear();
        fieldTimesPlaying.clear();
    }
}