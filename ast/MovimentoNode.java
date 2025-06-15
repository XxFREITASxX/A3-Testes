package ast;

import ast.visitor.XadrezVisitor;

public class MovimentoNode extends JogadaNode {
    private TipoPeca peca;          // Usando o enum (não mais String)
    private String desambiguidade;  // Ex: "e" em "exd5" ou "1" em "R1a3"
    private boolean captura;        // Indica se houve captura ("x")
    private String destino;         // Casa de destino (ex: "f3")
    private TipoPeca promocao;      // Peça promovida (ex: "Q" em "e8=Q") ou null

    public MovimentoNode(
        TipoPeca peca,             // Agora recebe TipoPeca, não String
        String desambiguidade,
        boolean captura,
        String destino,
        TipoPeca promocao          // TipoPeca para promoção também
    ) {
        this.peca = peca;
        this.desambiguidade = desambiguidade;
        this.captura = captura;
        this.destino = destino;
        this.promocao = promocao;
    }

    @Override
    public void accept(XadrezVisitor visitor) {
        visitor.visit(this);
    }

    public TipoPeca getPeca() {
        return peca;
    }

    public String getDesambiguidade() {
        return desambiguidade;
    }

    public boolean isCaptura() {
        return captura;
    }

    public String getDestino() {
        return destino;
    }

    public TipoPeca getPromocao() {
        return promocao;
    }
}