import java.awt.Color;

public class Voronoi {

    public static double pointDist(Point2D point1, Point2D point2) {
        return Math.sqrt( (Math. pow((point2.getX() - point1.getX()), 2)) + (Math. pow((point2.getY() - point1.getY()), 2)) );
    }

    public static int findClosestPoint(Point2D point, Point2D[] sites) {
        double[] temp = new double[sites.length];
        int minDist = 0;

        for (int i = 0; i < sites.length; i++) {
            temp[i] = pointDist(point, sites[i]);
        }

        double min = temp[0];
        for (int i = 0; i < sites.length; i++) {
            if (min > temp[i]) {
                minDist = i;
                min = temp[i];
            }
        }

        return minDist;
    }

    public static int[][] buildVoronoiMap(Point2D[] sites, int width, int height) {
        int[][] voronoiMap = new int[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                voronoiMap[i][j] = findClosestPoint(new Point2D(i,j) ,sites);
            }
        }

        return voronoiMap;
    }

    public static void plotVoronoiMap(Point2D[] sites, Color[] colors, int[][] indexMap) {
        int width = indexMap.length;
        int height = indexMap[0].length;

        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);

        StdDraw.setPenRadius(0.0);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                StdDraw.setPenColor(colors[indexMap[i][j]]);
                StdDraw.point(i,j);
            }
        }

        StdDraw.setPenColor(Color.BLACK);
        for (int i = 0; i < sites.length; i++) {
            StdDraw.filledCircle( sites[i].getX(), sites[i].getY() , 3);
        }

    }

    public static void main(String[] args) {
        int width = 200;
        int height = 200;

        int nSites = 5;
        Point2D[] sites = new Point2D[nSites];
        sites[0] = new Point2D(50, 50);
        sites[1] = new Point2D(100, 50);
        sites[2] = new Point2D(50, 100);
        sites[3] = new Point2D(125, 50);
        sites[4] = new Point2D(100, 175);

        Color[] colors = new Color[nSites];
        colors[0] = Color.BLUE;
        colors[1] = Color.GREEN;
        colors[2] = Color.YELLOW;
        colors[3] = Color.ORANGE;
        colors[4] = Color.MAGENTA;

        int[][] indexmap = buildVoronoiMap(sites, width, height);
        plotVoronoiMap(sites, colors, indexmap);

    }

}