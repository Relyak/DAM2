package básicos;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ConAnimaciónConTimer implements ActionListener{
	
	public static void main(String[] args) throws IOException {
		new ConAnimaciónConTimer();
	}
	
	BufferedImage img;
	JLabel etiqueta1;
	int i = 0;
	
	public ConAnimaciónConTimer () throws IOException {
		// Crear la ventana de la aplicacion
		JFrame ventana = new JFrame("Titulo de la ventana");
		ventana.setSize(800, 400);
		ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Crear los componentes
		img = ImageIO.read(ConImagen.class.getResource("/recursos/megaman.png"));
		etiqueta1 = new JLabel(new ImageIcon(img.getSubimage(0, 0, 200, 192)));
		
		etiqueta1.setBorder(BorderFactory.createLineBorder(Color.red));
		etiqueta1.setForeground(Color.BLUE);
				
		// Crear un contenedor
		JPanel panelDeContenido = new JPanel();

		// Asociar los componentes al contenedor para
		// que los muestre en su interior
		panelDeContenido.add(etiqueta1);

		// Asociar el contenedor a la ventana para
		// que le muestre en su interior
		ventana.setContentPane(panelDeContenido);
		
		// Hacer visible la ventana
		ventana.setVisible(true);
		
		int delay = 200; //milliseconds
		
		// new Timer(delay, taskPerformer).start();
		// taskPerformer es algo que implemente ActionListener
		new Timer(delay, this).start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		i++;
		if(i==4)i=0;
		etiqueta1.setIcon(new ImageIcon(img.getSubimage((i*200), 0, 200, 192)));
	}	
}