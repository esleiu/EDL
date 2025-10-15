package atividade_deque;
public class DequeDL implements IDeque {
    private NoDL inicio; 
    private NoDL fim;
    private int tamanho;

    public DequeDL() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    @Override
    public void inserirInicio(Object elemento) { // O(1)
        NoDL novoNo = new NoDL(elemento);
        if (estaVazia()) {
            inicio = novoNo;
            fim = novoNo;
        } else {
            novoNo.setNext(inicio);
            inicio.setPrev(novoNo);
            inicio = novoNo;
        }
        tamanho++;
    }

    @Override
    public void inserirFim(Object elemento) { // O(1)
        NoDL novoNo = new NoDL(elemento);
        if (estaVazia()) {
            inicio = novoNo;
            fim = novoNo;
        } else {
            novoNo.setPrev(fim);
            fim.setNext(novoNo);
            fim = novoNo;
        }
        tamanho++;
    }

    @Override
    public Object removerInicio() { // O(1)
        if (estaVazia()) throw new EDequeVazio("Deque vazio."); 

        Object elemento = inicio.getElemento();
        inicio = inicio.getNext();
        
        if (inicio != null) {
            inicio.setPrev(null);
        } else {
            fim = null; 
        }
        tamanho--;
        return elemento;
    }

    @Override
    public Object removerFim() { // O(1)
        if (estaVazia()) throw new EDequeVazio("Deque vazio."); 

        Object elemento = fim.getElemento();
        fim = fim.getPrev(); 
        if (fim != null) {
            fim.setNext(null); 
        } else {
            inicio = null; 
        }
        tamanho--;
        return elemento;
    }

    @Override
    public Object primeiro() { 
        if (estaVazia()) throw new EDequeVazio("Deque vazio.");
        return inicio.getElemento();
    }
    @Override
    public Object ultimo() { 
        if (estaVazia()) throw new EDequeVazio("Deque vazio.");
        return fim.getElemento();
    }

    @Override
    public int tamanho() { return tamanho; } 

    @Override
    public boolean estaVazia() { return tamanho == 0; }
}