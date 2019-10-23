import java.awt.*;
import java.util.Arrays;


public class Function {
    public int TestInt;
    public double[][] points, transformedPoints;
    public int totalPoints;
    Polygon graphicalFunction;
    public int[] xCo, yCo;//different ways of looking at all the coordinates; an array of all the x coordinates and an array of all the y
    public int xScale = 15, yScale = 1;
    public int xFocus, yFocus = 0;

    public Function(int max, double step) {
        int counter = 0;
        totalPoints = (int) (max / step);
        points = new double[totalPoints][2];
        transformedPoints = new double[totalPoints][2];
        xCo = new int[totalPoints];
        yCo = new int[totalPoints];


        for (double i = 0; i < max; i += step) {
//            points[counter] = new double[]{i, Math.pow(i, 4) + 3 * Math.pow(i, 3) + 6 * i + 4};
            points[counter] = new double[]{i, (i-3)*(i-5)*(i-7)};
            xCo[counter]= (int)(points[counter][0]*xScale + 500 + xFocus);
            yCo[counter]= (int)((points[counter][1])*-1+350+yFocus);
            counter++;
//            System.out.println(Arrays.deepToString(points));
        }

        graphicalFunction = new Polygon(xCo,yCo,totalPoints);

    }

}
