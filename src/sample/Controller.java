package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import sample.Functions.*;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {

    private static Thread thread;

    @FXML
    private Button cancelButton;

    @FXML
    private ToggleButton coolingToggleButton;

    @FXML
    private Group cncFrame;

    @FXML
    private GridPane cncFuncPartGridPane;
    @FXML
    private Button executeButton;

    @FXML
    private ToggleButton clockwiseToggleButton;

    @FXML
    private Label speedLabel;

    @FXML
    private Button checkButton;

    @FXML
    private Label xPosLabel;

    @FXML
    private TextField userInputTextField;

    @FXML
    private ToggleButton powerOnToggleButton;

    @FXML
    private Label yPosLabel;

    @FXML
    private Label textArea;

    @FXML
    private AnchorPane cncFramAnchorPane;


    public void cancelButtonMouseClicked(ActionEvent actionEvent) {
        thread.stop();
    }

    public void checkedButton(ActionEvent actionEvent) {
        ArrayList<Double> points = new ArrayList<>();
        double input;
        String command = userInputTextField.getText();
        textArea.setText(textArea.getText() + command + "\n");
        if (Setting.cncIsOn) {
            if (command.contains("G00")) {

                String modifier = command.replace("G00", "");
                modifier = modifier.replace("X", "");
                modifier = modifier.replace("Y", "");
                Scanner scanner = new Scanner(modifier);
                while (scanner.hasNextLine()) {
                    String modifiedCpmmand = scanner.next();
                    input = Double.parseDouble(modifiedCpmmand);
                    points.add(input);


                }
                G00.task(new Point2D(points.get(0), points.get(1)));
                points.clear();
            }
            if (command.contains("G01")) {
                Polyline polyline = new Polyline();
                polyline.setFill(Color.RED);
                cncFrame.getChildren().add(polyline);
                Spindle.polyline.add(polyline);

                String modifierG01 = command.replace("G01", "");
                modifierG01 = modifierG01.replace("X", "");
                modifierG01 = modifierG01.replace("Y", "");
                Scanner scanner = new Scanner(modifierG01);
                while (scanner.hasNextLine()) {
                    String modifiedCpmmand = scanner.next();
                    input = Double.parseDouble(modifiedCpmmand);
                    points.add(input);


                }
                G01.task(new Point2D(points.get(0), points.get(1)));
                points.clear();

            }
        } else if (command.contains("M00")) {
            new M00();
        } else if (command.contains("M01")) {
            new M01();
        }
    }

    public void executeButton(ActionEvent actionEvent) {
        //

//        Spindle spindle = new Spindle();
        //
        //Spindle spindle = new Spindle();
        if (Setting.cncIsOn) {
            thread = new Thread(new Spindle());
            thread.start();
        } else {
            textArea.setText("Turn the Device ON!"+"\n");

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cncFrame.getChildren().add(Main.drill);
        cncFramAnchorPane.setBackground(new Background(new BackgroundFill(Setting.backgroundScene, new CornerRadii(0), new Insets(0))));
        cncFramAnchorPane.setMinHeight(400);
        cncFuncPartGridPane.setBackground(new Background(new BackgroundFill(Color.AZURE, new CornerRadii(0), new Insets(0))));
    }
}