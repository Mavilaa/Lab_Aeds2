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
    date *data;
} typedef veiculo;

date *new_date() {
    date *a = (date *)malloc(sizeof(date));
    return a;
}

date *new_dates(int n) {
    date *a = (date *)malloc(n * sizeof(date));
    return a;
}

date *parseData(char *s) {
    date *data = new_date();
    char *car[3];
    car[0] = strtok(s, "-");
    car[1] = strtok(NULL, "-");
    car[2] = strtok(NULL, "-");
    data->ano = atoi(car[0]);
    data->mes = atoi(car[1]);
    data->dia = atoi(car[2]);
    return data;
}

void format_data(date *d, char *buffer) {
    sprintf(buffer, "%02d/%02d/%04d", d->dia, d->mes, d->ano);
}

veiculo *new_veiculo() {
    veiculo *a = (veiculo *)malloc(sizeof(veiculo));
    return a;
}

veiculo *new_veiculos(int n) {
    veiculo *veiculos = (veiculo *)malloc(n * sizeof(veiculo));
    return veiculos;
}

veiculo *parse_Veiculo(char *s) {
    veiculo *carro = new_veiculo();
    char *car[15];

    for (int i = 0; s[i] != '\0'; i++) {
        if (s[i] == '\n' || s[i] == '\r') {
            s[i] = '\0';
        }
    }

    car[0] = strtok(s, ",");
    for (int i = 1; i < 15; i++) {
        car[i] = strtok(NULL, ",");
    }

    carro->id = atoi(car[0]);
    sprintf(carro->marca, "%s", car[1]);
    sprintf(carro->modelo, "%s", car[2]);
    carro->ano = atoi(car[3]);
    sprintf(carro->categoria, "%s", car[4]);
    sprintf(carro->combustivel, "%s", car[5]);
    for (int i = 0; carro->combustivel[i] != '\0'; i++) {
        if (carro->combustivel[i] == ';') {
            carro->combustivel[i] = ',';
        }
    }
    carro->cilindros = atoi(car[6]);
    sscanf(car[7], "%lf", &carro->cilindrada);
    sprintf(carro->transmissao, "%s", car[8]);
    sprintf(carro->tracao, "%s", car[9]);
    sscanf(car[10], "%lf", &carro->consumo_cidade);
    sscanf(car[11], "%lf", &carro->consumo_estrada);
    sscanf(car[12], "%lf", &carro->co2);
    carro->turbo = strcmp(car[13], "true") == 0;
    carro->data = parseData(car[14]);

    return carro;
}

void format_veiculo(veiculo v, char *buffer) {
    char data_formatada[11];
    format_data(v.data, data_formatada);
    sprintf(buffer,
            "[%d ## %s ## %s ## %d ## %s ## [%s] ## %d ## %.1f ## %s ## %s ## "
            "%.2f ## %.2f ## %.1f ## %s ## %s]",
            v.id, v.marca, v.modelo, v.ano, v.categoria, v.combustivel,
            v.cilindros, v.cilindrada, v.transmissao, v.tracao,
            v.consumo_cidade, v.consumo_estrada, v.co2,
            v.turbo ? "true" : "false", data_formatada);
}

veiculo *lercsv(char *caminho, int *tamanho) {
    char linha[1000];
    int i = 0;
    *tamanho = 0;

    FILE *arquivo = fopen(caminho, "rt");
    if (arquivo == NULL) {
        return NULL;
    }

    fgets(linha, sizeof(linha), arquivo);
    while (fgets(linha, sizeof(linha), arquivo)) {
        (*tamanho)++;
    }

    veiculo *veiculos = new_veiculos(*tamanho);

    rewind(arquivo);
    fgets(linha, sizeof(linha), arquivo);
    while (fgets(linha, sizeof(linha), arquivo) && i < *tamanho) {
        veiculo *tmp = parse_Veiculo(linha);
        veiculos[i] = *tmp;
        free(tmp);
        i++;
    }

    fclose(arquivo);
    return veiculos;
}

veiculo *buscar(veiculo *carros, int total, int id) {
    for (int k = 0; k < total; k++) {
        if (carros[k].id == id) {
            return &carros[k];
        }
    }
    return NULL;
}

struct Celula {
    veiculo *elemento;
    struct Celula *prox;
} typedef Celula;

