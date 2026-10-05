#include<stdio.h>
#include<string.h>
#include<stdbool.h>
#include<stdlib.h>
struct date {
    int dia;
    int mes;
    int ano;
} typedef date;

struct Veiculo {
    int id;
    char marca[50];
    char modelo[50];
    int ano;
    char categoria[50];
    char combustivel[50];
    int cilindros;
    double cilindrada;
    char transmissao[50];
    char tracao[50];
    double consumo_cidade;
    double consumo_estrada;
    double co2;
    bool turbo;
    date* data;

} typedef veiculo;



date *new_date() {
    date *a = (date *)malloc(1 * (sizeof(a)));
    return (a);
}

date *new_dates(int n) {
    date *a;
    a = (date *)malloc(n * (sizeof(a)));

    return (a);
}

date* parseData(char *s) {
    date *data = new_date();
    char *car[3];
    car[0] = strtok(s, "-");
    car[1] = strtok(NULL, "-");
    car[2] = strtok(NULL, "-");
    data->ano = atoi(car[0]);
    data->mes = atoi(car[1]);
    data->dia = atoi(car[2]);
    return (data);
}

void format_data(date* d, char *buffer) { 
    char data[100];
    sprintf(buffer, "%02d/%02d/%04d", d->dia, d->mes, d->ano);
}

veiculo *new_veiculo() {

    veiculo *a = (veiculo *)malloc(1 * sizeof(veiculo));

    return (a);
}

veiculo *new_veiculos(int n) {
    veiculo *veiculos;
    veiculos = (veiculo *)malloc(n * sizeof(veiculo));

    return (veiculos);
}

veiculo *parse_Veiculo(char *s) {
    veiculo *carro = new_veiculo();
    date* a = new_date();
    char *car[14];
    car[0] = strtok(s, ",");
    car[1] = strtok(NULL, ",");
    car[2] = strtok(NULL, ",");
    car[3] = strtok(NULL, ",");
    car[4] = strtok(NULL, ",");
    car[5] = strtok(NULL, ",");
    car[6] = strtok(NULL, ",");
    car[7] = strtok(NULL, ",");
    car[8] = strtok(NULL, ",");
    car[9] = strtok(NULL, ",");
    car[10] = strtok(NULL, ",");
    car[11] = strtok(NULL, ",");
    car[12] = strtok(NULL, ",");
    car[13] = strtok(NULL, ",");
    car[14] = strtok(NULL, ",");

    // carro.data = new_date();
    carro->id = atoi(car[0]);
    sprintf(carro->marca, "%s", car[1]);
    sprintf(carro->modelo, "%s", car[2]);
    carro->ano = atoi(car[3]);
    sprintf(carro->categoria, "%s", car[4]);
    sprintf(carro->combustivel, "%s", car[5]);
    carro->cilindros = atoi(car[6]);
    sscanf(car[7], "%lf", &carro->cilindrada);
    sprintf(carro->transmissao, "%s", car[8]);
    sprintf(carro->tracao, "%s", car[9]);
    sscanf(car[10], "%lf", &carro->consumo_cidade);
    sscanf(car[11], "%lf", &carro->consumo_estrada);
    sscanf(car[12], "%lf", &carro->co2);
    sscanf(car[13], "%d", &carro->turbo);
    carro->data = parseData(car[14]);

    return (carro);
}

void format_veiculo(veiculo v, char *buffer) { 
char formatado[1000];
char data_formatada[11];
format_data(v.data, data_formatada);
sprintf(buffer,
"[%d ## %s ## %s ## %d ## %s ## [%s] ## %d\n"
        " ## %.2f ## %s ## %s ## %.2f ## %.2f ## %.2f\n"
        " ## %s ## %s]",
        v.id, v.marca, v.modelo, v.ano, v.categoria, v.combustivel, v.cilindros,
        v.cilindrada, v.transmissao, v.tracao, v.consumo_cidade,
        v.consumo_estrada, v.co2, v.turbo ? "true" : "false", data_formatada);

}

veiculo *lercsv(char *caminho) {
    veiculo *veiculos = new_veiculo();
    int tamanho = 0;
    char a[1000] = "";
    int i = 0;
    FILE *arquivo = fopen(caminho, "rt");
    fgets(a, sizeof(a), arquivo);
    while (fgets(a, sizeof(a), arquivo)) {
        tamanho++;
    }
    fclose(arquivo);

    veiculos = new_veiculos(tamanho);

    arquivo = fopen(caminho, "rt");
    fgets(a, sizeof(a), arquivo);
    while (fgets(a, sizeof(a), arquivo)) {
        veiculos[i] = *parse_Veiculo(a);
        i++;
    }
    fclose(arquivo);
    return (veiculos);
}

int main() { 
    veiculo* carro;
    char formatado[300];
    carro = lercsv("veiculos.csv");
    format_veiculo(carro[1], formatado);
    printf("%s", &formatado);
}