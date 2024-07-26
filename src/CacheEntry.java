package src;

public class CacheEntry {
    String descricao;
    String preco;
    long timestamp;

    CacheEntry(String descricao, String preco, long timestamp) {
        this.descricao = descricao;
        this.preco = preco;
        this.timestamp = timestamp;
    }
}