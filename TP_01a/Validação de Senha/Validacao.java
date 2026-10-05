import java.util.*;

public class Validacao {
    public static boolean senha(String senha){
        boolean ok = false;
        int n = senha.length();
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;

        for(int x = 0; x < n; x++){
            if(senha.charAt(x) >= 'A' && senha.charAt(x) <= 'Z'){
                a++;
            }
            if(senha.charAt(x) >= 'a' && senha.charAt(x) <= 'z'){
                b++;
            }
            if(senha.charAt(x) == '!' || senha.charAt(x) == '@' || senha.charAt(x) == '#' || senha.charAt(x) == '^'){
                c++;
            }
            if(senha.charAt(x) >= '0' && senha.charAt(x) <= '9'){
                d++;
            }
        }

        if(a >= 1 && b >= 1 && c >= 1 && d >= 1 && n >= 8){
            ok = true;
        }
        else{
            ok = false;
        }
        return(ok);
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
        boolean primeira = true;
        palavra = sc.nextLine();
        while(!fim(palavra)){
            if(!primeira){
                System.out.print("\n");
            }
            System.out.print(senha(palavra)? "SIM" : "NAO");
            primeira = false;
            palavra = sc.nextLine();
        }
        sc.close();
    }
}
