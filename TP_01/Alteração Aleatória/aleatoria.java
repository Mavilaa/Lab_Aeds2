import java.util.*;
public class aleatoria {

    public static String random(String a, char p, char s){
       String resultado  = "";
        for(int x =0; x < a.length(); x++){
            if(a.charAt(x) == p){   
                resultado = resultado+s;
            }
            else{
                resultado = resultado+ a.charAt(x);
            
            }
        }
        return(resultado);
    }

    public static boolean fim(String palavra){
        boolean ok = false;
        if(palavra.length() >=3){
        if(palavra.charAt(0) == 'F' && palavra.charAt(1) == 'I' && palavra.charAt(2) == 'M')
        {
            ok = true;
        }
    }
        return(ok);
    }
    
    

    public static void main(String[] args){
        char p = ' ';
       char s = ' ';
        Scanner sc = new Scanner(System.in);
        String palavra = "";
        palavra = sc.nextLine();
        Random random = new Random();
        random.setSeed(4);
            while(!fim(palavra)){
                p =(char)('a' + (Math.abs(random.nextInt()) % 26));
                s= (char)('a' + (Math.abs(random.nextInt()) % 26));
                System.out.println(random(palavra, p, s));
                palavra = sc.nextLine();
            }
        sc.close();
    }


}
