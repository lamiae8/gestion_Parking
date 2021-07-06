package myParking;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.logging.Level;
import java.util.logging.Logger;


public class mouvement {


    public static void moveIN(AccesVoiture c , int xMax) throws InterruptedException
    {

        while(c.x<=xMax)
        {

               /*   if(AccesVoiture.state==true &&  c.x<=AccesVoiture.postionExitCar)
                {
                    try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AccesVoiture.class.getName()).log(Level.SEVERE, null, ex);
                }
              }*/





            c.x+=10;
            c.setLocation(c.x, c.y);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(AccesVoiture.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


    }

    public static void parking(AccesVoiture c) throws InterruptedException {
        while(c.y>=50)
        {
            c.setIconParking(c.id);
            c.y-=10;
            c.setLocation(c.x, c.y);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(AccesVoiture.class.getName()).log(Level.SEVERE, null, ex);
            }

        }



    }

    public static void exitParking(AccesVoiture c  )
    {




        while(c.y<=220)
        {
            c.y+=10;
            c.setLocation(c.x, c.y);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(AccesVoiture.class.getName()).log(Level.SEVERE, null, ex);
            }

        }



    }

    public static void moveOut(AccesVoiture c)
    {
        while(c.x<=1080)
        {
            c.setIconExitParking(c.id);
            c.x+=10;
            c.setLocation(c.x, c.y);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(AccesVoiture.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        c.x=-200;
        c.y=160;

    }



}


