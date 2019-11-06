import java.util.Arrays;

public class Grid {

    public int[][] pointsY;
    public int[][] pointsX;
    public int[] tx, ty;
    public int xScale = 15, yScale = 1;

    int xFocus, yFocus, xLineLength, yLineLength;
    int txl, tyl;


    public Grid(int minX, int maxX, int minY, int maxY){
        xLineLength = maxX - minX;
        yLineLength = maxY - minY;
        pointsX = new int[maxX - minX][2];
        pointsY = new int[maxY - minY][2];
        tx = new int[maxX - minX];
        ty = new int[maxY - minY];
        int xCount=0, yCount=0;
        for(int y = minY; y < maxY; y++){
            pointsY[yCount] = new int[]{minX,y};
            ty[yCount]= (int)((pointsY[yCount][1])*-1+350+yFocus);
//            System.out.println(Arrays.toString(pointsY[yCount]));

            yCount++;
        }
        for(int x = minX; x < maxX; x++){
            pointsX[xCount] = new int[]{x,minY};
            tx[xCount]= (int)(pointsX[xCount][0]*xScale + 500 + xFocus);
//            System.out.println(Arrays.toString(pointsX[xCount]));

            xCount++;
        }
    }

    public void transform(int scaleXAxis, int scaleYAxis, int xCenter, int yCenter){
        if(scaleXAxis>0){xScale = scaleXAxis;}
        else {xScale = 1; }
        if(scaleYAxis>0){yScale = scaleYAxis;}
        else {yScale = 1; }
        xFocus = xCenter;
        yFocus = yCenter;
        for(int i  = 0; i < tx.length; i++){
            tx[i]= (int)(pointsX[i][0]* xScale + 500 + xFocus);
        }
        for(int i  = 0; i < ty.length; i++){
            ty[i]= (int)((pointsY[i][1])*-1*yScale+350+yFocus);
        }
        txl = xLineLength * xScale;
        tyl = yLineLength * yScale;


    }

}
