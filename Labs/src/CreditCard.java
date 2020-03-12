import java.util.Calendar;

public class CreditCard {

    private int expiryMonth;
    private int expiryYear;
    private String firstName;
    private String lastName;
    private String ccNumber;
    Calendar now = Calendar.getInstance();

    public CreditCard(int expiryMonth, int expiryYear, String firstName, String lastName, String ccNumber) {
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ccNumber = ccNumber;
    }

    public String formatExpiryDate() {
        String output;
        output = expiryMonth + "/" + Integer.toString(expiryYear).substring(2);
        return output;
    }

    public String formatFullName() {
        String output;
        output = firstName + " " + lastName;
        return output;
    }

    public String formatCCNumber() {
        String output = "";

        //output = String.format("%4s", ccNumber);
        output = ccNumber.replaceAll("....(?!$)", "$0 ");

/*        for (int i = 0; i < 16; i++) {
            if (i % 5 == 0) {
                output += " ";
            } else {
                output += (ccNumber.charAt(i));
            }
        }*/
        return output;
    }

    public boolean isValid() {
        boolean flag = false;

        boolean temp = ((expiryMonth > (now.get(Calendar.MONTH)+1)) && (expiryYear >= now.get(Calendar.YEAR)));
        boolean temp2 = ((expiryMonth <= (now.get(Calendar.MONTH)+1)) && (expiryYear > now.get(Calendar.YEAR)));


        if (temp || temp2) {
            flag = true;
        }

        return flag;
    }

    public String toString() {
        String output = "";

        output = String.format("Number: %s\nExpiration date: %s\nAccount holder: %s\nIs valid: %b", this.formatCCNumber(), this.formatExpiryDate(), this.formatFullName(), this.isValid());

        return output;
    }
}
