package atividade_deque;
public interface IDeque {
    void inserirInicio(Object elemento);
    Object removerInicio(); 
    void inserirFim(Object elemento);
    Object removerFim(); 

    Object primeiro();
    Object ultimo(); 
    int tamanho();
    boolean estaVazia();
}