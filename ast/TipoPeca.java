package ast;

import ast.visitor.XadrezVisitor;

public enum TipoPeca {
    REI("K"),
    RAINHA("Q"),
    TORRE("R"),
    BISPO("B"),
    CAVALO("N"),
    PEAO(""); // Peão não tem símbolo

    private final String simbolo;

    TipoPeca(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }

    // Método para converter de String (lexer) para Enum
    public static TipoPeca fromSimbolo(String simbolo) {
        if (simbolo == null || simbolo.isEmpty()) return PEAO;
        return switch (simbolo) {
            case "K" -> REI;
            case "Q" -> RAINHA;
            case "R" -> TORRE;
            case "B" -> BISPO;
            case "N" -> CAVALO;
            default -> throw new IllegalArgumentException("Peça inválida: " + simbolo);
        };
    }
}