import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Solver {

    public int getResult(int steps)
    {
        int result = 0;
        List<String> input = getFile("src/data.txt");
        int [][] array = new int[input.size()][input.get(0).length()];

        for(int row = 0; row < input.size(); row++)
            for (int column = 0; column < input.get(0).length(); column++){
                array[row][column] = Integer.parseInt(String.valueOf(input.get(row).charAt(column)));
            }

        for(int step = 0; step < steps; step++) {
            for (int row = 0; row < input.size(); row++)
                for (int column = 0; column < input.get(0).length(); column++)
                    array[row][column]++;

            boolean flashed = true;
            while (flashed) {
                flashed = false;
                for (int row = 0; row < input.size(); row++)
                    for (int column = 0; column < input.get(0).length(); column++)
                    {
                        if(array[row][column] > 9) {
                            flashed = true;
                            array[row][column] = 0;
                            result++;
                            increase(row - 1, column, array);
                            increase(row - 1, column + 1, array);
                            increase(row, column + 1, array);
                            increase(row + 1, column + 1, array);
                            increase(row + 1, column, array);
                            increase(row + 1, column - 1, array);
                            increase(row, column - 1, array);
                            increase(row - 1, column - 1, array);
                        }
                    }
            }
        }
        return result;
    }

    public int getSecondTask(int steps)
    {
        int result = 0;
        List<String> input = getFile("src/data.txt");
        int [][] array = new int[input.size()][input.get(0).length()];

        for(int row = 0; row < input.size(); row++)
            for (int column = 0; column < input.get(0).length(); column++){
                array[row][column] = Integer.parseInt(String.valueOf(input.get(row).charAt(column)));
            }

        for(int step = 0; step < steps; step++) {
            for (int row = 0; row < input.size(); row++)
                for (int column = 0; column < input.get(0).length(); column++)
                    array[row][column]++;
            int placeholder = result;

            boolean flashed = true;
            while (flashed) {
                flashed = false;
                for (int row = 0; row < input.size(); row++)
                    for (int column = 0; column < input.get(0).length(); column++)
                    {
                        if(array[row][column] > 9) {
                            flashed = true;
                            array[row][column] = 0;
                            result++;
                            //clockwise
                            increase(row - 1, column, array);
                            increase(row - 1, column + 1, array);
                            increase(row, column + 1, array);
                            increase(row + 1, column + 1, array);
                            increase(row + 1, column, array);
                            increase(row + 1, column - 1, array);
                            increase(row, column - 1, array);
                            increase(row - 1, column - 1, array);
                        }
                    }
            }
            if(result - placeholder == 100) return step + 1;
        }
        return -1;
    }

    private void increase(int row, int column, int[][] array)
    {
        if(row >= 0 && row < array.length && column >= 0 && column < array[0].length &&  array[row][column] != 0)
            array[row][column]++;
    }

    public List<String> getFile(String path)
    {
        List<String> list = null;
        try{
            list = Files.readAllLines(Path.of(path));
        }catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String [] args)
    {
        Solver solver = new Solver();
        System.out.println(solver.getResult(100));
        System.out.println("Step: " + solver.getSecondTask(1000)); //example value 1000
    }
}
