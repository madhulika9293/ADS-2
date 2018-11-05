import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * List of .
 *
 * @param      <Item>  The item
 */
public class Queue<Item> implements Iterable<Item> {
  /**
   * Node.
   */
  private Node<Item> first;    // beginning of queue
  /**
   * Node.
   */
  private Node<Item> last;     // end of queue
  /**
   * Node.
   */
  private int n;               // number of elements on queue

  // helper linked list class

  /**
   * Class for node.
   *
   * @param      <Item>  The item
   */
  private static class Node<Item> {
    /**
     * Item.
     */
    private Item item;
    /**
     * Next.
     */
    private Node<Item> next;
  }

  /**
   * Initializes an empty queue.
   */
  public Queue() {
    first = null;
    last  = null;
    n = 0;
  }

  /**
   * Returns true if this queue is empty.
   *
   * @return {@code true} if this queue is empty; {@code false} otherwise
   */
  public boolean isEmpty() {
    return first == null;
  }

  /**
   * Returns the number of items in this queue.
   *
   * @return the number of items in this queue
   */
  public int size() {
    return n;
  }

  /**
   * Returns the item least recently added to this queue.
   *
   * @return the item least recently added to this queue
   * @throws NoSuchElementException if this queue is empty
   */
  public Item peek() {
    if (isEmpty()) {
      throw new
      NoSuchElementException("Queue underflow");
    }
    return first.item;
  }

  /**
   * Adds the item to this queue.
   *
   * @param  item the item to add
   */
  public void enqueue(final Item item) {
    Node<Item> oldlast = last;
    last = new Node<Item>();
    last.item = item;
    last.next = null;
    if (isEmpty()) {
      first = last;
    } else {
      oldlast.next = last;
    }
    n++;
  }

  /**
   * Removes and returns the item on this queue that was least recently added.
   *
   * @return the item on this queue that was least recently added
   * @throws NoSuchElementException if this queue is empty
   */
  public Item dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue underflow");
    }
    Item item = first.item;
    first = first.next;
    n--;
    if (isEmpty()) {
      last = null;
    }
    return item;
  }

  /**
   * Returns a string representation of this queue.
   *
   * @return the sequence of items in FIFO order, separated by spaces
   */
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (Item item : this) {
      s.append(item);
      s.append(' ');
    }
    return s.toString();
  }

  /**
   * Returns an iterator that iterates over
   * the items in this queue in FIFO order.
   *
   * @return an iterator that iterates over
   * the items in this queue in FIFO order
   */
  public Iterator<Item> iterator()  {
    return new ListIterator<Item>(first);
  }

  // an iterator, doesn't implement remove() since it's optional

  /**
   * Class for list iterator.
   *
   * @param      <Item>  The item
   */
  private class ListIterator<Item> implements Iterator<Item> {
    /**
     * Node.
     */
    private Node<Item> current;

    /**
     * Constructs the object.
     *
     * @param      f  The first
     */
    ListIterator(final Node<Item> f) {
      current = f;
    }

    /**
     * Determines if it has next.
     *
     * @return     True if has next, False otherwise.
     */
    public boolean hasNext()  {
      return current != null;
    }
    /**
     * remove.
     */
    public void remove() {
      throw new UnsupportedOperationException();
    }

    /**
     * Next.
     *
     * @return     { description_of_the_return_value }
     */
    public Item next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      Item item = current.item;
      current = current.next;
      return item;
    }
  }
}
