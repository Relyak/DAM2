package ut2_Tra;


import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


import java.awt.event.*;

public class GUI extends JFrame {
    public GUI(){
        super("Mi primera ventana");
        JLabel l= new JLabel();
        l.setText("Hola mundo!");
        
        setLayout(new FlowLayout());

        getContentPane().add(l);

        JButton b = new JButton("¡Pulsame!");
        getContentPane().add(b);

        b.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
                l.setText("PURSAMO");
           } 
        });

        setSize(500, 400);
        setVisible(true); //
    }
    public static void main(String[] args) {
        new GUI();
    }
}
