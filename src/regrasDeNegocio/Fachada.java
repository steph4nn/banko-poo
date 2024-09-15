package regrasDeNegocio;

import modelos.Conta;
import modelos.Correntista;

import java.util.ArrayList;

public class Fachada {
    public static
    ArrayList<Correntista> listarCorrentistas() {
        return new ArrayList<Correntista>();
    }
    public static
    ArrayList<Conta> listarContas() {
        return new ArrayList<Conta>();
    }
    public static void criarCorrentista(String cpf, String nome, String senha) {

    }
    public static void criarConta(String cpf) {

    }
    public static void criarContaEspecial(String cpf, double limite){

    }
    public static void inserirCorrentistaConta(int id,String cpf){

    }
    public static void removerCorrentista(int id, String cpf){

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
