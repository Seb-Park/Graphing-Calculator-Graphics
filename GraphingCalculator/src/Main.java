//Adapted from Basic Game Application from Mr. Chun


import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main implements Runnable {

    final int WIDTH = 1000;
    final int HEIGHT = 700;

    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    public Function mainFunction;

    public int scale = 1;

    public static void main(String[] args) {
        Main ex = new Main();
        new Thread(ex).start();
    }

    public Main() {

        mainFunction = new Function(-500,500,0.5);
        setUpGraphics();

    }

    public void run() {

        while (true) {

            moveThings();
            render();
            pause(20);
        }
    }


    public void moveThings()
    {

    }

    public void graph(){

    }

    public void pause(int time ){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    private void setUpGraphics() {
        frame = new JFrame("Application Template");

        panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setLayout(null);

        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);

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

        g.setColor(mainFunction.functionColor);
//        g.drawPolygon(mainFunction.graphicalFunction);
        for (int i = 0; i < mainFunction.totalPoints-1; i++) {
            g.drawLine(mainFunction.xCo[i],mainFunction.yCo[i],mainFunction.xCo[i+1],mainFunction.yCo[i+1]);
        }

//        for(double[] p : mainFunction.points) {
//            g.fillOval((int)(p[0]*scale*100),(int)((p[1]*scale)*-1)+HEIGHT*scale,10,10);
//        }

        g.dispose();

        bufferStrategy.show();
    }
}