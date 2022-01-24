import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Stream;

public class Solver {


    int getSolutionFirstPart(String path){
        try(Stream<String> input = Files.lines(Paths.get(path))) {
            Iterator<String> it = input.iterator();
            String temp = it.next();
            String[] parse = temp.split("([,])");
            int[] array = new int[parse.length];
            int position = 0;
            int max = 0;
            for(String tmp : parse)
            {
                if(Integer.parseInt(tmp) > max)
                    max = Integer.parseInt(tmp);
                array[position] = Integer.parseInt(tmp);
                position++;
            }
            System.out.println(Arrays.toString(array));
            int minFuel = Integer.MAX_VALUE;
            for(int i = 0; i < max + 1; i++){
                int fuel = 0;
                for(int tmp : array)
                {
                    fuel += Math.abs(i - tmp);
                }
                if(fuel < minFuel)
                    minFuel = fuel;
            }
            return minFuel;
        }
        catch (IOException e) {
            return 0;
        }
    }

    int getSolution(String path){
        try(Stream<String> input = Files.lines(Paths.get(path))) {
            Iterator<String> it = input.iterator();
            String temp = it.next();
            String[] parse = temp.split("([,])");
            int[] array = new int[parse.length];
            int position = 0;
            int max = 0;
            for(String tmp : parse)
            {
                if(Integer.parseInt(tmp) > max)
                    max = Integer.parseInt(tmp);
                array[position] = Integer.parseInt(tmp);
                position++;
            }
            System.out.println(Arrays.toString(array));
            int minFuel = Integer.MAX_VALUE;
            for(int i = 0; i < max + 1; i++){
                int fuel = 0;
                for(int tmp : array)
                {
                    fuel += fuelConsumptionCalculator(Math.abs(i - tmp));
                }
                if(fuel < minFuel)
                    minFuel = fuel;
            }
            return minFuel;
        }
        catch (IOException e) {
            return 0;
        }
    }

    int fuelConsumptionCalculator(int resultOfSubtraction)
    {
        int result = 0;
        for(int i = 0; i < resultOfSubtraction + 1; i++)
            result += i;
        return result;
    }


    public static void main(String[] args)
    {
        Solver solver = new Solver();
        System.out.println(solver.getSolution( "src/data.txt"));
    }
}
