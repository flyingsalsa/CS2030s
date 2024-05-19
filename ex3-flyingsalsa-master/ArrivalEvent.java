public class ArrivalEvent extends Event {

  private Customer customer;
  private Bank bank;
  private Queue<Customer> q;

  public ArrivalEvent(double arrivalTime, Customer customer, Bank bank, Queue<Customer> q) {
    super(arrivalTime);
    this.customer = customer;
    this.bank = bank;
    this.q = q;
  }
  
  @Override
  public Event[] simulate() {
    Counter avaliableCounter = bank.getNextCounter();
    if (avaliableCounter == null && q.isFull()) {
      return new Event[] {
        new DepartureEvent(this.getTime(), customer, bank, q)
      };
    } else if (avaliableCounter == null && bank.getNextCounterQueue() == null) {
      return new Event[] {
        new JoinQueue(this.getTime(), customer, q)
      };
    }
    return new Event[]{
      new ServiceBeginEvent(this.getTime(), bank, customer, avaliableCounter, q)
    };
  }

  @Override
  public String toString() {
    return super.toString() + ": " + customer.toString() + " arrived " + q.toString();
  }
}
