import java.util.*;
public class Soma_de_Digitos_R {
    public static int soma(int digitos){
        int digito = 0;
        int soma = 0;
        if(digitos > 0 ){
            digito = digitos %10;
            soma = digito +soma(digitos/10);

        }
        return(soma);
    }



    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int digito = 0;
        while(sc.hasNextInt()){
            digito = sc.nextInt();
            System.out.print(soma(digito));
            if(sc.hasNextInt()){
            System.out.print("\n");
            }
        }

    
        sc.close();
    }
}
