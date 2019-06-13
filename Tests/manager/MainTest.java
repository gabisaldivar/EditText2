package manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import Model.Document;

public class MainTest extends ApplicationTest {

    @Override
    public void start (Stage stage) throws Exception {
        Parent mainNode = FXMLLoader.load(Main.class.getResource("sample.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @Before
    public void setUp () throws Exception {
    }

    @After
    public void tearDown () throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Test
    public void testSaveDocument () throws IOException, ParseException,InterruptedException{
        String nameFile = String.valueOf( 100 + (int)(Math.random()*999)).concat(".txt");
        String pathFileresult = "C:\\Users\\saldi\\Desktop\\".concat(nameFile);
        Document documentTest = new Document();
        documentTest.setAuthor("Gaby Saldivar");
        documentTest.setContent("Copy the following into the sample.fxml file");

        clickOn("#textAuthor");
        write(documentTest.getAuthor());
        clickOn("#textContent");
        write(documentTest.getContent());
        clickOn("#btnSave");
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection ss = new StringSelection(pathFileresult);
        clipboard.setContents(ss,ss);
        press(KeyCode.CONTROL).press(KeyCode.V).release(KeyCode.V).release(KeyCode.CONTROL).push(KeyCode.ENTER);

            Thread.sleep(5000);
            Document documentResult = new Document();
            documentResult = documentResult.readFile(new File(pathFileresult));
            if (documentTest.getAuthor().equals(documentResult.getAuthor())
            && documentTest.getContent().equals(documentResult.getContent())){
                Assert.assertTrue(true);
            } else{
                Assert.assertTrue(false);
            }
        }
    }
