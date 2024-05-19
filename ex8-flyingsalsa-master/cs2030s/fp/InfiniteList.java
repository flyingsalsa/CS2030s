package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class InfiniteList<T> {

  public final Lazy<Maybe<T>> head;
  public final Lazy<InfiniteList<T>> tail;
  private static InfiniteList<?> SENTINEL = new Sentinel();


  private InfiniteList() { 
    this.head = null; 
    this.tail = null;
  }

  public static <T> InfiniteList<T> generate(Producer<T> producer) {
    return new InfiniteList<>(
        Lazy.of(() -> Maybe.some(producer.produce())),
        Lazy.of(() -> generate(producer))
        );
  }

  public static <T> InfiniteList<T> iterate(T seed, Transformer<T, T> next) {
    return new InfiniteList<>(
        Lazy.of(Maybe.some(seed)),
        Lazy.of(() -> iterate(next.transform(seed), next))
        );
  }

  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    this.head = Lazy.of(Maybe.some(head));
    this.tail = Lazy.of(tail);
  }

  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  public T head() {
    Maybe<T> maybeHead = this.head.get();
    return (T) maybeHead.orElseGet(() -> this.tail.get().head());
  }

  public InfiniteList<T> tail() {
    Maybe<T> maybeHead = this.head.get();
    return maybeHead.map(x -> this.tail.get())
      .orElseGet(() -> this.tail.get().tail());
  }

  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
    return new InfiniteList<>(
        this.head.map(x -> x.map(mapper)),
        this.tail.map(x -> x.map(mapper))
     );
  }

  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    return new InfiniteList<>(
        Lazy.of(() -> this.head.get().filter(predicate)),
        Lazy.of(() -> this.tail.get().filter(predicate))
        );  
  }

  @SuppressWarnings("unchecked")
  //Sentinel content is null so it is safe to typecast to any other kind of InfiniteList<>
  public static <T> InfiniteList<T> sentinel() {
    return (InfiniteList<T>) SENTINEL;
  }

  public InfiniteList<T> limit(long n) {
    if (n <= 0) {
      return InfiniteList.<T>sentinel();
    }
    return new InfiniteList<T>(
        this.head,
        this.tail.map(t -> 
          this.head.get().map(h -> t.limit(n - 1))
            .orElseGet(() -> t.limit(n))));
  }

  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
    Lazy<Maybe<T>> filteredHead = this.head.map(x -> x.filter(predicate));

    return new InfiniteList<T>(filteredHead,
        filteredHead.map(fh -> fh.map(h -> this.tail.get().takeWhile(predicate))
          .orElseGet(() -> this.head.get().map(h -> InfiniteList.<T>sentinel())
            .orElseGet(() -> this.tail.get().takeWhile(predicate)))));
  }

  public boolean isSentinel() {
    return false;
  }

  public <U> U reduce(U identity, Combiner<? super U, ? super T, ? extends U> accumulator) {
    U result = this.tail.get().reduce(identity, accumulator);
    if (this.head.get().equals(Maybe.none())) {
      return result;
    }
    return accumulator.combine(result, this.head()); 
  }

  public long count() {
    return reduce(0, (x, y) -> x + 1);
  }

  public List<T> toList() {
    List<T> list = new ArrayList<>();
    InfiniteList<T> t = this;
    while (!t.isSentinel()) {
      if (t.head.get() != Maybe.none()) {
        list.add(t.head());
      }
      t = t.tail.get();
    }
    return list;
  }

  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }


  private static class Sentinel extends InfiniteList<Object> {
    
    public Sentinel() {
      super();
    }

    @Override
    public InfiniteList<Object> tail() {
      throw new NoSuchElementException();
    }

    @Override
    public Object head() {
      throw new NoSuchElementException();
    }

    @Override
    public <T> InfiniteList<T> map(Transformer<? super Object, ? extends T> mapper) {
      return InfiniteList.<T>sentinel();
    }

    @Override
    public InfiniteList<Object> filter(BooleanCondition<? super Object> predicate) {
      return InfiniteList.<Object>sentinel();
    }

    @Override
    public boolean isSentinel() {
      return true;
    }
    
    @Override
    public InfiniteList<Object> limit(long n) {
      return InfiniteList.<Object>sentinel();
    }

    @Override 
    public InfiniteList<Object> takeWhile(BooleanCondition<? super Object> predicate) {
      return InfiniteList.<Object>sentinel();
    }

    @Override
    public <U> U reduce(U id, Combiner<? super U, ? super Object, ? extends U> accumulator) {
      return id;
    }

    @Override
    public String toString() {
      return "-";
    }
  }
}
