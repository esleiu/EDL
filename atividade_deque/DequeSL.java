package atividade_deque;
public class dequesl implements IDeque {
    private NoSL inicioDequesl; 
    private NoSL fimDequesl; 
    private int tamanhoDequesl;

    public dequesl() {
        this.inicioDequesl = null;
        this.fimDequesl = null;
        this.tamanhoDequesl = 0;
    }

    @Override
    public void inseririnicio(Object elemento) { // O(1)
        NoSL novoNo = new NoSL(elemento);
        if (estaVazia()) {
            inicioDequesl = novoNo;
            fimDequesl = novoNo;
        } else {
            novoNo.setNext(inicioDequesl);
            inicioDequesl = novoNo;
        }
        tamanhoDequesl++;
    }

    @Override
    public void inserirfim(Object elemento) { // O(1)
        NoSL novoNo = new NoSL(elemento);
        if (estaVazia()) {
            inicioDequesl = novoNo;
            fimDequesl = novoNo;
        } else {
            fimDequesl.setNext(novoNo);
            fimDequesl = novoNo;
        }
        tamanhoDequesl++;
    }

    @Override
    public Object removerinicio() { // O(1)
        if (estaVazia()) throw new EDequeVazio("Deque vazio."); 

        Object elemento = inicioDequesl.getElemento();
        inicioDequesl = inicioDequesl.getNext(); 
        
        if (inicioDequesl == null) {
            fimDequesl = null;
        }
        tamanhoDequesl--;
        return elemento;
    }

    @Override
    public Object removerfim() { // O(n)
        if (estaVazia()) throw new EDequeVazio("Deque vazio.");
        
        Object elemento = fimDequesl.getElemento();

        if (tamanhoDequesl == 1) { 
            inicioDequesl = null;
            fimDequesl = null;
        } else {
            //  (O(n))
            NoSL atual = inicioDequesl;
            while (atual.getNext() != fimDequesl) { 
                atual = atual.getNext();
            }
            
            fimDequesl = atual;
            fimDequesl.setNext(null); 
        }
        
        tamanhoDequesl--;
        return elemento;
    }

    @Override
    public Object primeiro() { 
        if (estaVazia()) throw new EDequeVazio("Deque vazio.");
        return inicioDequesl.getElemento();
    }
    
    @Override
    public Object ultimo() { 
        if (estaVazia()) throw new EDequeVazio("Deque vazio.");
        return fimDequesl.getElemento();
    }

    @Override
    public int tamanho() { return tamanhoDequesl; } 
    
    @Override
    public boolean estavazia() { return tamanhoDequesl == 0; }
}