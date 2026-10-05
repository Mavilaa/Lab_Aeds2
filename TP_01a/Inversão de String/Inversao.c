#include<stdio.h>
#include<math.h>
#include<stdbool.h>

char* invert(char palavra[]){
int tamanho = 0; 
int y = 0;
int inicio = 0;
while(palavra[tamanho] != '\0'){
tamanho++;
}
int fim = tamanho -1;
while(inicio < fim){
    char temp = palavra[inicio];
    palavra[inicio] = palavra[fim];
    palavra[fim] = temp;
    inicio++;
    fim--;
}

return(palavra);
}

bool fim(char palavra[]){
bool ok  = false;
if(palavra[0] == 'F' && palavra[1] == 'I' && palavra[2] == 'M' && palavra[3] == '\0'){

ok = true;
}
else{

    ok = false;
}


return(ok);
}

int main(){
char palavra[100] = "";
fgets(palavra, 100, stdin);
int tamanho = 0;
while(palavra[tamanho] != '\0'){
    tamanho++;
}
if(tamanho > 0 && palavra[tamanho - 1] == '\n'){
    palavra[tamanho - 1] = '\0';
}
while(!fim(palavra)){
invert(palavra);
printf("%s\n", palavra);
fgets(palavra, 100, stdin);
int tamanho = 0;
while(palavra[tamanho] != '\0'){
    tamanho++;
}

if(tamanho > 0 && palavra[tamanho - 1] == '\n'){
    palavra[tamanho - 1] = '\0';
}   
}
}