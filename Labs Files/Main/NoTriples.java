public class NoTriples {
    public static boolean noTriples(int[] nums) {
        boolean output = true;
        int counter = 0;

        for (int i = 0;i < nums.length - 1;i++) {
            if (nums[i] == nums[i+1]) {
                counter++;
            } else {
                counter = 0;
            }

            if (counter == 2) {
                output = false;
                break;
            }
        }

        return output;
    }

    public static void main(String[] args) {
        int[] array ={1, 1, 2, 2, 1};
        System.out.println(noTriples(array));
    }
}
