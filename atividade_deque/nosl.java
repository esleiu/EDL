package atividade_deque;

class nosl {
    private Object elemento;
    private nosl next;

    nosl(Object elemento) { this.elemento = elemento; }

    Object getelemento() { return elemento; }
    void setelemento(Object elemento) { this.elemento = elemento; }
    nosl getnext() { return next; }
    void setnext(nosl next) { this.next = next; }
}
