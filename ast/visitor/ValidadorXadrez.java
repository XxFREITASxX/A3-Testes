package ast.visitor;

import ast.*;
import ast.enums.SufixoJogada;
import java.util.ArrayList;
import java.util.List;

public class ValidadorXadrez implements XadrezVisitor {
    private final List<String> erros = new ArrayList<>();
    private Tabuleiro tabuleiroAtual;

    public List<String> getErros() {
        return new ArrayList<>(erros);
    }

    @Override
    public void visit(PartidaNode node) {
        // Valida estrutura geral da partida
        if (node.getJogadas().isEmpty()) {
            erros.add("Partida sem jogadas");
        }
    }

    @Override
    public void visit(LanceNode node) {
        // Valida turno e jogadas básicas
        if (node.getNumeroTurno() <= 0) {
            erros.add("Número de turno inválido: " + node.getNumeroTurno());
        }
    }

    @Override
    public void visit(MovimentoNode node) {
        // Valida movimento específico
        if (node.getPeca().equals("P") && node.getDestino().charAt(1) == '8' && node.getPromocao() == null) {
            erros.add("Peão deve ser promovido na 8ª casa: " + node.getNotacaoAlgebrica());
        }

        if (node.temXequemate() && !tabuleiroAtual.estaEmXequeMate()) {
            erros.add("Movimento marcado como xeque-mate, mas a posição não é mate");
        }
    }

    @Override
    public void visit(RoqueNode node) {
        // Valida regras específicas do roque
        if (node.isGrande() && !tabuleiroAtual.roqueGrandePermitido()) {
            erros.add("Roque grande ilegal: torre ou rei já moveram");
        }
    }
}