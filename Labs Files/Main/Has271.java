public class Has271 {
    public static boolean has271(int[] nums) {
        boolean flag = false;

        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i+1] == nums[i] + 5 && nums[i+2] == nums[i] - 1) {
                flag = true;
            }
         }

        return flag;
    }
}
