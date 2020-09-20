package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Registration extends Application {
    Stage stage = new Stage();
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent data = FXMLLoader.load(getClass().getResource("/UI/Registration.fxml"));
            primaryStage.setTitle("注册");
            Scene scene = new Scene(data);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
