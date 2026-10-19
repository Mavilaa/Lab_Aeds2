import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class date {
    private int ano;
    private int mes;
    private int dia;

    static date parseData(String s) {
        date data = new date();
        String[] sdata = s.split("-");

        data.ano = Integer.parseInt(sdata[0]);
        data.mes = Integer.parseInt(sdata[1]);
        data.dia = Integer.parseInt(sdata[2]);

        return data;
    }

    String format_data() {
        return String.format(
                "%02d/%02d/%04d",
                this.dia, this.mes, this.ano);
    }
}

class veiculo {
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
    private date data;

    public veiculo() {
        this.id = 0;
        this.marca = "\0";
        this.modelo = "\0";
        this.ano = 0;
        this.categoria = "\0";
        this.combustivel = "\0";
        this.cilindros = 0;
        this.cilindrada = 0.0;
        this.transmissao = "\0";
        this.tracao = "\0";
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

    date get_data_registro() {
        return this.data;
    }

    static veiculo parse_Veiculo(String s) {
        veiculo carro = new veiculo();
        String[] car = s.split(",");

        carro.id = Integer.parseInt(car[0]);
        carro.marca = car[1];
        carro.modelo = car[2];
        carro.ano = Integer.parseInt(car[3]);
        carro.categoria = car[4];
        carro.combustivel = car[5];
        carro.cilindros = Integer.parseInt(car[6]);
        carro.cilindrada = Double.parseDouble(car[7]);
        carro.transmissao = car[8];
        carro.tracao = car[9];
        carro.consumo_cidade = Double.parseDouble(car[10]);
        carro.consumo_estrada = Double.parseDouble(car[11]);
        carro.co2 = Double.parseDouble(car[12]);
        carro.turbo = Boolean.parseBoolean(car[13]);
        carro.data = date.parseData(car[14]);

        return carro;
    }

    String format_combustivel() {
        String resultado = "";

        for (int i = 0; i < this.combustivel.length(); i++) {
            char c = this.combustivel.charAt(i);

            if (c == ';') {
                c = ',';
            }

            resultado = resultado + c;
        }

        return resultado;
    }

    String format_veiculo() {
        return "["
                + this.id
                + " ## " + this.marca
                + " ## " + this.modelo
                + " ## " + this.ano
                + " ## " + this.categoria
                + " ## [" + format_combustivel() + "]"
                + " ## " + this.cilindros
                + " ## " + this.cilindrada
                + " ## " + this.transmissao
                + " ## " + this.tracao
                + " ## " + String.format(Locale.US, "%.2f", this.consumo_cidade)
                + " ## " + String.format(Locale.US, "%.2f", this.consumo_estrada)
                + " ## " + this.co2
                + " ## " + this.turbo
                + " ## " + this.data.format_data()
                + "]";
    }
}

class LeitorCsv {

    public static veiculo[] lercsv(String path) {
        int n = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.readLine();

            while (br.readLine() != null) {
                n++;
            }

        } catch (IOException e) {
            System.err.println(
                    "Erro ao ler o arquivo: " + e.getMessage());
            return new veiculo[0];
        }

        veiculo[] veiculos = new veiculo[n];

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.readLine();

            for (int i = 0; i < n; i++) {
                veiculos[i] = veiculo.parse_Veiculo(br.readLine());
            }

        } catch (IOException e) {
            System.err.println(
                    "Erro ao ler o arquivo: " + e.getMessage());
            return new veiculo[0];
        }

        return veiculos;
    }
}

class Buscar {

    veiculo buscar_id(veiculo[] carros, int id, int total) {
        for (int i = 0; i < total; i++) {
            if (carros[i].get_id() == id) {
                return carros[i];
            }
        }

        return null;
    }

    static String minusculo(String original) {
        String minuscula = "";

        for (int x = 0; x < original.length(); x++) {
            char c = original.charAt(x);

            if (c >= 'A' && c <= 'Z') {
                c = (char) (c + 32);
            }

            minuscula = minuscula + c;
        }

        return minuscula;
    }
}

class Ordenar {

    void insercao(veiculo carros[], int n) {
        for (int i = 1; i < n; i++) {
            veiculo tmp = carros[i];
            String chave = Buscar.minusculo(tmp.get_marca());
            int j = i - 1;

            while (j >= 0 && Buscar.minusculo(carros[j].get_marca()).compareTo(chave) > 0) {
                carros[j + 1] = carros[j];
                j--;
            }

            carros[j + 1] = tmp;
        }
    }
}

public class Insercao {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        veiculo[] veiculos = LeitorCsv.lercsv("/tmp/veiculos.csv");
        veiculo[] escolhidos = new veiculo[veiculos.length];

        Buscar busca = new Buscar();
        Ordenar ordenar = new Ordenar();

        int id;
        int n = 0;

        while (sc.hasNextInt()) {
            id = sc.nextInt();

            if (id == -1) {
                break;
            }

            veiculo encontrado = busca.buscar_id(
                    veiculos, id, veiculos.length);

            if (encontrado != null) {
                escolhidos[n] = encontrado;
                n++;
            }
        }

        ordenar.insercao(escolhidos, n);

        for (int i = 0; i < n; i++) {
            System.out.println(escolhidos[i].format_veiculo());
        }

        sc.close();
    }
}
