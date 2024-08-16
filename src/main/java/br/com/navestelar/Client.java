package br.com.navestelar;

import br.com.navestelar.view.ClientView;

import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Representa um cliente que se conecta a um servidor proxy para enviar e receber dados.
 *
 * <p>A classe {@code Client} usa uma interface gráfica {@link ClientView} para coletar informações do usuário e se comunicar com um servidor proxy através de sockets.</p>
 */
public class Client {

    private ClientView clientView;

    /**
     * Constrói uma nova instância de {@code Client} e inicializa a interface gráfica.
     */
    public Client() {
        this.clientView = new ClientView(e -> sendId());
    }

    /**
     * Inicia a aplicação, tornando a interface gráfica visível.
     * <p>Este método é executado na thread de despacho de eventos do Swing para garantir que a interface gráfica seja atualizada corretamente.</p>
     */
    public void start() {
        SwingUtilities.invokeLater(() -> {
            clientView.setVisible(true);
        });
    }

    /**
     * Envia o ID inserido pelo usuário para o servidor proxy e exibe o resultado na interface gráfica.
     * <p>O método obtém as configurações do proxy da interface gráfica, estabelece uma conexão com o servidor proxy, envia o ID, e aguarda a resposta.
     * O resultado é então exibido na área de resultado da interface gráfica.</p>
     */
    private void sendId() {
        try {
            String proxyIp = clientView.getProxyIp();
            int proxyPort = clientView.getProxyPort();

            Socket socket = new Socket(proxyIp, proxyPort);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            int id = Integer.parseInt(clientView.getId());
            output.writeInt(id);
            output.flush();

            String[][] result = (String[][]) input.readObject();
            if (result != null) {
                StringBuilder resultText = new StringBuilder();
                if (result.length > 0) {
                    for (String[] entry : result) {
                        if (entry.length >= 2) {
                            resultText.append(entry[0]).append(": ").append(entry[1]).append("\n");
                        }
                    }
                } else {
                    resultText.append("No data found.");
                }
                clientView.setResultArea(resultText.toString());
            } else {
                clientView.setResultArea("Not found.");
            }

            input.close();
            output.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
            clientView.setResultArea("Error: " + e.getMessage());
        }
    }
}
