import java.awt.*;
import java.util.ArrayList;


public class Function {
    public double[][] points, transformedPoints;
    public int totalPoints;
    //    Polygon graphicalFunction;
    public int[] xCo, yCo;//different ways of looking at all the coordinates; an array of all the x coordinates and an array of all the y
    public int xScale = 15, yScale = 1;
    public int xFocus, yFocus = 0;

    public ArrayList holes;

    public Color functionColor;

    public Function(int min, int max, double precision) {

        functionColor = new Color(41, 196, 254);

        int counter = 0;
        totalPoints = (int) ((max - min) / precision);
        points = new double[totalPoints][2];
        transformedPoints = new double[totalPoints][2];
        xCo = new int[totalPoints];
        yCo = new int[totalPoints];


        for (double i = min; i < max; i += precision) {
//            points[counter] = new double[]{i, Math.pow(i, 4) + 3 * Math.pow(i, 3) + 6 * i + 4};
            points[counter] = new double[]{i, (i - 3) * (i - 5) * (i - 7)};
//            points[counter] = new double[]{i, Math.pow(i,2)};
//            points[counter] = new double[]{i, 6*Math.pow(i,7) + 3*Math.pow(i,3) + 7};
            xCo[counter] = (int) (points[counter][0] * xScale + 500 + xFocus);
            yCo[counter] = (int) ((points[counter][1]) * -1 + 350 + yFocus);
            counter++;
//            System.out.println(Arrays.deepToString(points));

        }

//        graphicalFunction = new Polygon(xCo,yCo,totalPoints);

    }

    public Function(boolean isGrid, int minX, int maxX, int minY, int maxY) {
        if (isGrid) {
            functionColor = new Color(0, 0, 0);
        }

        xCo = new int[totalPoints];
        yCo = new int[totalPoints];
        totalPoints = (maxX - minX) * (maxY - minY);
        points = new double[totalPoints][2];

        int counter = 0;

        for (int x = minX; x < maxX; x++) {
            for (int y = minY; y < maxY; y++) {
                points[counter] = new double[]{x, y};
                xCo[counter] = (int) (points[counter][0] * xScale + 500 + xFocus);
                yCo[counter] = (int) ((points[counter][1]) * -1 + 350 + yFocus);
                counter++;

            }
        }


    }

    public Function(ArrayList<Integer> inputs, int min, int max, double precision, String type) {
        if (type.equals("coefficients")) {
            functionColor = new Color(41, 196, 254);

            int counter = 0;
            totalPoints = (int) ((max - min) / precision);
            points = new double[totalPoints][2];
            transformedPoints = new double[totalPoints][2];
            xCo = new int[totalPoints];
            yCo = new int[totalPoints];


            for (double x = min; x < max; x += precision) {
//            points[counter] = new double[]{i, Math.pow(i, 4) + 3 * Math.pow(i, 3) + 6 * i + 4};
                double ypos = 0;//initialize what the y coordinate will be. It starts at zero and we add on.
                for (int n = 0; n < inputs.size(); n++) {
                    //for each of the coefficients in the arraylist,
                    // add to the y coordinate the coefficient * x to the (length of the arraylist - n) power.
                    // For example, if the arraylist were 6 integers long, and the first item in the arraylist were 3,
                    // this for loop would add to the y coordinate 3 * x^(6-0) or 3x^6.
                    ypos += (double) inputs.get(n) * Math.pow(x, inputs.size() - n - 1);
                }


                points[counter] = new double[]{x, ypos};
                xCo[counter] = (int) (points[counter][0] * xScale + 500 + xFocus);
                yCo[counter] = (int) ((points[counter][1]) * -1 + 350 + yFocus);
                counter++;
//                        System.out.println(Arrays.deepToString(points));

            }
        } else if (type.equals("zeroes")) {
            functionColor = new Color(254, 27, 12);

            int counter = 0;
            totalPoints = (int) ((max - min) / precision);
            points = new double[totalPoints][2];
            transformedPoints = new double[totalPoints][2];
            xCo = new int[totalPoints];
            yCo = new int[totalPoints];


            for (double x = min; x < max; x += precision) {
//            points[counter] = new double[]{i, Math.pow(i, 4) + 3 * Math.pow(i, 3) + 6 * i + 4};
                double ypos = x - inputs.get(0);//initialize what the y coordinate will be. It starts at zero and we add on.
                if (inputs.size() > 1) {
                    for (int n = 1; n < inputs.size(); n++) {
                        ypos *= (double) (x - inputs.get(n));
                    }
                }


                points[counter] = new double[]{x, ypos};
                xCo[counter] = (int) (points[counter][0] * xScale + 500 + xFocus);
                yCo[counter] = (int) ((points[counter][1]) * -1 + 350 + yFocus);
                counter++;
//                        System.out.println(Arrays.deepToString(points));

            }
        }


    }

