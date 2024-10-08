import java.util.Scanner;

/**
 * CS2030S Exercise 0: Estimating Pi with Monte Carlo
 * Semester 2, 2023/24
 *
 * <p>This program takes in two command line arguments: the 
 * number of points and a random seed.  It runs the
 * Monte Carlo simulation with the given argument and print
 * out the estimated pi value.
 *
 * @author XXX (00X)
 */

class Ex0 {

  // TODO estimatePi(int numOfPoints, int seed) {
  // }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int numOfPoints = sc.nextInt();
    int seed = sc.nextInt();

    double pi = estimatePi(numOfPoints, seed);

    System.out.println(pi);
    sc.close();
  }
}
