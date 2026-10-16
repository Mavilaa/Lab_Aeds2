#include<stdio.h>

int soma(int n , int digitos){
int digito = 0;
int sum= 0;
if(n > 0 && digitos > 0){

digito = digitos%10;
sum = digito+ soma(n-1, digitos/10);

}

return(sum);
}

int main(){
    int numero = 0;
    int n = 0;
    int num = 0;
    int sum = 0;
    int primeiro = 1;

    while(scanf("%d", &numero) != EOF){
        num = numero;
        n = 0;
        while(num > 0){
            num = num / 10;
            n++;
        }

        sum = soma(n, numero);

        if(!primeiro){
            printf("\n");
        }

        printf("%d", sum);
        primeiro = 0;
    }

    return 0;
}