package regrasDeNegocio;

import modelos.Conta;
import modelos.ContaEspecial;
import modelos.Correntista;
import repositorio.Repositorio;
import java.util.ArrayList;

public class Fachada {
    private static Repositorio repositorio = new Repositorio();	

    public static ArrayList<Correntista> listarCorrentistas() {
        return repositorio.getCorrentistas();
        
    }
    
    public static ArrayList<Conta> listarContas() {
        return repositorio.getContas();
    }

    public static void criarCorrentista(String cpf, String nome, String senha) throws Exception {
        ArrayList<Correntista> correntistas = repositorio.getCorrentistas();
        for (Correntista c : correntistas) { 
            if (c.getCpf().equals(cpf)) {
                throw new Exception("Já existe um correntista com o cpf informado");
            }
        }
        Correntista co = new Correntista(cpf, nome, senha);
        repositorio.addCorrentista(co);
        repositorio.salvarObjetos();
    }

    public static void criarConta(String cpf) throws Exception {
        ArrayList<Conta> contas = repositorio.getContas();
        Correntista co = repositorio.getCorrentistaByCpf(cpf);
        if (co == null){
            throw new Exception("O correntista não foi encontrado");
        }
        for (Conta conta : contas) {
            if (conta.verificarTitular(cpf)) {
                throw new Exception("O correntista informado já é titular de uma conta");
            }
        }
        int id;
        if (contas.isEmpty()) {
            id = 1;
        } else {
            id = contas.get(contas.size() - 1).getId() + 1; 
        }
        String data = null;
		Conta c = new Conta(id, data, 0);
        c.adicionarCorrentistaTitular(co);
        co.adicionarConta(c);
        repositorio.addConta(c);
        repositorio.salvarObjetos();
    }

    public static void criarContaEspecial(String cpf, double limite) throws Exception{
        ArrayList<Conta> contas = repositorio.getContas();
        Correntista co = repositorio.getCorrentistaByCpf(cpf);
        if (co == null){
            throw new Exception("O correntista não foi encontrado");
        }
        for (Conta conta : contas) {
            if (conta.verificarTitular(cpf)) {
                throw new Exception("O correntista informado já é titular de uma conta");
            }
        }
        int id = contas.getLast().getId()+1;
        String data = null;
		Conta c = new ContaEspecial(id, data, 0, limite);
        c.adicionarCorrentistaTitular(co);
        co.adicionarConta(c);
        repositorio.addConta(c);
        repositorio.salvarObjetos();
    }

    public static void inserirCorrentistaConta(int id,String cpf) throws Exception {
        Conta c = repositorio.getContaById(id);
        Correntista co = repositorio.getCorrentistaByCpf(cpf);
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
        } 
        c.adicionarCorrentista(co);
        co.adicionarConta(c);
        repositorio.salvarObjetos();    
    }

    public static void removerCorrentistaConta(int id, String cpf) throws Exception{
        Conta c = repositorio.getContaById(id);
        Correntista co = repositorio.getCorrentistaByCpf(cpf);
        if(co == null){
            throw new Exception("Correntista não encontrado");
        }
        if (c == null){
            throw new Exception("Conta não encontrada");
        }
        if (c.verificarTitular(cpf)){
            throw new Exception("Correntista titular não pode ser removido");
        }
        if(!c.verificarCotitular(cpf)){
            throw new Exception("O correntista informado não é cotitular da conta");
        }
        c.removerCorrentista(co);
        co.removerConta(c);
        repositorio.salvarObjetos();
    }

    public static void apagarConta(int id) throws Exception {
        Conta c = repositorio.getContaById(id);
        if (c == null) {
            throw new Exception("Conta não encontrada");
        }
        if (c.getSaldo() != 0) {
            throw new Exception("O saldo da conta deve ser 0.");
        }
        
        ArrayList<Correntista> correntistas = new ArrayList<>(c.getCorrentistas());
        for (Correntista co : correntistas) {
            c.removerCorrentista(co);
            co.removerConta(c);
        }
        repositorio.removerConta(c);
        repositorio.salvarObjetos();
    }
    
    public static void creditarValor(int id, String cpf, String senha, double valor) throws Exception {
        Conta c = repositorio.getContaById(id);
        Correntista co = repositorio.getCorrentistaByCpf(cpf);
        if (c == null) {
            throw new Exception("Conta não encontrada");
        }
        if (co == null) {
            throw new Exception("Correntista não encontrado");
        }
        if (!co.getSenha().equals(senha)) {
            throw new Exception("Senha inválida");
        }
        if (!c.verificarTitular(co.getCpf())) {
            throw new Exception("O correntista não é o titular da conta de origem");
        }
        c.creditar(valor);
        repositorio.salvarObjetos();
    }

    public static void debitarValor(int id, String cpf, String senha, double valor) throws Exception {
        Conta c = repositorio.getContaById(id);
        Correntista co = repositorio.getCorrentistaByCpf(cpf);
        if (c == null) {
            throw new Exception("Conta não encontrada");
        }
        if (co == null) {
            throw new Exception("Correntista não encontrado");
        }
        if (!co.getSenha().equals(senha)) {
            throw new Exception("Senha inválida");
        }   
        if (c instanceof ContaEspecial ce) {
            ce.debitar(valor);
        } else {
            c.debitar(valor);   	
        }
        repositorio.salvarObjetos();
    }

    public static void transferirValor(int id1, String cpf, String senha, int id2, double valor) throws Exception {
        Conta c1 = repositorio.getContaById(id1);
        Conta c2 = repositorio.getContaById(id2);
        Correntista co = repositorio.getCorrentistaByCpf(cpf);
        if (c1 == null) {
            throw new Exception("Conta de origem não encontrada");
        }
        if (c2 == null) {
            throw new Exception("Conta de destino não encontrada");
        }
        if (co == null) {
            throw new Exception("Correntista não encontrado");
        }
        if (!c1.verificarTitular(co.getCpf())) {
            throw new Exception("O correntista não é o titular da conta de origem");
        }
        if (c1 instanceof ContaEspecial) {
            ContaEspecial contaEspecial = (ContaEspecial) c1;
            if (c1.getSaldo() - valor < -contaEspecial.getLimite()) {
                throw new Exception("Saldo insuficiente, o limite da conta especial seria excedido");
            }
        } else {
            if (c1.getSaldo() < valor) {
                throw new Exception("Saldo insuficiente para realizar a transferência");
            }
        }
        co.verificarSenha(senha);
        c1.transferir(valor, c2);
        repositorio.salvarObjetos();
    }
    
}
