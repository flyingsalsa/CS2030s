/**
 * apply to another integer x.  Returns true if x is divisible 
 * by y, false otherwise.
 * CS2030S Exercise 4
 * AY23/24 Semester 2
 *
 * @author Put Your Name (Lab Group)
 */

class DivisibleBy implements BooleanCondition<Integer> {

  private int k;

  public DivisibleBy(int k) {
    this.k = k;
  }

  public boolean test(Integer t) {
    return t.intValue() % k == 0;
  }
}
