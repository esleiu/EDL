package atividade_deque;

public class testedeque {
    public static void testarremocaofim(ideque dq, String nome, int n) {
        for (int i = 0; i < n; i++) dq.inserirfim(i);
        long t0 = System.currentTimeMillis();
        for (int i = 0; i < n; i++) dq.removerfim();
        long t1 = System.currentTimeMillis();
        System.out.println("--- teste de eficiencia (" + nome + ") ---");
        System.out.println("total n: " + n);
        System.out.println("tempo total: " + (t1 - t0) + " ms");
        System.out.println("complexidade esperada: " + (nome.contains("sl") ? "o(n^2) total" : "o(n) total"));
        System.out.println("------------------------------------------");
    }

    public static void main(String[] args) {
        int n = 10000;
        System.out.println("analise: o(1) vs o(n) em removerfim()");
        ideque dl = new dequedl();
        testarremocaofim(dl, "dequedl (dll)", n);
        ideque sl = new dequesl();
        testarremocaofim(sl, "dequesl (sll)", n);
        System.out.println("para ver diferenca maior, aumente n (ex: 50000)");
    }
}
