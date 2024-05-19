package cs2030s.fp;

public class Lazy<T> {
  private Producer<? extends T> producer;
  private Maybe<T> value;
  
  private Lazy(Producer<? extends T> producer) {
    this.producer = producer;
    this.value = Maybe.<T>none();
  }
 
  private Lazy(T t) {
    this.producer = () -> t;
    this.value = Maybe.some(t);
  }


  public static <T> Lazy<T> of(T t) {
    return new Lazy<>(t);
  }

  public static <T> Lazy<T> of(Producer<? extends T> producer) {
    return new Lazy<>(producer);
  }
  
  public T get() {
    return value.orElseGet(() -> {
      T thing = producer.produce();
      value = Maybe.of(thing);
      return thing;
    });

  }
 
  @Override
  public String toString() {
    return String.valueOf(value.orElse("?"));
  }
 
  public <U> Lazy<U> map(Transformer<? super T, ? extends U> f) {
    return new Lazy<>(() -> {
      T value = get();
      return f.transform(value);
    });
  }

  public <U> Lazy<U> flatMap(Transformer<? super T, ? extends Lazy<? extends U>> f) {
    return new Lazy<>(() -> {
      T value = get();
      Lazy<? extends U> lazyValue = f.transform(value);
      return lazyValue.get();
    });
  }

  public Lazy<Boolean> filter(BooleanCondition<? super T> condition) {
    return new Lazy<>(() -> {
      T value = get();
      boolean result = condition.test(value);
      return result;
    });
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Lazy<?>)) {
      return false;
    }
    Lazy<?> other = (Lazy<?>) obj;
    T thisValue = this.get();
    Object otherValue = other.get();
    if (thisValue == null && otherValue == null) {
      return true;
    }
    if (thisValue == null || otherValue == null) {
      return false;
    }
    return thisValue.equals(otherValue);
  }
    
  public <S, R> Lazy<R> combine(Lazy<? extends S> other, 
      Combiner<? super T, ? super S, ? extends R> combiner) {
    return new Lazy<>(() -> {
      T thisValue = get();
      S otherValue = other.get();
      return combiner.combine(thisValue, otherValue);
    });
  }

}
