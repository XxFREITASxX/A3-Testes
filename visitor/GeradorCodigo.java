package visitor;

import ast.*;
import java.util.Objects;

public class GeradorCodigo implements XadrezVisitor {
    private final StringBuilder codigo = new StringBuilder();
    private int turnoAtual = 0;

    @Override
    public void visit(PartidaNode node) {
        node.getJogadas().forEach(lance -> lance.accept(this));
        if (node.getResultado() != null) {
            codigo.append("\nResultado: ").append(node.getResultado().getNotacao());
        }
    }

    @Override
    public void visit(LanceNode node) {
        turnoAtual = node.getNumeroTurno();
        
        codigo.append("TURNO ").append(turnoAtual).append("\n");
        node.getJogadaBrancas().accept(this);
        codigo.append("\n");
        
        if (node.getJogadaPretas() != null) {
            node.getJogadaPretas().accept(this);
            codigo.append("\n");
        }
        codigo.append("\n");
    }

    @Override
    public void visit(MovimentoNode node) {
        Objects.requireNonNull(node, "MovimentoNode não pode ser nulo");
        
        // Peça
        if (node.getPeca() != null) {
            switch (node.getPeca()) {
                case PEAO -> codigo.append("PEAO");
                case CAVALO -> codigo.append("CAVALO");
                case BISPO -> codigo.append("BISPO");
                case TORRE -> codigo.append("TORRE");
                case RAINHA -> codigo.append("RAINHA");
                case REI -> codigo.append("REI");
            }
        }
        
        // Desambiguidade (origem)
        if (node.getDesambiguidade() != null) {
            codigo.append(" ").append(node.getDesambiguidade().toUpperCase());
        }
        
        // Captura
        if (node.isCaptura()) {
            codigo.append(" CAPTURA ");
        } else {
            codigo.append(" ");
        }
        
        // Destino
        codigo.append(node.getDestino().toUpperCase());
        
        // Promoção
        if (node.getPromocao() != null) {
            codigo.append(" PROMOVE ").append(node.getPromocao().name());
        }
        
        // Xeque/Xequemate
        if (node.temXequemate()) {
            codigo.append(" XEQUEMATE");
        } else if (node.temXeque()) {
            codigo.append(" XEQUE");
        }
    }

    @Override
    public void visit(RoqueNode node) {
        Objects.requireNonNull(node, "RoqueNode não pode ser nulo");
        
        if (node.isRoqueGrande()) {
            codigo.append("ROQUE GRANDE");
        } else {
            codigo.append("ROQUE PEQUENO");
        }
        
        // Xeque/Xequemate
        if (node.temXequemate()) {
            codigo.append(" XEQUEMATE");
        } else if (node.temXeque()) {
            codigo.append(" XEQUE");
        }
    }

    public String getCodigo() {
        return codigo.toString();
    }
}