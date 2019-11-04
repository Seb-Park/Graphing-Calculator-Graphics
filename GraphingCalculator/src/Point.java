public class Point {
    public double[] coordinates;
    public int[] transformedCoordinates;
    public int xScale, yScale, xFocus, yFocus;

    public Point(int x, int y){
        coordinates = new double[] {x, y};
    }

    public Point(double[] in){
        coordinates = in.clone();
        transformedCoordinates = new int[2];
        transform(15,1,0,0);
    }

    public void transform(int scaleXAxis, int scaleYAxis, int xCenter, int yCenter) {
        if (scaleXAxis > 0) xScale = scaleXAxis;
        if (scaleYAxis > 0) yScale = scaleYAxis;
        xFocus = xCenter;
        yFocus = yCenter;
        transformedCoordinates[0] = (int) (coordinates[0] * xScale + 500 + xFocus);
        transformedCoordinates[1] = (int) ((coordinates[1]) * -1 * yScale + 350 + yFocus);
    }


}
