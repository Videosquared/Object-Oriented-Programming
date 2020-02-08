public class Testing {
    public static void main(String[] args) {
        int dimension = 26;
        char H_LEFT_COORD = 'B';

        if (dimension >= 4 && dimension <= 26) {
            int num_of_hounds = (dimension / 2);
            String[] start_pos = new String[num_of_hounds + 1];

            start_pos[0] = String.valueOf(H_LEFT_COORD);
            start_pos[0] += 1;

            for (int i = 1; i < start_pos.length - 1; i++) {
                start_pos[i] = String.valueOf((char) (start_pos[i - 1].charAt(0) + 2));
                start_pos[i] += 1;
            }

            if (dimension % 2 == 0) {
                start_pos[start_pos.length - 1] = String.valueOf((char) ('A' + (int) (Math.ceil(dimension / 2.0))));
                start_pos[start_pos.length - 1] += dimension;
            } else {
                start_pos[start_pos.length - 1] = String.valueOf((char) ('A' + (int) (Math.floor(dimension / 2.0))));
                start_pos[start_pos.length - 1] += dimension;
            }
            for (int i = 0; i < start_pos.length ; i++) {
                System.out.println(start_pos[i]);
            }

        } else {
            throw new IllegalArgumentException(String.format("Invalid Game dimension: %d", dimension));
        }


    }

}
