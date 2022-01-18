import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterato
import java.util.stream.Stream;

public class Solver {
    public class BingoField
    {
        BingoField(){
            value = 0;
            state = false;
            win = false;
        }
        private int value;
        private boolean state;
        private boolean win;
        void markedValue(int value){
            if(!state)
            {
                if (this.value == value)
                    state = true;
            }
        }

        void resetState()
        {
            this.state = false;
        }

        void setWin()
        {
            this.win = true;
        }

        void insertValue(int value)
        {
            this.value = value;
        }
        boolean isWin()
        {
            return this.win;
        }

        boolean isMarkedValue() {
            return this.state;
        }

        int getValue()
        {
            return this.value;
        }
    }
    public int getSolution()
    {
        int last = 0;
        int lastnum = 0;
        int lengthOfArray = 100;
        BingoField[][][] arrayOfNumbers = new BingoField[lengthOfArray][5][5];
        for (int i = 0; i < lengthOfArray; ++i)
            for(int j = 0; j < 5; ++j)
                for(int k = 0; k < 5; ++k)
                    arrayOfNumbers[i][j][k] = new BingoField();
        int [] numbers = new int[0];
        int position = 0;
        int iter = -1;
        String coma = ",";
        try(Stream<String> lines = Files.lines(Paths.get("src/data.txt")))
        {
            int row = 0;
            for (Iterator<String> it = lines.iterator(); it.hasNext(); ) {
                String temp = it.next();
                if (temp.isEmpty()) {
                    row = 0;
                    iter++;
                } else if (temp.contains(coma)) {
                    String[] arr = temp.split("(([, ])+)");
                    numbers = new int[arr.length];
                    for(String tmp : arr)
                    {
                        numbers[position] = Integer.parseInt(tmp);
                        position++;
                    }
                } else {
                    String[] arr = temp.split("(([, ])+)");
                    int column = 0;
                    for(String tmp : arr)
                    {
                        if(!tmp.isEmpty())
                        {
                            arrayOfNumbers [iter][row][column].insertValue(Integer.parseInt(tmp));
                            column++;
                        }
                    }
                    row++;
                }
            }
        } catch (IOException e) {
            //error happened
        }
        for (int elem : numbers)
            for (int i = 0; i < lengthOfArray; i++)
            {
                if (arrayOfNumbers[i][0][0].getValue() == 0 && arrayOfNumbers[i][0][1].getValue() == 0)
                    break;
                for(int j = 0; j < 5; j++)
                    for(int k = 0; k < 5; k++)
                        arrayOfNumbers[i][j][k].markedValue(elem);
                if(!arrayOfNumbers[i][0][0].isWin()) {
                    //horizontal
                    int counter;
                    for (int j = 0; j < 5; j++) {
                        counter = 0;
                        for (int k = 0; k < 5; k++)
                            if (arrayOfNumbers[i][j][k].isMarkedValue()) {
                                counter++;
                                if (counter == 5) {
                                    int result = 0;
                                    for (int a = 0; a < 5; a++)
                                        for (int b = 0; b < 5; b++) {
                                            if (!arrayOfNumbers[i][a][b].isMarkedValue())
                                                result += arrayOfNumbers[i][a][b].getValue();
                                        }
                                    arrayOfNumbers[i][0][0].setWin();
                                    //return elem * result;
                                    last = i;
                                    lastnum = elem;
                                }
                            }
                    }
                    //vertical
                    for (int k = 0; k < 5; k++) {
                        counter = 0;
                        for (int j = 0; j < 5; j++)
                            if (arrayOfNumbers[i][j][k].isMarkedValue()) {
                                counter++;
                                if (counter == 5) {
                                    int result = 0;
                                    for (int a = 0; a < 5; a++)
                                        for (int b = 0; b < 5; b++) {
                                            if (!arrayOfNumbers[i][a][b].isMarkedValue())
                                                result += arrayOfNumbers[i][a][b].getValue();
                                        }
                                    //return elem * result;
                                    arrayOfNumbers[i][0][0].setWin();
                                    last = i;
                                    lastnum = elem;
                                }
                            }
                    }
                }
            }
        System.out.println(last);
        System.out.println(lastnum);
        for (int i = 0; i < lengthOfArray; ++i)
            for(int j = 0; j < 5; ++j)
                for(int k = 0; k < 5; ++k)
                    arrayOfNumbers[i][j][k].resetState();

        for (int elem : numbers) {
            for (int i = 0; i < last + 1; i++) {
                for (int j = 0; j < 5; j++)
                    for (int k = 0; k < 5; k++)
                        arrayOfNumbers[i][j][k].markedValue(elem);
            }
            if (elem == lastnum) break;
        }
        int result = 0;
        for(int a = 0; a < 5; a++)
            for(int b = 0; b < 5; b++){
                if(!arrayOfNumbers[last][a][b].isMarkedValue())
                    result += arrayOfNumbers[last][a][b].getValue();
            }
        return result * lastnum;
    }

    public static void main(String[] args){
        Solver solver = new Solver();
        System.out.println(solver.getSolution());
    }
}
