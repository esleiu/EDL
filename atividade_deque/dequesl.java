package atividade_deque;

public class dequesl implements ideque {
    private nosl inicio;
    private nosl fim;
    private int tamanho;

    public dequesl() {
        inicio = null;
        fim = null;
        tamanho = 0;
    }

    @Override
    public void inseririnicio(Object elemento) {
        nosl novo = new nosl(elemento);
        if (estavazia()) {
            inicio = fim = novo;
        } else {
            novo.setnext(inicio);
            inicio = novo;
        }
        tamanho++;
    }

    @Override
    public void inserirfim(Object elemento) {
        nosl novo = new nosl(elemento);
        if (estavazia()) {
            inicio = fim = novo;
        } else {
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
        if (inicio == null) fim = null;
        tamanho--;
        return e;
    }

    @Override
    public Object removerfim() {
        if (estavazia()) throw new edequevazio("deque vazio");
        Object e = fim.getelemento();
        if (tamanho == 1) {
            inicio = fim = null;
        } else {
            nosl cur = inicio;
            while (cur.getnext() != fim) cur = cur.getnext();
            fim = cur;
            fim.setnext(null);
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
