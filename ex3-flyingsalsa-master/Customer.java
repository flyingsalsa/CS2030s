public class Customer {

  private int id;
  private double serviceTime;
  private int taskType;
  private int taskAmount;

  public Customer(int id, double serviceTime, int taskType, int taskAmount) {
    this.id = id;
    this.serviceTime = serviceTime;
    this.taskType = taskType;
    this.taskAmount = taskAmount;
  }
  
  public double calculateEndTime(double currentTime) {
    return serviceTime + currentTime;
  }
  
  public String toString() {
    return "C" + String.valueOf(id);
  }

  public String taskToString() {
    if (taskType == 0) {
      return "deposit";
    } else if (taskType == 1) {
      return "withdrawal";
    }
    return null;
  }

  public int getTaskAmount() {
    if (taskType == 0) {
      return taskAmount;
    } 
    if (taskType == 1) {
      return -taskAmount;
    }
    return 0;
  }

  public int getId() {
    return id;
  }
}
