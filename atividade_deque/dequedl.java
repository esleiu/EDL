package atividade_deque;

public class dequedl implements ideque {
    private nodl inicio;
    private nodl fim;
    private int tamanho;

    public dequedl() {
        inicio = null;
        fim = null;
        tamanho = 0;
    }

    @Override
    public void inseririnicio(Object elemento) {
        nodl novo = new nodl(elemento);
        if (estavazia()) {
            inicio = fim = novo;
        } else {
            novo.setnext(inicio);
            inicio.setprev(novo);
            inicio = novo;
        }
        tamanho++;
    }

    @Override
    public void inserirfim(Object elemento) {
        nodl novo = new nodl(elemento);
        if (estavazia()) {
            inicio = fim = novo;
        } else {
            novo.setprev(fim);
            fim.setnext(novo);
            fim = novo;
        }
        tamanho++;
    }

    @Override
    public Object removerinicio() {
        if (estavazia()) throw new edequevazio("deque vazio");
        Object e = inicio.getelemento();
        inicio = inicio.getnext();
        if (inicio != null) {
            inicio.setprev(null);
        } else {
            fim = null;
        }
        tamanho--;
        return e;
    }

    @Override
    public Object removerfim() {
        if (estavazia()) throw new edequevazio("deque vazio");
        Object e = fim.getelemento();
        fim = fim.getprev();
        if (fim != null) {
            fim.setnext(null);
        } else {
            inicio = null;
        }
        tamanho--;
        return e;
    }

    @Override
    public Object primeiro() {
        if (estavazia()) throw new edequevazio("deque vazio");
        return inicio.getelemento();
    }

    @Override
    public Object ultimo() {
        if (estavazia()) throw new edequevazio("deque vazio");
        return fim.getelemento();
    }

    @Override
    public int tamanho() { return tamanho; }

    @Override
    public boolean estavazia() { return tamanho == 0; }
}
