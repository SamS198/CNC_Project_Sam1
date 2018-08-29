package sample.Functions;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import sample.Main;

import java.util.ArrayList;

public class Spindle implements Runnable {
    public static double startX = 0, startY = 0;
    public static ArrayList<InputCollection> allTasks = new ArrayList<>();
   // public Polyline line = new Polyline();
    public static ArrayList<Polyline> polyline = new ArrayList<Polyline>();


    @Override
    public void run() {

        for (InputCollection inputCollection : allTasks) {
            for (Point2D point2D : inputCollection.points) {
                startX = (int) point2D.getX();
                startY = (int) point2D.getY();

                Main.drill.setCenterX(startX);
                Main.drill.setCenterY(startY);

                if (inputCollection.cut) {
                    startX = (int) point2D.getX();
                    startY = (int) point2D.getY();
                    polyline.get(0).setFill(Color.RED);
                    polyline.get(0).getPoints().addAll(startX,startY);

                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (inputCollection.cut)
            polyline.remove(0);

        }
    }

    }

