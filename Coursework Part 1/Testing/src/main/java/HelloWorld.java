public class HelloWorld {
    public static void main (String[] args) {
        Integer dimension = 8;
        String hound_positions = "";
        String fox_position = "";

        //Position of hounds for all potential board sizes
        //  if (dimension > 3 && dimension < 27) {
        int hounds = (dimension / 2);
        //Position of hounds for all potential board sizes
        switch (dimension) {
            case 4:
                hound_positions = "B1," + "D1,";
                fox_position = "C4";
                break;
            case 5:
                hound_positions = "B1," + "D1,";
                fox_position = "C5";
                break;
            case 6:
                hound_positions = "B1," + "D1," + "F1,";
                fox_position = "D6";
                break;
            case 7:
                hound_positions = "B1," + "D1," + "F1,";
                fox_position = "D7";
                break;
            case 8:
                hound_positions = "B1," + "D1," + "F1," + "H1,";
                fox_position = "E8";
                break;
            case 9:
                hound_positions = "B1," + "D1," + "F1," + "H1,";
                fox_position = "E9";
                break;
            case 10:
                hound_positions = "B1," + "D1," + "F1," + "H1," + "J1,";
                fox_position = "F10";
                break;
            case 11:
                hound_positions = "B1," + "D1," + "F1," + "H1," + "J1,";
                fox_position = "F11";
                break;
            case 12:
                hound_positions = "B1," + "D1," + "F1," + "H1," + "J1," + "L1,";
                fox_position = "G12";
                break;
            case 13:
                hound_positions = "B1," + "D1," + "F1," + "H1," + "J1," + "L1,";
                fox_position = "G13";
                break;
            case 14:
                hound_positions = "B1," + "D1," + "F1," + "H1," + "J1," + "L1," + "N1,";
                fox_position = "H14";
                break;
            case 15:
                hound_positions = "B1," + "D1," + "F1," + "H1," + "J1," + "L1," + "N1,";
                fox_position = "H15";
                break;
            case 16:
                hound_positions = "B1," + "D1," + "F1," + "H1," + "J1," + "L1," + "N1," + "P1,";
                fox_position = "I16";
                break;
            case 17:
                hound_positions = "B1," + "D1," + "F1," + "H1," + "J1," + "L1," + "N1," + "P1,";
                fox_position = "I17";
                break;
            case 18:
                hound_positions = "B1," + "D1," + "F1," + "H1," + "J1," + "L1," + "N1," + "P1," + "R1,";
                fox_position = "J18";
                break;
            case 19:
                hound_positions = "B1," + "D1," + "F1," + "H1," + "J1," + "L1," + "N1," + "P1," + "R1,";
                fox_position = "J19";
                break;
            case 20:
                hound_positions = "B1," + "D1," + "F1," + "H1," + "J1," + "L1," + "N1," + "P1," + "R1," + "T1,";
                fox_position = "K20";
                break;
            case 21:
                hound_positions = "B1," + "D1," + "F1," + "H1," + "J1," + "L1," + "N1," + "P1," + "R1," + "T1,";
                fox_position = "K21";
                break;
            case 22:
                hound_positions = "B1," + "D1," + "F1," + "H1," + "J1," + "L1," + "N1," + "P1," + "R1," + "T1," + "V1,";
                fox_position = "L22";
                break;
            case 23:
                hound_positions = "B1," + "D1," + "F1," + "H1," + "J1," + "L1," + "N1," + "P1," + "R1," + "T1," + "V1,";
                fox_position = "L23";
                break;
            case 24:
                hound_positions = "B1," + "D1," + "F1," + "H1," + "J1," + "L1," + "N1," + "P1," + "R1," + "T1," + "V1," + "X1,";
                fox_position = "M24";
                break;
            case 25:
                hound_positions = "B1," + "D1," + "F1," + "H1," + "J1," + "L1," + "N1," + "P1," + "R1," + "T1," + "V1," + "X1,";
                fox_position = "M25";
                break;
            case 26:
                hound_positions = "B1," + "D1," + "F1," + "H1," + "J1," + "L1," + "N1," + "P1," + "R1," + "T1," + "V1," + "X1," + "Z1,";
                fox_position = "N26";
                break;
        }


        //======================================================================================================================
        //======================================================================================================================
        //======================================================================================================================
        //======================================================================================================================
        //======================================================================================================================
        //======================================================================================================================
        //======================================================================================================================


        String[] player = new String[dimension];
        String all_pos = hound_positions + fox_position;
        player = all_pos.split(",");
        // }


        int hound_num = dimension/2;
        String ascii_1 = "-";
        String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        String[] numbers_str = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"};
        Integer[] numbers = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};
        System.out.println(ascii_1.repeat(2*dimension));


        //Top Alphabet
        System.out.print("    ");
        for (int i = 0; i < dimension; i++) {
            System.out.print(alphabet[i]);
        }
        System.out.print("\n");

        int index = 0;
        String[] coords = new String[dimension*dimension];
        for (int i = 0; i < dimension; i++) {
            for (int l = 0; l < dimension; l++) {
                coords[index] = alphabet[i] + numbers_str[l];
                index++;
            }
        }

        //Numbers and board in the middle
        int n = 0;
        for (int i = 0; i < dimension; i++) {

            if (i < 9) {
                System.out.println("0" + numbers_str[i] + "  " + ".".repeat(dimension) + "  " + "0" + numbers_str[i]);
            } else {
                System.out.println(numbers_str[i] + "  " + ".".repeat(dimension) + "  " + numbers_str[i]);
            }
            for (int x = 0; x < player.length; x++) {
                for (int k=0; k <dimension*dimension; k++) {
                    if (coords[k].equals(player[x])) {
                        System.out.print("F");
                    }
                }
            }


        }


        //Bottom Alphabet
        System.out.print("    ");
        for (int k = 0; k < dimension; k++) {
            System.out.print(alphabet[k]);
        }
        ///////////////////////////////////////////////////////////////////////
    }
}

