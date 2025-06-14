package ast;

import ast.JogadaNode;  // Supondo que você tenha uma classe base JogadaNode

public class LanceNode extends ASTNode {
    private int numeroTurno;
    private JogadaNode jogadaBrancas;
    private JogadaNode jogadaPretas;  // Pode ser null se for o último lance

    public LanceNode(int numeroTurno, JogadaNode jogadaBrancas, JogadaNode jogadaPretas) {
        this.numeroTurno = numeroTurno;
        this.jogadaBrancas = jogadaBrancas;
        this.jogadaPretas = jogadaPretas;
    }

    @Override
    public void accept(XadrezVisitor visitor) {
        visitor.visit(this);
        jogadaBrancas.accept(visitor);
        if (jogadaPretas != null) {
            jogadaPretas.accept(visitor);
        }
    }

    // Getters
    public int getNumeroTurno() {
        return numeroTurno;
    }

    public JogadaNode getJogadaBrancas() {
        return jogadaBrancas;
    }

    public JogadaNode getJogadaPretas() {
        return jogadaPretas;
    }
}