package ast;

public enum SufixoJogada {
    XEQUE("+"),
    XEQUEMATE("#"),
    NENHUM(null);

    private final String simbolo;

    SufixoJogada(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }
    
    public static SufixoJogada verificarSimbolo(String simbolo) {
        if (simbolo == null) return NENHUM;
        return switch (simbolo) {
            case "+" -> XEQUE;
            case "#" -> XEQUEMATE;
            default -> throw new IllegalArgumentException("Sufixo inválido: " + simbolo);
        };
    }
}

public abstract class JogadaNode extends ASTNode{
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