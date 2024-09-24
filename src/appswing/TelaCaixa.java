package appswing;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel label = new JLabel("Valor a ser creditado:");
        label.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label.setBounds(30, 30, 200, 20);
        panel.add(label);

        JTextField textField_valor = new JTextField();
        textField_valor.setBounds(30, 60, 200, 25);
        panel.add(textField_valor);

        JButton button_confirmar = new JButton("Confirmar");
        button_confirmar.setBounds(388, 157, 100, 30);
        panel.add(button_confirmar);

        button_confirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double valor = Double.parseDouble(textField_valor.getText());
//                    String id = textField_cpf.getText();
//                    String numeroConta = textField_numeroConta.getText();
//                    Fachada.creditarValor(id, numeroConta, valor);
                    System.out.println("Valor creditado com sucesso.");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        return panel;
    }

    private JPanel createPanelDebitar() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel label = new JLabel("Valor a ser debitado:");
        label.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label.setBounds(30, 30, 200, 20);
        panel.add(label);

        JTextField textField_valor = new JTextField();
        textField_valor.setBounds(30, 60, 200, 25);
        panel.add(textField_valor);

        JButton button_confirmar = new JButton("Confirmar");
        button_confirmar.setBounds(388, 157, 100, 30);
        panel.add(button_confirmar);

        button_confirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double valor = Double.parseDouble(textField_valor.getText());
//                    String cpf = textField_cpf.getText();
//                    String numeroConta = textField_numeroConta.getText();
//                    Fachada.debitarValor(cpf, numeroConta, valor);
                    System.out.println("Valor debitado com sucesso.");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        return panel;
    }

    private JPanel createPanelTransferir() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel label_valor = new JLabel("Valor a ser transferido:");
        label_valor.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label_valor.setBounds(30, 30, 200, 20);
        panel.add(label_valor);

        JTextField textField_valor = new JTextField();
        textField_valor.setBounds(30, 60, 200, 25);
        panel.add(textField_valor);

        JLabel label_cpfDestino = new JLabel("CPF do destinatário:");
        label_cpfDestino.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label_cpfDestino.setBounds(30, 100, 200, 20);
        panel.add(label_cpfDestino);

        JTextField textField_cpfDestino = new JTextField();
        textField_cpfDestino.setBounds(30, 130, 200, 25);
        panel.add(textField_cpfDestino);

        JButton button_confirmar = new JButton("Confirmar");
        button_confirmar.setBounds(388, 157, 100, 30);
        panel.add(button_confirmar);

        button_confirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double valor = Double.parseDouble(textField_valor.getText());
//                    String cpfOrigem = textField_cpf.getText();
//                    String numeroConta = textField_numeroConta.getText();
//                    String cpfDestino = textField_cpfDestino.getText();
//                    Fachada.transferirValor(cpfOrigem, numeroConta, cpfDestino, valor);
                    System.out.println("Valor transferido com sucesso.");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        return panel;
    }
}
