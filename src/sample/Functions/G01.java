package sample.Functions;

import javafx.geometry.Point2D;

public class G01 {


    public static void task(Point2D endPoint) {

        InputCollection input = new InputCollection();
        double slope = (endPoint.getY() - Spindle.startY) / (endPoint.getX() - Spindle.startX);
        if (Spindle.startX < endPoint.getX() && Spindle.startY < endPoint.getY()) {
            while (Spindle.startX != endPoint.getX()  && Spindle.startY != endPoint.getY() ) {
                input.points.add(new Point2D(Spindle.startX, Spindle.startY));
                Spindle.startX++;
                Spindle.startY += slope;
            }
        }

        else if (Spindle.startX == endPoint.getX() && Spindle.startY < endPoint.getY()) {
            while (Spindle.startY != endPoint.getY() ) {
                input.points.add(new Point2D(Spindle.startX, Spindle.startY));
                Spindle.startY++;
            }

        }
        else if (Spindle.startY == endPoint.getY() && Spindle.startX < endPoint.getX()) {

            while (Spindle.startX != endPoint.getX() ) {
                input.points.add(new Point2D(Spindle.startX, Spindle.startY));
                Spindle.startX++;
            }
        }
        else         if (Spindle.startX > endPoint.getX() && Spindle.startY > endPoint.getY()) {

            while (Spindle.startX != endPoint.getX()  && Spindle.startY != endPoint.getY() ) {
                input.points.add(new Point2D(Spindle.startX, Spindle.startY));
                Spindle.startX--;
                Spindle.startY -= slope;
            }



        }
        else if (Spindle.startX==endPoint.getX() && Spindle.startY>endPoint.getY()){
            while (Spindle.startY != endPoint.getY() ) {
                input.points.add(new Point2D(Spindle.startX, Spindle.startY));
                Spindle.startY--;
            }




        }

        else if (Spindle.startY==endPoint.getY() && Spindle.startX>endPoint.getX()){
            while (Spindle.startX != endPoint.getX() ) {
                input.points.add(new Point2D(Spindle.startX, Spindle.startY));
                Spindle.startX--;
            }




        }

        else if (Spindle.startY<endPoint.getY() && Spindle.startX>endPoint.getX()){
            while (Spindle.startX != endPoint.getX() && Spindle.startY != endPoint.getY() ) {
                input.points.add(new Point2D(Spindle.startX, Spindle.startY));
                Spindle.startX--;
                Spindle.startY-=slope;
            }




        }

        else if (Spindle.startY>endPoint.getY() && Spindle.startX<endPoint.getX()){
            while (Spindle.startX != endPoint.getX() && Spindle.startY != endPoint.getY() ) {
                input.points.add(new Point2D(Spindle.startX, Spindle.startY));
                Spindle.startX++;
                Spindle.startY+=slope;
            }




        }
        input.cut = true;
        Spindle.allTasks.add(input);
    }

}
