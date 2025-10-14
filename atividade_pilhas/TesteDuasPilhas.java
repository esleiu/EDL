package atividade_pilhas;

public class TesteDuasPilhas {

    public static void main(String[] args) {
        duaspilhas pilhas = new duaspilhas(12);

        System.out.println("--- estado inicial ---");
        System.out.println(pilhas);
        pilhas.listar();

        for (int i = 1; i <= 6; i++) pilhas.pushver("V" + i);
        for (int i = 1; i <= 6; i++) pilhas.pushpre("P" + i);
        System.out.println("\n--- cheio (sem duplicar ainda) ---");
        System.out.println(pilhas);
        pilhas.listar();

        pilhas.pushver("V7");
        System.out.println("\n--- após duplicação esperada (12 -> 24) ---");
        System.out.println(pilhas);
        pilhas.listar();

        while (pilhas.tamtotal() > 8) {
            if (!pilhas.vaziaver()) pilhas.popver();
            if (pilhas.tamtotal() > 8 && !pilhas.vaziapre()) pilhas.poppre();
        }
        System.out.println("\n--- após redução esperada (24 -> 12) ---");
        System.out.println(pilhas);
        pilhas.listar();

        while (!pilhas.vaziaver()) pilhas.popver();
        while (!pilhas.vaziapre()) pilhas.poppre();
        System.out.println("\n--- capacidade mínima preservada (permanece 12) ---");
        System.out.println(pilhas);
        pilhas.listar();

        for (int i = 1; i <= 6; i++) pilhas.pushver("R" + i);
        for (int i = 1; i <= 6; i++) pilhas.pushpre("B" + i);
        pilhas.pushpre("B7");
        System.out.println("\n--- nova duplicação confirmada (12 -> 24) ---");
        System.out.println(pilhas);
        pilhas.listar();
    }
}
