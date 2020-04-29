
/**
 * CODE/TEXT EDITOR
 *
 * A partially functional code editor. Still lacking most of it's planned features,
 * but it's capable of opening, editing and saving text files already.
 *
 * KOODI/TEKSTIEDITORI
 *
 * Osittain toimiva koodieditori. Suurin osa suunnitelluista ominaisuuksista puuttuu vielä,
 * mutta ohjelmalla voi jo avata, muokata ja tallentaa tekstitiedostoja.
 *
 *
 * @author  Riku Sänkiaho
 * @version 0.4
 * @since   2020-04-29
 */
        package com.company;

import com.company.util.FileHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;


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
    private String emptycheck;

    public void stop() {
    }

    @Override
    public void start(Stage mainStage) throws IOException {

        FileChooser filechooser1 = new FileChooser();
        FileHandler filehandler1 = new FileHandler();

        /**
         *LOCALIZATION
         *   The program will crash if locale is not found, hence Locale.getDefault will not work outside of finnish/english settings.
         *TO-DO LIST:
         *   - switch to a preset locale if default locale is not found
         *
         *LOKALISAATIO
         *   Ohjelma kaatuu jos oikeaa lokaalisaatiota ei löydy, joten Locale.getDefault ei toimi muilla kuin suomen- ja
         *   englanninkielisillä asetuksilla.
         *TEHTÄVÄLISTA:
         *   -vaihda lokaali valmiiksi määriteltyyn vaihtoehtoon jos oikeaa ei löydy
         *
         */
        Locale locale = new Locale("en", "US");
        //Locale locale = Locale.getDefault();
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

        /**
        * INTERFACE CONSTRUCTION - MENU
        *    Create all menus and their subcategories.
        * KÄYTTÖLIITTYMÄN RAKENNUS - VALIKKO
        *   Luo valikot ja niiden alaluokat.
        */

        MenuBar menubar1 = new MenuBar();                   //luodaan valikkopalkko

        Menu menu1 = new Menu(file);                        //luodaan valikot
        Menu menu2 = new Menu(edit);
        Menu menu3 = new Menu(run);
        Menu menu4 = new Menu(about);

        menubar1.getMenus().add(menu1);                     //lisätään valikot valikkopalkkiin
        menubar1.getMenus().add(menu2);
        menubar1.getMenus().add(menu3);
        menubar1.getMenus().add(menu4);

        MenuItem newfile = new MenuItem(newitem);           //luodaan valikoiden sisältö
        MenuItem openfile = new MenuItem(open);
        MenuItem savefile = new MenuItem(save);
        MenuItem exitprog = new MenuItem(exit);

        menu1.getItems().add(newfile);                      //lisätään valikon sisältö valikkoon
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

        /**
        *INTERFACE CONSTRUCTION - ABOUT PAGE
        *KÄYTTÖLIITTYMÄN RAKENNUS - OHJELMAN TIEDOT -SIVU
        */

        Alert aboutme = new Alert(Alert.AlertType.INFORMATION);
        aboutme.setTitle("About this software");
        aboutme.setHeaderText("JavaFX Code Editor 3000 v0.4");
        aboutme.setContentText("A crude code/text editor by Riku Sänkiaho!");

        /**
        *INTERFACE CONSTRUCTION - TOOLS AND WORK AREA
         * Create the necessary interface elements and connect them.
        *TO-DO LIST:
         *   -finish adding the font menu and add font changing functionality
         *
        *KÄYTTÖLIITTYMÄN RAKENNUS - TYÖKALUT JA TYÖALUE
         * Luo käyttöliittymän eri elementit ja yhdistä ne toimivaksi kokonaisuudeksi.
        *TEHTÄVÄLISTA:
         *    -lisää fonttivalikko ja fontin vaihtaminen tekstialueella
        */

        Button clear = new Button("clear");               //luodaan clear-nappi
        TextArea textarea1 = new TextArea();                //luodaan tekstialue
        textarea1.setFont(Font.font ("Verdana", 14));  //valitaan alustava fontti tekstialueelle
        textarea1.setWrapText(true);                        //tekstialue rajataan niin että ylimenevä teksti siirtyy seuraavalle riville

        ColorPicker colorpicker1 = new ColorPicker();
        TextField fontsize = new TextField();
        fontsize.setPrefColumnCount(3);                     //fonttivalikon pohjaa, ei valmis

        BorderPane root = new BorderPane(textarea1);        //luodaan pohja
        root.setCenter(textarea1);                          //lisätään pohjaan tekstialue
        ToolBar toolbar1 = new ToolBar(clear,colorpicker1); //luodaan valikkopalkki ja liitetään siihen työkalut
        VBox vbox1 = new VBox(menubar1, toolbar1);          //luodaan yläkomponentti ja liitetään siihen molemmat valikkopalkit
        root.setTop(vbox1);                                 //lisätään yläkomponentti pohjaan

        /**
         *INTERFACE INITIALIZATION
         *KÄYTTÖLIITTYMÄN ALUSTUS
         */

        Scene scene = new Scene(root, 800, 600);      //luodaan scene/ikkuna
        mainStage.setTitle("Editor");                       //nimetään ikkuna
        mainStage.initStyle(StageStyle.UNIFIED);            //valitaan tyyli
        mainStage.setScene(scene);
        mainStage.show();                                   //näytetään ikkuna
        mainStage.centerOnScreen();                         //siirretään ikkuna ruudun keskelle

        /**
         *KEYS & HOTKEY SETUP
         *TO-DO LIST:
          *     -remember to add hotkeys to new features
         *
         *NÄPPÄIMET JA PIKANÄPPÄIMET
         *TEHTÄVÄLISTA:
         *      -muista lisätä pikanäppäimet uusille toiminnoille
         */

        textarea1.setOnKeyPressed(keyEvent -> {                         //tabulaattorin muuntaminen välilyönneiksi
            if(keyEvent.getCode() == KeyCode.TAB) {                     //jos TABia painetaan
                int index1 = textarea1.getCaretPosition();              //tarkastetaan sijainti
                textarea1.replaceText(index1-1, index1, "     ");  //laitetaan sijalle välilyönnit
            }
        });

        newfile.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_DOWN));    //pikanäppäimet
        openfile.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.SHORTCUT_DOWN));
        savefile.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN));
        cuttext.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.SHORTCUT_DOWN));
        copytext.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.SHORTCUT_DOWN));
        pastetext.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.SHORTCUT_DOWN));



        /**
         *ACTIONS
         *TO-DO LIST:
         *     -add "Save To" feature
         *     -move certain features such as saving and opening to their own class to clean up the code.
         *     -add confirmation to clear button
         *     -reset color and font on new file
         *
         *TOIMINNOT
         *TEHTÄVÄLISTA:
         *     -lisää "Tallenna Nimellä" toiminto
         *     -siirrä toiminnot kuten tallennus ja avaaminen omiin luokkiinsa koodin selventämiseksi.
         *     -lisää clear-nappiin varmistus
         *     -resetoi väri ja fontti uudessa tiedostossa
         */

        colorpicker1.setOnAction(actionEvent -> {                           //värivalikkoa käytettäessä
            Color value = colorpicker1.getValue();                          //otetaan väriarvo (HEX)
            //System.out.println(value);
            textarea1.setStyle(                                             //muutetaan tekstialueen tekstin väriä
                    "-fx-text-fill: " + toRGBString(value) + ";");          //väriarvo lähetetään muunnettavaksi
        });

        clear.setOnAction(actionEvent ->  textarea1.clear());               //clear:ia painettaessa tekstialue puhdistuu
        cuttext.setOnAction(actionEvent -> textarea1.cut());                //leikkaa
        copytext.setOnAction(actionEvent -> textarea1.copy());              //kopioi
        pastetext.setOnAction(actionEvent -> textarea1.paste());            //liitä

        newfile.setOnAction(actionEvent -> {                                //uuden tiedoston luonti
            emptycheck=textarea1.getText().trim();                          //tarkastetaan onko tekstialue tyhjä
            if(!emptycheck.equals("")) {                                    //jos ei, varmistetaan toiminto
                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setTitle("Confirmation Dialog");
                alert1.setHeaderText("Confirm creating a new file");
                alert1.setContentText("Are you sure you want to create a new file? Unsaved changes to the current file will be lost!");
                alert1.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);  //alert-ikkunan koko tarpeeksi isoksi
                Optional<ButtonType> result = alert1.showAndWait();          //odotetaan vastausta
                if (result.get() == ButtonType.OK) {                         //jos OK
                    textarea1.setText("");                                   //tyhjennetään tekstialue
                }
            }
                });

        openfile.setOnAction(actionEvent -> {                                  //avataan tiedosto
            String emptycheck=textarea1.getText().trim();                       //tarkistetaan onko tyhjä jne.
            if(emptycheck.equals("")) {                                         //jos on:
            File selectedFile = filechooser1.showOpenDialog(mainStage);         //avataan valintaikkuna
                try {
                    filehandler1.setFilePath(selectedFile.getAbsolutePath());   //otetaan tiedoston polku
                    content = filehandler1.openfile();                          //käytetään FileHandleria avaamiseen
                    textarea1.setText(content);                                 //laitetaan sisältö tekstialueelle
                    //filePath = selectedFile.getAbsolutePath();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);         //varoitetaan jos tiedosto ei ole tyhjä
                alert1.setTitle("Confirmation Dialog");                         //muuten sama kuin ylempänä
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

        savefile.setOnAction(actionEvent -> {                               //tallennus
                    try {
                        filehandler1.savefile(textarea1.getText());         //tallennetaan FileHandlerin avulla
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

    /**
     *COLOR VALUE TRANSLATOR
     *Receives color values from the color picker (0-1 for red, green and blue) and translates them to a form
     *that setStyle accepts (0-255 for red, green and blue respectively).

     *VÄRIARVON KÄÄNTÄJÄ
     *ottaa vastaan väriarvoja ja muuttaa ne sopivaan muotoon setStyle komennolle. Heksadesimaaliarvoista saadaan
     *värin komponenttien arvot (0-1 punaiselle, vihreälle ja siniselle) getRed/getGreen/getBlue komennolla.
     *Nämä arvot muunnetaan RGB-muotoon kertomalla ne 255:llä.
     */

    private String toRGBString(Color c) {
        return "rgb("
                + (255*c.getRed())
                + "," + (255*c.getGreen())
                + "," + (255*c.getBlue())
                + ")";

    }


    public static void main(String args[]) {
        launch(args);
    }
}
