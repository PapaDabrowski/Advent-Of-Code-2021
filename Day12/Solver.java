import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class Solver {
    // In this ex At first part I was inspired by another developer's solution.
    // I did it in recursive method, but it wasn't fortunate. That's why I decided to inspire from internet a little.
    // I'm publishing solution on my github, because I implemented my interpretation and I'm also fully understand solution
    public int getResult(){
        int result = 0;
        List<String> input = getFile("src/data.txt");
        Map<String, List<String>> edges = new HashMap<>();

        for(String line : input)
        {
            String[] output = line.split("-");
            if(!edges.containsKey(output[0]))
                edges.put(output[0], new ArrayList<>());
            edges.get(output[0]).add(output[1]);
            if(!edges.containsKey(output[1]))
                edges.put(output[1], new ArrayList<>());
            edges.get(output[1]).add(output[0]);
        }

        Stack<String> first = new Stack<>();
        first.push("start");

        Stack<Stack<String>> todo = new Stack<>();
        todo.push(first);

        while(!todo.empty())
        {
            Stack<String> path = new Stack<>();
            path.addAll(todo.pop());
            if(path.lastElement().equals("end"))
            {
                result += 1;
                continue;
            }

            for(String val : edges.get(path.lastElement()))
            {
                if(val.equals("start")){
                    continue;
                }
                else if(Character.isUpperCase(val.charAt(0)) || !path.contains(val)) {
                    Stack<String> lastelem = new Stack<>();
                    lastelem.addAll(path);
                    lastelem.add(val);
                    todo.add(lastelem);
                }
                else if(Character.isLowerCase(val.charAt(0)) && !smallCaveOccurrences(path, val) && !val.equals("end"))
                {
                    Stack<String> lastelem = new Stack<>();
                    lastelem.addAll(path);
                    lastelem.add(val);
                    todo.add(lastelem);
                }
            }
        }
        return result;
    }

    public boolean smallCaveOccurrences(Stack<String> temp, String val)
    {
        for (int i = 0; i < temp.size(); i++) {
            for (int j = i + 1 ; j < temp.size(); j++) {
                if (temp.get(i).equals(temp.get(j)) && Character.isLowerCase(temp.get(i).charAt(0))) {
                    return true;
                }
            }
        }
        return false;
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

    public static void main(String[] args){
        Solver solver = new Solver();
        System.out.println(solver.getResult());
    }
}
