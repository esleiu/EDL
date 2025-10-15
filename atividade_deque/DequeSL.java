package atividade_deque;
public class DequeSL implements IDeque {
    private NoSL inicio; 
    private NoSL fim; 
    private int tamanho;

    public DequeSL() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    @Override
    public void inserirInicio(Object elemento) { // O(1)
        NoSL novoNo = new NoSL(elemento);
        if (estaVazia()) {
            inicio = novoNo;
            fim = novoNo;
        } else {
            novoNo.setNext(inicio);
            inicio = novoNo;
        }
        tamanho++;
    }

    @Override
    public void inserirFim(Object elemento) { // O(1)
        NoSL novoNo = new NoSL(elemento);
        if (estaVazia()) {
            inicio = novoNo;
            fim = novoNo;
        } else {
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
        
        if (inicio == null) {
            fim = null;
        }
        tamanho--;
        return elemento;
    }

    @Override
    public Object removerFim() { // O(n)
        if (estaVazia()) throw new EDequeVazio("Deque vazio.");
        
        Object elemento = fim.getElemento();

        if (tamanho == 1) { 
            inicio = null;
            fim = null;
        } else {
            // percorre a lista (O(n))
            NoSL atual = inicio;
            while (atual.getNext() != fim) { 
                atual = atual.getNext();
            }
            
            fim = atual;
            fim.setNext(null); 
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