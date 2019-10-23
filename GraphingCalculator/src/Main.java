//Adapted from Basic Game Application from Mr. Chun


import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main implements Runnable, MouseListener, MouseWheelListener, MouseMotionListener, KeyListener {

    final int WIDTH = 1000;
    final int HEIGHT = 700;

    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    public Function mainFunction;

    public int scale = 1;

    public boolean isShift, up, down, left, right;

    public static void main(String[] args) {
        Main ex = new Main();
        new Thread(ex).start();
    }

    public Main() {

        mainFunction = new Function(-500,500,0.05);
        setUpGraphics();
        canvas.addKeyListener(this);
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
        canvas.addMouseWheelListener(this);
        canvas.setFocusable(true);
    }

    public void run() {

        while (true) {

            scale();
            render();
            pause(20);
        }
    }


    public void scale()
    {
        if(up){
            if(isShift) {
                mainFunction.transform(mainFunction.xScale + 1, mainFunction.yScale, mainFunction.xFocus, mainFunction.yFocus);
            }
            else{
                mainFunction.transform(mainFunction.xScale,  mainFunction.yScale, mainFunction.xFocus, mainFunction.yFocus+1);
            }
        }
        if(down){
            if(isShift) {
                mainFunction.transform(mainFunction.xScale - 1,  mainFunction.yScale, mainFunction.xFocus, mainFunction.yFocus);
            }
            else{
                mainFunction.transform(mainFunction.xScale,  mainFunction.yScale, mainFunction.xFocus, mainFunction.yFocus-1);
            }
        }
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
        g.setStroke(new BasicStroke(5));
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

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(e.getWheelRotation()<-1){
            up = true;
            down = false;
        }
        if(e.getWheelRotation()>1){
            down = true;
            up = false;
        }
        if(e.getWheelRotation()>=-1&&e.getWheelRotation()<=1){
            up = false;
            down = false;
        }
        System.out.println(e.getWheelRotation());
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
        if(e.getKeyCode() == 16){
            isShift = true;
        }
        if(e.getKeyCode() == 37){
            left = true;
        }
        if(e.getKeyCode() == 38){
            up = true;
        }
        if(e.getKeyCode() == 39){
            right = true;
        }
        if(e.getKeyCode() == 40){
            down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == 16){
            isShift = false;
        }
        if(e.getKeyCode() == 37){
            left = false;
        }
        if(e.getKeyCode() == 38){
            up = false;
        }
        if(e.getKeyCode() == 39){
            right = false;
        }
        if(e.getKeyCode() == 40){
            down = false;
        }
    }
}