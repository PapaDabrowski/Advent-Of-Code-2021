import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Solver {
    public int getResult(List<String> list)
    {
        int[] array = new int[list.get(0).length()];
        String result = "";

        for(String elem : list)
            for (int i = 0; i < elem.length(); ++i) {
                if (elem.charAt(i) == '1') {
                    array[i]++;
                } else {
                    array[i]--;
                }
            }

        for (int elem : array) {
            if (elem >= 0) {
                result += "1";
            } else {
                result += "0";
            }
        }
        int left = Integer.parseInt("0" + result,2);
        // I have really no idea how to make byte negation with
        //System.out.println(Integer.toBinaryString(~temp));
        //Java negation of 001100001011 is 11111111111111111111110011110100 .. wth?
        result = "";
        for (int elem : array) {
            if (elem >= 0) {
                result += "0";
            } else {
                result += "1";
            }
        }
        int right = Integer.parseInt("0" + result,2);
        return left * right;
    }

    int getOxygenResult(List<String> list)
    {
        int[] array = new int[list.get(0).length()];
        array[0] = -1;
        int index = 0;

        for(int position = 0; position < list.get(0).length(); position++)
        {
            for (String elem : list)
                for (int i = 0; i < position; i++) {
                    if (elem.charAt(i) != Integer.toString(Integer.parseInt(array[i] >= 0 ? "1" : "0")).charAt(0)) {
                        break;
                    }
                    if (i + 1 == position) {
                        index = list.indexOf(elem);
                        if (elem.charAt(position) == '1') {
                            array[position]++;
                        } else {
                            array[position]--;
                        }
                        break;
                    }
                }
        }
        System.out.println(Arrays.toString(array));
        System.out.println(list.get(index));
        return Integer.parseInt("0" + list.get(index), 2);
    }

    int getC02Result(List<String> list)
    {
        int[] array = new int[list.get(0).length()];
        array[0] = -1;
        int index = 0;

        for(int position = 0; position < list.get(0).length(); position++)
        {
            for (String elem : list)
                for (int i = 0; i < position; i++) {
                    if (elem.charAt(i) != Integer.toString(Integer.parseInt(array[i] >= 0 ? "0" : "1")).charAt(0)) {
                        break;
                    }
                    if (i + 1 == position) {
                        index = list.indexOf(elem);
                        if (elem.charAt(position) == '1') {
                            array[position]++;
                        } else {
                            array[position]--;
                        }
                        break;
                    }
                }
        }
        System.out.println(Arrays.toString(array));
        System.out.println(list.get(index));
        return Integer.parseInt("0" + list.get(index), 2);
    }


    public static void main(String[] args)
    {
        Solver solver = new Solver();
        try {
            System.out.println(solver.getResult(Files.readAllLines(Path.of("src/data.txt"))));
            System.out.println(solver.getOxygenResult(Files.readAllLines(Path.of("src/data.txt"))) * solver.getC02Result(Files.readAllLines(Path.of("src/data.txt"))));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        int temp = 144;
        System.out.println(Integer.toBinaryString(temp));
    }
}
