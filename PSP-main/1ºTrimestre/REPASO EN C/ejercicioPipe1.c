
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#define LECTURA 0
#define ESCRITURA 1

void main () {
    //creo el proceso
    pid_t proceso;
    //aqui un array de 2 para leer o escribir
    int tuberia[2];
    
    //Aleatoriedad
    srand(time(NULL));
    //crea una tubería entre dos procesos para leer o escribir
    pipe(tuberia);
    proceso =fork();
           int numAle=0,suma=0;
    switch (proceso) {
        case -1:
            printf("Error en la creacion del proceso");

        break;
        //estoy en el hijo
        case 0:        
            //mientras sea distinto a 17 leo 
            while(numAle!=17){
                read(tuberia[LECTURA],&numAle,sizeof(numAle));
                printf("\n Numero aleatorio recibido: %d",numAle);
                suma+=numAle;
            }
            close(tuberia[LECTURA]);
            write(tuberia[ESCRITURA],&suma,sizeof(suma));
        break;
        //estoy en el daddy
        default:
            
            //mientras sea distinto a 17 escribe
            while (numAle!=17) {
                numAle = rand()%21;
                write(tuberia[ESCRITURA], &numAle,sizeof(numAle));
            }
            //se cierra la tubería
            close(tuberia[ESCRITURA]);
            wait(NULL);
            //lee la tuberia
            read(tuberia[LECTURA],&numAle,sizeof(numAle));
            printf("\nEl padre ha recibido el total sumado: %d",numAle);
        break;
    }
}
