
public class City {
    private double xCoor;
    private double yCoor;

    public City(double xCoor, double yCoor) {
        this.xCoor = xCoor;
        this.yCoor = yCoor;

    }

    public double getxCoor() {
        return xCoor;
    }

    public void setxCoor(double xCoor) {
        this.xCoor = xCoor;
    }

    public double getyCoor() {
        return yCoor;
    }

    public void setyCoor(double yCoor) {
        this.yCoor = yCoor;
    }

    public double distanceBetween(City city) {
        double a = Math.abs(xCoor - city.getxCoor());
        double b = Math.abs(yCoor - city.getyCoor());
        double distance = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
        return distance;
    }

}
