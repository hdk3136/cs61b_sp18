public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public static final double G = 6.67e-11;

    public Planet (double xP, double yP, double xV, 
                  double yV, double m, String img) {
                      xxPos = xP;
                      yyPos = yP;
                      xxVel = xV;
                      yyVel = yV;
                      mass = m;
                      imgFileName = img;                      
    }
    public Planet (Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance (Planet p) {
        return Math.sqrt((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) 
                         + (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos));
    }

    public double calcForceExertedBy (Planet p) {
        double distance = this.calcDistance(p);
        return G * this.mass * p.mass / (distance * distance);
    }

    public double calcForceExertedByX (Planet p) {
        double xScale = (p.xxPos - this.xxPos) / this.calcDistance(p);
        return xScale * this.calcForceExertedBy(p);

    }

    public double calcForceExertedByY (Planet p) {
        double yScale = (p.yyPos - this.yyPos) / this.calcDistance(p);
        return yScale * this.calcForceExertedBy(p);

    }

    public double calcNetForceExertedByX (Planet[] allPlanets) {
        double NetForceExertedByX = 0;
        for (Planet p : allPlanets) {
            if (this.equals(p)) {
                continue;
            }
            NetForceExertedByX += this.calcForceExertedByX(p);
        }
        return NetForceExertedByX;
    }

    public double calcNetForceExertedByY (Planet[] allPlanets) {
        double NetForceExertedByY = 0;
        for (Planet p : allPlanets) {
            if (this.equals(p)) {
                continue;
            }
            NetForceExertedByY += this.calcForceExertedByY(p);
        }
        return NetForceExertedByY;
    }

    public void update (double dt, double fX, double fY) {
        double aNetX = fX / this.mass;
        double aNetY = fY / this.mass;
        this.xxVel += dt * aNetX;
        this.yyVel += dt * aNetY;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    public void draw () {
        StdDraw.picture(this.xxPos,this.yyPos,"./images/"+this.imgFileName);
    }
}
 