package br.edu.ifpb.so;


import br.edu.ifpb.so.entities.Barbearia;
import br.edu.ifpb.so.entities.Barbeiro;
import br.edu.ifpb.so.entities.Cliente;

public class Main {
    public static void main(String[] args) {
        Barbearia barbearia = new Barbearia(5); // Inicializa com 5 cadeiras

        Thread barbeiroThread = new Thread(new Barbeiro(barbearia));
        barbeiroThread.start();

        for (int i = 1; i <= 15; i++) {
            new Thread(new Cliente(i, barbearia)).start();
            try {
                Thread.sleep((int) (Math.random() * 2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}