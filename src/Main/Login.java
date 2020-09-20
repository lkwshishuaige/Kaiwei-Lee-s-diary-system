package Main;

/*
*author kaiweilee
* qq 1120619035
* Personal website www.kaiweilee.top
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login extends Application {
    Stage stage = new Stage();
    @Override
    public void start(Stage primaryStage) {
        try {

            Parent log_in = FXMLLoader.load(getClass().getResource("/UI/log_in.fxml"));
            primaryStage.setTitle("kaiwei lee's Diary System");
            Scene scene = new Scene(log_in);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void restart(){
        start(stage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}