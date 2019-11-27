import java.awt.*;

public class Point {
    public double[] coordinates;
    public int[] transformedCoordinates;
    public int xScale, yScale, xFocus, yFocus;
    public Rectangle rect;

    public boolean isDisplaying;
    public int boxWidth;
    public String type = " ";
    public String coordinatePair;

    public Point(int x, int y) {
        coordinates = new double[]{x, y};
    }

    public Point(double[] in) {
        coordinates = in.clone();
        transformedCoordinates = new int[2];
        transform(15, 1, 0, 0);
        rect = new Rectangle(transformedCoordinates[0] - (4), transformedCoordinates[1] - (int) (5), 10, 10);
        coordinatePair = "(" + (Math.round(coordinates[0]*100))/100.0 + ", " + (Math.round(coordinates[1]*100))/100.0 + ")";
        boxWidth = (coordinatePair).length() * 8 + 20;
    }

    public void transform(int scaleXAxis, int scaleYAxis, int xCenter, int yCenter) {
        if (scaleXAxis > 0) xScale = scaleXAxis;
        if (scaleYAxis > 0) yScale = scaleYAxis;
        xFocus = xCenter;
        yFocus = yCenter;
        transformedCoordinates[0] = (int) (coordinates[0] * xScale + 500 + xFocus);
        transformedCoordinates[1] = (int) ((coordinates[1]) * -1 * yScale + 350 + yFocus);
        rect = new Rectangle(transformedCoordinates[0] - (4), transformedCoordinates[1] - (int) (5), 10, 10);
    }


}
