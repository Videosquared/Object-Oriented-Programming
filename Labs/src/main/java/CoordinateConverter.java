public class CoordinateConverter {

    public static double convertXYtoR(double x, double y) {
        double output = Math.sqrt((x*x)+(y*y));
        return output;
    }

    public static double convertXYtoT(double x, double y) {
        double output = Math.atan2(y, x);
        return output;
    }

    public static double convertRTtoX(double r, double theta) {
        double output = r * Math.cos(theta);
        return output;
    }

    public static double convertRTtoY(double r, double theta) {
        double output = r * Math.sin(theta);
        return output;
    }

    public static double convertDegToRad(double deg) {
        double output = deg * (Math.PI / 180);
        return output;
    }

    public static double convertRadToDeg(double rad) {
        double output = rad * (180 / Math.PI);
        return output;
    }

}