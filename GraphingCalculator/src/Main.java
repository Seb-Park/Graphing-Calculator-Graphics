//Adapted from Basic Game Application from Mr. Chun


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Arrays;

public class Main implements Runnable, MouseListener, MouseWheelListener, MouseMotionListener, KeyListener {

    final int WIDTH = 1000;
    final int HEIGHT = 700;

    public JFrame frame;
    public JTextField inputArea;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    public Function mainFunction, otherFunction;
    public Grid grid;
    public Point min;

    public int scale = 1;

    public boolean isShift, up, down, left, right;
    public boolean scrollUp, scrollDown;

    public Main() {

        otherFunction = new Function(new ArrayList<Integer>(Arrays.asList(20,45,-3,13)), -50, 50, 0.1, "coefficients");
        mainFunction = new Function(new ArrayList<Integer>(Arrays.asList(1,2,3,4,5)), -50, 50, 0.1, "zeroes");
//        mainFunction = new Function(new ArrayList<Integer>(Arrays.asList(3,0,5,0,0,0,-21)), -50, 50, 0.1, "coefficients");
        mainFunction = new Function(new ArrayList<Integer>(Arrays.asList(3, 0, 2, 4, -21)), -50, 50, .1, "coefficients");
        min = new Point(LocalMinFinder.findFirstMax(new ArrayList<Integer>(Arrays.asList(3, 0, 2, 4, -21))));
        System.out.println(Arrays.toString(min.coordinates));

//        mainFunction = new Function(new ArrayList<Integer>(Arrays.asList(1,0,0)), -50, 50, 0.1);
//        grid = new Function(true, -100, 100, -100, 100);
//        mainFunction = new Function(new ArrayList<Integer>(Arrays.asList(1)), -50, 50, 0.1, "zeroes");
        grid = new Grid(-1000,1000,-1000,1000);
        setUpGraphics();
        canvas.addKeyListener(this);
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
        canvas.addMouseWheelListener(this);
        canvas.setFocusable(true);
    }

    public static void main(String[] args) {
        Main ex = new Main();
        new Thread(ex).start();
    }

    public void run() {

        while (true) {

            scale();
            render();
            pause(20);
        }
    }


    public void scale() {
        if (scrollUp) {
            mainFunction.transform(mainFunction.xScale + 3, mainFunction.yScale + 3, mainFunction.xFocus, mainFunction.yFocus);
        }
        if (scrollDown) {
            mainFunction.transform(mainFunction.xScale - 3, mainFunction.yScale - 3, mainFunction.xFocus, mainFunction.yFocus);
        }
        if (up) {
            if (isShift) {
                mainFunction.transform(mainFunction.xScale, mainFunction.yScale + 1, mainFunction.xFocus, mainFunction.yFocus);
            } else {
                mainFunction.transform(mainFunction.xScale, mainFunction.yScale, mainFunction.xFocus, mainFunction.yFocus + 7);
            }
        }
        if (down) {
            if (isShift) {
                mainFunction.transform(mainFunction.xScale, mainFunction.yScale - 1, mainFunction.xFocus, mainFunction.yFocus);
            } else {
                mainFunction.transform(mainFunction.xScale, mainFunction.yScale, mainFunction.xFocus, mainFunction.yFocus - 7);
            }
        }
        if (left) {
            if (isShift) {
                mainFunction.transform(mainFunction.xScale - 1, mainFunction.yScale, mainFunction.xFocus, mainFunction.yFocus);
            } else {
                mainFunction.transform(mainFunction.xScale, mainFunction.yScale, mainFunction.xFocus + 7, mainFunction.yFocus);
            }
        }
        if (right) {
            if (isShift) {
                mainFunction.transform(mainFunction.xScale + 1, mainFunction.yScale, mainFunction.xFocus, mainFunction.yFocus);
            } else {
                mainFunction.transform(mainFunction.xScale, mainFunction.yScale, mainFunction.xFocus - 7, mainFunction.yFocus);
            }
        }
        grid.transform(mainFunction.xScale,mainFunction.yScale,mainFunction.xFocus,mainFunction.yFocus);
        min.transform(mainFunction.xScale,mainFunction.yScale,mainFunction.xFocus,mainFunction.yFocus);
        otherFunction.transform(mainFunction.xScale,mainFunction.yScale,mainFunction.xFocus,mainFunction.yFocus);

    }

