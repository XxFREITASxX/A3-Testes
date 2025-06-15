package ast;

import visitor.XadrezVisitor;

import java.util.ArrayList;
import java.util.List;

public class PartidaNode extends ASTNode {
    private List<LanceNode> jogadas = new ArrayList<>();
    private Resultado resultado;

    public enum Resultado {
        VITORIA_BRANCAS("1-0"),
        VITORIA_PRETAS("0-1"), 
        EMPATE("1/2-1/2");

        private final String notacao;

        // Construtor do enum
        Resultado(String notacao) {
            this.notacao = notacao;
        }

        public String getNotacao() {
            return notacao;
        }
    }

    @Override
    public void accept(XadrezVisitor visitor) {
        visitor.visit(this);
    }

    // Getters e Setters
    public List<LanceNode> getJogadas() {
        return jogadas;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setJogadas(List<LanceNode> jogadas) {
        this.jogadas = jogadas;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }
}