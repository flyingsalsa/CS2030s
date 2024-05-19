/**
 * The Seq<T> class for CS2030S 
 *
 * @author XXX
 * @version CS2030S AY23/24 Semester 2
 */
@SuppressWarnings("rawtypes")

class Seq<T extends Comparable<T>> { // TODO: Change to bounded type parameter
  private T[] seq;

  Seq(int size) {
    @SuppressWarnings("unchecked")
    T[] a = (T[]) new Comparable[size];
    this.seq = a;
  }

  public void set(int index, T item) {
    this.seq[index] = item;
  }

  public T get(int index) {
    return this.seq[index];
  }

  public T min() {
    T thing = this.seq[0];
    int length = seq.length;
    for (int i = 0; i < length; i++) {
      if (thing.compareTo(seq[i]) > 0) {
        thing = seq[i];
      }
    }
    return thing;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    for (int i = 0; i < this.seq.length; i++) {
      s.append(i + ":" + this.seq[i]);
      if (i != this.seq.length - 1) {
        s.append(", ");
      }
    }
    return s.append(" ]").toString();
  }
}
