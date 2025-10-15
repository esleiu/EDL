package atividade_deque;
public class dequedl implements IDeque {
    private NoDL inicioDequedl; 
    private NoDL fimDequedl;
    private int tamanhoDequedl;

    public dequedl() {
        this.inicioDequedl = null;
        this.fimDequedl = null;
        this.tamanhoDequedl = 0;
    }

    @Override
    public void inseririnicio(Object elemento) { // O(1)
        NoDL novoNo = new NoDL(elemento);
        if (estaVazia()) {
            inicioDequedl = novoNo;
            fimDequedl = novoNo;
        } else {
            novoNo.setNext(inicioDequedl);
            inicioDequedl.setPrev(novoNo);
            inicioDequedl = novoNo;
        }
        tamanhoDequedl++;
    }

    @Override
    public void inserirfim(Object elemento) { // O(1)
        NoDL novoNo = new NoDL(elemento);
        if (estaVazia()) {
            inicioDequedl = novoNo;
            fimDequedl = novoNo;
        } else {
            novoNo.setPrev(fimDequedl);
            fimDequedl.setNext(novoNo);
            fimDequedl = novoNo;
        }
        tamanhoDequedl++;
    }

    @Override
    public Object removerinicio() { // O(1)
        if (estaVazia()) throw new EDequeVazio("Deque vazio."); 

        Object elemento = inicioDequedl.getElemento();
        inicioDequedl = inicioDequedl.getNext();
        
        if (inicioDequedl != null) {
            inicioDequedl.setPrev(null);
        } else {
            fimDequedl = null; 
        }
        tamanhoDequedl--;
        return elemento;
    }

    @Override
    public Object removerfim() { // O(1)
        if (estaVazia()) throw new EDequeVazio("Deque vazio."); 

        Object elemento = fimDequedl.getElemento();
        fimDequedl = fimDequedl.getPrev(); 
        if (fimDequedl != null) {
            fimDequedl.setNext(null); 
        } else {
            inicioDequedl = null; 
        }
        tamanhoDequedl--;
        return elemento;
    }

    @Override
    public Object primeiro() { 
        if (estaVazia()) throw new EDequeVazio("Deque vazio.");
        return inicioDequedl.getElemento();
    }
    @Override
    public Object ultimo() { 
        if (estaVazia()) throw new EDequeVazio("Deque vazio.");
        return fimDequedl.getElemento();
    }

    @Override
    public int tamanho() { return tamanhoDequedl; } 

    @Override
    public boolean estavazia() { return tamanhoDequedl == 0; }
}