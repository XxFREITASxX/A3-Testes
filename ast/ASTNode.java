package ast;

import ast.visitor.XadrezVisitor;

public abstract class ASTNode{
    public abstract void accept(XadrezVisitor visitor);
}