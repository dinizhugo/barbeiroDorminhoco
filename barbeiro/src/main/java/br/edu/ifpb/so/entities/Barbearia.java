package br.edu.ifpb.so.entities;

import java.util.concurrent.Semaphore;

public class Barbearia {
    private final Semaphore clientes; // Semaforo para controler clientes;
    private final Semaphore barbeiro; // Semáforo para indicar que o barbeiro está disponível
    private final Semaphore mutex; // Exclusão mútua para cadeiras
    private int cadeirasDisponiveis;

    public Barbearia(int quantidade) {
        this.cadeirasDisponiveis = quantidade;
        this.clientes = new Semaphore(0);
        this.barbeiro = new Semaphore(0);
        this.mutex = new Semaphore(1);
    }

    public void entrarCliente(int nomeCliente) {
        try {
            mutex.acquire(); // garante que apenas uma thread por vez possa acessar/modificar uma seção crítica de código ou recurso compartilhado.
            if (cadeirasDisponiveis > 0) {
                cadeirasDisponiveis--;
                System.out.println("O Cliente " + nomeCliente + " sentou-se na sala de espera. Cadeiras disponíveis: " + cadeirasDisponiveis);
                clientes.release(); // Notifica o barbeiro
                mutex.release();
                barbeiro.acquire(); // Aguardar atendimento
                System.out.println("O Cliente " + nomeCliente + " está sendo atendido.");
            } else {
                System.out.println("O Cliente " + nomeCliente + " foi embora, pois não há cadeiras disponíveis.");
                mutex.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void atenderCliente() {
        try {
            clientes.acquire(); // Aguarda um cliente
            mutex.acquire();
            cadeirasDisponiveis++;
            System.out.println("Barbeiro está atendendo um cliente. Cadeiras disponíveis: " + cadeirasDisponiveis);
            mutex.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void finalizarAtendimento() {
        System.out.println("Barbeiro terminou de atender o cliente.");
        barbeiro.release(); // Libera o cliente atendido
    }
}
