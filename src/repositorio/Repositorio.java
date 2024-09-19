package repositorio;

import modelos.Conta;
import modelos.ContaEspecial;
import modelos.Correntista;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Repositorio {
    private ArrayList<Conta> contas = new ArrayList<>();
    private ArrayList<Correntista> correntistas = new ArrayList<>();

    // public void adicionarConta(Conta c)	{
    //     contas.add(c);
    // }
    // public void adicionarCorrentista(Correntista c)	{
    //     correntistas.add(c);
    // }
    // public void removerConta(Conta c)	{
    //     contas.remove(c);
    // }
    // public void removerCorrentista(Correntista c)	{
    //     correntistas.remove(c);
    // }

    // public ArrayList<Conta> getContas() {
    //     return contas;
    // }

    // public ArrayList<Correntista> getCorrentistas() {
    //     return correntistas;
    // }

    public void carregarObjetos(){
        try {
            File f1 = new File( new File(".\\contas.csv").getCanonicalPath() ) ;
            File f2 = new File( new File(".\\correntistas.csv").getCanonicalPath() ) ;
            if (!f1.exists() || !f2.exists() ) {
                FileWriter arquivo1 = new FileWriter(f1); arquivo1.close();
                FileWriter arquivo2 = new FileWriter(f2); arquivo2.close();
                return;
            }
        } catch(Exception ex){
            throw new RuntimeException("criacao dos arquivos vazios:"+ex.getMessage());
        }

        String linha;	
		String[] partes;	
		Conta c;
		Correntista co;

        try	{
			String tipo_conta, id, data, saldo, limite;
			File f = new File( new File(".\\contas.csv").getCanonicalPath());
			Scanner arquivo1 = new Scanner(f);	 
			while(arquivo1.hasNextLine()) 	{
				linha = arquivo1.nextLine().trim();		
				partes = linha.split(";");
                
				tipo_conta = partes[0];
				id = partes[1];
				data = partes[2];
				saldo = partes[3];

                if(tipo_conta.equals("ContaEspecial")){
                    limite = partes[4];
                    c = new ContaEspecial(Integer.parseInt(id), data, Double.parseDouble(saldo), Double.parseDouble(limite));
                } 
				c = new Conta(Integer.parseInt(id), data, Double.parseDouble(saldo));
				contas.add(c);
			} 
			arquivo1.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("leitura arquivo de contas:"+ex.getMessage());
		}
        try	{
			String cpf, nome,senha, ids;
			File f = new File( new File(".\\correntistas.csv").getCanonicalPath());
			Scanner arquivo2 = new Scanner(f);	 
			while(arquivo2.hasNextLine()) 	{
				linha = arquivo2.nextLine().trim();		
				partes = linha.split(";");
                
				cpf = partes[0];
				nome = partes[1];
				senha = partes[2];
				ids= partes[3];

                String[] idsArray = ids.split(",");
                ArrayList<Conta> contas = new ArrayList<>();
                for(Conta conta : contas){
                    for (String id : idsArray){
                        if(conta.getId() == Integer.parseInt(id)){
                            contas.add(conta);
                        }
                    }
                }
                co = new Correntista(cpf, nome, senha);
                co.setContas(contas);
                correntistas.add(co);
			} 
			arquivo2.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("leitura arquivo de correntistas:"+ex.getMessage());
		}
    }
    public void	salvarObjetos()  {
		//gravar nos arquivos csv os objetos que est�o no reposit�rio
		try	{
			File f = new File( new File(".\\contas.csv").getCanonicalPath())  ;
			FileWriter arquivo1 = new FileWriter(f); 
			for(Conta c : contas) 	{
                if(c instanceof ContaEspecial ce){
                    arquivo1.write("ContaEspecial;"+c.getId()+";"+c.getData()+";"+c.getSaldo()+";"+ce.getLimite()+"\n");
                } else {
                    arquivo1.write("Conta;"+c.getId()+";"+c.getData()+";"+c.getSaldo()+"\n");
                }	
			} 
			arquivo1.close();
		}
		catch(Exception e){
			throw new RuntimeException("problema na cria��o do arquivo  contas "+e.getMessage());
		}

		try	{
			File f = new File( new File(".\\correntistas.csv").getCanonicalPath())  ;
			FileWriter arquivo2 = new FileWriter(f) ; 
			ArrayList<String> lista ;
			String listaId;
			for(Correntista co : correntistas) {
				//montar uma lista com os id dos eventos do participante
				lista = new ArrayList<>();
                for(Conta c : co.getContas()) {
                    lista.add(c.getId()+"");
                }
				listaId = String.join(",", lista);
                arquivo2.write(co.getCpf()+";"+co.getNome()+";"+co.getSenha()+";"+listaId);

            }

			arquivo2.close();
		}
		catch (Exception e) {
			throw new RuntimeException("problema na cria��o do arquivo  correntistas "+e.getMessage());
		}

	}
}