    public Function(ArrayList<Integer> inputsT, ArrayList<Integer> inputsB, int min, int max, double precision) {
        functionColor = new Color(41, 196, 254);

        int counter = 0;
        totalPoints = (int) ((max - min) / precision);
        points = new double[totalPoints][2];
        transformedPoints = new double[totalPoints][2];
        xCo = new int[totalPoints];
        yCo = new int[totalPoints];
        holes = new ArrayList<>();


        for (double x = min; x < max; x += precision) {
//            points[counter] = new double[]{i, Math.pow(i, 4) + 3 * Math.pow(i, 3) + 6 * i + 4};
            double num = 0;
            double denom = 0;
            double ypos = 0;//initialize what the y coordinate will be. It starts at zero and we add on.
            for (int n = 0; n < inputsT.size(); n++) {
                //for each of the coefficients in the arraylist,
                // add to the y coordinate the coefficient * x to the (length of the arraylist - n) power.
                // For example, if the arraylist were 6 integers long, and the first item in the arraylist were 3,
                // this for loop would add to the y coordinate 3 * x^(6-0) or 3x^6.
                num += (double) inputsT.get(n) * Math.pow(x, inputsT.size() - n - 1);
            }

            for (int n = 0; n < inputsB.size(); n++) {
                //for each of the coefficients in the arraylist,
                // add to the y coordinate the coefficient * x to the (length of the arraylist - n) power.
                // For example, if the arraylist were 6 integers long, and the first item in the arraylist were 3,
                // this for loop would add to the y coordinate 3 * x^(6-0) or 3x^6.
                denom += (double) inputsB.get(n) * Math.pow(x, inputsB.size() - n - 1);
            }
            if(denom!=0) {
                ypos = num / denom;
                points[counter] = new double[]{x, ypos};
                xCo[counter] = (int) (points[counter][0] * xScale + 500 + xFocus);
                yCo[counter] = (int) ((points[counter][1]) * -1 + 350 + yFocus);
            }
            else
            {
                holes.add(x);
            }
            counter++;
//                        System.out.println(Arrays.deepToString(points));

        }


    }

    public Function(ArrayList<Integer> coefficients, int min, int max, double precision) {

        functionColor = new Color(41, 196, 254);

        int counter = 0;
        totalPoints = (int) ((max - min) / precision);
        points = new double[totalPoints][2];
        transformedPoints = new double[totalPoints][2];
        xCo = new int[totalPoints];
        yCo = new int[totalPoints];


        for (double x = min; x < max; x += precision) {
//            points[counter] = new double[]{i, Math.pow(i, 4) + 3 * Math.pow(i, 3) + 6 * i + 4};
            double ypos = 0;//initialize what the y coordinate will be. It starts at zero and we add on.
            for (int n = 0; n < coefficients.size(); n++) {
                //for each of the coefficients in the arraylist,
                // add to the y coordinate the coefficient * x to the (length of the arraylist - n) power.
                // For example, if the arraylist were 6 integers long, and the first item in the arraylist were 3,
                // this for loop would add to the y coordinate 3 * x^(6-0) or 3x^6.
                ypos += (double) coefficients.get(n) * Math.pow(x, coefficients.size() - n - 1);
            }


            points[counter] = new double[]{x, ypos};
            xCo[counter] = (int) (points[counter][0] * xScale + 500 + xFocus);
            yCo[counter] = (int) ((points[counter][1]) * -1 + 350 + yFocus);
            counter++;
//                        System.out.println(Arrays.deepToString(points));

        }

    }

    public void transform(int scaleXAxis, int scaleYAxis, int xCenter, int yCenter) {
        if (scaleXAxis > 0) xScale = scaleXAxis;
        if (scaleYAxis > 0) yScale = scaleYAxis;
        xFocus = xCenter;
        yFocus = yCenter;
        for (int i = 0; i < xCo.length; i++) {
            xCo[i] = (int) (points[i][0] * xScale + 500 + xFocus);
            yCo[i] = (int) ((points[i][1]) * -1 * yScale + 350 + yFocus);
        }

    }

}
