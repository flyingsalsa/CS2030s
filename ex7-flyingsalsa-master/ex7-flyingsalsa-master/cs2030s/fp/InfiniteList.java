package cs2030s.fp;

import java.util.List;

public class InfiniteList<T> {

  private final Lazy<Maybe<T>> head;
  private final Lazy<InfiniteList<T>> tail;

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
        Lazy.of(() -> Maybe.some(seed)),
        Lazy.of(() -> iterate(next.transform(seed), next))
        );
  }

  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    // TODO
    this.head = null;
    this.tail = null;
  }

  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  @SuppressWarnings("unchecked")
  public T head() {
    Maybe<T> maybeHead = head.get();
    return (T) maybeHead.orElseGet(() -> tail.get().head());
  }

  public InfiniteList<T> tail() {
    head();
    Maybe<T> maybeHead = head.get();
    Maybe<InfiniteList<T>> maybeTail = Maybe.of(tail.get());
    return maybeTail.flatMap(x -> maybeHead)
      .flatMap(x -> maybeTail)
      .orElseGet(() -> tail.get().tail());
  }

  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
    return new InfiniteList<>(
        Lazy.of(() -> Maybe.some(mapper.transform(head()))),
        Lazy.of(() -> tail().map(mapper))
        );
  }

  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    return new InfiniteList<>(
        Lazy.of(() -> Maybe.of(head()).filter(predicate)),
        Lazy.of(() -> tail().filter(predicate))
        );  
  }

  public static <T> InfiniteList<T> sentinel() {
    // TODO
    return new InfiniteList<>();
  }

  public InfiniteList<T> limit(long n) {
    // TODO
    return new InfiniteList<>();
  }

  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
    // TODO
    return new InfiniteList<>();
  }

  public boolean isSentinel() {
    return false;
  }

  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    // TODO
    return null;
  }

  public long count() {
    // TODO
    return 0;
  }

  public List<T> toList() {
    // TODO
    return List.of();
  }

  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }
}
