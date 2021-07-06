package myParking;

import java.awt.Dimension;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
/**
 *
 * @author HP
 */
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import myParking.mouvement;




public class AccesVoiture extends JLabel implements Runnable {



    /**
     *
     */
    static TimeUnit unit= null;
    long sleepTime = 0;

    ImageIcon car ;
    voitureDAO s;
    int id ;
    int x;
    int y;
    private static Time entrer;
    private static Time sortir;
    private  String matricule;



    private static int NBR_PLACES = 1;
    private static int NBR_RAMPE  = 1;
    private static Semaphore semPlace = new Semaphore( NBR_PLACES , true );
    private static Semaphore semRampe = new Semaphore( NBR_RAMPE  , true );
    public AccesVoiture ( long time , TimeUnit unit,int id,String mat ) throws SQLException {
        this.s=new voitureDAO();
        this.unit = unit;
        this.sleepTime = time;
        this.id=id;
        this.x=-200 ;
        this.y=230 ;
        car=new ImageIcon("src/img/car"+this.id+".png");
        this.setIcon(car);
        Dimension size = this.getPreferredSize();
        setBounds(x, y, size.width, size.height);
        this.entrer=null;
        this.sortir=null;
        this.matricule=mat;
    }

    public String getMat(){
        return this.matricule;
    }

    public  int getId(){
        return this.id;
    }

    public static Time getDateEntree() {
        entrer = Time.valueOf(LocalTime.now ());
        return entrer;
    }

    public static Time getDateSortie() {
        sortir = Time.valueOf(LocalTime.now () );
        return sortir;
    }

    /** reference time
     */
    private static final long referenceTime = System.currentTimeMillis();

    private String getAccesVoitureDesc() {
        return "[" + ( System.currentTimeMillis() - referenceTime ) + "] (Proc : " + Thread.currentThread().getName() + ")";
    }

    public void run(){
        try {
            System.out.println( getAccesVoitureDesc() + " veut rentrer dans le parking !");
            this.entrer_parking();
            System.out.println( getAccesVoitureDesc() + " veut sortir du parking !");
            this.sortir_parking();
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }

    }

    public void entrer_parking() throws InterruptedException, SQLException {
        try {
            semPlace.acquire();
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println( getAccesVoitureDesc()  + " a donné sa carte, ATTENTE");
        try {
            this.unit.sleep( this.sleepTime );
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println( getAccesVoitureDesc() + " a fini son attente");
            this.s.save(this);
        }


        semRampe.acquire();
        System.out.println( getAccesVoitureDesc() + " passe sur la rampe");
        mouvement.moveIN(this, 510);
        mouvement.parking(this);
        semRampe.release();
        System.out.println( getAccesVoitureDesc() + " est descendu de la rampe et est garé a sa place !");
        this.unit.sleep(((int)Math.random()*10000)+5000);

    }

    public void sortir_parking() throws InterruptedException, SQLException {
        semRampe.acquire();
        mouvement.exitParking(this);

        System.out.println( getAccesVoitureDesc() + " passe sur la rampe (pour sortir)");
        mouvement.moveOut(this);
        semRampe.release();
        System.out.println( getAccesVoitureDesc() + " est descendu de la rampe (pour sortir)");
        semPlace.release();
        this.s.update(this);
        System.out.println( getAccesVoitureDesc() + " est sorti du parking...Place liberée !");

    }


    public void setIconParking(int i)
    {
        car=new ImageIcon("src/img/car"+i+"_"+i+".png");
        this.setIcon(car);
    }
    public void setIconExitParking(int i)
    {
        car=new ImageIcon("src/img/car"+this.id+".png");
        this.setIcon(car);
    }


}

