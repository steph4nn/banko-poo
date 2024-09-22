package modelos;

import java.util.ArrayList;

public class Correntista {
    private String cpf;
    private String nome;
    private String senha;
    private ArrayList<Conta> contas = new ArrayList<>();
    
    public Correntista(String cpf, String nome, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
    }
    

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Conta> getContas() {
        return contas;
    }

    public String getSenha(){
        return senha;
    }
    public void adicionarConta(Conta conta) {
    	contas.add(conta);
    }
    
    public void removerConta(Conta conta) {
    	contas.remove(conta);
    }

    public void setContas(ArrayList<Conta> contas) {
        this.contas = contas;
    }

    double getSaldoTotal() {
        double saldoTotal = 0;
        for (Conta c : contas) {
        	saldoTotal += c.getSaldo();
        }
        return saldoTotal;
    }

}
