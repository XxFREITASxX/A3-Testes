package ast;

import visitor.XadrezVisitor;

import ast.JogadaNode;

public class LanceNode extends ASTNode {
    private int numeroTurno;
    private JogadaNode jogadaBrancas;
    private JogadaNode jogadaPretas;

    public LanceNode(int numeroTurno, JogadaNode jogadaBrancas, JogadaNode jogadaPretas) {
        this.numeroTurno = numeroTurno;
        this.jogadaBrancas = jogadaBrancas;
        this.jogadaPretas = jogadaPretas;
    }

    @Override
    public void accept(XadrezVisitor visitor) {
        visitor.visit(this);
    }

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