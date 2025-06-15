package ast;

import visitor.XadrezVisitor;

public class RoqueNode extends JogadaNode {
    private boolean isRoqueGrande; // true = O-O-O (grande), false = O-O (pequeno)
    private boolean isBrancas;

    public RoqueNode(boolean isRoqueGrande) {
        this.isRoqueGrande = isRoqueGrande;
    }

    @Override
    public void accept(XadrezVisitor visitor) {
        visitor.visit(this);
    }

    public boolean isRoqueGrande() {
        return isRoqueGrande;
    }

    public boolean isBrancas(){
         return isBrancas;
    }
}