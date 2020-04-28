
package com.company;

import com.company.util.FileHandler;
//import java.util.logging.FileHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;


public class App extends Application {


    public App() {
        System.out.println("constructor");
    }

    public void init() {
    }

    private FileChooser filechooser1;
    private FileHandler filehandler1;
    private Stage mainStage;
    private TextArea textarea1;
    private String content;
    private String filePath;

    public void stop() {
    }



    //public static final StageStyle UNIFIED
    @Override
    public void start(Stage mainStage) throws IOException {

        FileChooser filechooser1 = new FileChooser();
        FileHandler filehandler1 = new FileHandler();

        //LOCALIZATION

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
        MenuItem openfile0 = new MenuItem(open);
        MenuItem savefile = new MenuItem(save);
        MenuItem exitprog = new MenuItem(exit);

        menu1.getItems().add(newfile);
        menu1.getItems().add(openfile0);
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

        Clipboard clipboard1 = Clipboard.getSystemClipboard();
        ClipboardContent content1 = new ClipboardContent();
        content1.putString("");
        content1.putHtml("<b>test</b>");

        //ABOUT

        Alert aboutme = new Alert(Alert.AlertType.INFORMATION);
        aboutme.setTitle("About this software");
        aboutme.setHeaderText("JavaFX Code Editor v0.1");
        aboutme.setContentText("This page is currently a work in progress, come back later!");

        //
        Button clear = new Button("clear");
        TextArea textarea1 = new TextArea();
        textarea1.setWrapText(true);

        //KEYS & HOTKEYS

        cuttext.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.SHORTCUT_DOWN));
        copytext.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.SHORTCUT_DOWN));
        pastetext.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.SHORTCUT_DOWN));

        textarea1.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.TAB) {
                int index1 = textarea1.getCaretPosition();
                textarea1.replaceText(index1-1, index1, "     ");
            }
        });


        //TOOLS

        ColorPicker colorpicker1 = new ColorPicker();

        TextField fontsize = new TextField();
        fontsize.setPrefColumnCount(3);

        //INTERFACE SET UP

        BorderPane root = new BorderPane(textarea1);
        root.setCenter(textarea1);
        ToolBar toolbar1 = new ToolBar(clear,colorpicker1);
        VBox vbox1 = new VBox(menubar1, toolbar1);
        root.setTop(vbox1);

        //INTERFACE INITIALIZATION

        Scene scene = new Scene(root, 800, 600);
        mainStage.setTitle("Editor");
        mainStage.initStyle(StageStyle.UNIFIED);
        mainStage.setScene(scene);
        mainStage.show();
        mainStage.centerOnScreen();

        //ACTIONS

        colorpicker1.setOnAction(actionEvent -> {
            Color value = colorpicker1.getValue();
        });

        clear.setOnAction(actionEvent -> textarea1.setText(""));

        cuttext.setOnAction(actionEvent -> textarea1.cut());;
        copytext.setOnAction(actionEvent -> textarea1.copy());
        pastetext.setOnAction(actionEvent -> textarea1.paste());

        newfile.setOnAction(actionEvent -> {

            String emptycheck=textarea1.getText().trim();
            if(!emptycheck.equals("")) {
                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setTitle("Confirmation Dialog");
                alert1.setHeaderText("Confirm creating a new file");
                alert1.setContentText("Are you sure you want to create a new file? Unsaved changes to the current file will be lost!");
                alert1.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                Optional<ButtonType> result = alert1.showAndWait();
                if (result.get() == ButtonType.OK) {
                    textarea1.setText("");
                }
            }
                });

        openfile0.setOnAction(actionEvent -> {


            String emptycheck=textarea1.getText().trim();
            if(emptycheck.equals("")) {
            File selectedFile = filechooser1.showOpenDialog(mainStage);
                try {
                    filehandler1.setFilePath(selectedFile.getAbsolutePath());
                    content = filehandler1.openfile();
                    textarea1.setText(content);
                    filePath = selectedFile.getAbsolutePath();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setTitle("Confirmation Dialog");
                alert1.setHeaderText("Confirm opening a new file");
                alert1.setContentText("Are you sure you want to open a new file? Unsaved changes to the current file will be lost!");
                alert1.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                Optional<ButtonType> result = alert1.showAndWait();
                if (result.get() == ButtonType.OK) {
                    File selectedFile = filechooser1.showOpenDialog(mainStage);
                    try {
                        filehandler1.setFilePath(selectedFile.getAbsolutePath());
                        content = filehandler1.openfile();
                        textarea1.setText(content);
                        filePath = selectedFile.getAbsolutePath();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
                });

        savefile.setOnAction(actionEvent -> {
                    try {
                        filehandler1.savefile(textarea1.getText());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });

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
