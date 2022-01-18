import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;


public class Solver {
    public class Position
    {
        Position()
        {
            this.coveredByLines = 0;
        }

        boolean isCoveredBy(int amountOfLines)
        {
            return amountOfLines <= this.coveredByLines;
        }

        void addLine()
        {
            this.coveredByLines += 1;
        }

        private int coveredByLines;
    }

    boolean getFileInput(String path, Position[][] array)
    {
        try(Stream<String> input = Files.lines(Paths.get(path)))
        {
            for(Iterator<String> it = input.iterator(); it.hasNext();) {
                String temp = it.next();
                String[] arr = temp.split("([, - > -])+");
                System.out.println(Arrays.toString(arr));
                if (Integer.parseInt(arr[0]) == Integer.parseInt(arr[2]))
                {
                    for(int i = Math.min(Integer.parseInt(arr[1]), Integer.parseInt(arr[3])); i < Math.max(Integer.parseInt(arr[1]), Integer.parseInt(arr[3])) + 1; i++)
                        array[Integer.parseInt(arr[0])][i].addLine();
                }
                else if(Integer.parseInt(arr[1]) == Integer.parseInt(arr[3]))
                {
                    for(int i = Math.min(Integer.parseInt(arr[0]), Integer.parseInt(arr[2])); i < Math.max(Integer.parseInt(arr[0]), Integer.parseInt(arr[2])) + 1; i++)
                        array[i][Integer.parseInt(arr[1])].addLine();
                }
                else{
                    int y = 0;
                    int increment = 1;
                    if(Integer.parseInt(arr[0]) < Integer.parseInt(arr[2])){
                        y = Integer.parseInt(arr[1]);
                        if(Integer.parseInt(arr[1]) > Integer.parseInt(arr[3]))
                            increment = -1;
                    }
                    else
                    {
                        y = Integer.parseInt(arr[3]);
                        if(Integer.parseInt(arr[3]) > Integer.parseInt(arr[1]))
                            increment = -1;
                    }
                    for (int x = Math.min(Integer.parseInt(arr[0]), Integer.parseInt(arr[2])); x < Math.max(Integer.parseInt(arr[0]), Integer.parseInt(arr[2])) + 1; x++) {
                            array[x][y].addLine();
                        System.out.println(array[x][y]);
                        System.out.println(x);
                        System.out.println(y);
                        y += increment;
                    }//820,46 -> 25,841
                }
            }
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }

    int getSolution()
    {
        Position[][] area = new Position[1000][1000];
        for(int i = 0; i < 1000; i++)
            for(int j = 0; j < 1000; j++)
                area[i][j] = new Position();
        if (!getFileInput("src/data.txt", area))
        {
            System.out.println("Error");
            return 0;
        }
        int result = 0;
        for(int i = 0; i < 1000; i++)
            for(int j = 0; j < 1000; j++)
                result += (area[i][j].isCoveredBy(2) ? 1 : 0);
        return result;
    }

    public static void main(String[] args)
    {
        Solver solver = new Solver();
        System.out.println(solver.getSolution());
    }
}
