#include <stdio.h>
#include <stdbool.h>

char* cifra(char palavra[], int n, int tamanho){
    if(n < tamanho){
        palavra[n] = (char)(palavra[n] + 3);
        return cifra(palavra, n + 1, tamanho);
    }
    return palavra;
}

bool fim(char palavra[]){
    bool ok = false;
    if(palavra[0] == 'F' && palavra[1] == 'I' && palavra[2] == 'M' && palavra[3] == '\0'){
        ok = true;
    }
    return ok;
}

int main(){
    char palavra[1000];
    fgets(palavra, 1000, stdin);
    int tamanho = 0;
    while(palavra[tamanho] != '\0' && palavra[tamanho] != '\n'){
        tamanho++;
    }
    palavra[tamanho] = '\0';

    while(!fim(palavra)){
        printf("%s\n", cifra(palavra, 0, tamanho-1));
        fgets(palavra, 1000, stdin);
        tamanho = 0;
        while(palavra[tamanho] != '\0' && palavra[tamanho] != '\n'){
            tamanho++;
        }
        palavra[tamanho] = '\0';
    }

    return 0;
}
