package com.example.kursach_demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Base {

    //створення нової сцени
    public static Stage primaryStage = new Stage();

    //масив елементів бази
    static TableView<Item> table;

    //метод для створення нової кнопки
    static Button new_button(String name, float x, float y) {
        Button tmp = new Button(name);
        tmp.setLayoutX(x);
        tmp.setLayoutY(y);
        return tmp;
    }

    //метод для створення нового текстового поля
    static TextField new_textfield(float x, float y) {
        TextField tmp = new TextField();
        tmp.setLayoutX(x);
        tmp.setLayoutY(y);
        return tmp;
    }

    //метод для створення нового напису
    static Label new_label(String name, float x, float y) {
        Label tmp = new Label(name);
        tmp.setLayoutX(x);
        tmp.setLayoutY(y);
        return tmp;
    }

    //опрацювання кнопки пошуку
    static void filter(VBox vbox) {
        vbox.setVisible(!vbox.isVisible());
    }

    //опрацювання кновки додавання нового співробітника
    static void new_employee(ObservableList<Item> Items) {

        TextField t_name = new_textfield(198, 26);
        TextField t_birth = new_textfield(198, 64);
        TextField t_department = new_textfield(198, 102);
        TextField t_jobTitle = new_textfield(198, 140);
        TextField t_startDate = new_textfield(198, 178);

        Label l_name = new_label("ПІБ співробітника: ", 36, 26);
        Label l_birth = new_label("Дата народження: ", 36, 64);
        Label l_department = new_label("Назва відділу: ", 36, 102);
        Label l_jobTitle = new_label("Посада: ", 36, 140);
        Label l_startDate = new_label("Дата початку роботи: ", 36, 178);

        Button btn_create = new_button("Додати", 140, 255);

        Pane create = new Pane(btn_create, t_name, t_birth, t_department, t_jobTitle, t_startDate, l_name, l_birth, l_department, l_jobTitle, l_startDate);
        Stage newStage = new Stage();
        newStage.setTitle("Додавання робітника");
        Scene tmp = new Scene(create, 390, 290);
        newStage.setScene(tmp);
        newStage.show();
        newStage.setResizable(false);

        btn_create.setOnAction(event -> {
            Items.add(new Item(t_name.getText(), t_birth.getText(), t_department.getText(), t_jobTitle.getText(), t_startDate.getText()));

            newStage.close();
        });

    }

    private static void mergeSortByExperience(List<Item> Items) {
        if (Items.size() > 1) {
            List<Item> left = new ArrayList<>();
            List<Item> right = new ArrayList<>();
            int middle = Items.size() / 2;
            for (int i = 0; i < middle; i++) {
                left.add(Items.get(i));
            }
            for (int i = middle; i < Items.size(); i++) {
                right.add(Items.get(i));
            }
            mergeSortByExperience(left);
            mergeSortByExperience(right);
            mergeByExperience(Items, left, right);
        }
    }

    private static void mergeSortByBirth(List<Item> Items) {
        if (Items.size() > 1) {
            List<Item> left = new ArrayList<>();
            List<Item> right = new ArrayList<>();
            int middle = Items.size() / 2;
            for (int i = 0; i < middle; i++) {
                left.add(Items.get(i));
            }
            for (int i = middle; i < Items.size(); i++) {
                right.add(Items.get(i));
            }
            mergeSortByBirth(left);
            mergeSortByBirth(right);
            mergeByBirth(Items, left, right);
        }
    }

    private static void mergeByExperience(List<Item> Items, List<Item> left, List<Item> right) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            int yearLeft = Integer.parseInt(left.get(i).getStartDate().substring(6, 10));
            int yearRight = Integer.parseInt(right.get(j).getStartDate().substring(6, 10));
            if (yearLeft < yearRight) {
                Items.set(k++, left.get(i++));
            } else {
                Items.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) {
            Items.set(k++, left.get(i++));
        }
        while (j < right.size()) {
            Items.set(k++, right.get(j++));
        }
    }

    private static void mergeByBirth(List<Item> Items, List<Item> left, List<Item> right) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            int yearLeft = Integer.parseInt(left.get(i).getBirth().substring(6, 10));
            int yearRight = Integer.parseInt(right.get(j).getBirth().substring(6, 10));
            if (yearLeft < yearRight) {
                Items.set(k++, left.get(i++));
            } else {
                Items.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) {
            Items.set(k++, left.get(i++));
        }
        while (j < right.size()) {
            Items.set(k++, right.get(j++));
        }
    }

    //опрацювання кнопки сортування
    static void toSort(ObservableList<Item> Items) {

        ToggleGroup radioGr = new ToggleGroup();

        RadioButton pension = new RadioButton("Пенсіонери");
        pension.setToggleGroup(radioGr);
        pension.setLayoutX(50);
        pension.setLayoutY(20);

        RadioButton experience = new RadioButton("За стажем");
        experience.setToggleGroup(radioGr);
        experience.setLayoutX(50);
        experience.setLayoutY(60);

        Button okButton = new_button("ОК", 80, 100);

        Pane sort = new Pane(pension, experience, okButton);
        Stage sortStage = new Stage();
        sortStage.setTitle("Вибір сортування");
        Scene tmp = new Scene(sort, 200, 150);
        sortStage.setScene(tmp);
        sortStage.show();
        sortStage.setResizable(false);

        okButton.setOnAction(event -> {
            if (experience.isSelected()) {
                mergeSortByExperience(Items);
            } else {
                mergeSortByBirth(Items);
            }
            sortStage.close();
        });

    }

    //метод який запускає це вікно
    public static void display() {

        //масив співробітників
        ObservableList<Item> Items = FXCollections.observableArrayList(
                new Item("Мельник Н. Ф.", "25.06.1955", "Продажі", "Продавець", "15.11.2012"),
                new Item("Коваль М. С.", "15.03.2000", "Маркетинг", "Маркетолог", "23.03.2010"),
                new Item("Ткаченко В. В.", "01.05.1958", "Кадри", "HR", "08.06.2020"),
                new Item("Лисенко С. В.", "29.11.1976", "Продажі", "Старший Продавець", "13.10.2015")
        );

        table = new TableView<>(Items);
        table.setPrefWidth(753);
        table.setPrefHeight(300);

        //перша колонка таблиці
        TableColumn<Item, Integer> idColumn = new_int_cols("id", "id", 40);
        TableColumn<Item, String> nameColumn = new_string_cols("ПІБ співробітника", "name", 165);
        TableColumn<Item, Integer> numberColumn = new_int_cols("Дата народження", "birth", 124);
        TableColumn<Item, Integer> priceColumn = new_int_cols("Назва відділу", "department", 132);
        TableColumn<Item, String> typeColumn = new_string_cols("Посада", "jobTitle", 106);
        TableColumn<Item, String> placeColumn = new_string_cols("Дата початку роботи", "startDate", 179);

        FilteredList<Item> flgood = new FilteredList<>(Items, p -> true);
        table.setItems(flgood);
        table.getColumns().addAll(idColumn, nameColumn, numberColumn, priceColumn, typeColumn, placeColumn);

        TextField text = new_textfield(10, 430);
        text.setText(null);
        text.setVisible(false);

        //блок пошуку співробітників
        //-------------------------------------------------------
        //меню вибору параметру співробітника для пошуку
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("id", "ПІБ співробітника", "Дата народження", "Назва відділу", "Посада", "Дата початку роботи");
        choiceBox.setValue("id");

        TextField textField = new TextField();
        textField.setPromptText("Search here!");
        textField.textProperty().addListener((obs, oldValue, newValue) -> {
            switch (choiceBox.getValue()) {
                case "id" -> flgood.setPredicate(p -> Integer.toString(p.getId()).toLowerCase().contains(newValue.toLowerCase().trim()));
                case "ПІБ співробітника" -> flgood.setPredicate(p -> p.getName().toLowerCase().contains(newValue.toLowerCase().trim()));
                case "Дата народження" -> flgood.setPredicate(p -> p.getBirth().toLowerCase().contains(newValue.toLowerCase().trim()));
                case "Назва відділу" -> flgood.setPredicate(p -> p.getDepartment().toLowerCase().contains(newValue.toLowerCase().trim()));
                case "Посада" -> flgood.setPredicate(p -> p.getJobTitle().toLowerCase().contains(newValue.toLowerCase().trim()));
                case "Дата початку роботи" -> flgood.setPredicate(p -> p.getStartDate().toLowerCase().contains(newValue.toLowerCase().trim()));
            }
        });

        choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
                -> {
            if (newVal != null) {
                textField.setText("");
            }
        });

        VBox vbox = new VBox(choiceBox, textField);
        vbox.setPadding(new Insets(311, 0, 0, 14));
        vbox.setVisible(false);

        //---------------------------------------------------------

        //додавання кнопок
        Button search = new_button("Пошук", 170, 311);
        Button add = new_button("Додати", 307, 311);
        Button sort = new_button("Відсортувати", 395, 311);

        if (!Main.isAdmin) {
            add.setDisable(true);
            sort.setDisable(true);
        }

        //опрацювання натискання на кнопки
        add.setOnAction(event -> new_employee(Items));
        search.setOnAction(event -> filter(vbox));
        sort.setOnAction(event -> toSort(Items));

        //налаштування сцени
        Pane root = new Pane(search, vbox, add, sort, table);
        primaryStage.setTitle("Work base");
        primaryStage.setScene(new Scene(root, 753, 384));
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    //метод для додавання нової колонки з типом данних string
    static TableColumn<Item, String> new_string_cols(String name, String id, float width) {
        TableColumn<Item, String> tmpColumn = new TableColumn<>(name);
        tmpColumn.setPrefWidth(width);
        tmpColumn.setStyle("-fx-alignment: CENTER;");
        tmpColumn.setCellValueFactory(new PropertyValueFactory<>(id));
        return tmpColumn;
    }

    //метод для додавання нової колонки з типом данних int
    static TableColumn<Item, Integer> new_int_cols(String name, String id, float width) {
        TableColumn<Item, Integer> tmpColumn = new TableColumn<>(name);
        tmpColumn.setPrefWidth(width);
        tmpColumn.setStyle("-fx-alignment: CENTER;");
        tmpColumn.setCellValueFactory(new PropertyValueFactory<>(id));
        return tmpColumn;
    }

        public static int search(int[] array, int key) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == key) {
                    return i;
                }
            }
            return -1;
        }

        public static int[] init(int[] array, int key) {
            int res = search(array, key);
            return new int[]{res};
        }
}
