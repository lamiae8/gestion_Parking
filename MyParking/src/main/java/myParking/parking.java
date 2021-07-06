package myParking;


import java.awt.*;
import java.util.concurrent.Semaphore;
import javax.swing.*;


public class parking extends JPanel {

    ImageIcon image;
    ImageIcon Car;

    public parking(){


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        image =new ImageIcon("src/img/plaa.png");
        image.paintIcon(this, g, WIDTH,WIDTH);


    }



}
