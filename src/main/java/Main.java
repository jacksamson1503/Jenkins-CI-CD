public class Main {

    public static int calculateTotal(int price, int tax) {
        return price + tax;
    }

    public static void main(String[] args) {
        System.out.println("Hello from Jenkins CI/CD");
        System.out.println("Total: " + calculateTotal(100, 20));
    }
}
