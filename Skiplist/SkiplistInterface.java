package SkiplistQuestion;

public interface SkiplistInterface {

    /**
     * Insert the target value into the skiplist. Do nothing
     * if the target value already exists.
     *
     * @param target the target value
     */
    void insert(int target);

    /**
     * Return true if the target exists in the skiplist and the
     * target is removed. Return false if the target does not
     * exist in the skiplist.
     *
     * @param target the target value
     * @return true if target is removed. Otherwise, return false
     */
    boolean delete(int target);

    /**
     * Return true if the target exists in the skiplist. Otherwise,
     * return false.
     *
     * @param target the target number
     * @return true if target exists. Otherwise, return false
     */
    boolean search(int target);

}
