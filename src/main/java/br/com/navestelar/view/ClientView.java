package br.com.navestelar.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * A classe {@code ClientView} representa a interface gráfica do cliente que permite ao usuário
 * inserir um ID, configurar um proxy e visualizar os resultados.
 *
 * <p>Esta classe estende {@code JFrame} e utiliza componentes Swing para criar a interface do usuário.</p>
 */
public class ClientView extends JFrame {
    private JTextField idField;
    private JTextArea resultArea;
    private JTextField proxyIpField;
    private JTextField proxyPortField;
    private JButton sendButton;

    /**
     * Construtor da classe {@code ClientView}.
     *
     * <p>Configura o título da janela, o tamanho e o layout, e inicializa os componentes da interface gráfica.
     * Adiciona um listener ao botão de envio que é passado como parâmetro.</p>
     *
     * @param sendListener O {@code ActionListener} que será adicionado ao botão de envio para lidar com eventos de clique.
     */
    public ClientView(ActionListener sendListener) {
        setTitle("Client Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Proxy IP and Port Panel
        JPanel proxyPanel = new JPanel(new GridLayout(2, 2));
        proxyPanel.setBorder(BorderFactory.createTitledBorder("Proxy Settings"));

        proxyIpField = new JTextField();
        proxyPortField = new JTextField();

        proxyPanel.add(new JLabel("Proxy IP:"));
        proxyPanel.add(proxyIpField);
        proxyPanel.add(new JLabel("Proxy Port:"));
        proxyPanel.add(proxyPortField);

        JPanel inputPanel = new JPanel(new BorderLayout());
        idField = new JTextField();
        inputPanel.setBorder(BorderFactory.createTitledBorder("ID"));
        inputPanel.add(idField, BorderLayout.CENTER);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        sendButton = new JButton("Send");
        sendButton.addActionListener(sendListener);

        // Add components to the main frame
        add(proxyPanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
        add(sendButton, BorderLayout.PAGE_END);
    }

    /**
     * Retorna o ID inserido pelo usuário.
     *
     * @return O ID inserido, como uma {@code String}.
     */
    public String getId() {
        return idField.getText().trim();
    }

    /**
     * Retorna o IP do proxy inserido pelo usuário.
     *
     * @return O IP do proxy inserido, como uma {@code String}.
     */
    public String getProxyIp() {
        return proxyIpField.getText().trim();
    }

    /**
     * Retorna a porta do proxy inserida pelo usuário.
     *
     * @return A porta do proxy inserida, como um {@code int}.
     * @throws NumberFormatException Se o valor inserido não puder ser convertido para um inteiro.
     */
    public int getProxyPort() {
        return Integer.parseInt(proxyPortField.getText().trim());
    }

    /**
     * Define o texto a ser exibido na área de resultados.
     *
     * @param text O texto a ser exibido na área de resultados.
     */
    public void setResultArea(String text) {
        resultArea.setText(text);
    }
}
