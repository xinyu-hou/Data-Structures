package HashQuestion;

import java.util.ArrayList;
import java.util.List;

public class Hash implements HashInterface {

    private List<Node> buckets;
    private int numOfBuckets; // number of buckets in the table
    private int numOfEntries; // number of entries in the table
    private int numOfOccupiedBuckets;
    private final double DEFAULT_LOAD_FACTOR = 0.75;


    public Hash(int numOfBuckets) {
        this.numOfBuckets = numOfBuckets;
        this.numOfEntries = 0;
        this.numOfOccupiedBuckets = 0;
        this.buckets = new ArrayList<>();
        for (int i = 0; i < this.numOfBuckets; i++) {
            this.buckets.add(null);
        }
    }

    /**
     * Return the hashcode of the given key. Source:
     * https://stackoverflow.com/questions/2624192/good-hash-function-for-strings
     *
     * @param key a string key
     * @return the hashcode of the string key
     */
    private Integer hashFunc(String key) {
        int h = 7;
        char[] arr = key.toCharArray();
        if (arr.length > 0) {
            for (int i = 0; i < arr.length; i++) {
                h = 31 * h + arr[i];
            }
        }
        Integer hashCode = Math.abs(h % numOfBuckets);
        return hashCode;
    }

    @Override
    public void insert(String key, int value) {
        if (this.numOfOccupiedBuckets / this.numOfBuckets > this.DEFAULT_LOAD_FACTOR) {
            this.resize();
        }
        Integer findResult = this.find(key);
        Integer index = hashFunc(key);
        Node head = this.buckets.get(index);
        if (findResult == null) { // key does not exist
            Node nodeToAdd = new Node(key, value);
            if (head != null) { // linked list has element(s)
                nodeToAdd.next = head;
            } else { // linked list has no element
                this.numOfOccupiedBuckets += 1;
            }
            this.buckets.set(index, nodeToAdd);
            this.numOfEntries += 1;
        } else { // key already exists
            while (head != null) {
                if (head.key.equals(key)) {
                    head.value += value;
                }
                head = head.next;
            }
        }
    }

    /**
     * Resize the table when the current load factor is larger than
     * the default load factor (0.75).
     */
    private void resize() {
        this.numOfBuckets *= 2; // double the number of buckets
        this.numOfOccupiedBuckets = 0;
        List<Node> newBuckets = new ArrayList<>();
        // Give null heads to the new buckets
        for (int i = 0; i < this.numOfBuckets; i++) {
            newBuckets.add(null);
        }
        // Insert old key-value pairs into the new buckets
        for (Node temp : this.buckets) {
            while (temp != null) {
                Node nodeToAdd = new Node(temp.key, temp.value);
                Integer index = this.hashFunc(temp.key);
                Node head = newBuckets.get(index);
                if (head != null) {
                    nodeToAdd.next = head;
                } else {
                    this.numOfOccupiedBuckets += 1;
                }
                newBuckets.set(index, nodeToAdd);
                temp = temp.next;
            }
        }
        this.buckets = newBuckets;
    }

    @Override
    public void delete(String key) {
        Integer findResult = this.find(key);
        if (findResult == null) { // key does not exist
            return; // do nothing
        } else { // key already exists
            Integer index = hashFunc(key);
            Node head = this.buckets.get(index);
            if (head.key.equals(key)) { // match with current head
                head = head.next;
                this.buckets.set(index, head);
                this.numOfOccupiedBuckets -= 1;
                this.numOfEntries -= 1;
            } else { // does not match with current head
                Node prev = null;
                while (head != null) {
                    if (head.key.equals(key)) {
                        prev.next = head.next;
                        this.numOfEntries -= 1;
                        return;
                    }
                    prev = head;
                    head = head.next;
                }
            }
        }
    }

    @Override
    public void increase(String key) {
        this.insert(key, 1);
    }

    @Override
    public Integer find(String key) {
        Integer index = this.hashFunc(key);
        Node head = this.buckets.get(index);
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    @Override
    public List<String> listAllKeys() {
        List<String> allKeys = new ArrayList<>();
        for (Node bucket : this.buckets) {
            while (bucket != null) {
                allKeys.add(bucket.key);
                bucket = bucket.next;
            }
        }
        return allKeys;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Entries of the table: " + this.numOfEntries + "\n");
        sb.append("Size of the table: " + this.numOfBuckets + "\n");
        for (int i = 0; i < this.numOfBuckets; i++) {
            sb.append("Index " + i + ": ");
            Node temp = this.buckets.get(i);
            while (temp != null) {
                if (temp.next != null) {
                    sb.append(temp.key + "=" + temp.value + ", ");
                } else {
                    sb.append(temp.key + "=" + temp.value);
                }
                temp = temp.next;
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
