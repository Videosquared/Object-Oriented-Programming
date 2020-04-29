public class DNAStrand {

    private String dna;

    public DNAStrand(String dna){
        this.dna = dna;
    }

    public boolean isValidDNA() {
        boolean[] checkChar = new boolean[4];
        int counter = 0;

        for (int i = 0; i < dna.length(); i++) {
            if (dna.charAt(i) == 'A') {
                checkChar[0] = true;
                counter++;
            } else if (dna.charAt(i) == 'T') {
                checkChar[1] = true;
                counter++;
            } else if (dna.charAt(i) == 'C') {
                checkChar[2] = true;
                counter++;
            } else if (dna.charAt(i) == 'G') {
                checkChar[3] = true;
                counter++;
            }
        }

        boolean temp = checkChar[0] && checkChar[1] && checkChar[2] && checkChar[3];

        return (counter == dna.length()) && temp;

    }


    public String complementWC() {
        String output = "";

        for (int i = 0; i < dna.length(); i++) {
            if (dna.charAt(i) == 'A') {
                output += 'T';
            } else if (dna.charAt(i) == 'T') {
                output += 'A';
            } else if (dna.charAt(i) == 'C' ) {
                output += 'G';
            } else if (dna.charAt(i) == 'G') {
                output += 'C';
            }
        }
        return output;
    }

    public String palindromeWC() {
        String output = "";
        String temp = this.complementWC();

        for (int i = 0; i < temp.length(); i++) {
            output += temp.charAt(temp.length() - (i+1));
        }

        return output;
    }

    public boolean containsSequence(String seq) {
        int counter = 0;
        boolean flag = false;
        for (int j = 0; j < (dna.length() - (seq.length()-1)); j++) {
            counter = 0;
            for (int i = 0; i < seq.length(); i++) {
                if (dna.charAt(j+i) == seq.charAt(i)) {
                    counter++;
                }
            }
            if (counter == seq.length()) {
                flag = true;
                break;
            }
        }
        return flag;
    }


    public String toString() {
        return dna;
    }
}
