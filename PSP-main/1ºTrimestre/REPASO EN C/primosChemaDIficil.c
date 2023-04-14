

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(){
	//este codigo no lo entiende nadie pero si cambias n cambias el numero de hijos 
	int n=2, ini=100000,fin=200000, div=(fin-ini)/n;
	for (int i=0;i<n;i++){
		if (fork()==0) {
			for (int j=ini+(div*i);j<ini+(div*(i+1));j++){
				int cont=0;
				for (int k=1;k<=j;k++){
					if (j % k == 0) {
					cont++;
					}
				}	
				if (cont==2) printf("%d \t",j);
			}
			exit(0);
		}
	}
	for (int i=0;i<n;i++) wait(i);	
}
