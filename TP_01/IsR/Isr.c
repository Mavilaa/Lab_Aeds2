#include<stdio.h>
#include<stdbool.h>

int conta_vogal(char palavra[], int n){
    if(n < 0) return 0;
    int v = 0;
    if(palavra[n] == 'a' || palavra[n] == 'e' || 
       palavra[n] == 'i'|| palavra[n] == 'o' || palavra[n] == 'u' ||
       palavra[n] == 'A' || palavra[n] == 'E' || 
       palavra[n] == 'I'|| palavra[n] == 'O' || palavra[n] == 'U'){
        v = 1;
    }
    return v + conta_vogal(palavra, n-1);
}

bool vogal(char palavra[], int n){
    bool ok = false;
    if(conta_vogal(palavra, n) == (n+1)){

        ok = true;
    
    }
    else{ok = false;}
    return (ok);
}

int conta_consoante(char palavra[], int n){
    if(n < 0) return 0;
    int c = 0;
    if((palavra[n]!='a' && palavra[n]!='e' && palavra[n]!='i' && palavra[n]!='o' && palavra[n]!='u'&&
        palavra[n]!='A' && palavra[n]!='E' && palavra[n]!='I' && palavra[n]!='O'&& palavra[n]!='U')){
    if((palavra[n]>='A' && palavra[n]<='Z') || (palavra[n]>='a' && palavra[n]<='z')){
        c = 1;
    }
}
    return c + conta_consoante(palavra, n-1);
}

bool consoante(char palavra[], int n){
    bool ok = false;
    if(conta_consoante(palavra, n) == (n+1)){

        ok = true;
    
    }
    else{ok = false;}
    return (ok);
}

int conta_inteiro(char palavra[], int n){
    if(n < 0) return 0;
    int i = 0;
    if((n == 0 && (palavra[n]=='-' || palavra[n]=='+')) || (palavra[n]>='0' && palavra[n]<='9')){
        i = 1;
    }
    return i + conta_inteiro(palavra, n-1);
}

bool inteiro(char palavra[], int n){
    bool ok = false;
    if(conta_inteiro(palavra, n) == (n+1)){

        ok = true;
    
    }
    else{ok = false;}
    return (ok);
}

int conta_real(char palavra[], int n, int ponto){
    if(n < 0) return 0;
    int r = 0;
    if((palavra[n]>='0' && palavra[n]<='9') ||
       ((palavra[n]=='-' || palavra[n]=='+')) ||
       palavra[n]=='.' || palavra[n]==','){
        r = 1;
    }
    if(palavra[n]=='.' || palavra[n]==','){
        ponto++;
    }
    if(ponto >1){
        r = 0;
    }
    return r + conta_real(palavra, n-1, ponto);
}

bool real(char palavra[], int n){
    bool ok = false;
    if(conta_real(palavra, n, 0) == (n+1)){

        ok = true;
    
    }
    else{ok = false;}
    return (ok);
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
    char palavra[1000] = "";
    int n= 0;
    int a = 0;
    fgets(palavra, 1000, stdin);
    while(!fim(palavra)){
        n = 0;
        while(palavra[n] != '\0' && palavra[n] != '\n') n++;
        if(palavra[n] == '\n') palavra[n] = '\0';
        printf(vogal(palavra, n-1)?"SIM ":"NAO ");
        printf(consoante(palavra, n-1)?"SIM ":"NAO " );
        printf(inteiro(palavra, n-1)?"SIM ":"NAO ");
        printf(real(palavra, n-1)?"SIM\n":"NAO\n");
        fgets(palavra, 1000, stdin);
    }
}