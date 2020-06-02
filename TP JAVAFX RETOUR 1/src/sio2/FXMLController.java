package sio2;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;

import javafx.beans.binding.When;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;


public class FXMLController
{

    private RotateTransition rotate;



    @FXML
    private StackPane stackPane;
    @FXML
    private Text text2;
    @FXML
    private Text text3;


    @FXML
    public void initialize()
    {
        rotate = new RotateTransition(Duration.millis(2500), stackPane);
        rotate.setToAngle(360);
        rotate.setFromAngle(0);
        rotate.setInterpolator(Interpolator.LINEAR);

        rotate.statusProperty().addListener((ObservableValue, oldValue, newValue) -> {
            text2.setText("L’animation était " + oldValue + " \nmaintenant elle est " + newValue);

            text2.strokeProperty().bind(new When(rotate.statusProperty().isEqualTo(Animation.Status.RUNNING)).then(Color.GREEN).otherwise(Color.RED));
            text3.textProperty().bind(stackPane.rotateProperty().asString("%.1f"));
        });
    }

    @FXML
    private void handleMouseClick(MouseEvent mouseEvent)
    {
        if (rotate.getStatus().equals(Animation.Status.RUNNING)) {
            rotate.pause();
        } else {
            rotate.play();
        }
    }



}



