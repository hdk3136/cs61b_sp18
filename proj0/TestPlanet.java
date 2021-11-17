public class TestPlanet {
    
    public static void Test () {
        Planet Samh = new Planet (1, 0, 0, 0, 10, "Samh");
        Planet Aegir = new Planet (3, 3, 0, 0, 5, "Saturn");
        Planet Rocinante = new Planet (5, -3, 0, 0, 50, "Rocinante");
        Planet[] allPlanets = {Samh, Aegir, Rocinante};
        System.out.println("FnetX exerted on Samh :"+Samh.calcNetForceExertedByX(allPlanets));
        System.out.println("FnetY exerted on Samh :"+Samh.calcNetForceExertedByY(allPlanets));
    }

    public static void main (String[] args) {
        Test();
    }
}