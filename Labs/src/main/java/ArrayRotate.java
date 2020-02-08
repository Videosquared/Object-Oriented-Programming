public class ArrayRotate {
    public static void main(String[] args){
        int n = args.length;
        int[] nums = new int[n];
        int[] copy = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(args[i]);
        }

        for (int j = 0; j < n-1; j++){
            copy[j] = nums[j+1];
        }
        copy[n - 1] = nums[0];

        for (int x = 0; x < n; x++){
            System.out.print(copy[x]);
            System.out.print(" ");
        }
    }
}
