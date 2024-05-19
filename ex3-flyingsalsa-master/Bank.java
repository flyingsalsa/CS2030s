public class Bank {

  private Counter[] counterArray;
  private int numOfCounters;

  public Bank(int numOfCounters, int counterQueue) {
    this.counterArray = new Counter[numOfCounters];
    this.numOfCounters = numOfCounters;
    for (int id = 0; id < numOfCounters; id++) {
      counterArray[id] = new Counter(id, counterQueue);
    }
  }

  public Counter getNextCounter() {
    for (int i = 0; i < numOfCounters;  i++) {
      if (counterArray[i].getAvailability()) {
        return counterArray[i];
      }
    }
    return null;
  }

  public Counter getNextCounterQueue() {
    for (int i = 0; i < numOfCounters; i++) {
      if (!counterArray[i].getQueue().isFull()){
        return counterArray[i];
      }
    }
    return null;
  }
}
