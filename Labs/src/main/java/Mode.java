public class Mode {
    public static void main(String[] args){
        int n = args.length;
        int[] dataset = new int[n];
        int[] count = new int[10];
        int max = 0;
        int maxp = 0;

        for (int i = 0; i < n; i++){
            dataset[i] = Integer.parseInt(args[i]);
        }

        for (int i = 0; i < n; i++){
            if (dataset[i] == 0 ){
                count[0] += 1;
            } else if ( dataset[i] == 1){
                count[1] += 1;
            } else if ( dataset[i] == 2){
                count[2] += 1;
            } else if ( dataset[i] == 3){
                count[3] += 1;
            } else if ( dataset[i] == 4){
                count[4] += 1;
            } else if ( dataset[i] == 5){
                count[5] += 1;
            } else if ( dataset[i] == 6){
                count[6] += 1;
            } else if ( dataset[i] == 7){
                count[7] += 1;
            } else if ( dataset[i] == 8){
                count[8] += 1;
            } else {
                count[9] += 1;
            }
        }

        for (int i = 0; i < 10; i++){
            if (count[i] > 0 ){
                System.out.print("[" + i + "s: " + count[i] +"] ");
                for (int j = 0; j < count[i]; j++) {
                    System.out.print(".");
                }
                System.out.println("");
            } else {
                System.out.println("[" + i + "s: " + count[i] +"]");
            }
        }
        for (int i = 0;i < 10 ; i++){
            if (max < count[i]){
                max = count[i];
                maxp = i;
            }
        }
        System.out.println("Mode: " + maxp);
    }
}
