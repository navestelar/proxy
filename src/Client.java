package src;

import javax.swing.*;

import src.envReader.EnvReader;
import src.view.ClientView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
  private ClientView clientView;

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      Client client = new Client();
      client.clientView.setVisible(true);
    });
  }

  public Client() {
    clientView = new ClientView(e -> sendProductId());
  }

  private void sendProductId() {
    try {
      Socket socket = new Socket(EnvReader.getProxyIP(), EnvReader.getProxyPort());
      ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
      ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

      int id = Integer.parseInt(clientView.getProductId());
      output.writeInt(id);
      output.flush();

      String[] result = (String[]) input.readObject();
      if (result != null && result[0] != null && result[1] != null) {
        clientView.setResultArea("Descrição: " + result[0] + "\nPreço: " + result[1]);
      } else {
        clientView.setResultArea("Produto não encontrado.");
      }

      input.close();
      output.close();
      socket.close();
    } catch (Exception e) {
      e.printStackTrace();
      clientView.setResultArea("Erro: " + e.getMessage());
    }
  }
}
