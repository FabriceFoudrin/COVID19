package sio2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DesFormesFXML extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/fxml/DesFormesFXML.fxml"));
        Scene scene = new Scene(root, Color.LIGHTYELLOW);
        stage.setTitle("Des formes avec JavaFx");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}

