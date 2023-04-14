#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
//funcionan a la vez asi que no sirve de nada poner soy el proceso x
	void main () {
	    int NUMERO = 4;
	    for (int i = 0;i < NUMERO;i++) {
	    	//hace un fork dentro de la primera iteración, cuando i=0(primera iteracion) hace fork
		if(i==0){
		
			//dentro del if hago fork	
		    if (fork()==0) {
		        imprimePrimos(100000,125000);
		        exit(0);
		    }
		}
		//segunda iteracion
		else if(i==1){
			
		    if (fork()==0) {
		        imprimePrimos(125000,150000);
		        exit(0);
		    }
		}
		//tercera iteracion
		
		else if(i==2){
	
		    if (fork()==0) {
		        imprimePrimos(150000,175000);
		        exit(0);
		    }
		}
		//cuarta iteracion
		else{
	
		    if (fork()==0) {
		        imprimePrimos(175000,200000);
		        exit(0);
		    }
		}
	    }
	    
	    for (int i = 0;i < NUMERO;i++ ) {
		wait(0);
	    }

	}

		void imprimePrimos(int num1, int num2) {
		    for (num1;num1<num2;num1++) {
			    int y=2;
			    for (y;y<num1;y++) {
				if (num1%y==0) {
				    break;
				}
			    }
			    if (y>=num1) {
				printf("\n primai: %d ",num1);
			    }
			}
		}
