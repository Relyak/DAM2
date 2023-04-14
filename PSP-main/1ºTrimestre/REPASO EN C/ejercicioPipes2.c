#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#define LEE 0
#define ESCRIBE 1
//CREA 2 HIJOS, UNO SUMA MULTIPLOS DE 4 Y OTRO LO DEMÁS
//SI EL PADRE DEVUELVE 0, SE ACABA Y DEVUELVE AMBAS SUMAS


//PARA ESCRIBIR O LEER w/r(SI ES LECTURA O ESCRITURA[0||1],&AQUI LA VARIABLE, sizeof(variable));
int main(void )
{
	
	int tuberia1[2];
	int tuberia2[2];
	
	//tuberias leer/escribir
        pipe(tuberia1);
        pipe(tuberia2);
        
	//eso es lo que se utiliza para que si salgan numeros aleatorios(?) (srand==math.Random)
        srand(time(NULL));

        int entero=0;
        int fin = 0;
        
        int suma=0;
        int suma2=0;
        
	   int proceso = fork();
	   	//error
	   	if (proceso == -1){
	   		return 2;
	   	} else if (proceso == 0){
	   	//lee hazta que el padre envíe un 0 el primer hijo lee lo que recibe del padre
		   	while(0!=read(tuberia1[LEE],&entero, sizeof(entero))){
	  			//suma los múltiplos de 4
				//AQUI EL PRIMER HIJO VA SUMANDO LOS MULTIPLOS DE 4           		
		   		if(entero%4==0 && entero!=0){
			       	printf("\tEl HIJO1 recibe multiplos: %d\n",entero);
			       	suma=suma+entero;
			       	
				}if (entero==0){
					//CUANDO EL PADRE LE PASA 0, DEVUELVE LA SUMA DE TODOS LOS MULTIPLOS YA SUMADOS
					//SE LO ENVIA CON WRITE
					write(tuberia1[ESCRIBE], &suma, sizeof(suma));
					exit(0); 
				}
		   	}
           	
		} else{
			//CREA EL SEGUNDO HIJO 
			 if (fork() == 0){ 
			 	//AQUI LO MISMO, EL PADRE PASA NUMEROS Y SI ES 0 TERMINA, SI NO ES MULTIPLO DE 4 LOS VA SUMANDO
			      	while(0!=read(tuberia2[0], &entero, sizeof(entero))){
			      			//SUMA LOS NUMEROS QUE NO SON MULTIPLOS DE 4
						if(entero%4!=0){
							printf("\tEl HIJO2 recibe normales: %d\n",entero);
							suma2=suma2+entero;
						} if (entero==0){
							// OPCIONAL close(tuberia2[LEE]);
							write(tuberia2[ESCRIBE], &suma2, sizeof(suma2));	
							exit(0);
						}
			      	}
			}
			//ESTOY EN EL PADRE Y ESTOY ENVIANDO LA INFORMACION A LOS HIJOS
			for(int i=0;i<10;i++){
				//GENERANDO NUMEROS DEL 1 AL 100 DE FORMA ALEATORIA
				entero=rand() % 100;
				if(entero%4==0){
					write(tuberia1[ESCRIBE], &entero, sizeof(entero)); //escribo en pipe DE MULTIPLES DE 4
					printf("El PADRE ENVIA MENSAJE AL HIJO multiplos ...%d\n", entero);

				}else{
					write(tuberia2[ESCRIBE], &entero, sizeof(entero)); //escribo en pipe DE MULTIPLES !=4
					printf("El PADRE ENVIA MENSAJE AL HIJO2 normales ...%d\n",entero);
				}
			}
			//ESCRIBO AQUI EL 0 PARA QUE TERMINE EL PROGRAMA YA QUE DE LOS NUMEROS ALEATORIOS NO ENVIARÉ NUNCA UN 0
			write(tuberia1[ESCRIBE], &fin, sizeof(fin));
			write(tuberia2[ESCRIBE], &fin, sizeof(fin));
			//ESPERA A LOS DOS HIJOS TERMINAR
			wait(NULL);//ESPERANDO HIJO
			wait(NULL);//ESPERANDO HIJO
			
			close(tuberia1[ESCRIBE]);
			close(tuberia2[ESCRIBE]);
			
			read(tuberia1[LEE], &suma, sizeof(suma));
			read(tuberia2[LEE], &suma2, sizeof(suma2));
			
			printf("\tSuma FINAL MULTIPLOS: %d\n",suma);
			printf("\tSuma FINAL NORMALES: %d\n",suma2);
		
		
	}
     
}
