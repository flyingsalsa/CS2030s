public class ServiceEndEvent extends Event {

  private Customer customer;
  private Bank bank;
  private Counter counter;
  private Queue<Customer> q;

  public ServiceEndEvent(double time, Bank bank, Counter counter, 
      Customer customer, Queue<Customer> q) {
    super(time);
    this.customer = customer;
    this.counter = counter;
    this.bank = bank;
    this.q = q;
  }
  
  @Override
  public Event[] simulate() {
    counter.setAvailable();
    return new Event[] {
      new DepartureEvent(this.getTime(), customer, bank, q)
    };  
  }

  @Override
  public String toString() {
    String str = String.format(": %s %s done (by %s)", customer.toString(),
                customer.taskToString(), counter.toString());
    return super.toString() + str;
  }
}

