package src.envReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvReader {
  private static Properties properties = new Properties();

  static {
    try (FileInputStream fileInputStream = new FileInputStream(".env")) {
      properties.load(fileInputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static String getProperty(EnvProperty key) {
    return properties.getProperty(key.getValue());
  }

  public static String getProxyIP() {
    String proxyConnection = getProperty(EnvProperty.PROXY_CONNECTION);
    if (proxyConnection != null && proxyConnection.contains(":")) {
      return proxyConnection.split(":")[0];
    }
    return null;
  }

  public static Integer getProxyPort() {
    String proxyConnection = getProperty(EnvProperty.PROXY_CONNECTION);
    if (proxyConnection != null && proxyConnection.contains(":")) {
      String portStr = proxyConnection.split(":")[1];
      try {
        return Integer.parseInt(portStr);
      } catch (NumberFormatException e) {
        e.printStackTrace();
      }
    }
    return null;
  }
}
