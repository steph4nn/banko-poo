package modelos;

import java.util.ArrayList;

public class Conta {
    int id;
    String data;
    double saldo;
    ArrayList<Correntista> correntistas;

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public double getSaldo() {
        return saldo;
    }
    

    public Conta(int id, String data, double saldo) {
        this.id = id;
        this.data = data;
        this.saldo = saldo;
    }

    void depositar(double valor) {
        saldo += valor;
    }
    void creditar(double valor) {
        saldo -= valor;
    }
    void transferir(double valor, Conta destino) {
        saldo -= valor;
        destino.depositar(valor);
    }
}
