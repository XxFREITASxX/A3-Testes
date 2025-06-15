package ast;

import visitor.XadrezVisitor;

public abstract class ASTNode{
    public abstract void accept(XadrezVisitor visitor);
}