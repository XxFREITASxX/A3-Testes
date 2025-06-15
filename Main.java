import java.io.FileReader;
import ast.*;
import visitor.*;
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
            
            // 3. Geração de código intermediário
            GeradorCodigo gerador = new GeradorCodigo();
            ast.accept(gerador);
            
            // 4. Saída do código gerado
            System.out.println("=== CÓDIGO INTERMEDIÁRIO GERADO ===");
            System.out.println(gerador.getCodigo());
            
        } catch (Exception e) {
            System.err.println("Erro durante o processamento:");
            e.printStackTrace();
        }
    }
}