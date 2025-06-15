package model;

public enum TipoSufixo {
    XEQUE("+"),
    XEQUEMATE("#"),
    NENHUM(null);

    private final String simbolo;

    TipoSufixo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }
    
    public static TipoSufixo verificarSimbolo(String simbolo) {
        if (simbolo == null) return NENHUM;
        return switch (simbolo) {
            case "+" -> XEQUE;
            case "#" -> XEQUEMATE;
            default -> throw new IllegalArgumentException("Sufixo inválido: " + simbolo);
        };
    }
}