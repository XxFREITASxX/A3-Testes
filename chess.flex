import java_cup.runtime.*;
import erros.ListaErros;

%%
%class ChessLexer
%unicode
%cup
%line
%column
%{
    private ListaErros listaErros;

    public ChessLexer(java.io.FileReader in, ListaErros listaErros) {
        this(in);
        this.listaErros = listaErros;
    }

    public ListaErros getListaErros(){
        return listaErros;
    }

    public void defineErro(int linha, int coluna, String texto){
        listaErros.defineErro(linha, coluna, texto);
    }

    public void defineErro(int linha, int coluna){
        listaErros.defineErro(linha, coluna);
    }

    public void defineErro(String texto){
        listaErros.defineErro(texto);
    }

    private Symbol simbolo(int code) {
        return new Symbol(code, yyline, yycolumn, null);
    }
   
    private Symbol simbolo(int code, Object value) {
        return new Symbol(code, yyline, yycolumn, value);
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

{RoqueGrande}      { return simbolo(sym.ROQUE_GRANDE, yytext()); }
{RoquePequeno}     { return simbolo(sym.ROQUE_PEQUENO, yytext()); }
{Xequemate}        { return simbolo(sym.XEQUEMATE, yytext()); }
{Xeque}            { return simbolo(sym.XEQUE, yytext()); }
{Captura}          { return simbolo(sym.CAPTURA, yytext()); }
{Promocao}         { return simbolo(sym.PROMOCAO, yytext()); }

{NumeroTurno}      { return simbolo(sym.NUMERO_TURNO, yytext()); }
{VitoriaBrancas}   { return simbolo(sym.VITORIA_BRANCAS, yytext()); }
{VitoriaPretas}    { return simbolo(sym.VITORIA_PRETAS, yytext()); }
{Empate}           { return simbolo(sym.EMPATE, yytext()); }

{Peca}             { return simbolo(sym.PECA, yytext()); }    
{Coluna}           { return simbolo(sym.COLUNA, yytext()); }   
{Linha}            { return simbolo(sym.LINHA, yytext()); }    
{Posicao}          { return simbolo(sym.POSICAO, yytext()); }

{WhiteSpace}       { /* Ignora */ }
<<EOF>>            { return simbolo(sym.EOF); }

.                  { this.defineErro(yyline, yycolumn, "Lexico - Simbolo desconhecido:" + yytext());}
