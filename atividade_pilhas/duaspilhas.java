package atividade_pilhas;

public class duaspilhas {

    private static final int CAP_MIN = 12;
    private Object[] lista;
    private int topover;
    private int topopre;
    private int cap;

    public duaspilhas(int capinicial) {
        if (capinicial < 2) {
            throw new IllegalArgumentException("capacidade inicial deve ser no minimo 2");
        }
        this.cap = capinicial;
        this.lista = new Object[this.cap];
        this.topover = -1; 
        this.topopre = this.cap; 
    }

    private void verredim() {
        if (topover + 1 == topopre) {
            redim(cap * 2);
        } else if (tamtotal() <= cap / 3 && cap > CAP_MIN) {
            redim(cap / 2);
        }
    }

    private void redim(int novacap) {
        Object[] novalista = new Object[novacap];

        for (int i = 0; i <= topover; i++) {
            novalista[i] = lista[i];
        }

        int novoidxpre = novacap;
        
        for (int i = cap - 1; i >= topopre; i--) {
            novoidxpre--;
            novalista[novoidxpre] = lista[i];
        }

        this.lista = novalista;
        this.cap = novacap;
        this.topopre = novoidxpre;
    }


    public void pushver(Object elem) {
        verredim();
        lista[++topover] = elem;
    }

    public Object popver() {
        if (vaziaver()) {
            throw new pilhavaziaexcecao("a pilha vermelha esta vazia");
        }
        Object elem = lista[topover];

        lista[topover] = null; 
        topover--;

        verredim();
        return elem;
    }

    public Object topver() {
        if (vaziaver()) {
            throw new pilhavaziaexcecao("a pilha vermelha esta vazia");
        }
        return lista[topover];
    }

    public boolean vaziaver() {
        return topover == -1;
    }

    public int tamver() {
        return topover + 1;
    }

    public void pushpre(Object elem) {
        verredim();
        lista[--topopre] = elem;
    }

    public Object poppre() {
        if (vaziapre()) {
            throw new pilhavaziaexcecao("a pilha preta esta vazia");
        }
        Object elem = lista[topopre];
        lista[topopre] = null; 
        topopre++;
        verredim();
        return elem;
    }

    public Object toppre() {
        if (vaziapre()) {
            throw new pilhavaziaexcecao("a pilha preta esta vazia");
        }
        return lista[topopre];
    }

    public boolean vaziapre() {
        return topopre == cap;
    }

    public int tampre() {
        return cap - topopre;
    }

    public int tamtotal() {
        return tamver() + tampre();
    }

    public int getcap() {
        return cap;
    }

    @Override

    public String toString() {
        return "duaspilhas [cap=" + cap +
                ", ver=" + tamver() +
                ", pre=" + tampre() +
                ", total=" + tamtotal() + "]";
    }
    
    public void listar() {

        for (int i = 0; i < cap; i++) { 
            if (lista[i] != null) {
                System.out.print(lista[i] + " ");
            } else {
                System.out.print(" - ");
            }
        }
        System.out.println();
    }
}