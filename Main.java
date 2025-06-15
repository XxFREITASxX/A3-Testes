import java.io.FileReader;
import ast.*;
import java_cup.runtime.*;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. Executar lexer e parser
            ChessLexer lexer = new ChessLexer(new FileReader("chess.txt"));
            ChessParser parser = new ChessParser(lexer);
            
            // 2. Construir AST
            Symbol result = parser.parse();
            PartidaNode ast = (PartidaNode) result.value;
            
            // 3. Imprimir estrutura da AST
            System.out.println("=== ESTRUTURA DA AST ===");
            System.out.println("Partida com " + ast.getJogadas().size() + " lances");
            
            if (ast.getResultado() != null) {
                System.out.println("Resultado: " + ast.getResultado().name());
            }
            
            System.out.println("\nDetalhes dos Lances:");
            for (LanceNode lance : ast.getJogadas()) {
                printLance(lance);
            }
            
        } catch (Exception e) {
            System.err.println("Erro durante o parsing:");
            e.printStackTrace();
        }
    }
    
    private static void printLance(LanceNode lance) {
        System.out.println("\nTurno " + lance.getNumeroTurno() + ":");
        System.out.println("  Brancas: " + jogadaToString(lance.getJogadaBrancas()));
        
        if (lance.getJogadaPretas() != null) {
            System.out.println("  Pretas:  " + jogadaToString(lance.getJogadaPretas()));
        }
    }
    
    private static String jogadaToString(JogadaNode jogada) {
        if (jogada instanceof MovimentoNode) {
            return movimentoToString((MovimentoNode) jogada);
        } else if (jogada instanceof RoqueNode) {
            return roqueToString((RoqueNode) jogada);
        }
        return "Tipo de jogada desconhecido";
    }
    
    private static String movimentoToString(MovimentoNode movimento) {
        StringBuilder sb = new StringBuilder();
        
        // Peça
        if (movimento.getPeca() != TipoPeca.PEAO) {
            sb.append(movimento.getPeca().getSimbolo());
        }
        
        // Desambiguação
        if (movimento.getDesambiguidade() != null) {
            sb.append("(").append(movimento.getDesambiguidade()).append(")");
        }
        
        // Captura
        if (movimento.isCaptura()) {
            sb.append("x");
        }
        
        // Destino
        sb.append(movimento.getDestino());
        
        // Promoção
        if (movimento.getPromocao() != null) {
            sb.append("=").append(movimento.getPromocao().getSimbolo());
        }
        
        // Sufixo
        if (movimento.temXeque()) {
            sb.append("+");
        } else if (movimento.temXequemate()) {
            sb.append("#");
        }
        
        return sb.toString();
    }
    
    private static String roqueToString(RoqueNode roque) {
        String tipo = roque.isRoqueGrande() ? "O-O-O" : "O-O";
        if (roque.temXeque()) {
            return tipo + "+";
        } else if (roque.temXequemate()) {
            return tipo + "#";
        }
        return tipo;
    }
}