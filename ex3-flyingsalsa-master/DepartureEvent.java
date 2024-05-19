public class DepartureEvent extends Event {
  
  private Customer customer;
  private Queue<Customer> q;
  private Bank bank;
  
  public DepartureEvent(double time, Customer customer, Bank bank, Queue<Customer> q) {
    super(time);
    this.customer = customer;
    this.q = q;
    this.bank = bank;
  }
  
  @Override
  public Event[] simulate() {
    if (q.length() > 0 && bank.getNextCounter() != null) {
      return new Event[]{
        new ServiceBeginEvent(this.getTime(), bank, (Customer) q.deq(), bank.getNextCounter(), q)
      };
    }
    return new Event[] {};  
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": %s departed", customer.toString());
    return super.toString() + str;
  }
}

