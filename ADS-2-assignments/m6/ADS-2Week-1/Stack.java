import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class for stack.
 *
 * @param      <Item>  The item
 */
public class Stack<Item> implements Iterable<Item> {
  /**
   * Size of the stack.
   */
  private int n;
  /**
   * Top of the stack.
   */
  private Node first;

  /**
   * Class for node.
   */

  private class Node {
    /**
     * Item variable.
     */
    private Item item;
    /**
     * Next node.
     */
    private Node next;
  }

  /**
    * Create an empty stack.
    */
  public Stack() {
    first = null;
    n = 0;
  }

  /**
   * Determines if empty.
   *
   * @return     True if empty, False otherwise.
   */
  public boolean isEmpty() {
    return first == null;
  }

  /**
   * Size function.
   *
   * @return     { description_of_the_return_value }
   */
  public int size() {
    return n;
  }

  /**
   * Push function.
   *
   * @param      item  The item
   */
  public void push(final Item item) {
    Node oldfirst = first;
    first = new Node();
    first.item = item;
    first.next = oldfirst;
    n++;
  }

  /**
   * Pop function.
   *
   * @return     { description_of_the_return_value }
   */
  public Item pop() {
    if (isEmpty()) {
      throw new RuntimeException("Stack underflow");
    }
    Item item = first.item;        // save item to return
    first = first.next;            // delete first node
    n--;
    return item;                   // return the saved item
  }


  /**
   * Peek Function.
   *
   * @return     { description_of_the_return_value }
   */
  public Item peek() {
    if (isEmpty()) {
      throw new RuntimeException("Stack underflow");
    }
    return first.item;
  }

  /**
   * Returns a string representation of the object.
   *
   * @return     String representation of the object.
   */
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (Item item : this) {
      s.append(item + " ");
    }
    return s.toString();
  }


  /**
   * Iterator function.
   *
   * @return     { description_of_the_return_value }
   */
  public Iterator<Item> iterator()  {
   return new ListIterator();
  }

  /**
   * Class for list iterator.
   */
  private class ListIterator implements Iterator<Item> {
    /**
     * Temporary Node.
     */
    private Node current = first;
    /**
     * Determines if it has next.
     *
     * @return     True if has next, False otherwise.
     */
    public boolean hasNext() {
     return current != null;
    }
    /**
     * Remove function.
     */
    public void remove() {
     throw new UnsupportedOperationException();
       }
    /**
     * To return the next item.
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


  /**
    * A test client.
    */
  /*    public static void main(String[] args) {
          Stack<String> s = new Stack<String>();
          while (!StdIn.isEmpty()) {
              String item = StdIn.readString();
              if (!item.equals("-")) s.push(item);
              else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
          }
          StdOut.println("(" + s.size() + " left on stack)");
      }*/
}


