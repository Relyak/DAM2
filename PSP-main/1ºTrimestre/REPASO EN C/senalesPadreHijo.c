#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>
//defino una funcion que recibirá un int
void cogeSenal(int);

int main () {
   signal(SIGINT, cogeSenal);
   signal(SIGKILL, cogeSenal);
	//bucle infinito que simplemente muestra el pid del proceso y espera un segundo
   while(1) {
      printf("Interrúmpeme si puedes, soy el proceso %d \n",getpid());
      printf("Going to sleep for a second...\n");
      sleep(1); 
   }
   return(0);
}
//funcion ya definida antes que recibe un proceso
void cogeSenal(int proceso) {
   
   //si hago control c se hace un fork
   if(proceso==2){
      printf("No me puedes cerrar así\n");
      fork();
   }

   //exit(1);
}
