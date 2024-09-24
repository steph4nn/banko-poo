package appswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
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
import javax.swing.text.MaskFormatter;

import modelos.Conta;
import modelos.ContaEspecial;
import modelos.Correntista;
import regrasDeNegocio.Fachada;

public class TelaCorrentista {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton button;
	private JButton button_listar;
	private JTextField textField_cpf;
	private JTextField textField_nome;
	private JLabel label;
	private JLabel label_cpf;
	private JLabel label_8;
	private JLabel label_nome;
	private JLabel label_senha;
	private JTextField textField_senha;
	
	public TelaCorrentista() {
		initialize();
		frame.setVisible(true);
	}
	
	private void initialize() {
		frame = new JDialog();
		frame.setModal(true);
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				listagem();
			}
		});
		frame.setTitle("Correntistas");
		frame.setBounds(100, 100, 912, 351);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 42, 844, 120);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);


		button = new JButton("Criar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField_senha.getText().isEmpty() || 
							textField_cpf.getText().isEmpty() ||
							textField_nome.getText().isEmpty()) 
					{
						label.setText("campo vazio");
						return;
					}

					String senha = textField_senha.getText();
					String cpf = textField_cpf.getText();
					String nome = textField_nome.getText();
					Fachada.criarCorrentista(cpf, nome, senha);
					label.setText("Correntista criado: ");
					listagem();
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.setBounds(271, 239, 95, 23);
		frame.getContentPane().add(button);

		label = new JLabel("");
		label.setForeground(Color.BLUE);
		label.setBackground(Color.RED);
		label.setBounds(26, 287, 830, 14);
		frame.getContentPane().add(label);

		label_cpf = new JLabel("cpf:");
		label_cpf.setHorizontalAlignment(SwingConstants.LEFT);
		label_cpf.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_cpf.setBounds(45, 207, 23, 28);
		frame.getContentPane().add(label_cpf);

		textField_cpf = new JTextField();
		textField_cpf.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_cpf.setColumns(10);
		textField_cpf.setBounds(78, 212, 140, 20);
		frame.getContentPane().add(textField_cpf);

		label_8 = new JLabel("selecione");
		label_8.setBounds(26, 163, 561, 14);
		frame.getContentPane().add(label_8);

		label_nome = new JLabel("nome:");
		label_nome.setHorizontalAlignment(SwingConstants.LEFT);
		label_nome.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_nome.setBounds(34, 242, 56, 14);
		frame.getContentPane().add(label_nome);

		textField_nome = new JTextField();
		textField_nome.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_nome.setColumns(10);
		textField_nome.setBounds(78, 239, 140, 20);
		frame.getContentPane().add(textField_nome);

		button_listar = new JButton("Listar Contas");
		button_listar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_listar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cpf = textField_cpf.getText();
			
				listagem();
			}
		});
		button_listar.setBounds(492, 239, 114, 23);
		frame.getContentPane().add(button_listar);
		
		label_senha = new JLabel("senha:");
		label_senha.setHorizontalAlignment(SwingConstants.LEFT);
		label_senha.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_senha.setBounds(25, 272, 43, 14);
		frame.getContentPane().add(label_senha);
		
		textField_senha = new JTextField();
		textField_senha.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_senha.setColumns(10);
		textField_senha.setBounds(78, 270, 140, 20);
		frame.getContentPane().add(textField_senha);

	

	}

	//*****************************
	public void listagem () {
		try{
			List<Correntista> correntistas = Fachada.listarCorrentistas();

			//model contem todas as linhas e colunas da tabela
			DefaultTableModel model = new DefaultTableModel();
			//colunas
			model.addColumn("cpf");
			model.addColumn("nome");
			model.addColumn("contas");
			
			String contas = "";
			//linhas
			for(Correntista co: correntistas) {
				if(co.getContas().isEmpty())
					contas = "Nenhuma Conta Associada";
				else
					for(Conta c: co.getContas())
						contas += c.getId() + " ";
			
				model.addRow(new Object[]{co.getCpf()+"", co.getNome(), contas});
				contas = "";
			}

			table.setModel(model);
			label_8.setText("resultados: "+correntistas.size()+ " correntistas encontrados");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}

	}
}


