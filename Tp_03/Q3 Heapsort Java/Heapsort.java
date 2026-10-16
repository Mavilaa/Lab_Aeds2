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

        int comparar(veiculo pai, veiculo filho) {
            double a = pai.get_co2();
            double b = filho.get_co2();

            if (a < b) {
                return -1;
            } else if (a > b) {
                return 1;
            }

            String ta = minusculo(pai.get_transmissao());
            String tb = minusculo(filho.get_transmissao());

            return ta.compareTo(tb);
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

        veiculo[] vet;
        int n;
        Buscar a = new Buscar();
        public Ordenar(veiculo[] vet, int n) {
            this.vet = vet;
            this.n = n;
        }

        void heapsort() {

            // Construção do heap
            for (int tam = 2; tam <= n; tam++) {
                construir(tam);
            }

        
            int tam = n;
            while (tam > 1) {
                swap(1, tam--);
                reconstruir(tam);
            }
        }

        void construir(int tam) {
            for (int i = tam; i > 1 && a.comparar(vet[i], vet[i/2]) > 0; i /= 2) {
                swap(i, i / 2);
            }
        }

        void reconstruir(int tam)  {
            int i = 1;
            while (hasFilho(i, tam) == true) {
                int filho = getMaiorFilho(i, tam);
                if (a.comparar(vet[i], vet[filho]) < 0) {
                    swap(i, filho);
                    i = filho;
                } else {
                    i = tam; 
                }
            }
        }

        boolean hasFilho(int i, int tam) {
            return (i <= (tam / 2));
        }

        int getMaiorFilho(int i, int tam)  {
            int filho = -1;
            if (2 * i == tam || a.comparar(vet[2 * i], vet[2 * i + 1]) >= 0) {
                filho = 2 * i;
            } else {
                filho = 2 * i + 1;
            }

            return filho;
        }

        void swap(int x, int j) {
            veiculo temp = vet[x];
            vet[x] = vet[j];
            vet[j] = temp;

        }


    }

    public class Heapsort {

        public static void main(String args[]) {
            Scanner sc = new Scanner(System.in);

            veiculo[] veiculos = LeitorCsv.lercsv("veiculos.csv");
            veiculo[] escolhidos = new veiculo[veiculos.length+1];

            Buscar busca = new Buscar();

            int id;
            int n = 0;

            while (sc.hasNextInt()) {
                id = sc.nextInt();
                if (id == -1) {
                    break;
                }
                veiculo encontrado = busca.buscar_id( veiculos, id, veiculos.length);

                if (encontrado != null) {
                    n++;
                    escolhidos[n] = encontrado;
                }
            }

                Ordenar heap = new Ordenar(escolhidos, n);
                heap.heapsort();
    

            for (int i = 1; i <= n; i++) {
                System.out.println(escolhidos[i].format_veiculo());
            }

            sc.close();
        }
    }