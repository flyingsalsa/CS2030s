public class JoinQueue extends Event {

  private Customer customer;
  private Queue<Customer> q;
  private String prevQueueState;
  
  public JoinQueue(double time, Customer customer, Queue<Customer> q) {
    super(time);
    this.customer = customer;
    this.q = q;
    prevQueueState = q.toString();
  }

  public Event[] simulate() {
    q.enq(customer);
    return new Event[] {};
  }

  public String toString() {
    return super.toString() + ": " + customer.toString() + " joined queue " + prevQueueState;
  }
}
