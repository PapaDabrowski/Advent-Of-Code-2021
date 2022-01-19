import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Stream;

public class Solver {

    long getSolution(int days, String path){
        try(Stream<String> input = Files.lines(Paths.get(path))) {
            Iterator<String> it = input.iterator();
            String temp = it.next();
            String [] parse = temp.split("([,])");
            long[] array = new long[10];
            for(String tmp : parse)
            {
                array[Integer.parseInt(tmp)]++;
            }
            System.out.println(Arrays.toString(array));
            for (int i = 0; i < days; i++) {
                System.out.println(Arrays.toString(array));
                for(int j= 0; j < 10; j++)
                {
                    if(j == 0){
                        array[9] += array[0];
                        array[7] += array[0];
                        array[0] = 0;
                    }
                    else
                    {
                        array[j - 1] = array[j];
                        array[j] = 0;
                    }
                }
            }
            System.out.println(Arrays.toString(array));
            long result = 0;
            for(long a : array)
                result += a;
            return result;
        }
        catch (IOException e)
        {
            return 0;
        }
    }
        /*
        try(Stream<String> input = Files.lines(Paths.get(path))) {
            Iterator<String> it = input.iterator();
            String temp = it.next();
            String [] parse = temp.split("([,])");
            System.out.println(Arrays.toString(parse));
            ArrayList<Integer> list = new ArrayList<Integer>();
            //Vector<Integer> vector = new Vector<Integer>(400,100);
            for(String tmp : parse)
                list.add(Integer.parseInt(tmp));
            for (int i = 0; i < days; i++) {
                System.out.println(list.size());
                System.out.println(i);
                for(int j = 0; j < list.size(); j++)
                {
                    if(list.get(j) == 0){
                        list.set(j, 6);
                        list.add(9);
                    }
                    else
                    {
                        list.set(j, list.get(j) - 1);
                    }
                }
            }
            return list.size();
        }
        catch (IOException e)
        {
            return 0;
        }
    }*/

    public static void main(String[] args)
    {
        Solver solver = new Solver();
        System.out.println(solver.getSolution(256, "src/data.txt"));
    }
}
