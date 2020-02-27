public class Sqrt {

    private final static double A = 0.0000000001;

    public static double getMin() {
        double low = 1.4;
        double high = 1.5;

        double mid = (low + high) / 2;

        while (high - low > A) {
            if (mid * mid > 2) {
                high = mid;
            } else {
                low = mid;
            }
            mid = (high + low) / 2;
        }
        return mid;
    }

    public static void main(String[] args) {
       /* System.out.println(Math.sqrt(2.0));
        System.out.println(Sqrt.getMin());*/

        System.out.println(Integer.toString(0b11111111,10));
    }
}
