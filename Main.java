import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static boolean whitesTurn = true;
    private static ChessBoard board = new ChessBoard();

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Por favor, especifique o arquivo de entrada como argumento.");
            System.exit(1);
        }

        String inputFile = args[0];
        
        try (FileReader reader = new FileReader(inputFile)) {
            ChessLexer lexer = new ChessLexer(reader);
            parserName parser = new parserName(lexer);
            
            System.out.println("Analisando partida de xadrez no arquivo " + inputFile);
            System.out.println("===============================================");
            
            board.initialize();
            parser.parse();
            
            System.out.println("\nAnálise concluída. Partida válida!");
            
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("\nErro durante a análise: " + e.getMessage());
            System.exit(1);
        }
    }

    static class ChessBoard {
        private Piece[][] squares = new Piece[8][8];
        private Piece lastMovedPiece = null;

        public void initialize() {
            squares[0][0] = new Piece('R', true);
            squares[0][1] = new Piece('N', true);
            squares[0][2] = new Piece('B', true);
            squares[0][3] = new Piece('Q', true);
            squares[0][4] = new Piece('K', true);
            squares[0][5] = new Piece('B', true);
            squares[0][6] = new Piece('N', true);
            squares[0][7] = new Piece('R', true);
            for (int i = 0; i < 8; i++) {
                squares[1][i] = new Piece('P', true);
            }

            squares[7][0] = new Piece('R', false);
            squares[7][1] = new Piece('N', false);
            squares[7][2] = new Piece('B', false);
            squares[7][3] = new Piece('Q', false);
            squares[7][4] = new Piece('K', false);
            squares[7][5] = new Piece('B', false);
            squares[7][6] = new Piece('N', false);
            squares[7][7] = new Piece('R', false);
            for (int i = 0; i < 8; i++) {
                squares[6][i] = new Piece('P', false);
            }
        }

        private String getPosition(Piece piece) {
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (squares[row][col] == piece) {
                        return "" + (char)('a' + col) + (8 - row);
                    }
                }
            }
            return null;
        }

        private Piece identifyMovingPiece(String to, char pieceType, boolean isWhite) {
            int toCol = to.charAt(0) - 'a';
            int toRow = 8 - Character.getNumericValue(to.charAt(1));
            
            if (Character.toUpperCase(pieceType) == 'P') {
                int direction = isWhite ? -1 : 1;
                int possibleFromRow = toRow + direction;
                
                if (possibleFromRow >= 0 && possibleFromRow < 8) {
                    Piece possiblePawn = squares[possibleFromRow][toCol];
                    if (possiblePawn != null && possiblePawn.type == 'P' && possiblePawn.isWhite == isWhite) {
                        return possiblePawn;
                    }
                }
                
                if ((isWhite && toRow == 4) || (!isWhite && toRow == 3)) {
                    possibleFromRow = toRow + 2 * direction;
                    if (possibleFromRow >= 0 && possibleFromRow < 8) {
                        Piece possiblePawn = squares[possibleFromRow][toCol];
                        if (possiblePawn != null && possiblePawn.type == 'P' && possiblePawn.isWhite == isWhite) {
                            return possiblePawn;
                        }
                    }
                }
            }
            
            return null;
        }

        public boolean isValidMove(String from, String to, char piece, boolean isWhite) {
            if (isWhite != whitesTurn) {
                throw new RuntimeException("Não é a vez deste jogador");
            }

            Piece movingPiece;
            if (from == null) {
                movingPiece = identifyMovingPiece(to, piece, isWhite);
                if (movingPiece == null) {
                    throw new RuntimeException("Não foi possível identificar a peça sendo movida para " + to);
                }
                from = getPosition(movingPiece);
            } else {
                int fromCol = from.charAt(0) - 'a';
                int fromRow = 8 - Character.getNumericValue(from.charAt(1));
                movingPiece = squares[fromRow][fromCol];
            }

            int fromCol = from.charAt(0) - 'a';
            int fromRow = 8 - Character.getNumericValue(from.charAt(1));
            int toCol = to.charAt(0) - 'a';
            int toRow = 8 - Character.getNumericValue(to.charAt(1));

            if (movingPiece == null) {
                throw new RuntimeException("Não há peça na posição " + from);
            }

            if (movingPiece.isWhite != isWhite) {
                throw new RuntimeException("A peça em " + from + " não pertence ao jogador atual");
            }

            if (Character.toUpperCase(movingPiece.type) != Character.toUpperCase(piece)) {
                throw new RuntimeException("Tipo de peça incorreto em " + from);
            }

            if (Character.toUpperCase(piece) == 'P') {
                if (lastMovedPiece == movingPiece && from.charAt(0) == to.charAt(0)) {
                    throw new RuntimeException("Não pode mover o mesmo peão duas vezes seguidas sem captura");
                }
                
                boolean isCapture = (from.charAt(0) != to.charAt(0));
                
                if (!isCapture && movingPiece.hasMoved && Math.abs(toRow - fromRow) == 2) {
                    throw new RuntimeException("Peão só pode avançar duas casas no primeiro movimento");
                }
            }

            boolean isValid;
            switch (Character.toUpperCase(piece)) {
                case 'P': 
                    isValid = validatePawnMove(fromRow, fromCol, toRow, toCol, isWhite);
                    break;
                case 'N': 
                    isValid = validateKnightMove(fromRow, fromCol, toRow, toCol);
                    break;
                case 'B': 
                    isValid = validateBishopMove(fromRow, fromCol, toRow, toCol);
                    break;
                case 'R': 
                    isValid = validateRookMove(fromRow, fromCol, toRow, toCol);
                    break;
                case 'Q': 
                    isValid = validateQueenMove(fromRow, fromCol, toRow, toCol);
                    break;
                case 'K': 
                    isValid = validateKingMove(fromRow, fromCol, toRow, toCol);
                    break;
                default:
                    throw new RuntimeException("Tipo de peça inválido: " + piece);
            }

            if (!isValid) {
                throw new RuntimeException("Movimento inválido para " + piece + " de " + from + " para " + to);
            }

            return true;
        }

        private boolean validatePawnMove(int fromRow, int fromCol, int toRow, int toCol, boolean isWhite) {
            int direction = isWhite ? -1 : 1;
            
            if (fromCol == toCol) {
                if (toRow == fromRow + direction && squares[toRow][toCol] == null) {
                    return true;
                }
                
                if ((isWhite && fromRow == 1) || (!isWhite && fromRow == 6)) {
                    if (toRow == fromRow + 2 * direction && 
                        squares[fromRow + direction][fromCol] == null && 
                        squares[toRow][toCol] == null) {
                        return true;
                    }
                }
            }
            else if (Math.abs(fromCol - toCol) == 1 && toRow == fromRow + direction) {
                if (squares[toRow][toCol] != null && squares[toRow][toCol].isWhite != isWhite) {
                    return true;
                }
            }
            
            return false;
        }

        private boolean validateKnightMove(int fromRow, int fromCol, int toRow, int toCol) {
            int rowDiff = Math.abs(fromRow - toRow);
            int colDiff = Math.abs(fromCol - toCol);
            return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
        }

        private boolean validateBishopMove(int fromRow, int fromCol, int toRow, int toCol) {
            if (Math.abs(fromRow - toRow) != Math.abs(fromCol - toCol)) {
                return false;
            }
            
            int rowStep = (toRow > fromRow) ? 1 : -1;
            int colStep = (toCol > fromCol) ? 1 : -1;
            
            for (int r = fromRow + rowStep, c = fromCol + colStep; r != toRow; r += rowStep, c += colStep) {
                if (squares[r][c] != null) {
                    return false;
                }
            }
            
            return true;
        }

        private boolean validateRookMove(int fromRow, int fromCol, int toRow, int toCol) {
            if (fromRow == toRow) {
                int step = (toCol > fromCol) ? 1 : -1;
                for (int c = fromCol + step; c != toCol; c += step) {
                    if (squares[fromRow][c] != null) {
                        return false;
                    }
                }
                return true;
            }
            else if (fromCol == toCol) {
                int step = (toRow > fromRow) ? 1 : -1;
                for (int r = fromRow + step; r != toRow; r += step) {
                    if (squares[r][fromCol] != null) {
                        return false;
                    }
                }
                return true;
            }
            
            return false;
        }

        private boolean validateQueenMove(int fromRow, int fromCol, int toRow, int toCol) {
            return validateRookMove(fromRow, fromCol, toRow, toCol) || 
                   validateBishopMove(fromRow, fromCol, toRow, toCol);
        }

        private boolean validateKingMove(int fromRow, int fromCol, int toRow, int toCol) {
            return Math.abs(fromRow - toRow) <= 1 && Math.abs(fromCol - toCol) <= 1;
        }

        public void makeMove(String from, String to, char piece, boolean isWhite) {
            if (isValidMove(from, to, piece, isWhite)) {
                int fromCol = from.charAt(0) - 'a';
                int fromRow = 8 - Character.getNumericValue(from.charAt(1));
                int toCol = to.charAt(0) - 'a';
                int toRow = 8 - Character.getNumericValue(to.charAt(1));

                squares[fromRow][fromCol].hasMoved = true;
                lastMovedPiece = squares[fromRow][fromCol];

                squares[toRow][toCol] = squares[fromRow][fromCol];
                squares[fromRow][fromCol] = null;
                
                whitesTurn = !whitesTurn;
            }
        }
    }

    static class Piece {
        char type;
        boolean isWhite;
        boolean hasMoved = false;

        public Piece(char type, boolean isWhite) {
            this.type = type;
            this.isWhite = isWhite;
        }
    }
}