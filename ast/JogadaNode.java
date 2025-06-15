package ast;

import model.TipoSufixo;
import visitor.XadrezVisitor;

public abstract class JogadaNode extends ASTNode {
    protected TipoSufixo sufixo = TipoSufixo.NENHUM; 

    public void setSufixo(String simbolo) {
        this.sufixo = TipoSufixo.verificarSimbolo(simbolo);
    }

    public boolean temXeque() {
        return sufixo == TipoSufixo.XEQUE;
    }

    public boolean temXequemate() {
        return sufixo == TipoSufixo.XEQUEMATE;
    }
}