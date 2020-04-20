
package com.company;

import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.BorderPane;
import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.text.html.ImageView;
import java.io.File;

public class App extends Application {

    private Button clear;
    private TextArea textarea1;

    public App() {
        System.out.println("constructor");
    }

    public void init() {
    }

    public void stop() {
    }

    //public static final StageStyle UNIFIED
    @Override
    public void start(Stage mainStage) {

        clear = new Button("clear");
        textarea1 = new TextArea();

        //MENUS
        MenuBar menubar = new MenuBar();
        Menu menu1 = new Menu("File");
        Menu menu2 = new Menu("Edit");
        Menu menu3 = new Menu("Run");
        Menu menu4 = new Menu("About");
        menubar.getMenus().add(menu1);
        menubar.getMenus().add(menu2);
        menubar.getMenus().add(menu3);
        menubar.getMenus().add(menu4);
        MenuItem newfile = new MenuItem("New");
        MenuItem openfile = new MenuItem("Open");
        MenuItem savefile = new MenuItem("Save");
        MenuItem exit = new MenuItem("Exit");

        menu1.getItems().add(newfile);
        menu1.getItems().add(openfile);
        menu1.getItems().add(savefile);
        menu1.getItems().add(exit);

        MenuItem cuttext = new MenuItem("Cut");
        MenuItem copytext = new MenuItem("Copy");
        MenuItem pastetext = new MenuItem("Paste");

        menu2.getItems().add(cuttext);
        menu2.getItems().add(copytext);
        menu2.getItems().add(pastetext);

        MenuItem runcompile = new MenuItem("Compile and Run");
        menu3.getItems().add(runcompile);

        MenuItem about = new MenuItem("About");
        menu4.getItems().add(about);

        FileChooser filechooser1 = new FileChooser();
        final Clipboard clipboard1 = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString("");
        content.putHtml("<b>test</b>");



        //ABOUT
        Alert aboutme = new Alert(Alert.AlertType.INFORMATION);
        aboutme.setTitle("About this software");
        aboutme.setHeaderText("JavaFX Code Editor v0.1");
        aboutme.setContentText("This page is currently a work in progress, come back later!");

        //???
        BorderPane borderpane1 = new BorderPane();
        ToolBar toolbar1 = new ToolBar();
        VBox vbox1 = new VBox(menubar);

        //COMPLETE MESS!
        toolbar1.setOrientation(Orientation.VERTICAL);
        toolbar1.getItems().add(clear);
        borderpane1.setTop(toolbar1);
        borderpane1.setCenter(textarea1);
        borderpane1.setBottom(new TextField("test"));

        //Group group1 = new Group(borderpane1, clear);
        Scene scene = new Scene(vbox1, 800, 600);
        mainStage.setTitle("Editor");
        //mainStage.setWidth(800);
        //mainStage.setHeight(600);
        mainStage.initStyle(StageStyle.UNIFIED);
        mainStage.setScene(scene);
        mainStage.show();
        mainStage.centerOnScreen();

        //ACTIONS
        clear.setOnAction(actionEvent -> textarea1.setText(""));

        openfile.setOnAction(e -> {
            File selectedFile = filechooser1.showOpenDialog(mainStage);
            });;

        about.setOnAction(actionEvent -> aboutme.showAndWait());

        exit.setOnAction(actionEvent -> System.exit(0));

        //clipboard1.setContent(content);

    }

    public static void main(String args[]) {
        launch(args);
    }
}