package envReader;

public enum EnvProperty {
  DATABASE_CONNECTION("DATABASE_CONNECTION"),
  DATABASE_USER("DATABASE_USER"),
  DATABASE_PASSWORD("DATABASE_PASSWORD"),
  PROXY_CONNECTION("PROXY_CONNECTION");

  private final String value;

  EnvProperty(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
