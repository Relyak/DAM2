package ut2_Tra;


import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.*;

public class Reloj extends JFrame {
    public Reloj(){
        super("Mi primera ventana");
        JLabel j1= new JLabel();
        j1.setText("0");
        JLabel j2= new JLabel();
        j1.setText("0");
        JLabel valores= new JLabel();
        valores.setText("Puntuación: \n");
        
        setLayout(new FlowLayout());

        getContentPane().add(j1);
        getContentPane().add(j2);
        JButton boton = new JButton("BOTON DEL JUGADOR 1");
        getContentPane().add(boton);
        JButton boton2 = new JButton("BOTON DEL JUGADOR 2");
        getContentPane().add(boton2);
        Contador puntos1= new Contador(j1);
        Contador puntos2= new Contador(j2);
        Thread t1= new Thread(puntos1);
        boton.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
            if(t1.isAlive()){
                j1.setText("Puntuación 1:");
                t1.resume();
            }else{
              
              t1.start();
            }
         }
        });
        JButton bPara1 = new JButton("¡Para!");
        getContentPane().add(bPara1);
        bPara1.addActionListener(new ActionListener(){
            
            @Override
                public void actionPerformed(ActionEvent e){

                    t1.suspend();
                }
        });

        setSize(500, 400);
        setVisible(true); //
    }
    public static void main(String[] args) {
        new Reloj();
    }
}
