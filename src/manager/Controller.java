package manager;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Window;
import Model.Document;
import javafx.stage.FileChooser;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

    public class Controller {

        private SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        @FXML
        private TextArea textContent;

        @FXML
        private TextField textAuthor;

        @FXML
        private TextField textDate;

        @FXML
        private Button btnSave;

        @FXML
        private Button btnLoad;

        @FXML
        private Button btnClean;

        @FXML
        protected void btnLoadFile(ActionEvent activeEvent) throws IOException, ParseException {
            Window owner = this.btnLoad.getScene().getWindow();
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(owner);
            if (file != null){
                Document doc = new Document();
                Document document = doc.readFile(file);
                this.textAuthor.setText(document.getAuthor());
                this.textContent.setText(document.getContent());
                this.textDate.setText(sdf.format(document.getDate()));
            } else{
                AlertMessage.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Error file not found");
            }
        }



        @FXML
        protected void btnCleanWin(ActionEvent activeEvent) {
            this.textDate.setText("");
            this.textContent.setText("");
            this.textAuthor.setText("");
        }

        @FXML
        protected void btnSaveFile(ActionEvent activeEvent) {
            Window owner = this.btnSave.getScene().getWindow();
            if (textAuthor.getText().length() > 0) {
                AlertMessage.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter author");
                return;
            }
            if (textContent.getText().length() > 0) {
                AlertMessage.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter content");
                return;
            }

            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.setInitialDirectory(new File("C:\\Users\\saldi\\Desktop"));
            fileChooser.setInitialFileName("document.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(owner);

            Document document = new Document();
            document.setAuthor(this.textAuthor.getText());
            document.setContent(this.textContent.getText());
            document.setDate(new Date());

            if (file != null) {
                Document doc = new Document();
                doc.saveTextToFile(document, file, owner);
                this.textDate.setText(sdf.format(document.getDate()));
            } else{
                AlertMessage.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "File error");
                return;
            }

        }
    }
