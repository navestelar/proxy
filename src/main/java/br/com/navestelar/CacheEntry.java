package br.com.navestelar;

import java.util.Map;

/**
 * Representa uma entrada de cache que contém valores e um timestamp.
 *
 * <p>A classe {@code CacheEntry} armazena um mapa de valores e um timestamp associado. O timestamp pode ser utilizado para determinar a validade da entrada de cache.</p>
 */
public class CacheEntry {

    private Map<String, String> values;
    private long timestamp;

    /**
     * Constrói uma nova entrada de cache com os valores fornecidos e o timestamp.
     *
     * @param values O mapa de valores associados a esta entrada de cache.
     * @param timestamp O timestamp quando a entrada de cache foi criada ou atualizada.
     */
    public CacheEntry(Map<String, String> values, long timestamp) {
        this.values = values;
        this.timestamp = timestamp;
    }

    /**
     * Obtém o mapa de valores armazenados nesta entrada de cache.
     *
     * @return O mapa de valores armazenados.
     */
    public Map<String, String> getValues() {
        return values;
    }

    /**
     * Obtém o timestamp associado a esta entrada de cache.
     *
     * @return O timestamp quando a entrada de cache foi criada ou atualizada.
     */
    public long getTimestamp() {
        return timestamp;
    }
}
