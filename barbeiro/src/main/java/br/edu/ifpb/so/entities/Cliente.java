package br.edu.ifpb.so.entities;

public class Cliente implements Runnable{
    private int id;
    private final Barbearia barbearia;

    public Cliente(int id, Barbearia barbearia) {
        this.id = id;
        this.barbearia = barbearia;
    }

    @Override
    public void run() {
        barbearia.entrarCliente(id);
    }
}
