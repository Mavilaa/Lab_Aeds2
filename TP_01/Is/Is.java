import java.util.*;
public class Is {

    static boolean vogal(String palavra){
        int v = 0;
        boolean ok = false;
        for(int i = 0; i < palavra.length(); i++){
            char c = palavra.charAt(i);
            if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u'||
               c=='A'||c=='E'||c=='I'||c=='O'||c=='U'){
                v++;
            }
        }
        if(v == palavra.length()){

            ok = true;
        }
        return (ok);
    }

    static boolean consoante(String palavra){
        int c = 0;
        boolean ok = false;
        for(int i = 0; i < palavra.length(); i++){
            char ch = palavra.charAt(i);
            if(!(ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u'||
            ch=='A'||ch=='E'||ch=='I'||ch=='O'||ch=='U') && ((ch>='A' && ch<='Z') || (ch>='a' && ch<='z'))){
                c++;
            }
            if(c == palavra.length()){

                ok = true;
            }
        }
        return (ok);
    }

    static boolean inteiro(String palavra){
        int x = 0;
        boolean ok = false;
        for(int i = 0; i < palavra.length(); i++){
            char c = palavra.charAt(i);
            if((i == 0 && (c=='-' || c=='+')) || (c>='0' && c<='9')){
                x++;
            }
        }
        if(x == palavra.length()){

            ok = true;
        }
        return (ok);
    }

    static boolean real(String palavra){
        int r = 0;
        int ponto = 0;
        boolean ok = false;
        for(int i = 0; i < palavra.length(); i++){
            char c = palavra.charAt(i);
            if((c>='0' && c<='9') || (i == 0 && (c=='-' || c=='+')) || c=='.' || c==','){
                r++;
            }
            if(c=='.' || c==','){
                ponto++;
            }
        }
        if(r == palavra.length() && ponto <=1){
            ok = true;
        }
        return (ok);
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
            System.out.print(vogal(palavra)? "SIM ":"NAO ");
            System.out.print(consoante(palavra)? "SIM ":"NAO ");
            System.out.print(inteiro(palavra)? "SIM ":"NAO ");
            System.out.print(real(palavra)? "SIM":"NAO");
            System.out.print("\n");
            palavra = sc.nextLine();
        }
        sc.close();
    }
}