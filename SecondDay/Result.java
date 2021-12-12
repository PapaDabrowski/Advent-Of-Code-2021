import java.util.List;

public class Result {
    public int getResult(List<String> temp)
    {
        int horizontal = 0;
        int depht = 0;
        int aim = 0;
        for(String elem : temp)
        {
            if (elem.contains(forward)) {
                horizontal += Integer.parseInt(elem.replaceAll("\\D+", ""));
                if (aim != 0)
                    depht += (Integer.parseInt(elem.replaceAll("\\D+", "")) * aim);
            }
            if (elem.contains(up))
                aim -= Integer.parseInt(elem.replaceAll("\\D+",""));
            if (elem.contains(down))
                aim +=  Integer.parseInt(elem.replaceAll("\\D+",""));
        }
        return horizontal * depht;
    }
    private String forward = "forward";
    private String up = "up";
    private String down = "down";
}
