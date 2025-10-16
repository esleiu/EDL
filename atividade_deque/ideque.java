package atividade_deque;

public interface ideque {
    void inseririnicio(Object elemento);
    void inserirfim(Object elemento);
    Object removerinicio();
    Object removerfim();
    Object primeiro();
    Object ultimo();
    int tamanho();
    boolean estavazia();
}
