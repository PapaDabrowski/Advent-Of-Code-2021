import java.util.List;
public class Second {
    public static void main(String[] args)
    {
        GetFile file = new GetFile();
        List<String> input = file.getListFromFile("input.txt");
        Result result = new Result();
        System.out.println(result.getResult(input));
    }
}
