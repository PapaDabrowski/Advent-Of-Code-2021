import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Solver {

    private int [][] inputArray;

    public int getResult(){
        List<String> temp = getFileInput("src/data.txt");
        inputArray = new int[temp.size()][temp.get(0).length()];
        int counter = 0;
        for (String line : temp) {
            for (int i = 0; i < temp.get(0).length(); i++) {
                inputArray[counter][i] = Integer.parseInt(String.valueOf(line.charAt(i)));
            }
            counter++;
        }
        int result = 0;
        for(int i = 0; i < temp.size(); i++)
            for(int j = 0; j < temp.get(0).length(); j++)
            {
                boolean left, right, up, down;

                if(i + 1 == temp.size()) right = true;
                else right = inputArray[i + 1][j] > inputArray[i][j];

                if (i == 0) left = true;
                else left = inputArray[i - 1][j] > inputArray[i][j];

                if (j + 1 == temp.get(0).length()) down = true;
                else down = inputArray[i][j + 1] > inputArray[i][j];

                if (j == 0) up = true;
                else up = inputArray[i][j - 1] > inputArray[i][j];

                if(left && right && up && down) result += (inputArray[i][j] + 1);
            }

        return result;
    }

    public int getResultSecondTask()
    {
        List<String> temp = getFileInput("src/data.txt");
        inputArray = new int[temp.size()][temp.get(0).length()];
        int counter = 0;
        for (String line : temp) {
            for (int i = 0; i < temp.get(0).length(); i++) {
                inputArray[i][counter] = Integer.parseInt(String.valueOf(line.charAt(i)));
            }
            counter++;
        }

        int result = 1;
        List<Integer> allResults = new ArrayList<Integer>();
        for(int i = 0; i < temp.get(0).length(); i++)
            for(int j = 0; j < temp.size(); j++)
            {
                int surface = 0;
                if(inputArray[j][i] < 9) {
                    surface = crawlingAlghorithm(j, i);
                    System.out.println(surface);
                    allResults.add(surface);
                }
            }
        for(int i = 0; i < 3; i++)
        {
            int max = 0;
            for (int value : allResults)
                if(value > max)
                    max = value;
            result *= max;
            System.out.println(max);
            allResults.remove(allResults.indexOf(max));
        }

        return result;
    }

    private int crawlingAlghorithm(int column, int row)
    {
        if (inputArray[column][row] < 9)
        {
            int result = 1;
            inputArray[column][row] = 10;
            if(row != 99) result += crawlingAlghorithm(column, row + 1);
            if(row != 0) result += crawlingAlghorithm(column, row - 1);
            if(column != 0) result += crawlingAlghorithm(column - 1, row);
            if(column != 99) result += crawlingAlghorithm(column + 1, row);
            return result;
        }
        else
        {
            return 0;
        }
    }

    private List<String> getFileInput(String path)
    {
        List<String> list = null;
        try {
            list = Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args)
    {
        Solver solver = new Solver();
        System.out.println(solver.getResult());
        System.out.println(solver.getResultSecondTask());
    }
}
