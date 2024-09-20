package modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    

    public Conta(int id) {
        this.id = id;
        this.saldo = 0;
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data = dataAtual.format(formatter);  
    }

    public Conta(int id, String data, double saldo){
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

    
    public void adicionarCorrentistaTitular(Correntista co){
        if (correntistas.isEmpty()){
            correntistas.add(co);
        }
    }

    public void adicionarCorrentista(Correntista co){
        correntistas.add(co);
    }

    public void removerCorrentistaCotitular(Correntista co){
        if (!this.verificarTitular(co.getCpf())){
            correntistas.remove(co);
        }
    }

    public Boolean verificarTitular(String cpf){
        if (correntistas.getFirst().getCpf().equals(cpf)){
            return true;
        }
        return false;
    }
    
    public boolean verificarCotitular(String cpf){
        for(Correntista co : correntistas){
            if(co.getCpf().equals(cpf)){
                return true;
            }
        }
        return false;
    }
}
