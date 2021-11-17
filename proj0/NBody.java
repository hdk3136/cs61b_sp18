public class NBody {
    public static double readRadius (String filename) {
        In in = new In (filename);
        int N = in.readInt();
        double Radius = in.readDouble();
        return Radius;
    }

    public static Planet[] readPlanets (String filename) {
        In in = new In (filename);
        int N = in.readInt();
        double Radius = in.readDouble();
        Planet[] allPlanets = new Planet[N];
        for (int i = 0; i < N; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            allPlanets[i] = new Planet(xP, yP, xV, yV, m, img);
        }
        return allPlanets;
    }

    public static void main (String[] args) {
        /**Input the data and filename from command line */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        /**Read in the planets and the universe radius from the file */
        double Radius = readRadius(filename);
        Planet[] allPlanets = readPlanets(filename);
        /**Set the window size and scale */
        StdDraw.setCanvasSize(720,720);
        StdDraw.setScale(-Radius, Radius);
        /**Make the animation more smooth */
        StdDraw.enableDoubleBuffering();
        double t = 0;
        while (t < T) {
            double[] xForces = new double[allPlanets.length];
            double[] yForces = new double[allPlanets.length];
            for (int i = 0; i < allPlanets.length; i++) {
                xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
            }
            /**Important: Call update after all the xForces and yForces are calculated */
            for (int i = 0; i < allPlanets.length; i++) {
                allPlanets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.clear();
            /**Attention that: 
             * If use picture(double x, double y, String filename), the picture will have the origin size in pixels.
             * But if use picture(double x, double y, String filename, double scaledWidth, double scaledHeight), 
             * the last two "scaled" parameter is measured by coordinate value.
             * 
             */
            StdDraw.picture(0, 0, "./images/starfield.jpg", 2 * Radius, 2 * Radius);
            /**Drawing all planets */
            for (Planet p:allPlanets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;

        }
        /**Print the specific information of the universe after the loop */
        System.out.println(allPlanets.length);
        System.out.println(String.format("%.2e",Radius));
        for (int i = 0; i < allPlanets.length; i++) {
            System.out.print(String.format("%11.4e ",allPlanets[i].xxPos));
            System.out.print(String.format("%11.4e ",allPlanets[i].yyPos));
            System.out.print(String.format("%11.4e ",allPlanets[i].xxVel));
            System.out.print(String.format("%11.4e ",allPlanets[i].yyVel));
            System.out.print(String.format("%11.4e ",allPlanets[i].mass));
            System.out.print(String.format("%12s\n",allPlanets[i].imgFileName));
        }
    }
}