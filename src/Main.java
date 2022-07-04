import java.io.BufferedWriter;
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class Main {

    private static ArrayList<City> cityList = new ArrayList<City>();
    private static ArrayList<City> shortestPath = new ArrayList<City>();

    public static void main(String[] args) {
        try {
            File myObj = new File("/Users/bugra/Desktop/ca4663.tsp");
            Scanner myReader = new Scanner(myObj);
            String line = myReader.nextLine();
            while (!line.equals("EOF")) {
                if (line.substring(0, 1).matches("[0-9]+")) {
                    // System.out.println(line);
                    double x = Double.parseDouble(line.substring(line.indexOf(' '), line.lastIndexOf(' ')));
                    double y = Double.parseDouble(line.substring(line.lastIndexOf(' ')));
                    City city = new City(x, y);
                    cityList.add(city);
                }
                line = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Long start = System.currentTimeMillis();
        int shortestCityID = 0;
        for (int j = 0; j < cityList.size(); j++) {
            City currentCity = cityList.get(shortestCityID);
            shortestPath.add(cityList.get(shortestCityID));
            cityList.remove(cityList.get(shortestCityID));
            double maxDist = 100000000.0;
            for (int i = 0; i < cityList.size(); i++) {
                double betweenDist = currentCity.distanceBetween(cityList.get(i));
                if (betweenDist < maxDist) {
                    maxDist = betweenDist;
                    shortestCityID = i;
                }
            }
        }
        Long end = System.currentTimeMillis();
        System.out.println("Algorithm Time (in miliseconds): " + (end - start));
        double shortestDist = 0;
        for (int i = 0; i < shortestPath.size() - 1; i++) {
            shortestDist += shortestPath.get(i).distanceBetween(shortestPath.get(i + 1));
        }
        System.out.println("Shortest distance: " + shortestDist);

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("shortestPath.txt"), "utf-8"))) {
            for (int i = 0; i < shortestPath.size(); i++) {
                writer.write(
                        shortestPath.get(i).getxCoor() + " " + shortestPath.get(i).getyCoor() + System.lineSeparator());
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}