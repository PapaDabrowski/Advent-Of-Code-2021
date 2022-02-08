import com.sun.security.jgss.GSSUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;



/*
  Display:
     aaaa       aaaa
   b      c   b      c
   b      c   b      c
     dddd       dddd
   e      f   e      f
   e      f   e      f
     gggg       gggg

 */

//dcga cadgbfe gecba cbfde eda cdbea gbadfe fegcba bedgca da | bgefdac bdace ad agcd

public class Solver {
    private static final int INDEX_NOT_FOUND = 0;

    public int decoder(String code, String toDecode)
    {
            if (toDecode.length() == 2)
                return 1;
            else if (toDecode.length() == 3)
                return 7;
            else if (toDecode.length() == 4)
                return 4;
            else if (toDecode.length() == 7)
                return 8;
            else if (toDecode.length() == 5) {
                if(toDecode.contains(String.valueOf(code.charAt(4))))
                    return 2;
                else if(toDecode.contains(String.valueOf(code.charAt(1))))
                    return 5;
                else if (toDecode.contains(String.valueOf(code.charAt(2))) && toDecode.contains(String.valueOf(code.charAt(5))))
                    return 3;
            }
            else if (toDecode.length() == 6) {
                System.out.println("siema");
                System.out.println(String.valueOf(code.charAt(3)));
                if(!toDecode.contains(String.valueOf(code.charAt(3))))
                    return 0;
                else if(toDecode.contains(String.valueOf(code.charAt(2))) && toDecode.contains(String.valueOf(code.charAt(3))))
                    return 9;
                else if (toDecode.contains(String.valueOf(code.charAt(4))) &&  toDecode.contains(String.valueOf(code.charAt(3))))
                    return 6;
            }
        System.out.println(toDecode);
        System.out.println(toDecode.length());
            return 0;
    }


    public List<String> getFile(String path) {
        List<String> list = null;
        try {
            list = Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    int getResult()
    {
        List<String> list = getFile("src/data.txt");
        int result = 0;
        for(String temp : list){
            int tmpResult = 0;
            int counter = 0;
            String code = solvePositions(temp);
            for (String word : temp.split(" ")) {
                if(counter > 10)
                {
                    tmpResult += decoder(code, word) * counterToPostion(counter);
                    System.out.println(temp);
                }
                counter++;
            }
            System.out.println(tmpResult);
            System.out.println(code);
            result += tmpResult;
        }
        return result;
    }

    private String solvePositions(String line)
    {
        String[] array = null;
        StringBuilder code = new StringBuilder("xxxxxxx");
        array = line.split(" ");
        String one = "", four = "", seven = "", eight = "", nine = "";

        //assign certain values
        for (int i = 0; i < array.length - 5; i++) {
            if(array[i].length() == 2)
                one = array[i];
            if(array[i].length() == 3)
                seven = array[i];
            if(array[i].length() == 4)
                four = array[i];
            if(array[i].length() == 7)
                eight = array[i];
        }
        //1 -> 7 = A
        code.setCharAt(0, diffLetters(one, seven).charAt(0));
        String tempChars = four + code.charAt(0);
        char[] chars = tempChars.toCharArray();
        Arrays.sort(chars);
        String sevenPlusFour = new String(chars);
        //8 -> 6 = C, F Certain
        //8 -> 0 = B, D Certain
        for(int i = 0; i < array.length - 5; i++)
        {
            if(array[i].length() == eight.length() - 1){
                if(diffLetters(eight, array[i]).charAt(0) == one.charAt(0))
                {
                    code.setCharAt(2,one.charAt(0));
                    code.setCharAt(5,one.charAt(1));
                }
                else if (diffLetters(eight, array[i]).charAt(0) == one.charAt(1))
                {
                    code.setCharAt(2,one.charAt(1));
                    code.setCharAt(5,one.charAt(0));
                }
                if (diffLetters(eight, array[i]).charAt(0) == diffLetters(four, one).charAt(0))
                {
                    code.setCharAt(3,diffLetters(four, one).charAt(0));
                    code.setCharAt(1,diffLetters(four, one).charAt(1));
                }
                else if (diffLetters(eight, array[i]).charAt(0) == diffLetters(four, one).charAt(1))
                {
                    code.setCharAt(3,diffLetters(four, one).charAt(1));
                    code.setCharAt(1,diffLetters(four, one).charAt(0));
                }
            }
            //4 -> 9 = G
            if(diffLetters(array[i], sevenPlusFour).length() == 1 && array[i].length() == sevenPlusFour.length() + 1)
            {
                code.setCharAt(6,diffLetters(sevenPlusFour, array[i]).charAt(0));
                nine = array[i];
            }
        }
        code.setCharAt(4,diffLetters(nine, eight).charAt(0));
        return code.toString();
    }

    private int counterToPostion(int counter) {
        if (counter == 11)
            return 1000;
        if (counter == 12)
            return 100;
        if (counter == 13)
            return 10;
        return 1;
    }

    private String diffLetters(String A, String B)
    {
        String temp = A;
        String tempLonger = B;
        if(A.length() > B.length()) {
            temp = B;
            tempLonger = A;
        }

        for (int i = 0; i < temp.length(); i++)
        {
            tempLonger = tempLonger.replace(String.valueOf(temp.charAt(i)), "");
        }
        return tempLonger;
    }

    public static void main(String [] args){
        Solver solver = new Solver();
        System.out.println(solver.getResult());
    }
}
