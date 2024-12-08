package br.edu.ifpb.so.entities;

public class Barbeiro implements Runnable {
    private final Barbearia barbearia;

    public Barbeiro(Barbearia barbearia) {
        this.barbearia = barbearia;
    }

    @Override
    public void run() {
        while (true) {
            barbearia.atenderCliente();
            try {
                // Simula o tempo de corte de cabelo
                Thread.sleep((int) (Math.random() * 3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            barbearia.finalizarAtendimento();
        }
    }
}
