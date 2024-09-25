package appswing;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import regrasDeNegocio.Fachada;

import java.awt.Color;

public class TelaCaixa {
    private JDialog frame;
    private JPanel panelPrincipal;
    private CardLayout cardLayout;
    private JLabel label_id;
    private JTextField textField_id;
    private JLabel label_cpf;
    private JTextField textField;
    private JLabel label_senha;
    private JTextField textField_senha;
    private JLabel label_origem;
    private JTextField textField_id_origem;
    private JLabel label_destino;
    private JTextField textField_id_destino;
    private JLabel label_senha_debitar;
    private JTextField textField_senha_debitar;
    private JLabel label_msg;

    public TelaCaixa() {
        initialize();
        frame.setVisible(true);
    }

    private void initialize() {
        frame = new JDialog();
        frame.setModal(true);
        frame.setTitle("Caixa Eletrônico");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton button_creditar = new JButton("Creditar");
        button_creditar.setBounds(30, 11, 100, 30);
        frame.getContentPane().add(button_creditar);

        JButton button_debitar = new JButton("Debitar");
        button_debitar.setBounds(234, 11, 100, 30);
        frame.getContentPane().add(button_debitar);

        JButton button_transferir = new JButton("Transferir");
        button_transferir.setBounds(430, 11, 100, 30);
        frame.getContentPane().add(button_transferir);

        panelPrincipal = new JPanel();
        panelPrincipal.setBorder(new LineBorder(Color.BLACK));
        panelPrincipal.setBounds(30, 150, 500, 200);
        frame.getContentPane().add(panelPrincipal);
        cardLayout = new CardLayout();
        panelPrincipal.setLayout(cardLayout);

        JPanel panelCreditar = createPanelCreditar();
        JPanel panelDebitar = createPanelDebitar();
        JPanel panelTransferir = createPanelTransferir();

        panelPrincipal.add(panelCreditar, "Creditar");
        panelPrincipal.add(panelDebitar, "Debitar");
        panelPrincipal.add(panelTransferir, "Transferir");
        

        button_creditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "Creditar");
            }
        });

        button_debitar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "Debitar");
            }
        });

        button_transferir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "Transferir");
            }
        });
    }

    private JPanel createPanelCreditar() {
        JPanel panel_creditar = new JPanel();
        panel_creditar.setLayout(null);

        JLabel label = new JLabel("Valor a ser creditado:");
        label.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label.setBounds(30, 121, 200, 20);
        panel_creditar.add(label);

        JTextField textField_valor = new JTextField();
        textField_valor.setBounds(30, 152, 200, 25);
        panel_creditar.add(textField_valor);

        JButton button_confirmar = new JButton("Confirmar");
        button_confirmar.setBounds(388, 157, 100, 30);
        panel_creditar.add(button_confirmar);
        
        label_id = new JLabel("Id da conta:");
        label_id.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label_id.setBounds(30, 31, 84, 20);
        panel_creditar.add(label_id);
        
        textField_id = new JTextField();
        textField_id.setBounds(30, 62, 84, 25);
        panel_creditar.add(textField_id);
        
        label_cpf = new JLabel("Cpf correntista:");
        label_cpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label_cpf.setBounds(146, 31, 108, 20);
        panel_creditar.add(label_cpf);
        
        textField = new JTextField();
        textField.setBounds(146, 62, 84, 25);
        panel_creditar.add(textField);
        
        label_senha = new JLabel("senha:");
        label_senha.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label_senha.setBounds(264, 31, 84, 20);
        panel_creditar.add(label_senha);
        
        textField_senha = new JTextField();
        textField_senha.setBounds(264, 62, 84, 25);
        panel_creditar.add(textField_senha);
        
        label_msg = new JLabel("");
        label_msg.setForeground(new Color(0, 128, 255));
        label_msg.setBounds(30, 184, 46, 14);
        panel_creditar.add(label_msg);

        button_confirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JLabel label_msg = new JLabel();
                try {
                	Integer id = Integer.parseInt(textField_id.getText());
                    String cpf = textField.getText();
                    String senha = textField_senha.getText();
                    double valor = Double.parseDouble(textField_valor.getText());
                    Fachada.creditarValor(id, cpf, senha, valor);
                    label_msg.setText("Valor creditado com sucesso.");
                } catch (Exception ex) {
                    label_msg.setText(ex.getMessage());
                }
            }
        });

        return panel_creditar;
    }

    private JPanel createPanelDebitar() {
    	JPanel panel_debitar = new JPanel();
        panel_debitar.setLayout(null);

        JLabel label = new JLabel("Valor a ser debitado:");
        label.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label.setBounds(30, 121, 200, 20);
        panel_debitar.add(label);

        JTextField textField_valor = new JTextField();
        textField_valor.setBounds(30, 152, 200, 25);
        panel_debitar.add(textField_valor);

        JButton button_confirmar = new JButton("Confirmar");
        button_confirmar.setBounds(388, 157, 100, 30);
        panel_debitar.add(button_confirmar);
        
        label_id = new JLabel("Id da conta:");
        label_id.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label_id.setBounds(30, 31, 84, 20);
        panel_debitar.add(label_id);
        
        textField_id = new JTextField();
        textField_id.setBounds(30, 62, 84, 25);
        panel_debitar.add(textField_id);
        
        label_cpf = new JLabel("Cpf correntista:");
        label_cpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label_cpf.setBounds(146, 31, 108, 20);
        panel_debitar.add(label_cpf);
        
        textField = new JTextField();
        textField.setBounds(146, 62, 84, 25);
        panel_debitar.add(textField);
        
        label_senha = new JLabel("senha:");
        label_senha.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label_senha.setBounds(264, 31, 84, 20);
        panel_debitar.add(label_senha);
        
        textField_senha = new JTextField();
        textField_senha.setBounds(264, 62, 84, 25);
        panel_debitar.add(textField_senha);
        
        JLabel label_msg = new JLabel();
        label_msg.setForeground(new Color(0, 128, 255));
        label_msg.setBounds(30, 184, 46, 14);
        panel_debitar.add(label_msg);

        button_confirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	Integer id = Integer.parseInt(textField_id.getText());
                    String cpf = textField.getText();
                    String senha = textField_senha.getText();
                    double valor = Double.parseDouble(textField_valor.getText());
                    Fachada.debitarValor(id, cpf, senha, valor);
                    
					label_msg.setText("Valor transferido com sucecsso.");
                } catch (Exception ex) {
                    label_msg.setText(ex.getMessage());
                }
            }
        });

        return panel_debitar;
    }
    

    private JPanel createPanelTransferir() {
        JPanel panel_transferir = new JPanel();
        panel_transferir.setLayout(null);

        JLabel label_valor = new JLabel("Valor a ser transferido:");
        label_valor.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label_valor.setBounds(30, 140, 146, 20);
        panel_transferir.add(label_valor);

        JTextField textField_valor = new JTextField();
        textField_valor.setBounds(30, 160, 146, 25);
        panel_transferir.add(textField_valor);

        JLabel label_cpforigem = new JLabel("CPF do correntista:");
        label_cpforigem.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label_cpforigem.setBounds(300, 30, 137, 20);
        panel_transferir.add(label_cpforigem);

        JTextField textField_cpfOrigem = new JTextField();
        textField_cpfOrigem.setBounds(299, 61, 162, 25);
        panel_transferir.add(textField_cpfOrigem);

        JButton button_confirmar = new JButton("Confirmar");
        button_confirmar.setBounds(388, 157, 100, 30);
        panel_transferir.add(button_confirmar);
        
        label_origem = new JLabel("id da conta 1:");
        label_origem.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label_origem.setBounds(30, 30, 91, 20);
        panel_transferir.add(label_origem);
        
        textField_id_origem = new JTextField();
        textField_id_origem.setBounds(30, 49, 91, 25);
        panel_transferir.add(textField_id_origem);
        
        label_destino = new JLabel("id da conta 2:");
        label_destino.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label_destino.setBounds(139, 30, 91, 20);
        panel_transferir.add(label_destino);
        
        textField_id_destino = new JTextField();
        textField_id_destino.setBounds(139, 49, 91, 25);
        panel_transferir.add(textField_id_destino);
        
        label_senha_debitar = new JLabel("senha:");
        label_senha_debitar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label_senha_debitar.setBounds(30, 85, 91, 20);
        panel_transferir.add(label_senha_debitar);
        
        textField_senha_debitar = new JTextField();
        textField_senha_debitar.setBounds(30, 104, 91, 25);
        panel_transferir.add(textField_senha_debitar);
        
        JLabel label_msg = new JLabel();
        label_msg.setForeground(new Color(0, 128, 255));
        label_msg.setBounds(30, 184, 46, 14);
        panel_transferir.add(label_msg);

        button_confirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double valor = Double.parseDouble(textField_valor.getText());
                    String cpfOrigem = textField_cpfOrigem.getText();
                    Integer contaOrigem = Integer.parseInt(textField_id_origem.getText());
                    Integer contaDestino = Integer.parseInt(textField_id_destino.getText());
                    String senha = textField_senha_debitar.getText();
                    Fachada.transferirValor(contaOrigem, cpfOrigem, senha, contaDestino, valor);
					label_msg.setText("Valor transferido com sucecsso.");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        return panel_transferir;
    }
}
