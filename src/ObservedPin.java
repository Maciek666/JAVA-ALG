import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ObservedPin {
    public static final List<List<String>> negibourList = new ArrayList<List<String>>() {
        {
            add(Arrays.asList("0", "8"));
            add(Arrays.asList("1", "2", "4"));
            add(Arrays.asList("1", "2", "3", "5"));
            add(Arrays.asList("2", "3", "6"));
            add(Arrays.asList("1", "4", "5", "7"));
            add(Arrays.asList("2", "4", "5", "6", "8"));
            add(Arrays.asList("3", "5", "6", "9"));
            add(Arrays.asList("4", "7", "8"));
            add(Arrays.asList("0", "5", "7", "8", "9"));
            add(Arrays.asList("6", "8", "9"));
        }
    };

    public static void GeneratePermutations(List<List<String>> Lists, List<String> result, int depth, String current) {
        if (depth == Lists.size()) {
            result.add(current);
            return;
        }

        for (int i = 0; i < Lists.get(depth).size(); ++i) {
            GeneratePermutations(Lists, result, depth + 1, current + Lists.get(depth).get(i));
        }
    }

    public static List<String> getPINs(String observed) {
        List<String> pins = new ArrayList<>();
        List<List<String>> comb = Arrays
                .stream(observed.split(""))
                .mapToInt(Integer::parseInt)
                .mapToObj(negibourList::get)
                .collect(Collectors.toList());

        GeneratePermutations(comb,pins,0,"");
        return pins;
    }

    public static void main(String[] args) {
        getPINs("12");
    }
}
