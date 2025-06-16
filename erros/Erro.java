package erros;

public class Erro {
    private int linha, coluna;
    private String texto;

    // Construtor sem argumentos
    public Erro() {
        this.linha = 0;
        this.coluna = 0;
        this.texto = null;
    }

    // Construtor com linha e coluna
    public Erro(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        this.texto = null;
    }

    // Construtor com linha, coluna e texto
    public Erro(int linha, int coluna, String texto) {
        this.linha = linha;
        this.coluna = coluna;
        this.texto = texto;
    }

    // Getters e Setters
    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void imprime() {
        String aux = "linha:" + this.linha + ", coluna:" + this.coluna + ", ";

        if (this.texto == null) {
            aux += "erro indefinido!";
        } else {
            aux += this.texto;
        }
        System.out.println(aux);
    }
}