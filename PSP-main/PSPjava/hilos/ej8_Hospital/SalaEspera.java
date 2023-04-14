package ej8_Hospital;

//Sala de espera de hospital: Implementa un programa en Java que simule una sala de espera de hospital. 
//Cada paciente debe ser un thread que llega al hospital, espera en la sala de espera y luego es atendido por un doctor aleatorio. 
//También debe haber un thread para la enfermero/a que se encarga de guiar a los pacientes. 
//Explicación para implementar: Crea la clase Hospital que contenga un Array de cadenas con nombre de pacientes. 
//Ese array es una región crítica. 
//El Thread Enfermero/a genera genera pacientes aleatorios y llama a un método del hospital para añadir al paciente. 
//Los threads Doctor/a pide pacientes a la clase Hospital.

public class SalaEspera {
    public static void main(String[] args) {
        Hospital hospital = new Hospital();

        // Creamos enfermero
        Thread enfermero = new Thread(new Enfermero(hospital));
        enfermero.start();

        // Creamos los doctores
        int numDoctores = 3;
        Thread[] doctores = new Thread[numDoctores];
        for (int i = 0; i < numDoctores; i++) {
            doctores[i] = new Thread(new Doctor(hospital, "doctor "+ (i+1)));
            doctores[i].start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Fin
        enfermero.interrupt();
        for (Thread doctor : doctores) {
            doctor.interrupt();
        }
    }
}