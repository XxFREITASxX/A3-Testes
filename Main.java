import java.io.FileReader;
import ast.*;
import visitor.*;
import java_cup.runtime.*;
import erros.ListaErros;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. Executar lexer e parser
            ListaErros listaErros = new ListaErros();
            ChessLexer lexer = new ChessLexer(new FileReader("chess.txt"), listaErros);
            ChessParser parser = new ChessParser(lexer);
            
            // 2. Construir AST
            Symbol result = parser.parse();
            
            // Verifica se houve erros
            if (listaErros.hasErros()) {
                System.err.println("\n=== ERROS ENCONTRADOS ===");
                listaErros.dump();
                System.err.println("\nTotal de erros: " + listaErros.getErros().size());
                return; // Encerra a execução se houver erros
            }
            
            // Se não houve erros, continua com a geração de código
            PartidaNode ast = (PartidaNode) result.value;
            
            // 3. Geração de código intermediário
            GeradorCodigo gerador = new GeradorCodigo();
            ast.accept(gerador);
            
            // 4. Saída do código gerado
            System.out.println("\n=== CÓDIGO INTERMEDIÁRIO GERADO ===");
            System.out.println(gerador.getCodigo());
            
        } catch (Exception e) {
            System.err.println("\n=== ERRO FATAL ===");
            System.err.println("Erro durante o processamento: " + e.getMessage());
            e.printStackTrace();
        }
    }
}