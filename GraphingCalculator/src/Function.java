import org.w3c.dom.css.RGBColor;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


public class Function {
    public double[][] points, transformedPoints;
    public int totalPoints;
//    Polygon graphicalFunction;
    public int[] xCo, yCo;//different ways of looking at all the coordinates; an array of all the x coordinates and an array of all the y
    public int xScale = 15, yScale = 1;
    public int xFocus, yFocus = 0;

    public Color functionColor;

    public Function(int min, int max, double precision) {

        functionColor = new Color(41, 196, 254);

        int counter = 0;
        totalPoints = (int) ((max-min) / precision);
        points = new double[totalPoints][2];
        transformedPoints = new double[totalPoints][2];
        xCo = new int[totalPoints];
        yCo = new int[totalPoints];


        for (double i = min; i < max; i += precision) {
//            points[counter] = new double[]{i, Math.pow(i, 4) + 3 * Math.pow(i, 3) + 6 * i + 4};
            points[counter] = new double[]{i, (i-3)*(i-5)*(i-7)};
//            points[counter] = new double[]{i, Math.pow(i,2)};
//            points[counter] = new double[]{i, 6*Math.pow(i,7) + 3*Math.pow(i,3) + 7};
            xCo[counter]= (int)(points[counter][0]*xScale + 500 + xFocus);
            yCo[counter]= (int)((points[counter][1])*-1+350+yFocus);
            counter++;
//            System.out.println(Arrays.deepToString(points));

        }

//        graphicalFunction = new Polygon(xCo,yCo,totalPoints);

    }

    public Function(ArrayList<Integer> coefficients, int min, int max, double precision) {

        functionColor = new Color(41, 196, 254);

        int counter = 0;
        totalPoints = (int) ((max-min) / precision);
        points = new double[totalPoints][2];
        transformedPoints = new double[totalPoints][2];
        xCo = new int[totalPoints];
        yCo = new int[totalPoints];


        for (double x = min; x < max; x += precision) {
//            points[counter] = new double[]{i, Math.pow(i, 4) + 3 * Math.pow(i, 3) + 6 * i + 4};
            double ypos = 0;//initialize what the y coordinate will be. It starts at zero and we add on.
            for(int n=0;n<coefficients.size();n++){
                //for each of the coefficients in the arraylist,
                // add to the y coordinate the coefficient * x to the (length of the arraylist - n) power.
                // For example, if the arraylist were 6 integers long, and the first item in the arraylist were 3,
                // this for loop would add to the y coordinate 3 * x^(6-0) or 3x^6.
                ypos+=(double)coefficients.get(n)*Math.pow(x,coefficients.size()-n-1);
            }


            points[counter] = new double[]{x, ypos};
            xCo[counter]= (int)(points[counter][0]*xScale + 500 + xFocus);
            yCo[counter]= (int)((points[counter][1])*-1+350+yFocus);
            counter++;
//                        System.out.println(Arrays.deepToString(points));

        }

    }


    public void transform(int scaleXAxis, int scaleYAxis, int xCenter, int yCenter){
        if(scaleXAxis>0)xScale = scaleXAxis;
        if(scaleYAxis>0)yScale = scaleYAxis;
        xFocus = xCenter;
        yFocus = yCenter;
        for (int i = 0; i < xCo.length; i++) {
            xCo[i]= (int)(points[i][0]*xScale + 500 + xFocus);
            yCo[i]= (int)((points[i][1])*-1*yScale+350+yFocus);
        }

    }

}
