
package com.company;

import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.text.html.ImageView;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

public class App extends Application {

    private Button clear;
    static TextArea textarea1;

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


        //localization | Lokaalisaatio

        //Locale locale = new Locale("en", "US");
        Locale locale = Locale.getDefault();
        ResourceBundle labels = ResourceBundle.getBundle("language", locale);

        String title = labels.getString("title");

        String file = labels.getString("file");
        String edit = labels.getString("edit");
        String run = labels.getString("run");
        String about = labels.getString("about");

        String newitem = labels.getString("new");
        String open = labels.getString("open");
        String save = labels.getString("save");
        String exit = labels.getString("exit");

        String cut = labels.getString("cut");
        String copy = labels.getString("copy");
        String paste = labels.getString("paste");

        String compilerun = labels.getString("compilerun");

        String about2 = labels.getString("about2");

        //MENUS
        MenuBar menubar1 = new MenuBar();
        Menu menu1 = new Menu(file);
        Menu menu2 = new Menu(edit);
        Menu menu3 = new Menu(run);
        Menu menu4 = new Menu(about);
        menubar1.getMenus().add(menu1);
        menubar1.getMenus().add(menu2);
        menubar1.getMenus().add(menu3);
        menubar1.getMenus().add(menu4);
        MenuItem newfile = new MenuItem(newitem);
        MenuItem openfile = new MenuItem(open);
        MenuItem savefile = new MenuItem(save);
        MenuItem exitprog = new MenuItem(exit);

        menu1.getItems().add(newfile);
        menu1.getItems().add(openfile);
        menu1.getItems().add(savefile);
        menu1.getItems().add(exitprog);

        MenuItem cuttext = new MenuItem(cut);
        MenuItem copytext = new MenuItem(copy);
        MenuItem pastetext = new MenuItem(paste);

        menu2.getItems().add(cuttext);
        menu2.getItems().add(copytext);
        menu2.getItems().add(pastetext);

        MenuItem runcompile = new MenuItem(compilerun);
        menu3.getItems().add(runcompile);

        MenuItem aboutthis = new MenuItem(about2);
        menu4.getItems().add(aboutthis);

        FileChooser filechooser1 = new FileChooser();
        final Clipboard clipboard1 = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString("");
        content.putHtml("<b>test</b>");

        //Clipboard systemClipboard = Clipboard.getSystemClipboard();
        //ClipboardContent content = new ClipboardContent();
        //content.putString(text);S
        //String clipboardText = systemClipboard.getString();


        //ABOUT
        Alert aboutme = new Alert(Alert.AlertType.INFORMATION);
        aboutme.setTitle("About this software");
        aboutme.setHeaderText("JavaFX Code Editor v0.1");
        aboutme.setContentText("This page is currently a work in progress, come back later!");

        //
        clear = new Button("clear");
        textarea1 = new TextArea();

        BorderPane root = new BorderPane(textarea1);
        root.setCenter(textarea1);
        ToolBar toolbar1 = new ToolBar(clear);
        VBox vbox1 = new VBox(menubar1, toolbar1);
        root.setTop(vbox1);
        textarea1.setStyle("-fx-background-colour: #FF00FF;");






        Scene scene = new Scene(root, 800, 600);
        mainStage.setTitle("Editor");
        mainStage.initStyle(StageStyle.UNIFIED);
        mainStage.setScene(scene);
        mainStage.show();
        mainStage.centerOnScreen();

        //ACTIONS
        clear.setOnAction(actionEvent -> textarea1.setText(""));

        openfile.setOnAction(e -> {
            File selectedFile = filechooser1.showOpenDialog(mainStage);
        });
        ;

        //private void copy(ActionEvent e){
        //textarea1.copy();
        //}

        aboutthis.setOnAction(actionEvent -> aboutme.showAndWait());

        exitprog.setOnAction(actionEvent -> System.exit(0));

        //clipboard1.setContent(content);

    }

    public static void main(String args[]) {
        launch(args);
    }
}
