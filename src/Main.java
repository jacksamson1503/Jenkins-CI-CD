public class Main {

    public static void main(String[] args) {
        System.out.println("Jenkins CI/CD + SonarQube Demo");

        String user = "Jack";
        System.out.println("Hello, " + user);

        int result = calculateTotal(100, 20);
        System.out.println("Total: " + result);
    }

    public static int calculateTotal(int price, int tax) {
        return price + tax;
    }
}
