public class Vector3D {

    private double xCoord;
    private double yCoord;
    private double zCoord;


    public Vector3D(double x, double y, double z) {
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }

    public double getR() {
        return Math.sqrt(Math.pow(xCoord, 2) + Math.pow(yCoord, 2) + Math.pow(zCoord, 2));
    }

    public double getTheta(){
        return Math.acos(zCoord/this.getR());
    }

    public double getPhi() {
        return Math.atan2(yCoord, xCoord);
    }

    public static Vector3D add(Vector3D lhs, Vector3D rhs) {
        return new Vector3D(lhs.xCoord + rhs.xCoord, lhs.yCoord + rhs.yCoord, lhs.zCoord + rhs.zCoord);
    }

    public static Vector3D subtract(Vector3D lhs, Vector3D rhs) {
        return new Vector3D(lhs.xCoord - rhs.xCoord, lhs.yCoord - rhs.yCoord, lhs.zCoord - rhs.zCoord);
    }

    public static Vector3D scale( Vector3D v, double scaleFactor) {
        return new Vector3D(v.xCoord * scaleFactor, v.yCoord * scaleFactor, v.zCoord * scaleFactor);
    }

}