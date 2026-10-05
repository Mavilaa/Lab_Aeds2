import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Data {
    private int ano;
    private int mes;
    private int dia;

    static Data parsedate(String s) {
        Data data = new Data();
        String sdata[] = s.split("-");
        data.ano = Integer.parseInt(sdata[0]);
        data.mes = Integer.parseInt(sdata[1]);
        data.dia = Integer.parseInt(sdata[2]);
        return data;
    }

    String format() {
        return String.format("%02d/%02d/%04d", this.dia, this.mes, this.ano);
    }
}

class Veiculo {
    private int id;
    private String marca;
    private String modelo;
    private int ano;
    private String categoria;
    private String combustivel;
    private int cilindros;
    private double cilindrada;
    private String transmissao;
    private String tracao;
    private double consumo_cidade;
    private double consumo_estrada;
    private double co2;
    private boolean turbo;
    private Data data;

    public Veiculo() {
        this.id = 0;
        this.marca = "";
        this.modelo = "";
        this.ano = 0;
        this.categoria = "";
        this.combustivel = "";
        this.cilindros = 0;
        this.cilindrada = 0.0;
        this.transmissao = "";
        this.tracao = "";
        this.consumo_cidade = 0.0;
        this.consumo_estrada = 0.0;
        this.co2 = 0.0;
        this.turbo = false;
        this.data = null;
    }

    int get_id() {
        return this.id;
    }

    String get_marca() {
        return this.marca;
    }

    String get_modelo() {
        return this.modelo;
    }

    int get_ano() {
        return this.ano;
    }

    String get_categoria() {
        return this.categoria;
    }

    String get_combustivel() {
        return this.combustivel;
    }

    int get_cilindros() {
        return this.cilindros;
    }

    double get_cilindrada() {
        return this.cilindrada;
    }

    String get_transmissao() {
        return this.transmissao;
    }

    String get_tracao() {
        return this.tracao;
    }

    double get_consumo_cidade() {
        return this.consumo_cidade;
    }

    double get_consumo_estrada() {
        return this.consumo_estrada;
    }

    double get_co2() {
        return this.co2;
    }

    boolean get_turbo() {
        return this.turbo;
    }

    Data get_data_registro() {
        return this.data;
    }

    static Veiculo parseVeiculo(String s) {
        Veiculo carro = new Veiculo();
        String[] car = s.split(",");
        carro.id = Integer.parseInt(car[0]);
        carro.marca = car[1];
        carro.modelo = car[2];
        carro.ano = Integer.parseInt(car[3]);
        carro.categoria = car[4];
        String[] comb = car[5].split(";");
        carro.combustivel = comb[0];
        for (int i = 1; i < comb.length; i++) {
            carro.combustivel = carro.combustivel + "," + comb[i];
        }
        carro.cilindros = Integer.parseInt(car[6]);
        carro.cilindrada = Double.parseDouble(car[7]);
        carro.transmissao = car[8];
        carro.tracao = car[9];
        carro.consumo_cidade = Double.parseDouble(car[10]);
        carro.consumo_estrada = Double.parseDouble(car[11]);
        carro.co2 = Double.parseDouble(car[12]);
        carro.turbo = car[13].equals("true");
        carro.data = Data.parsedate(car[14]);
        return carro;
    }

    String format() {
        return String.format(Locale.US,
                "[%d ## %s ## %s ## %d ## %s ## [%s] ## %d ## %.1f ## %s ## %s ## %.2f ## %.2f ## %.1f ## %b ## %s]",
                this.id, this.marca, this.modelo, this.ano, this.categoria, this.combustivel, this.cilindros,
                this.cilindrada, this.transmissao, this.tracao, this.consumo_cidade, this.consumo_estrada,
                this.co2, this.turbo, this.data.format());
    }
}

class Modelagem {
    public static Veiculo[] ler(String path) {
        Veiculo[] veiculos = new Veiculo[0];
        try {
            int n = 0;
            BufferedReader br = new BufferedReader(new FileReader(path));
            br.readLine();
            while (br.readLine() != null) {
                n++;
            }
            br.close();

            veiculos = new Veiculo[n];
            br = new BufferedReader(new FileReader(path));
            br.readLine();
            for (int i = 0; i < n; i++) {
                veiculos[i] = Veiculo.parseVeiculo(br.readLine());
            }
            br.close();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return veiculos;
    }

    static Veiculo buscar(Veiculo[] carros, int id) {
        for (int k = 0; k < carros.length; k++) {
            if (carros[k].get_id() == id) {
                return carros[k];
            }
        }
        return null;
    }

    public static void main(String args[]) {
        Veiculo[] carros = ler("/tmp/veiculos.csv");
        Scanner sc = new Scanner(System.in);

        int numero = sc.nextInt();
        while (numero != -1) {
            Veiculo v = buscar(carros, numero);
            if (v != null) {
                System.out.println(v.format());
            }
            numero = sc.nextInt();
        }
        sc.close();
    }
}
