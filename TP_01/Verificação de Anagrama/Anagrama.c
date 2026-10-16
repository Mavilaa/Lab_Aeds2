#include<stdio.h>
#include<stdbool.h>

bool palavras(char palavra1[], char palavra2[], int tamanho){
    bool ok = false;
    bool condicao = false;
    int contar =0;
  
    if(palavra1[0] >= 'A' && palavra1[0]<= 'Z'){
        palavra1[0] = palavra1[0]+32;
    }
    if(palavra2[0] >= 'A' && palavra2[0]<= 'Z'){
        palavra2[0] = palavra2[0]+32;
    
}
for(int x = 0; x< tamanho; x++){
for(int y =0; y < tamanho; y++){
if(palavra1[x] == palavra2[y] && !ok){

contar++;
ok = true;
}
}
ok = false;
}
if(contar == tamanho){

condicao = true;

}

return(condicao);
}
bool fim(char palavra[]){
    bool ok  = false;
    if(palavra[3] == '\n'){

        palavra[3] = '\0';
    }
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
        char palavra1[100] = "";
        char palavra2[100] = "";
        bool condicao = false;
        fgets(palavra, 100, stdin);
        while(!fim(palavra)){
            int n = 0;
            int x = 0;
            int i = 0;
            int j =0;
        while(palavra[n] != '\0'){
            n++;
        }
    
        if(palavra[n-1] == '\n'){
            palavra[n-1] = '\0';
        }
    

        while(palavra[x] != ' ' && palavra[x] != '\0'){
            palavra1[x] = palavra[x];
            x++;
        }
        j = x;
    
        palavra1[x] = '\0';
        
        x++; // pula o espaço em branco que separa as strings
    

        while(palavra[x] != '\0'){
            palavra2[i] = palavra[x];
            i++;
            x++;
        }
     
        palavra2[i] = '\0';
        
        if(j == i){
            printf(palavras(palavra1, palavra2, j)?"SIM\n":"NAO\n");            
            
        }
        else{

            printf("%s", "NAO\n");
        
        }
        fgets(palavra, 100, stdin);
        
    }
    
        return 0;
    
}