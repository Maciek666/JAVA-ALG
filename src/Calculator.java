import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
task from codewars.com
3kyu
Create a simple calculator that given a string of operators (), +, -, *, / and numbers separated by spaces returns the value of that expression

        Example:

    Calculator.evaluate("2 / 2 + 3 * 4 - 6") // => 7
    Remember about the order of operations! Multiplications and divisions have a higher priority and should be performed left-to-right. Additions and subtractions have a lower priority and should also be performed left-to-right.
*/
public class Calculator {
    public static Double evaluate(String expression) {
        List<String> elements = new ArrayList<String>();
        elements.addAll(Arrays.asList(expression.split(" ")));
        // dzielnie mnozenie
        //rm - licznik usuniec
        if (elements.size() == 1) return Double.valueOf(elements.get(0));
        int rm = 0;
        for (int i = 1; i < elements.size() - rm * 2; i += 2) {
            if (elements.get(i).equals("/")) {
                elements.set(i - 1, String.valueOf(Double.valueOf(elements.get(i - 1)) / Double.valueOf(elements.get(i + 1))));
                elements.remove(i);
                elements.remove(i);
                i-=2;

            } else if (elements.get(i).equals("*")) {
                elements.set(i - 1, String.valueOf(Double.valueOf(elements.get(i - 1)) * Double.valueOf(elements.get(i + 1))));
                elements.remove(i);
                elements.remove(i);
                i-=2;
            }
        }
        rm = 0;
        for (int i = 1; i < elements.size() - rm * 2; i += 2) {
            if (elements.get(i).equals("+")) {
                elements.set(0, String.valueOf(Double.valueOf(elements.get(0)) + Double.valueOf(elements.get(i + 1))));
            } else if (elements.get(i).equals("-")) {
                elements.set(0, String.valueOf(Double.valueOf(elements.get(0)) - Double.valueOf(elements.get(i + 1))));
            }
        }

        return Double.valueOf(elements.get(0));

    }
    
}
