/**
 * A conditional statement that returns either true of false.
 * CS2030S Exercise 4
 * AY23/24 Semester 2
 *
 * @author Put Your Name (Lab Group)
 */

package cs2030s.fp;

public interface BooleanCondition<T> {
  public boolean test(T o);
}
    
