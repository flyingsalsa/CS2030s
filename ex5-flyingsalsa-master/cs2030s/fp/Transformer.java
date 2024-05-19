/**
 * The Transformer interface that can transform a type T
 * to type U.
 * CS2030S Exercise 4
 * AY23/24 Semester 2
 *
 * @author Put Your Name (Lab Group)
 */

package cs2030s.fp;

public interface Transformer<T, U> {

  public U transform(T t);
}

