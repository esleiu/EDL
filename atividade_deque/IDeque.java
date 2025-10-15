package atividade_deque;
public interface IDeque {
    void inseririnicio(Object elemento);
    Object removerinicio(); 
    void inserirfim(Object elemento);
    Object removerfim(); 

    Object primeiro();
    Object ultimo(); 
    int tamanho();
    boolean estavazia();
}