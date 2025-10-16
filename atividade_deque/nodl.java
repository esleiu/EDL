package atividade_deque;

class nodl {
    private Object elemento;
    private nodl next;
    private nodl prev;

    nodl(Object elemento) { this.elemento = elemento; }

    Object getelemento() { return elemento; }
    void setelemento(Object elemento) { this.elemento = elemento; }
    nodl getnext() { return next; }
    void setnext(nodl next) { this.next = next; }
    nodl getprev() { return prev; }
    void setprev(nodl prev) { this.prev = prev; }
}
