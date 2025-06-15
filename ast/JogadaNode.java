package ast;

import ast.visitor.XadrezVisitor;

public abstract class JogadaNode extends ASTNode {
    protected SufixoJogada sufixo = SufixoJogada.NENHUM; 

    public void setSufixo(String simbolo) {
        this.sufixo = SufixoJogada.verificarSimbolo(simbolo);
    }

    public boolean temXeque() {
        return sufixo == SufixoJogada.XEQUE;
    }

    public boolean temXequemate() {
        return sufixo == SufixoJogada.XEQUEMATE;
    }
}