package regrasDeNegocio;

import modelos.Conta;
import modelos.ContaEspecial;
import modelos.Correntista;
import repositorio.Repositorio;
import java.util.ArrayList;

public class Fachada {
    private static Repositorio repositorio = new Repositorio();	

    public static
    ArrayList<Correntista> listarCorrentistas() {
        //retorna ordenado pelo cpf
        ArrayList<Correntista> correntistas = new ArrayList<Correntista>();
        correntistas = repositorio.getCorrentistas();
        return correntistas;
        
    }

    
    public static ArrayList<Conta> listarContas() {
        ArrayList<Conta> contas = repositorio.getContas();
        return contas;
    }

    public static void criarCorrentista(String cpf, String nome, String senha) {
        try {
            ArrayList<Correntista> correntistas = repositorio.getCorrentistas();
            for (Correntista c : correntistas) {
                if (c.getCpf().equals(cpf)) {
                    throw new Exception("Já existe um correntista com o cpf informado");
                }
            }
        Correntista co = new Correntista(cpf, nome, senha);
        repositorio.addCorrentista(co);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static void criarConta(String cpf) {
        ArrayList<Conta> contas = repositorio.getContas();
        Correntista co = repositorio.getCorrentistaByCpf(cpf);
        try {
            if (co == null){
                throw new Exception("O correntista não foi encontrado");
            }
            for (Conta conta : contas) {
                if (conta.verificarTitular(cpf)) {
                    throw new Exception("O correntista informado já é titular de uma conta");
                }
            }
            int id = contas.size()+1;
            Conta c = new Conta(id);
            c.adicionarCorrentistaTitular(co);
            repositorio.addConta(c);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static void criarContaEspecial(String cpf, double limite){
        ArrayList<Conta> contas = repositorio.getContas();
        Correntista co = repositorio.getCorrentistaByCpf(cpf);
        try {
            if (co == null){
                throw new Exception("O correntista não foi encontrado");
            }
            for (Conta conta : contas) {
                if (conta.verificarTitular(cpf)) {
                    throw new Exception("O correntista informado já é titular de uma conta");
                }
            }
            int id = contas.size()+1;
            Conta c = new ContaEspecial(id, limite);
            c.adicionarCorrentistaTitular(co);
            repositorio.addConta(c);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static void inserirCorrentistaConta(int id,String cpf){
        Conta c = repositorio.getContaById(id);
        Correntista co = repositorio.getCorrentistaByCpf(cpf);
        try{
            if(co == null){
                throw new Exception("Correntista não encontrado");
            }
            if (c == null){
                throw new Exception("Conta não encontrada");
            }
            if (c.verificarTitular(cpf)){
                throw new Exception("O correntista informado é o titular da conta");
            }
            if(c.verificarCotitular(cpf)){
                throw new Exception("O cpf informado já é cotitular da conta");
            };
            c.adicionarCorrentista(co);
        }catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static void removerCorrentista(int id, String cpf){
        Conta c = repositorio.getContaById(id);
        Correntista co = repositorio.getCorrentistaByCpf(cpf);
        try{
            if(co == null){
                throw new Exception("Correntista não encontrado");
            }
            if (c == null){
                throw new Exception("Conta não encontrada");
            }
            if (c.verificarTitular(cpf)){
                throw new Exception("O correntista informado é o titular da conta");
            }
            if(!c.verificarCotitular(cpf)){
                throw new Exception("O correntista informado não é cotitular da conta");
            };
            c.removerCorrentistaCotitular(co);
        }catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static void apagarConta(int id){

    }
    public static void creditarValor(int id, String cpf, String senha, double valor){

    }
    public static void debitarValor(int id, String cpf, String senha, double valor){

    }
    public static void transferirValor(int id1, String cpf, String senha, int id2, double valor){

    }
}
