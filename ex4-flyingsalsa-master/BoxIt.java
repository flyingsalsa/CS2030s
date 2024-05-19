/**
 * Takes an item and return the item in a box.
 * CS2030S Exercise 4
 * AY23/24 Semester 2
 *
 * @author Put Your Name (Lab Group)
 */
public class BoxIt<T> implements Transformer<T, Box<T>> {
  
  public Box<T> transform(T t) {
    return Box.of(t);
  }
}
