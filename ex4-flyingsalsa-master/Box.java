/**
 * A generic box storing an item.
 * CS2030S Exercise 4
 * AY23/24 Semester 2
 *
 * @author Matthew Lee 
 */
class Box<T> {

  private final T content;
  private static final Box<?> EMPTY_BOX = new Box<>(null);

  @Override 
  public boolean equals(Object o) {
    if (this == o) { 
      return true; 
    }
    if (o instanceof Box<?>) {
      Box<?> b = (Box<?>) o;
      if (this.content == b.content) { 
        return true; 
      }
      if (this.content == null || b.content == null) {
        return false; 
      }
      return this.content.equals(b.content);
    }
    return false;
  }

  @Override 
  public String toString() {
    if (isPresent()) {
      return "[" + content.toString() + "]";
    }
    return "[]";
  }

  private Box(T o) {
    this.content = o;
  }

  @SuppressWarnings("unchecked")
  public static <T> Box<T> empty() {
    return (Box<T>) EMPTY_BOX;
  }
 
  public static <T> Box<T> of(T o) {
    if (o == null) {
      return null;
    }
    return new Box<T>(o);
  }

  public static <T> Box<T> ofNullable(T o) {
    if (o == null) {
      return Box.<T>empty();
    }
    return new Box<T>(o);
  }

  public boolean isPresent() {
    return content != null;
  }

  public Box<T> filter(BooleanCondition<? super T> b) {
    if (content == null || !b.test(content)) {
      return Box.empty();
    }
    return this;
  }

  public <U> Box<U> map(Transformer<? super T, U> thing) {
    if (isPresent() && thing.transform(content) != null) {
      return Box.<U>of(thing.transform(content));
    }
    return Box.empty();
  }
}


