public class NbyN {
    public static int[][] nbyn(int N) {
        int[][] output = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                output[i][j] = 0;
            }
        }

        for (int i = 0; i < N; i++) {
            output[i][i] = i;
        }

        return output;
    }

    public static void main(String[] args) {
        int size = 10;
        int[][] myArray = nbyn(size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(myArray[i][j] + " ");
            }
            System.out.println();
        }
    }
}
