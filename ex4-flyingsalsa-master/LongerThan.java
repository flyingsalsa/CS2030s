/**
 * A boolean condition with parameter x that can be applied to
 * a string.  Returns true if the string is longer than x; false
 * otherwise.
 * CS2030S Exercise 4
 * AY23/24 Semester 2
 *
 * @author Put Your Name (Lab Group)
 */

class LongerThan implements BooleanCondition<String> {

  private int k;

  public LongerThan(int k) {
    this.k = k;
  }

  public boolean test(String j) {
    return j.length() > k;
  }
}
