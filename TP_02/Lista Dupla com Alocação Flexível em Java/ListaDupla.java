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

class CelulaDupla {
    public Veiculo elemento;
    public CelulaDupla ant;
    public CelulaDupla prox;

    public CelulaDupla() {
        this(null);
    }

    public CelulaDupla(Veiculo elemento) {
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

    public void inserirInicio(Veiculo veiculo) {
        CelulaDupla tmp = new CelulaDupla(veiculo);
        tmp.ant = primeiro;
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        } else {
            tmp.prox.ant = tmp;
        }
    }

    public void inserirFim(Veiculo veiculo) {
        ultimo.prox = new CelulaDupla(veiculo);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }

    public void inserir(Veiculo veiculo, int posicao) throws Exception {
        int tamanho = tamanho();
        if (posicao < 0 || posicao > tamanho) {
            throw new Exception("Erro ao inserir!");
        } else if (posicao == 0) {
            inserirInicio(veiculo);
        } else if (posicao == tamanho) {
            inserirFim(veiculo);
        } else {
            CelulaDupla i = primeiro;
            for (int j = 0; j < posicao; j++) {
                i = i.prox;
            }
            CelulaDupla tmp = new CelulaDupla(veiculo);
            tmp.ant = i;
            tmp.prox = i.prox;
            tmp.ant.prox = tmp;
            tmp.prox.ant = tmp;
        }
    }

    public Veiculo removerInicio() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover!");
        }
        CelulaDupla tmp = primeiro;
        primeiro = primeiro.prox;
        Veiculo resp = primeiro.elemento;
        tmp.prox = null;
        primeiro.ant = null;
        return resp;
    }

    public Veiculo removerFim() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover!");
        }
        Veiculo resp = ultimo.elemento;
        ultimo = ultimo.ant;
        ultimo.prox.ant = null;
        ultimo.prox = null;
        return resp;
    }

    public Veiculo remover(int posicao) throws Exception {
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
        Veiculo resp = i.elemento;
        i.prox = null;
        i.ant = null;
        return resp;
    }

    public void mostrar() {
        for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
            System.out.println(i.elemento.format());
        }
    }
}

class ListaDupla {
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

    static void removido(Veiculo v) {
        System.out.println("(R)" + v.get_marca() + " " + v.get_modelo());
    }

    public static void main(String args[]) throws Exception {
        Veiculo[] carros = ler("/tmp/veiculos.csv");
        Lista lista = new Lista();
        Scanner sc = new Scanner(System.in);

        int numero = sc.nextInt();
        while (numero != -1) {
            Veiculo v = buscar(carros, numero);
            if (v != null) {
                lista.inserirFim(v);
            }
            numero = sc.nextInt();
        }

        int quantidade = sc.nextInt();
        for (int i = 0; i < quantidade; i++) {
            String comando = sc.next();
            if (comando.equals("II")) {
                lista.inserirInicio(buscar(carros, sc.nextInt()));
            } else if (comando.equals("I*")) {
                int posicao = sc.nextInt();
                lista.inserir(buscar(carros, sc.nextInt()), posicao);
            } else if (comando.equals("IF")) {
                lista.inserirFim(buscar(carros, sc.nextInt()));
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
