package sample.Functions;

import javafx.geometry.Point2D;

public class G01 {


    public static void task(Point2D endPoint) {

        InputCollection input = new InputCollection();
        double slope = (endPoint.getY() - Spindle.startY) / (endPoint.getX() - Spindle.startX);
        while (Spindle.startX != endPoint.getX() + 1 && Spindle.startY != endPoint.getY() + 1) {

            input.points.add(new Point2D(Spindle.startX, Spindle.startY) {
            });
            Spindle.startX++;
            Spindle.startY += slope;
        }
        input.cut = true;
        Spindle.allTasks.add(input);
    }

}
