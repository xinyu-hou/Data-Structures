package HashQuestion;

import java.util.List;

public interface HashInterface {

    /**
     * Check if the key already exists. If yes, increment the existing value with the
     * provided value. If no, add the key-value pair into the table.
     *
     * @param key   the key
     * @param value a value for a certain key
     */
    void insert(String key, int value);

    /**
     * Check if the key already exists. If yes, delete the key-value pair from the table.
     * If no, do nothing.
     *
     * @param key the key
     */
    void delete(String key);

    /**
     * Check if the key already exists. If yes, increment the existing value by one.
     * If no, insert the key with the value 1.
     *
     * @param key the key
     */
    void increase(String key);

    /**
     * Check if the key already exists. If yes, return a pointer to the value.
     * If no, return null.
     *
     * @param key the key
     * @return a pointer to the value if the key is present. Otherwise, return null
     */
    Integer find(String key);

    /**
     * Return a list of all the existing keys.
     *
     * @return a list of existing keys
     */
    List<String> listAllKeys();
}
