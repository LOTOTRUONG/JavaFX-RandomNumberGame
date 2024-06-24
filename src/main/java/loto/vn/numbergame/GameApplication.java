package loto.vn.numbergame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class GameApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Font.loadFont(getClass().getResourceAsStream("loto/vn/numbergame/fonts/Lato-Regular.ttf"), 12);
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("interfaceGame.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        scene.getStylesheets().add(getClass().getResource("gameCSS.css").toExternalForm());
        stage.setTitle("Number Game!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}