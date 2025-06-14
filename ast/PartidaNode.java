package ast;

public class PartidaNode extends ASTNode {
    private List<LanceNode> jogadas = new ArrayList<>();
    private Resultado resultado;

    public enum Resultado{
    VITORIA_BRANCAS("1-0")
    VITORIA_PRETAS("0-1") 
    EMPATE("1/2-1/2") 
    }

    @Override
    public void accept(XadrezVisitor visitor) {
        visitor.visit(this);
        jogadas.forEach(lance -> lance.accept(visitor));
    }

      // Getters e Setters
    public List<LanceNode> getJogadas() {
        return jogadas;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }
}