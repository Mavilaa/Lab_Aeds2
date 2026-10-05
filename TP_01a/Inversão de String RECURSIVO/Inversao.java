import java.util.*;
public class Inversao {
    public static String invert(String palavra, int n){
        char invertida = ' ';
        String i = "";
        if(n >= 0){
            invertida = palavra.charAt(n);
            i = invertida +invert(palavra, n-1);
        }
        return(i);
    }
    public static boolean fim(String palavra){
        boolean ok = false;
        if(palavra.length() >= 3){
        if(palavra.charAt(0) == 'F' && palavra.charAt(1) == 'I' && palavra.charAt(2) == 'M'){
            ok = true;
        }
    }
        return(ok);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String palavra = "";
        palavra = sc.nextLine();
        while(!fim(palavra)){
            System.out.println(invert(palavra, palavra.length()-1));
            palavra = sc.nextLine();
        }
        sc.close();
    }
}
    

