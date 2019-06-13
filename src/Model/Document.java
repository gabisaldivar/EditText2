package Model;

    import javafx.scene.control.Alert;
    import javafx.stage.Window;
    import manager.AlertMessage;

    import java.io.*;
    import java.text.ParseException;
    import java.text.SimpleDateFormat;
    import java.util.ArrayList;
    import java.util.Date;
    import java.util.List;

public class Document {

        private String author;
        private String content;
        private Date date;
        private SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

    public Document readFile(File file) throws IOException, ParseException {
        Document document;
            BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
            List<String> list = new ArrayList<String>();
            String line;
            while((line = br.readLine()) != null){
                list.add(line);
            }

            document = new Document();
            document.setAuthor(list.get(0));
            document.setContent(list.get(1));
            document.setDate(sdf.parse(list.get(2)));

        return document;
    }

    public void saveTextToFile(Document document, File file, Window owner) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(document.getAuthor());
            writer.println(document.getContent());
            writer.println(sdf.format(document.getDate()));
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            AlertMessage.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Error dont save File");
        }
    }

}

