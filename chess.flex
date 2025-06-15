import java_cup.runtime.*;

%%
%class ChessLexer
%unicode
%cup
%line
%column
%{
    private Symbol symbol(int type) {
        return new Symbol(type);
    }
   
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, value);
    }
%}

/* Definições de padrões */
Linha = [1-8]
Coluna = [a-h]
Posicao = {Coluna}{Linha}
Peca = [KQRBN]
Captura = "x"
Xeque = "+"
Xequemate = "#"
Promocao = "="
RoquePequeno = "O-O"
RoqueGrande = "O-O-O"
NumeroTurno = [1-9][0-9]*"."
VitoriaBrancas = "1-0"
VitoriaPretas = "0-1"
Empate = "1\/2-1\/2"
WhiteSpace = [ \t\n\r]

%%

{RoqueGrande}      { return symbol(sym.ROQUE_GRANDE, yytext()); }
{RoquePequeno}     { return symbol(sym.ROQUE_PEQUENO, yytext()); }
{Xequemate}        { return symbol(sym.XEQUEMATE, yytext()); }
{Xeque}            { return symbol(sym.XEQUE, yytext()); }
{Captura}          { return symbol(sym.CAPTURA, yytext()); }
{Promocao}         { return symbol(sym.PROMOCAO, yytext()); }

{NumeroTurno}      { return symbol(sym.NUMERO_TURNO, yytext()); }
{VitoriaBrancas}   { return symbol(sym.VITORIA_BRANCAS, yytext()); }
{VitoriaPretas}    { return symbol(sym.VITORIA_PRETAS, yytext()); }
{Empate}           { return symbol(sym.EMPATE, yytext()); }

{Peca}             { return symbol(sym.PECA, yytext()); }    
{Coluna}           { return symbol(sym.COLUNA, yytext()); }   
{Linha}            { return symbol(sym.LINHA, yytext()); }    
{Posicao}          { return symbol(sym.POSICAO, yytext()); }

{WhiteSpace}       { /* Ignora */ }
<<EOF>>            { return symbol(sym.EOF); }

.                  { System.err.println("Caractere inválido: " + yytext()); }
