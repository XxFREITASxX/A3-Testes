package ast;

import model.TipoPeca;
import visitor.XadrezVisitor;

public class MovimentoNode extends JogadaNode {
    private TipoPeca peca;
    private String desambiguidade;
    private boolean captura;
    private String destino;
    private TipoPeca promocao;

    public MovimentoNode(
        TipoPeca peca,
        String desambiguidade,
        boolean captura,
        String destino,
        TipoPeca promocao
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

    public String getNotacaoAlgebrica() {
    StringBuilder notacao = new StringBuilder();
    
    if (peca != TipoPeca.PEAO) {
        notacao.append(peca.toString().charAt(0));
    }
    
    if (desambiguidade != null) {
        notacao.append(desambiguidade);
    }
    
    if (captura) {
        notacao.append('x');
    }
    
    notacao.append(destino);
    
    if (promocao != null) {
        notacao.append('=').append(promocao.toString().charAt(0));
    }
    
    return notacao.toString();
}
}