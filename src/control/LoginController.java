package control;

import Main.*;
import MySQL.ConnMysql;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import user.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class LoginController {
    public Label userIden;
    public Label S_J_num;
    public TextField id;
    public Label errorId;
    public PasswordField password;
    public Button loginButton;
    public javafx.scene.control.TableView tableView;
    public TableColumn tableCol;
    public TextField viewName;
    public Button logoutButton;
    public TextField diaryDate;
    public TextField diaryTheme;
    public TextField diaryWeather;
    public TextArea diaryContent;
    public Label savedSuccessfully;
    public TextField newPassword;
    public Button buttonPassword;
    public TextField registrationId;
    public PasswordField registrationPassword;
    public TextField registrationName;
    public Label registrationError;
    public Button buttonRegistration;
    public Label registrationTrue;
    public Label welcomeName;


    User user = new User();
    User.LogUser logUser;
    Connection conn = ConnMysql.connect();
    private final String filePath = null;

//    public void ClickOne(ActionEvent event) {
//        user.setUserIden(1);
//        System.out.println(user.getUserIden());
//        userIden.setText("学生");
//        S_J_num.setText("学号：");
//        errorId.setText("");
//    }

    public void ClickLogin(ActionEvent event) {
        String logId = id.getText();
        String logpassword = password.getText();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT sid,password FROM user WHERE sid = " + logId + " AND password = '" + logpassword + "'");
            if (rs.next()) {
                logUser = new User.LogUser(Integer.valueOf(logId));
                System.out.println(rs.getString("sid"));
                System.out.println(rs.getString("password"));
                System.out.println("查询成功");
                Stage primaryStage = (Stage) loginButton.getScene().getWindow();//将submit(登录按钮)与Main类中的primaryStage(新窗口)绑定 并执行close()
                primaryStage.close();//打开新的窗口 所以要关闭当前的窗口
                Logged we = new Logged();//新窗口类
                Stage stage = new Stage();
                we.start(stage);//打开新窗口.
            } else {
                errorId.setText("登录失败，请检查账号和密码！");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void ClickExit(ActionEvent event) {
        System.exit(0);
    }


    public void ClickOut(ActionEvent event) {

        Stage primaryStage = (Stage) logoutButton.getScene().getWindow();//将submit(登录按钮)与Main类中的primaryStage(新窗口)绑定 并执行close()
        primaryStage.close();//打开新的窗口 所以要关闭当前的窗口
        //mStage.close();
        Login we = new Login();//新窗口类
        Stage stage = new Stage();
        try {
            we.start(stage);//打开新窗口
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void ClickSave(ActionEvent event) {
        String data = "<date>" + diaryDate.getText() + "<-date>\n";
        String theme = "<theme>" + diaryTheme.getText() + "<-theme>\n";
        String weather = "<weather>" + diaryWeather.getText() + "<-weather>\n";
        String content = "<content>" + diaryContent.getText() + "<-content>\n";
        String diary = data + theme + weather + content;
        String filePath = "C:\\Users\\11206\\Documents\\日记\\" + User.LogUser.userId + "\\";
        File fwriter = new File(filePath);
        if (!fwriter.exists())
            fwriter.mkdir();
        fwriter = new File(filePath + "\\" + diaryTheme.getText() + ".txt");// 相对路径，如果没有则要建立一个新的output。txt文件
        try {
            fwriter.createNewFile(); // 创建新文件

            BufferedWriter out = new BufferedWriter(new FileWriter(fwriter));
            out.write(diary); // \r\n即为换行
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
            savedSuccessfully.setText("保存成功");
            Connection conn = new ConnMysql().connect();
            Statement stmt = null;
            String saveDataBase = "insert into file values(" + User.LogUser.userId + ",'" + diaryDate.getText() + "','" + diaryTheme.getText() + "','" + diaryWeather.getText() + "','" + diaryContent.getText() + "')";
            try {
                stmt = conn.createStatement();
                int rs;
                rs = stmt.executeUpdate(saveDataBase);
            } catch (SQLException e) {
                e.printStackTrace();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void ClickSearch(ActionEvent event) {

        TablePrint we = new TablePrint(User.LogUser.userId);//新窗口类
        Stage stage = new Stage();
        we.start(stage);//打开新窗口.

    }

    public void viewButton(ActionEvent event) {
        ResultSet rs;
        Connection conn = new ConnMysql().connect();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT date,theme,weather,content FROM file WHERE sid = " + User.LogUser.userId + " AND theme = '" + viewName.getText() + "'");
            if (rs.next()) {
                diaryDate.setText(rs.getString("date"));
                diaryTheme.setText(rs.getString("theme"));
                diaryWeather.setText(rs.getString("weather"));
                diaryContent.setText(rs.getString("content"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ClickUpdate(ActionEvent event) {
        UpdatePassword we = new UpdatePassword();
        Stage stage = new Stage();
        we.start(stage);
    }

    public void updatePassword(ActionEvent event) {
        String Password = newPassword.getText();
        Connection conn = new ConnMysql().connect();
        Statement stmt = null;
        String updataPassword = "update user set password = '" + Password + "' where sid = " + User.LogUser.userId;
        try {
            stmt = conn.createStatement();
            int rs;
            rs = stmt.executeUpdate(updataPassword);
            Stage primaryStage = (Stage) buttonPassword.getScene().getWindow();//将submit(登录按钮)与Main类中的primaryStage(新窗口)绑定 并执行close()
            primaryStage.close();//打开新的窗口 所以要关闭当前的窗口
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void buttonRegistration(ActionEvent event) {
        Registration we = new Registration();
        Stage stage = new Stage();
        we.start(stage);
    }

    public void ClickRegistration(ActionEvent event) {
        int id = Integer.valueOf(registrationId.getText());
        String password = registrationPassword.getText();
        String name = registrationName.getText();
        Connection conn = new ConnMysql().connect();
        Statement stmt = null;
        String registration = "insert into user values(" + id + ",'" + name + "','" + "C:\\\\Users\\\\11206\\\\Documents\\\\日记\\\\" + id + "\\\\" + "'," + password + ")";
        try {
            stmt = conn.createStatement();
            int rs;
            rs = stmt.executeUpdate(registration);
            Stage primaryStage = (Stage) buttonRegistration.getScene().getWindow();//将submit(登录按钮)与Main类中的primaryStage(新窗口)绑定 并执行close()
            Thread.sleep(1000);
            primaryStage.close();//打开新的窗口 所以要关闭当前的窗口
        } catch (Exception e) {
            e.printStackTrace();
            registrationError.setText("您输入的账号已被使用！");
        }
    }
}
