package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientView extends JFrame {
  private JTextField idField;
  private JTextArea resultArea;
  private JButton sendButton;

  public ClientView(ActionListener sendButtonListener) {
    setTitle("Cliente Proxy");
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());

    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    JLabel idLabel = new JLabel("ID do Produto:");
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.WEST;
    inputPanel.add(idLabel, gbc);

    idField = new JTextField(10);
    idField.setPreferredSize(new Dimension(100, 30));
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    inputPanel.add(idField, gbc);

    sendButton = new JButton("Enviar");
    sendButton.setPreferredSize(new Dimension(100, 30));
    sendButton.setBackground(Color.BLUE);
    sendButton.setForeground(Color.WHITE);
    sendButton.addActionListener(sendButtonListener);
    gbc.gridx = 1;
    gbc.gridy = 1;
    inputPanel.add(sendButton, gbc);

    panel.add(inputPanel, BorderLayout.NORTH);

    resultArea = new JTextArea();
    resultArea.setEditable(false);
    resultArea.setLineWrap(false);
    resultArea.setWrapStyleWord(true);
    resultArea.setFont(new Font("Serif", Font.PLAIN, 16));

    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new GridBagLayout());
    centerPanel.setBackground(Color.WHITE);
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(10, 10, 10, 10);
    centerPanel.add(resultArea, gbc);

    panel.add(centerPanel, BorderLayout.CENTER);

    add(panel);
  }

  public String getProductId() {
    return idField.getText();
  }

  public void setResultArea(String result) {
    resultArea.setText(result);
  }
}
