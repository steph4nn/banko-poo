package modelos;

import java.util.ArrayList;

public class Correntista {
    private String cpf;
    private String nome;
    private String senha;
    private ArrayList<Conta> contas;

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

    public void setContas(ArrayList<Conta> contas) {
        this.contas = contas;
    }

    public Correntista(String cpf, String nome, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
    }

    double getSaldoTotal() {
        return 0;
    }

    void validarSenha(String senha) {
        
    }
}
