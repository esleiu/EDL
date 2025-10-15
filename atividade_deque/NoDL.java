package atividade_deque;
class NoDL {
    private Object elemento;
    private NoDL next;
    private NoDL prev;

    public NoDL(Object elemento) {
        this.elemento = elemento;
    }

    public Object getElemento() { return elemento; }
    public void setElemento(Object elemento) { this.elemento = elemento; }
    public NoDL getNext() { return next; }
    public void setNext(NoDL next) { this.next = next; }
    public NoDL getPrev() { return prev; }
    public void setPrev(NoDL prev) { this.prev = prev; }
}