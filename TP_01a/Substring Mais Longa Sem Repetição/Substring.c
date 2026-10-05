#include<stdio.h>
#include<stdbool.h>

int substring(char* palavra, int n){
    int maior = 0;
    int m =0;
    bool a  =true;
    for(int x = 0; x < n; x++){
        maior = 0;
        a = true;
        for(int y = x; y < n && a; y++){
            for(int z = x; z < y && a; z++){
                if(palavra[z] == palavra[y]){
                    a = false;
                }
            }        
            maior = maior+(a ? 1 : 0);
        }
        if(maior > m) m = maior;
    }
    return(m);
}


bool fim(char palavra[]){
    bool a = false;
    if(palavra[3] == '\n'){
        palavra[3] = '\0';
    }
    if(palavra[0] == 'F' && palavra[1] == 'I' && palavra[2] == 'M' && palavra[3] == '\0'){
        a = true;
    }
    else{
        a = false;   
    }

    return(a);
}




int main(){
    char palavra[100] = "";
    int n= 0;
    int a = 0;
    fgets(palavra, 100, stdin);
    while(!fim(palavra)){
        n = 0;
        while(palavra[n] != '\0' && palavra[n] != '\n') n++;

        printf("%d\n", substring(palavra, n));
        fgets(palavra, 100, stdin);
    }




 
    





    
}