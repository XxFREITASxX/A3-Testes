package visitor;

import ast.PartidaNode;
import ast.LanceNode;
import ast.MovimentoNode;
import ast.RoqueNode;

public interface XadrezVisitor {
    void visit(PartidaNode node);
    void visit(LanceNode node);
    void visit(MovimentoNode node);
    void visit(RoqueNode node);
}