    public void graph() {

    }

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    private void setUpGraphics() {
        frame = new JFrame("Graph");
        inputArea = new JTextField();

        panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setLayout(new GridLayout(2,1));
//        panel.setLayout(null);

        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);
        panel.add(inputArea);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();


        System.out.println("DONE graphic setup");

    }

    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.lightGray);

        g.setStroke(new BasicStroke(1));

        for(int i = 0; i < grid.pointsX.length; i++){
            if(grid.pointsX[i][0] == 0){//This sets the line x = 0 to black and thick so you can see the axis
                g.setStroke(new BasicStroke(3));
                g.setColor(Color.black);
            }
            g.drawLine(grid.tx[i],grid.ty[0],grid.tx[i],grid.ty[0] - grid.tyl);
            if(grid.pointsX[i][0] == 0){
                g.setStroke(new BasicStroke(1));
                g.setColor(Color.lightGray);
            }
        }
        for(int i = 0; i < grid.pointsY.length; i++){
            if(grid.pointsY[i][1] == 0){//This sets the line x = 0 to black and thick so you can see the axis
                g.setStroke(new BasicStroke(3));
                g.setColor(Color.black);
            }
            g.drawLine(grid.tx[0],grid.ty[i],grid.tx[0]+grid.txl,grid.ty[i]);
            if(grid.pointsY[i][1] == 0){//This sets the line x = 0 to black and thick so you can see the axis
                g.setStroke(new BasicStroke(1));
                g.setColor(Color.lightGray);
            }
        }


        g.setStroke(new BasicStroke(2));
        g.setColor(mainFunction.functionColor);
//        g.drawPolygon(mainFunction.graphicalFunction);
        for (int i = 0; i < mainFunction.totalPoints - 1; i++) {
            g.drawLine(mainFunction.xCo[i], mainFunction.yCo[i], mainFunction.xCo[i + 1], mainFunction.yCo[i + 1]);
        }

        g.setColor(Color.RED);

        g.fillOval(min.transformedCoordinates[0]-(4), min.transformedCoordinates[1]-(int)(5), 10,10);

//        g.setColor(otherFunction.functionColor);
//
//        for (int i = 0; i < otherFunction.totalPoints - 1; i++) {
//            g.drawLine(otherFunction.xCo[i], otherFunction.yCo[i], otherFunction.xCo[i + 1], otherFunction.yCo[i + 1]);
//        }

//        for (int i = 0; i < grid.totalPoints - 1; i++) {
//            g.drawLine(grid.xCo[i], grid.yCo[i], grid.xCo[i + 1], grid.yCo[i + 1]);
//        }

//        for(double[] p : mainFunction.points) {
//            g.fillOval((int)(p[0]*scale*100),(int)((p[1]*scale)*-1)+HEIGHT*scale,10,10);
//        }

        g.dispose();

        bufferStrategy.show();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        scrollUp = false;
        scrollDown = false;
        right = false;
        up = false;
        left = false;
        down = false;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() < -1) {
//            up = true;
//            down = false;
//            left = false;
//            right = true;
//            isShift = true;
            scrollUp = true;
            scrollDown = false;
        }
        if (e.getWheelRotation() > 1) {
//            down = true;
//            up = false;
//            left = true;
//            right = false;
//            isShift = true;
            scrollDown = true;
            scrollUp = false;
        }
        if (e.getWheelRotation() >= -1 && e.getWheelRotation() <= 1) {
//            up = false;
//            down = false;
//            left = false;
//            right = false;
//            isShift = false;
            scrollDown = false;
            scrollUp = false;
        }
//        System.out.println(e.getWheelRotation());
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 16) {
            isShift = true;
        }
        if (e.getKeyCode() == 37) {
            left = true;
        }
        if (e.getKeyCode() == 38) {
            up = true;
        }
        if (e.getKeyCode() == 39) {
            right = true;
        }
        if (e.getKeyCode() == 40) {
            down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 16) {
            isShift = false;
        }
        if (e.getKeyCode() == 37) {
            left = false;
        }
        if (e.getKeyCode() == 38) {
            up = false;
        }
        if (e.getKeyCode() == 39) {
            right = false;
        }
        if (e.getKeyCode() == 40) {
            down = false;
        }
    }
}