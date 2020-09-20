package Main;

import MySQL.ConnMysql;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TablePrint extends Application {


    public TablePrint(int userId) {
        Connection conn = new ConnMysql().connect();
        Statement stmt = null;
        String searchDataBase = "SELECT theme FROM FILE WHERE sid = " + userId;
        try {
            stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(searchDataBase);
            while (rs.next()) {
                DiaryName diaryName = new DiaryName(rs.getString("theme"));
                data.add(diaryName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private javafx.scene.control.TableView<DiaryName> table = new javafx.scene.control.TableView<DiaryName>();
    private final ObservableList<DiaryName> data = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(250);
        stage.setHeight(500);

        final Label label = new Label("Diary Name");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn firstNameCol = new TableColumn("Diary Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<DiaryName, String>("diarytheme"));


        table.setItems(data);
        table.getColumns().addAll(firstNameCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

    public static class DiaryName {

        private final SimpleStringProperty diarytheme;

        private DiaryName(String fName) {
            this.diarytheme = new SimpleStringProperty(fName);
        }

        public String getDiarytheme() {
            return diarytheme.get();
        }

        public void setDiarytheme(String fName) {
            diarytheme.set(fName);
        }

    }
} 