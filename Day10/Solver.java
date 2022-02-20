import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class Solver {

    public int getResult()
    {
        Stack<Character> queue;
        List<String> list = getFile("src/data.txt");
        int result = 0;
        for(String line : list)
        {
            queue = new Stack<>();
            for(int i = 0; i < line.length(); i++)
            {
                if (line.charAt(i) == '(' || line.charAt(i) == '{' ||  line.charAt(i) == '<' ||  line.charAt(i) == '[')
                    queue.push(line.charAt(i));
                else if(line.charAt(i) == ')')
                {
                    if(queue.pop() != '(') {
                        result += 3;
                        break;
                    }
                }
                else if(line.charAt(i) == '}')
                {
                    if(queue.pop() != '{'){
                        result += 1197;
                        break;
                    }
                }
                else if(line.charAt(i) == '>')
                {
                    if(queue.pop() != '<') {
                        result += 25137;
                        break;
                    }
                }
                else if(line.charAt(i) == ']')
                {
                    if(queue.pop() != '[') {
                        result += 57;
                        break;
                    }
                }
            }

        }
        return result;
    }

    public long getSecondResult()
    {
        Stack<Character> queue;
        List<Long> scores = new ArrayList<>();
        List<String> list = getFile("src/data.txt");
        for(String line : list)
        {
            long result = 0;
            queue = new Stack<>();
            for(int i = 0; i < line.length(); i++)
            {
                if (line.charAt(i) == '(' || line.charAt(i) == '{' ||  line.charAt(i) == '<' ||  line.charAt(i) == '[')
                    queue.push(line.charAt(i));
                else if(line.charAt(i) == ')')
                {
                    if(queue.pop() != '(') {
                        queue.clear();
                        break;
                    }
                }
                else if(line.charAt(i) == '}')
                {
                    if(queue.pop() != '{'){
                        queue.clear();
                        break;
                    }
                }
                else if(line.charAt(i) == '>')
                {
                    if(queue.pop() != '<') {
                        queue.clear();
                        break;
                    }
                }
                else if(line.charAt(i) == ']')
                {
                    if(queue.pop() != '[') {
                        queue.clear();
                        break;
                    }
                }
            }
            if(!queue.empty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    char temp = queue.pop();
                    result = result * 5;
                    if (temp == '(')      result += 1;
                    else if (temp == '[') result += 2;
                    else if (temp == '{') result += 3;
                    else if (temp == '<') result += 4;
                }
                scores.add(result);
            }
        }
        Collections.sort(scores);
        return scores.get(scores.size() / 2);
    }

    public List<String> getFile(String path)
    {
        List<String> list = null;
        try{
            list = Files.readAllLines(Path.of(path));
        }catch(IOException e){
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args)
    {
        Solver solver = new Solver();
        System.out.println(solver.getResult());
        System.out.println(solver.getSecondResult());
    }
}
