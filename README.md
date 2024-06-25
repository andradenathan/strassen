# Strassen - Multiplicação de matrizes

### Descrição do Projeto
Projeto final da disciplina de Programação Concorrente do
curso de Ciência da Computação da Universidade Federal do Rio de Janeiro.

### Pré-execução
Para executar o projeto, é necessário executar na raíz uma pré-execução:
```bash
make run <dimension> <input_name>
```

onde,

- dimension: Dimensão da matriz quadrada para o algoritmo de Strassen computar;
- input_name: Nome do arquivo de entrada que contém as matrizes a serem multiplicadas;
- threads: Número de threads que o programa principal deve utilizar.

para carregar uma matriz de números inteiros de dimensões N aleatória e o número de threads a ser utilizado pelo programa principal.


### Execução
Para executar o projeto, é necessário executar na raíz:

```bash
make compile <dimension> <matrix_file_name> <matrix2_file_name> <threads>
```

caso seja necessário verificar a corretude do algoritmo, é possível executar o comando:
```bash
make test <dimension> <matrix_file_name> <matrix2_file_name> <threads>
```


### Autores

<a href="https://github.com/andradenathan">
<img src="https://avatars.githubusercontent.com/u/42661561?v=4" width="150px"/>
</a>

<a href="https://github.com/mellaniepereira">
<img src="https://avatars.githubusercontent.com/u/83502847?v=4" width="148px" />
</a>
