/**
 * A transformer with a parameter k that takes in an object x 
 * and outputs the last k digits of the hash value of x.
 * CS2030S Exercise 4
 * AY23/24 Semester 2
 *
 * @author Put Your Name (Lab Group)
 */

public class LastDigitsOfHashCode implements Transformer<Object, Integer> {

  private int k;

  public LastDigitsOfHashCode(int k) {
    this.k = k;
  }

  public Integer transform(Object o) {
    return Math.abs(o.hashCode()) % (int) Math.pow(10, k);
  }
}

