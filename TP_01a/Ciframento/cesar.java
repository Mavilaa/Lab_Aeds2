import java.util.Scanner;
    
public class cesar{

public static String cifra (String palavra){
int tamanho = palavra.length();
char letra  = ' ';
String s = "";
for(int x = 0; x < tamanho; x++){
    letra = (char)(palavra.charAt(x)+3);
    s = s + letra;
}
return(s);
}
public static boolean fim(String palavra){
    boolean ok = false;
    if(palavra.charAt(0) == 'F' && palavra.charAt(1) == 'I' && palavra.charAt(2) == 'M'){
        ok = true;
    }
    return(ok);
}


public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String palavra = "";
    palavra = sc.nextLine();

    while(!fim(palavra)){
        System.out.println(cifra(palavra));
        palavra = sc.nextLine();
    }
    sc.close();
}
}
