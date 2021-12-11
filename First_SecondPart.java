
import java.util.Scanner;
import java.io.*;

public class First_SecondPart
{
    public static void main(String[] args)
    {
      calculate obj = new calculate();
      System.out.println(obj.calculateResult(obj.readFile("temp-ex1.txt"));
    }
}    

class calculate
{
    //In pretty way I will add a list to use a method to handle this in "pretty" way.
    int calculateResult(int[] array)
    {
        int current = Integer.MAX_VALUE;
        int counter = 0;
        int[] tempArray = new int[array.length];
        tempArray[0] = array[0];
        tempArray[0] += array[1];
        tempArray[1] = array[1];
        for(int i = 2; i < array.length; ++i)
        {
            tempArray[i] += array[i];
            tempArray[i - 1] += array[i];
            tempArray[i - 2] += array[i];
            if(current < tempArray[i - 2])
            {
                counter += 1;
            }
            current = tempArray[i - 2];
        }
        return counter;
    }

    int[] readFile(String filePath)
    {
      int[] array = new int[100];
      Scanner sc = new Scanner(new BufferedReader(new FileReader(new File(filePath))));
      for(int i = 0; sc.hasNextLine(); i++) 
      {
        array[i]=Integer.parseInt(sc.nextLine());
      }
      return array;
    }
}
