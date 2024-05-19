public class Counter implements Comparable<Counter> {

  private int id;
  private boolean isAvaliable = true;
  private Queue<Customer> q;

  public Counter(int id, int counterQueue) {
    this.id = id;
    this.q = new Queue<Customer>(counterQueue);
  }

  public void setBusy() {
    isAvaliable = false;
  }

  public void setAvailable() {
    isAvaliable = true;
  }

  public boolean getAvailability() {
    return isAvaliable;
  }
  
  public Queue<Customer> getQueue() {
    return q;
  }

  public int getId() {
    return id;
  }
  
  public String toString() {
    return "S" + String.valueOf(id);
  }

  @Override
  public int compareTo(Counter counter) {
    if (this.id == counter.getId()) {
      return 0;
    } else if (this.id > counter.getId()) {
      return 1;
    } else {
      return -1;
    }
  }
}
