public class ArrayFront9 {
    public static boolean arrayFront9(int[] nums){
        boolean output = false;
        if (nums.length >= 4 ){
            for (int i = 0; i < 4; i++){
                if (nums[i] == 9) {
                    output = true;
                    break;
                }
            }
        } else {
            for (int i = 0; i < nums.length; i++){
                if (nums[i] == 9 ){
                    output = true;
                    break;
                }
            }
        }

        return output;
    }

    public static void main(String[] args) {
        int N = args.length;
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(args[i]);
        }
        System.out.println(arrayFront9(nums));
    }
}
