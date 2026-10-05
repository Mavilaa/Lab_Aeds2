package Tp_02;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class date {
    private int ano;
    private int mes;
    private int dia;

    static date parsedate(String s) {
        date data = new date();
        String sdata[] = s.split("-");

        data.ano = Integer.parseInt(sdata[0]);
        data.mes = Integer.parseInt(sdata[1]);
        data.dia = Integer.parseInt(sdata[2]);

        return (data);
    }

    String format() {
        String date = "";
        date = String.format("%02d", this.dia) + '/' + String.format("%02d", this.mes) + '/'
                + String.format("%04d", this.ano);
        return (date);
    }

}

class Data {
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

    public Data() {
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
        return (this.id);
    }

    String get_marca() {
        return (this.marca);
    }

    String get_modelo() {
        return (this.modelo);
    }

    int get_ano() {
        return (this.ano);
    }

    String get_categoria() {
        return (this.categoria);
    }

    String get_combustivel() {
        return (this.combustivel);
    }

    int get_cilindros() {
        return (this.cilindros);
    }

    double get_cilindrada() {
        return (this.cilindrada);
    }

    String get_transmissao() {
        return (this.transmissao);
    }

    String get_tracao() {
        return (this.tracao);
    }

    double get_consumo_cidade() {
        return (this.consumo_cidade);
    }

    double get_consumo_estrada() {
        return (this.consumo_estrada);
    }

    double get_co2() {
        return (this.co2);
    }

    boolean get_turbo() {
        return (this.turbo);
    }

    date get_data_registro() {
        return (this.data);
    }

    static Data parseVeiculo(String s) {

        Data carro = new Data();
        String[] car = s.split(",");
        carro.data = new date();
        try {
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
            carro.data = date.parsedate(car[14]);

        } catch (Exception e) {
            System.out.println("Nao foi possivel converter");

        }

        return (carro);

    }

    String format() {
        date dia = this.data;
        String a = dia.format();
        String dados = "";
        dados = ("[") + String.format("%d", this.id) + (" ## ") + this.marca + (" ## ") + this.modelo + (" ## ")
                + String.format("%d", this.ano)
                + (" ## ") + this.categoria + (" ## ") + ("[") + this.combustivel + ("]") + (" ## ")
                + String.format("%d", this.cilindros) + ("\n")
                + (" ## ") + String.format("%.2f", this.cilindrada) + (" ## ") +
                this.transmissao + (" ## ") + this.tracao + (" ## ") + String.format("%.2f", this.consumo_cidade)
                + (" ## ") + String.format("%.2f", this.consumo_estrada) + (" ## ")
                + String.format("%.2f", this.co2) + ("\n") + (" ## ") + String.format("%b", this.turbo) + (" ## ") + a +
                ("]");
        return (dados);

    }
}

class LeitorCsv {
    public static Data[] ler(String path) {
        Data[] veiculos = new Data[0];
        try {
            int n = 0;
            BufferedReader br = new BufferedReader(new FileReader(path));
            br.readLine();
            while (br.readLine() != null)
                n++;
            br.close();

            veiculos = new Data[n];
            br = new BufferedReader(new FileReader(path));
            br.readLine();
            for (int i = 0; i < n; i++) {
                veiculos[i] = Data.parseVeiculo(br.readLine());
            }
            br.close();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return veiculos;
    }

    public static void main(String args[]) {
        Data[] carro = LeitorCsv.ler("veiculos.csv");
        System.out.println(carro[300].format());

    }

}
