package ast;

import ast.visitor.XadrezVisitor;

public class RoqueNode extends JogadaNode {
    private boolean isRoqueGrande; // true = O-O-O (grande), false = O-O (pequeno)

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
}