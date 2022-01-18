
import java.util.Scanner;
import java.io.*;

public class First
{
    public static void main(String[] args)
    {
      calculate obj = new calculate();
      System.out.println(obj.calculateResult(obj.readFile("temp-ex1.txt"));
    }
}    

class calculate
{
    int calculateResult(int[] array)
    {
      int counter = 0;
      int current = Integer.MAX_VALUE;
      for(int temp : array)
      {
        if(current < temp)
        {
          counter += 1;
        }
        current = temp;
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
