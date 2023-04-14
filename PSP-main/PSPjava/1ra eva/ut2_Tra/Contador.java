package ut2_Tra;

import javax.swing.JLabel;

public class Contador implements Runnable {
    private static final long INTERVALOSEGUNDOS =100;
    private static final long INTERVALOMILESIMAS =100;
    JLabel _salida;
    int cont_segundos=0;
    int cont_milesimas=0;
    int cont_minutos=0;
    public Contador(JLabel salida){
        _salida=salida;
    }
    @Override
    public void run() {
        String devolver="";
        
        // TODO Auto-generated method stub
        try{
            while (true){
                devolver=cont_minutos+":"+cont_segundos+":"+cont_milesimas;
                _salida.setText(devolver+"");
                Thread.sleep(4);
                cont_milesimas+=4;
                if(cont_milesimas==1000){
                    cont_milesimas=0;
                    cont_segundos+=1;
                }
                
                if(cont_segundos==60){
                   cont_minutos+=1;
                }
    
                }
            
        }catch(Exception e){
            _salida.setText("0:000");
        }
        
    }
    public void incrementa() {
    }
}
