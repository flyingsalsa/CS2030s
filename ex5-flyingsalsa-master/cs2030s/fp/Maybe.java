/**
 * CS2030S Lab 5
 * AY23/24 Semester 2
 *
 * @author Put Your Name (Lab Group)
 */

package cs2030s.fp;

import java.util.NoSuchElementException; 

public abstract class Maybe<T> {

  public static final None none = new None();

  private static final class Some<T> extends Maybe<T> {
    private final T content;

    private Some(T t) {
      this.content = t;
    }

    @Override
    protected T get() {
      return content;
    }

    @Override
    public String toString() {
      return "[" + content + "]";
    }

    @Override
    public boolean equals(Object o) {            
      if (this == o) {        
        return true;      
      }      
      if (o instanceof Some<?>) {        
        Some<?> b = (Some<?>) o;        
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
    public <U> Maybe<U> map(Transformer<? super T, ? extends U> thing) {
      return Maybe.<U>some(thing.transform(content));
    }

    @Override
    public Maybe<T> filter(BooleanCondition<? super T> b) {
      if (content == null) {
        return this;
      }
      if (!b.test(content)) {
        return Maybe.none();
      }
      return this;
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> mapper) {
      Maybe<U> r = (Maybe<U>) mapper.transform(content); 
      return r;
    }

    @Override
    public T orElse(Object o) {
      return content;
    }

    @Override
    public <U extends T> T orElseGet(Producer<U> p) {
      return content;
    }

    @Override
    public void ifPresent(Consumer<? super T> c) {
      c.consume(content);
    }
   
  }
  
  private static class None extends Maybe<Object> {
   
    private None(){
    }
    
    @Override
    public String toString() {
      return "[]";
    }

    @Override 
    protected Object get() {
      throw new NoSuchElementException("");
    }

    @Override
    public <U> Maybe<U> map(Transformer<? super Object, ? extends U> t) {
      return Maybe.<U>none();
    }
    
    @Override
    public Maybe<Object> filter(BooleanCondition<? super Object> t) {
      return this;
    }

    @Override 
    public <U> Maybe<U> flatMap(Transformer<? super Object, ? extends Maybe<? extends U>> mapper) {
      return Maybe.<U>none();
    }

    @Override
    public <U extends Object> U orElse(U o) {
      return o;
    }

    @Override
    public <U extends Object> U orElseGet(Producer<U> p) {
      return p.produce();
    }

    @Override
    public void ifPresent(Consumer<? super Object> c) {
    }

  }

  public static <T> Maybe<T> some(T t) {
    return new Some<T>(t);
  }

  @SuppressWarnings("unchecked")
  public static <T> Maybe<T> none() {
    return (Maybe<T>) none;
  }

  public static <T> Maybe<T> of(T t) {
    if (t == null) {
      return Maybe.<T>none();
    }
    return Maybe.<T>some(t);
  }

  protected abstract T get();

  public abstract String toString();

  public abstract <U> Maybe<U> map(Transformer<? super T, ? extends U> t);
  
  public abstract Maybe<T> filter(BooleanCondition<? super T> b);

  public abstract <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> mapper);

  public abstract <U extends T> T orElse(U o);

  public abstract <U extends T> T orElseGet(Producer<U> p);

  public abstract void ifPresent(Consumer<? super T> c);
}


