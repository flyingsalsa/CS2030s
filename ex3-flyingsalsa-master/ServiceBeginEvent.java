public class ServiceBeginEvent extends Event {
  
  private Customer customer;
  private Bank bank;
  private Counter counter;
  private Queue<Customer> q;

  public ServiceBeginEvent(double time, Bank bank, Customer customer, 
      Counter availableCounter, Queue<Customer> q) {
    super(time);
    this.customer = customer;
    this.bank = bank;
    this.counter = availableCounter;
    this.q = q;
  }

  @Override
  public Event[] simulate() {
    counter.setBusy();
    return new Event[] {
      new ServiceEndEvent(customer.calculateEndTime(this.getTime()),
         bank, counter, customer, q)
    };
  }
  
  @Override
  public String toString() {
    String str = String.format(": %s %s begin (by %s)", customer.toString(),
        customer.taskToString(), counter.toString());
    return super.toString() + str;
  }
}
