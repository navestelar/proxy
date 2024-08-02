

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import envReader.EnvProperty;
import envReader.EnvReader;
import view.template;

public class ProxyServer {
  private static final long CACHE_EXPIRY = 60000;
  private static final int PROXY_PORT = EnvReader.getProxyPort();
  private static final String DATABASE_CONNECTION = EnvReader.getProperty(EnvProperty.DATABASE_CONNECTION);
  private static final String DATABASE_USER = EnvReader.getProperty(EnvProperty.DATABASE_USER);
  private static final String DATABASE_PASSWORD = EnvReader.getProperty(EnvProperty.DATABASE_PASSWORD);

  private static Map<Integer, CacheEntry> cache = new HashMap<>();

  public static void main(String[] args) {

    try (ServerSocket serverSocket = new ServerSocket(PROXY_PORT)) {
      InetAddress ip = InetAddress.getLocalHost();

      template.title("SERVIDOR: " + ip.getHostAddress() + ":" + PROXY_PORT);
      System.out.println(" ");

      while (true) {
        Socket clientSocket = serverSocket.accept();
        InetAddress clientAddress = clientSocket.getInetAddress();
        template.Line();
        template.alignLeft("Solicitado por: " + clientAddress.getHostAddress());
        template.Line();
        new Thread(new ProxyHandler(clientSocket)).start();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static class ProxyHandler implements Runnable {
    private Socket clientSocket;

    public ProxyHandler(Socket clientSocket) {
      this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
      try (ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
          ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream())) {
        int id = input.readInt();
        String[] result = getData(id);
        output.writeObject(result);
        output.flush();
        template.alignLeft("Resposta enviada ao cliente: " + Arrays.toString(result));
        template.Line();
        System.out.println(" ");
      } catch (IOException | SQLException e) {
        e.printStackTrace();
      }
    }

    private String[] getData(int id) throws SQLException {
      long currentTime = System.currentTimeMillis();
      CacheEntry entry = cache.get(id);

      if (entry != null && (currentTime - entry.timestamp < CACHE_EXPIRY)) {
        template.alignLeft("Produto buscado no cache ID: " + id);
        return new String[] { entry.descricao, entry.preco };
      }

      template.alignLeft("Produto buscado no banco de dados ID: " + id);
      String[] result = queryDatabase(id);

      if (result != null) {
        cache.put(id, new CacheEntry(result[0], result[1], currentTime));
      }

      return result;
    }

    private String[] queryDatabase(int id) throws SQLException {
      String[] result = new String[2];
      try (Connection connection = DriverManager.getConnection(DATABASE_CONNECTION, DATABASE_USER, DATABASE_PASSWORD);
          PreparedStatement statement = connection
              .prepareStatement("SELECT descricao, preco FROM produto WHERE id = ?")) {
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
          result[0] = rs.getString("descricao");
          result[1] = rs.getString("preco");
          return result;
        }
      }
      return null;
    }
  }
}
