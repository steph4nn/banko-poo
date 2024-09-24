package appswing;

import java.awt.Color;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelos.Conta;
import modelos.ContaEspecial;
import modelos.Correntista;
import regrasDeNegocio.Fachada;

public class TelaConta {

	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel label;
	private JLabel label_6;
	private JLabel label_1;
	private JLabel label_cpf;
	private JTextField textField;
	private JTextField textField_cpf;
	private JButton button;
	private JButton button_1;
	private JLabel label_limite;
	private JTextField textField_limite;
	private JButton button_apagar;
	private JButton button_add_co;
	private JButton button_rem_co;
	private JButton button_5;



	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					TelaEventos window = new TelaEventos();
	//					window.frame.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}

	/**
	 * Create the application.
	 */
	public TelaConta() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setModal(true);
		frame.setResizable(false);
		frame.setTitle("Participante/Convidado");
		frame.setBounds(100, 100, 729, 385);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				listagem();
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 43, 674, 148);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		label = new JLabel("");
		label.setForeground(Color.BLUE);
		label.setBounds(21, 321, 688, 14);
		frame.getContentPane().add(label);

		label_6 = new JLabel("selecione");
		label_6.setBounds(21, 190, 431, 14);
		frame.getContentPane().add(label_6);

		label_1 = new JLabel("Digite parte do nome");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(21, 14, 128, 14);
		frame.getContentPane().add(label_1);

		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBackground(Color.WHITE);
		textField.setBounds(159, 11, 137, 20);
		frame.getContentPane().add(textField);

		label_cpf = new JLabel("cpf:");
		label_cpf.setHorizontalAlignment(SwingConstants.LEFT);
		label_cpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_cpf.setBounds(21, 269, 71, 14);
		frame.getContentPane().add(label_cpf);

		textField_cpf = new JTextField();
		textField_cpf.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_cpf.setColumns(10);
		textField_cpf.setBounds(68, 264, 195, 20);
		frame.getContentPane().add(textField_cpf);

		button_1 = new JButton("Criar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField_cpf.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}
					String cpf = textField_cpf.getText();
					String limite = textField_limite.getText();
					if(limite.isEmpty())
						Fachada.criarConta(cpf);
					else
						Fachada.criarContaEspecial(cpf, Double.parseDouble(limite));

					label.setText("Conta criada: ");
					listagem();
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(535, 265, 86, 23);
		frame.getContentPane().add(button_1);

		button = new JButton("Listar");
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		button.setBounds(306, 9, 89, 23);
		frame.getContentPane().add(button);

		label_limite = new JLabel("limite (especial):");
		label_limite.setHorizontalAlignment(SwingConstants.LEFT);
		label_limite.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_limite.setBounds(273, 269, 89, 14);
		frame.getContentPane().add(label_limite);

		textField_limite = new JTextField();
		textField_limite.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_limite.setColumns(10);
		textField_limite.setBounds(372, 266, 71, 20);
		frame.getContentPane().add(textField_limite);

		button_apagar = new JButton("Apagar");
		button_apagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){
						Integer id = (Integer) table.getValueAt( table.getSelectedRow(), 0);
						Fachada.apagarConta(id);
						label.setText("deletou conta "+id );
						listagem();
					}
					else
						label.setText("conta nao selecionada");
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_apagar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_apagar.setBounds(247, 215, 86, 23);
		frame.getContentPane().add(button_apagar);

		button_add_co = new JButton("Adicionar Correntista");
		button_add_co.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){
						Integer id = (Integer) table.getValueAt( table.getSelectedRow(), 0);
						String cpf = JOptionPane.showInputDialog(frame, "Digite o cpf");

						JOptionPane.showMessageDialog(frame, "Adicionar correntista "+cpf + " à conta " + id + " ?");

						Object[] options = { "Confirmar", "Desistir" };
						int escolha = JOptionPane.showOptionDialog(null, "Confirma adição de Cotitular à conta "+id, "Alerta",
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
						if(escolha == 0) {
							Fachada.inserirCorrentistaConta(id, cpf);
							listagem();
						}
						else
							label.setText("nao adicionou correntista" +id );

					}
					else
						label.setText("conta nao selecionada");
				}
				catch(NumberFormatException ex) {
					label.setText("formato do id invalido");
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_add_co.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_add_co.setBounds(354, 215, 150, 23);
		frame.getContentPane().add(button_add_co);

		button_rem_co = new JButton("Remover Correntista");
		button_rem_co.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){
						Integer id = (Integer) table.getValueAt( table.getSelectedRow(), 0);
						String cpf = JOptionPane.showInputDialog(frame, "Digite o cpf");

						JOptionPane.showMessageDialog(frame, "Remover correntista "+cpf + " da conta " + id + " ?");

						Object[] options = { "Confirmar", "Desistir" };
						int escolha = JOptionPane.showOptionDialog(null, "Confirma remocao do correntista "+id, "Alerta",
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
						if(escolha == 0) {
							Fachada.removerCorrentistaConta(id, cpf);
							listagem();
						}
						else
							label.setText("nao removeu correntista " +id );

					}
					else
						label.setText("conta nao selecionada");
				}
				catch(NumberFormatException ex) {
					label.setText("formato do id invalido");
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_rem_co.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_rem_co.setBounds(535, 215, 160, 23);
		frame.getContentPane().add(button_rem_co);

		button_5 = new JButton("Limpar");
		button_5.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) {
						textField.setText("");
						textField.requestFocus();
					}
				}
				);
		button_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_5.setBounds(402, 10, 89, 23);
		frame.getContentPane().add(button_5);
	}

	public void listagem() {
		try{
			List<Conta> contas = Fachada.listarContas();

			//			//***************************************************************
			//			
			//			//Alternativa de ordenacao 1 (por nome)
			//			Collections.sort(lista);
			//						
			//			//Alternativa de ordenacao 2
			//			Collections.sort(lista, new Comparator<Participante>() {
			//				public int compare(Participante p1, Participante p2) {
			//					int idade1 = p1.getIdade();
			//					int idade2 = p2.getIdade();
			//					return Integer.compare(idade1, idade2);
			//				}
			//			});

			//			//Alternativa de ordenacao 3
			//			Collections.sort(lista, new Comparator<Participante>() {
			//				public int compare(Participante p1, Participante p2) {
			//					String nome1 = p1.getNome();
			//					String nome2 = p2.getNome();
			//					return nome1.compareTo(nome2);
			//				}
			//			});
			//
			//			//Alternativa de ordenacao 4
			//			Collections.sort(lista, (p1,p2) -> p1.getNome().compareTo(p2.getNome()));
			//			
			//			//***************************************************************

			//model contem todas as linhas e colunas da tabela
			DefaultTableModel model = new DefaultTableModel();

			//colunas
			model.addColumn("id");
			model.addColumn("data");
			model.addColumn("saldo");
			model.addColumn("limite");

			//linhas
			String texto;
			for(Conta c : contas) {

				if(c.getCorrentistas().isEmpty())
					texto="sem correntistas ";
				else {
					texto=" ";
					for(Correntista co : c.getCorrentistas()) 
						texto += co.getCpf()+ " " ;
				}

				if(c instanceof ContaEspecial ce)
					model.addRow(new Object[]{c.getId(), c.getData(), c.getSaldo(), ((ContaEspecial) c).getLimite(), texto});
				else
					model.addRow(new Object[]{c.getId(), c.getData(), c.getSaldo(), "", texto});

			}

			table.setModel(model);
			label_6.setText("resultados: "+contas.size()+ " contas");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}

	
}
