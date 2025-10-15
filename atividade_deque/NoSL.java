package atividade_deque;
class NoSL {
    private Object elemento;
    private NoSL next;

    public NoSL(Object elemento) {
        this.elemento = elemento;
    }
    public Object getElemento() { return elemento; }
    public void setElemento(Object elemento) { this.elemento = elemento; }
    public NoSL getNext() { return next; }
    public void setNext(NoSL next) { this.next = next; }
}