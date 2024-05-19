import cs2030s.fp.BooleanCondition;
import cs2030s.fp.Consumer;
import cs2030s.fp.Maybe;
import cs2030s.fp.Producer;
import cs2030s.fp.Transformer;
import java.util.ArrayList;
import java.util.List;

class Test4 {
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();

    i.expect("Maybe.<Number>none().orElse(4) returns 4",
        Maybe.<Number>none().orElse(4), 4);
    i.expect("Maybe.<Integer>some(1).orElse(4) returns 1",
        Maybe.<Integer>some(1).orElse(4), 1);

    Producer<Double> zero = new Producer<>() {
      @Override
      public Double produce() {
        return 0.0;
      }
    };
    i.expect("Maybe.<Number>none().orElseGet(zero) returns 0.0",
        Maybe.<Number>none().orElseGet(zero), 0.0);
    i.expect("Maybe.<Number>some(1).orElseGet(zero) returns 1",
        Maybe.<Number>some(1).orElseGet(zero), 1);

    List<Object> list = new ArrayList<>();
    Consumer<Object> addToList = new Consumer<>() {
      @Override
      public void consume(Object o) {
        list.add(o);
      }
    };

    Maybe.<Number>none().ifPresent(addToList);
    i.expect("Maybe.<Number>none().ifPresent(addToList) does nothing",
        list.size(), 0);
    Maybe.<Number>some(1).ifPresent(addToList);
    i.expect("Maybe.<Number>some(1).ifPresent(addToList) add 1 to list",
        list.get(0), 1);
  }
}
