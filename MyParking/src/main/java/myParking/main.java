package myParking;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;



/**
 *
 * @author fatah
 */
public class main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws SQLException {


        JFrame frame = new JFrame("Parking Simulator");
        parking panel = new parking();
        frame.setContentPane(panel);
        panel.setLayout(null);
        frame.setSize(1090, 400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        AccesVoiture p = new AccesVoiture( 10000, TimeUnit.MILLISECONDS,1,"M6546/54" );
        AccesVoiture a = new AccesVoiture( 10000, TimeUnit.MILLISECONDS,3,"AS037/019" );
        AccesVoiture q = new AccesVoiture( 10000, TimeUnit.MILLISECONDS,2,"LM546/65" );
        AccesVoiture d = new AccesVoiture( 10000, TimeUnit.MILLISECONDS,4,"L464P/966" );



        Thread p1=new Thread(p);
        Thread p2=new Thread(a);
        Thread p3=new Thread(q);
        Thread p4=new Thread(d);
        panel.add(p);
        panel.add(a);
        panel.add(q);
        panel.add(d);
        p1.start();
        p2.start();
        p3.start();
        p4.start();

        frame.setVisible(true);


    }

}