Celula *primeiro;
Celula *ultimo;

Celula *new_celula(veiculo *elemento) {
    Celula *nova = (Celula *)malloc(sizeof(Celula));
    nova->elemento = elemento;
    nova->prox = NULL;
    return nova;
}

void start() {
    primeiro = new_celula(NULL);
    ultimo = primeiro;
}

int tamanho() {
    int tamanho = 0;
    for (Celula *i = primeiro; i != ultimo; i = i->prox) {
        tamanho++;
    }
    return tamanho;
}

void inserirInicio(veiculo *v) {
    Celula *tmp = new_celula(v);
    tmp->prox = primeiro->prox;
    primeiro->prox = tmp;
    if (primeiro == ultimo) {
        ultimo = tmp;
    }
}

void inserirFim(veiculo *v) {
    ultimo->prox = new_celula(v);
    ultimo = ultimo->prox;
}

void inserir(veiculo *v, int posicao) {
    int tam = tamanho();
    if (posicao < 0 || posicao > tam) {
        printf("Erro ao inserir!\n");
    } else if (posicao == 0) {
        inserirInicio(v);
    } else if (posicao == tam) {
        inserirFim(v);
    } else {
        Celula *i = primeiro;
        for (int j = 0; j < posicao; j++) {
            i = i->prox;
        }
        Celula *tmp = new_celula(v);
        tmp->prox = i->prox;
        i->prox = tmp;
    }
}

veiculo *removerInicio() {
    if (primeiro == ultimo) {
        return NULL;
    }
    Celula *tmp = primeiro;
    primeiro = primeiro->prox;
    veiculo *resp = primeiro->elemento;
    free(tmp);
    return resp;
}

veiculo *removerFim() {
    if (primeiro == ultimo) {
        return NULL;
    }
    Celula *i;
    for (i = primeiro; i->prox != ultimo; i = i->prox) {
    }
    veiculo *resp = ultimo->elemento;
    free(ultimo);
    ultimo = i;
    ultimo->prox = NULL;
    return resp;
}

veiculo *remover(int posicao) {
    int tam = tamanho();
    if (primeiro == ultimo || posicao < 0 || posicao >= tam) {
        return NULL;
    } else if (posicao == 0) {
        return removerInicio();
    } else if (posicao == tam - 1) {
        return removerFim();
    }
    Celula *i = primeiro;
    for (int j = 0; j < posicao; j++) {
        i = i->prox;
    }
    Celula *tmp = i->prox;
    veiculo *resp = tmp->elemento;
    i->prox = tmp->prox;
    free(tmp);
    return resp;
}

void mostrar() {
    char formatado[1000];
    for (Celula *i = primeiro->prox; i != NULL; i = i->prox) {
        format_veiculo(*i->elemento, formatado);
        printf("%s\n", formatado);
    }
}

void removido(veiculo *v) {
    if (v != NULL) {
        printf("(R)%s %s\n", v->marca, v->modelo);
    }
}

int main() {
    int numero = 0;
    int total = 0;
    int quantidade = 0;
    int posicao = 0;
    char comando[10];

    veiculo *carros = lercsv("/tmp/veiculos.csv", &total);
    start();

    while (scanf("%d", &numero) == 1 && numero != -1) {
        veiculo *v = buscar(carros, total, numero);
        if (v != NULL) {
            inserirFim(v);
        }
    }

    scanf("%d", &quantidade);
    for (int i = 0; i < quantidade; i++) {
        scanf("%s", comando);
        if (strcmp(comando, "II") == 0) {
            scanf("%d", &numero);
            inserirInicio(buscar(carros, total, numero));
        } else if (strcmp(comando, "I*") == 0) {
            scanf("%d %d", &posicao, &numero);
            inserir(buscar(carros, total, numero), posicao);
        } else if (strcmp(comando, "IF") == 0) {
            scanf("%d", &numero);
            inserirFim(buscar(carros, total, numero));
        } else if (strcmp(comando, "RI") == 0) {
            removido(removerInicio());
        } else if (strcmp(comando, "R*") == 0) {
            scanf("%d", &posicao);
            removido(remover(posicao));
        } else if (strcmp(comando, "RF") == 0) {
            removido(removerFim());
        }
    }

    mostrar();

    free(carros);
    return 0;
}
