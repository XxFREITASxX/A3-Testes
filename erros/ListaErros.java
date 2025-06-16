package erros;

import java.util.ArrayList;
import java.util.List;

public class ListaErros {
    
    private List<Erro> erros = new ArrayList<>();

    public ListaErros() {
        this.erros = new ArrayList<>();
    }

    public void defineErro(int l, int c, String texto) {
        Erro e = new Erro(l, c, texto);
        this.erros.add(e);
    }

    public void defineErro(int l, int c) {
        Erro e = new Erro(l, c);
        this.erros.add(e);
    }

    public void defineErro(String texto) {
        for (Erro e : this.erros) {
            if (e.getTexto() == null) {
                e.setTexto(texto);
                return;
            }
        }
    }

    public void dump() {
        for (Erro e : this.erros) {
            e.imprime();
        }
    }

    public boolean hasErros() {
        return !erros.isEmpty();
    }

    public List<Erro> getErros() {
        return erros;
    }
}