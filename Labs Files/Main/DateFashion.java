public class DateFashion {
    public static int dateFashion(int you, int date){
        int output = 0;
        if (you >= 8 || date >= 8){
            if (you > 2 && date > 2) {
                output = 2;
            } else {
                output = 0;
            }
        } else if (you > 2 && date > 2) {
                output = 1;
        } else {
                output = 0;
        }
        return output;
    }

    public static void main(String[] args){
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);

        System.out.println(dateFashion(a,b));
    }
}
