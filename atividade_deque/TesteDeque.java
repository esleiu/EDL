package atividade_deque;

public class TesteDeque {
    public static void testarRemocaoFim(IDeque deque, String nome, int N) {
        long startTime, endTime;
        long duracao;
        for (int i = 0; i < N; i++) {
            deque.inserirfim(i);
        }

        System.out.println("--- Teste de Eficiencia (" + nome + ") ---");
        System.out.println("Total de Elementos (N): " + N);
        startTime = System.currentTimeMillis();
        
        for (int i = 0; i < N; i++) {
            deque.removerfim(); 
        }

        endTime = System.currentTimeMillis();
        duracao = endTime - startTime;

        System.out.println("Tempo Total para " + N + " remocoes: " + duracao + " ms");
        System.out.println("Complexidade Esperada: " + (nome.contains("SL") ? "O(N^2) Total" : "O(N) Total"));
        System.out.println("------------------------------------------");
    }

    public static void main(String[] args) {
        final int N = 10000; 
        
        System.out.println("******************************************************");
        System.out.println("ANALISE DE COMPLEXIDADE: O(1) vs O(N) em removerFim()");
        System.out.println("******************************************************");
    IDeque dequeDL = new dequedl();
    testarRemocaoFim(dequeDL, "dequedl (Duplamente Encadeada)", N);
    IDeque dequeSL = new dequesl();
    testarRemocaoFim(dequeSL, "dequesl (Simplesmente Encadeada)", N);
        
        System.out.println("Para resultados mais dramaticos, aumente o valor de N (ex: 50000).");
    }
}