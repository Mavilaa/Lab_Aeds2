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

        data.ano = Integer.parseInt(sdata[0].trim());
        data.mes = Integer.parseInt(sdata[1].trim());
        data.dia = Integer.parseInt(sdata[2].trim());

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

        carro.id = Integer.parseInt(car[0].trim());
        carro.marca = car[1].trim();
        carro.modelo = car[2].trim();
        carro.ano = Integer.parseInt(car[3].trim());
        carro.categoria = car[4].trim();
        carro.combustivel = car[5].trim();
        carro.cilindros = Integer.parseInt(car[6].trim());
        carro.cilindrada = Double.parseDouble(car[7].trim());
        carro.transmissao = car[8].trim();
        carro.tracao = car[9].trim();
        carro.consumo_cidade = Double.parseDouble(car[10].trim());
        carro.consumo_estrada = Double.parseDouble(car[11].trim());
        carro.co2 = Double.parseDouble(car[12].trim());
        carro.turbo = Boolean.parseBoolean(car[13].trim());
        carro.data = date.parseData(car[14].trim());

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
        // Locale.US garante o ponto decimal (3.40) em qualquer maquina;
        // com o locale pt_BR o "%.2f" imprimiria virgula (3,40).
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

class CelulaDupla {
    public veiculo elemento;
    public CelulaDupla ant;
    public CelulaDupla prox;

    public CelulaDupla() {
        this(null);
    }

    public CelulaDupla(veiculo elemento) {
        this.elemento = elemento;
        this.ant = null;
        this.prox = null;
    }
}

class Lista {
    private CelulaDupla primeiro;
    private CelulaDupla ultimo;

    public Lista() {
        primeiro = new CelulaDupla();
        ultimo = primeiro;
    }

    public int tamanho() {
        int tamanho = 0;
        for (CelulaDupla i = primeiro; i != ultimo; i = i.prox) {
            tamanho++;
        }
        return tamanho;
    }

    public void inserirInicio(veiculo carro) {
        CelulaDupla tmp = new CelulaDupla(carro);
        tmp.ant = primeiro;
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        } else {
            tmp.prox.ant = tmp;
        }
    }

    public void inserirFim(veiculo carro) {
        ultimo.prox = new CelulaDupla(carro);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }

    public void inserir(veiculo carro, int posicao) throws Exception {
        int tamanho = tamanho();
        if (posicao < 0 || posicao > tamanho) {
            throw new Exception("Erro ao inserir!");
        } else if (posicao == 0) {
            inserirInicio(carro);
        } else if (posicao == tamanho) {
            inserirFim(carro);
        } else {
            CelulaDupla i = primeiro;
            for (int j = 0; j < posicao; j++) {
                i = i.prox;
            }
            CelulaDupla tmp = new CelulaDupla(carro);
            tmp.ant = i;
            tmp.prox = i.prox;
            tmp.ant.prox = tmp;
            tmp.prox.ant = tmp;
        }
    }

    public veiculo removerInicio() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover!");
        }
        CelulaDupla tmp = primeiro;
        primeiro = primeiro.prox;
        veiculo resp = primeiro.elemento;
        tmp.prox = null;
        primeiro.ant = null;
        return resp;
    }

    public veiculo removerFim() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover!");
        }
        veiculo resp = ultimo.elemento;
        ultimo = ultimo.ant;
        ultimo.prox.ant = null;
        ultimo.prox = null;
        return resp;
    }

    public veiculo remover(int posicao) throws Exception {
        int tamanho = tamanho();
        if (primeiro == ultimo || posicao < 0 || posicao >= tamanho) {
            throw new Exception("Erro ao remover!");
        } else if (posicao == 0) {
            return removerInicio();
        } else if (posicao == tamanho - 1) {
            return removerFim();
        }
        CelulaDupla i = primeiro.prox;
        for (int j = 0; j < posicao; j++) {
            i = i.prox;
        }
        i.ant.prox = i.prox;
        i.prox.ant = i.ant;
        veiculo resp = i.elemento;
        i.prox = null;
        i.ant = null;
        return resp;
    }

    public void mostrar() {
        for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
            System.out.println(i.elemento.format_veiculo());
        }
    }
}

public class ListaDupla {

    static void removido(veiculo v) {
        System.out.println("(R)" + v.get_marca() + " " + v.get_modelo());
    }

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);

        veiculo[] veiculos = LeitorCsv.lercsv("/tmp/veiculos.csv");
        Lista lista = new Lista();

        Buscar busca = new Buscar();

        int id;

        while (sc.hasNextInt()) {
            id = sc.nextInt();

            if (id == -1) {
                break;
            }

            veiculo encontrado = busca.buscar_id(
                    veiculos, id, veiculos.length);

            if (encontrado != null) {
                lista.inserirFim(encontrado);
            }
        }

        int quantidade = sc.nextInt();

        for (int i = 0; i < quantidade; i++) {
            String comando = sc.next();

            if (comando.equals("II")) {
                lista.inserirInicio(busca.buscar_id(veiculos, sc.nextInt(), veiculos.length));
            } else if (comando.equals("I*")) {
                int posicao = sc.nextInt();
                lista.inserir(busca.buscar_id(veiculos, sc.nextInt(), veiculos.length), posicao);
            } else if (comando.equals("IF")) {
                lista.inserirFim(busca.buscar_id(veiculos, sc.nextInt(), veiculos.length));
            } else if (comando.equals("RI")) {
                removido(lista.removerInicio());
            } else if (comando.equals("R*")) {
                removido(lista.remover(sc.nextInt()));
            } else if (comando.equals("RF")) {
                removido(lista.removerFim());
            }
        }

        sc.close();

        lista.mostrar();
    }
}
