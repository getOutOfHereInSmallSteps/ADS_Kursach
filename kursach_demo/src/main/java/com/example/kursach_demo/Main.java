package com.example.kursach_demo;

import javafx.application.Application;
import javafx.stage.Stage;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static com.example.kursach_demo.Base.search;

public class Main extends Application {

    //змінна яка визначає чи адмін користувач
    public static boolean isAdmin;

    //метод який опрацьовує натискання кнопки
    void onOk(TextArea loginField, PasswordField passwordField, Label incorrectText, Stage primaryStage){

        String login = loginField.getText();
        String password = passwordField.getText();

        //опрацювання логіну і паролю звичайного користувача
        if(login.equals("1234") && password.equals("1234")){
            isAdmin = false;
            Base.display();
            primaryStage.hide();
        }

        //опрацювання логіну і паролю адміна
        else if(login.equals("admin") && password.equals("admin")){
            isAdmin = true;
            Base.display();
            primaryStage.hide();
        }

        //опрацювання випадку неправильного паролю
        else{
            incorrectText.setVisible(true);
        }
    }

    @Override
    public void start(Stage primaryStage) {

        //додавання всіх текстів
        Label loginText = new Label("Login:");
        Label passwordText = new Label("Password:");
        Label mainText = new Label("Авторизуйтесь в системі");
        Label incorrectText = new Label("Неправильний логін або пароль");

        //форматування текстів
        loginText.setFont(new Font("Arial", 18));
        passwordText.setFont(new Font("Arial", 18));
        mainText.setFont(new Font("Arial", 24));
        incorrectText.setFont(new Font("Arial", 13));
        incorrectText.setTextFill(Color.RED);

        //розставляння текстів на похиції по координатам
        loginText.setLayoutX(86);
        loginText.setLayoutY(102);
        passwordText.setLayoutX(63);
        passwordText.setLayoutY(175);
        mainText.setLayoutX(100);
        mainText.setLayoutY(34);
        incorrectText.setLayoutX(154);
        incorrectText.setLayoutY(227);

        incorrectText.setVisible(false);//ховання тексту на випадок неправильного паролю

        //додавання полів вводу
        TextArea loginField = new TextArea();
        PasswordField passwordField = new PasswordField();

        //виставлення розмірів полів
        loginField.setPrefWidth(200);
        loginField.setPrefHeight(30);
        passwordField.setPrefWidth(200);
        passwordField.setPrefHeight(30);

        //виставленння полів на позиції по координатам
        loginField.setLayoutX(177);
        loginField.setLayoutY(101);
        passwordField.setLayoutX(177);
        passwordField.setLayoutY(171);

        Button okButton = new Button("Ok");//додавання кнопки Ок

        //виставлення параметрів кнопки
        okButton.setPrefWidth(70); okButton.setPrefHeight(30);
        okButton.setLayoutX(200); okButton.setLayoutY(263);

        //опрацювання натискання на кнопку
        okButton.setOnAction(event -> onOk(loginField, passwordField, incorrectText, primaryStage));

        //макет для візуалу програми
        Pane pane = new Pane(loginText, passwordText, mainText, incorrectText, loginField, passwordField, okButton);

        //створення та налаштування "сцени"
        Scene authScene = new Scene(pane, 470, 320);
        primaryStage.setScene(authScene);
        primaryStage.setTitle("Work base");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        Base.init(new int[]{1}, 0);
    }

}


