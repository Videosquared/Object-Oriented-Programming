import java.util.*;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.ArrayList;


public class Test {
    public static void main(String[] args) {

        String[] a = {"123", "a", "B2", "A", "B3", "5"};


        List<String> b = new ArrayList<>(Arrays.asList(a));
        List<String> x = new ArrayList<>();
        System.out.println(b);
        System.out.println(x.size());


        List<String> newList = b.stream().distinct().collect(Collectors.toList());
        System.out.println(newList);
        newList.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println(newList);





    }
}

/*


        HashMap<Character, String> data = new HashMap<>();

        data.put('A', "test1");
        data.put('A', "test2");

        for (Map.Entry<Character, String> entry : data) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }



        String a = "hello world how egsoijf sjfsodnfsdbf sf";
        String[] b = a.split(" ", 2);
        boolean c = b.length == 2 && !b[1].isEmpty();
        System.out.println(c);

        ArrayList<String> names = new ArrayList<>();
        names.add("String1");
        names.add("String2");
        names.add("String3");

        String INDICATOR = "String2";

        Iterator<String> nameIter = names.iterator();
        while (nameIter.hasNext()) {
            String name = nameIter.next();
            if (name.contains(INDICATOR)) {
                nameIter.remove();
            }
        }

        System.out.println(names);

    String a = "Hello World";
    System.out.println(Arrays.toString(a.split("-")));

    String title = "bob";
    String[] authors = {"bob", "john", "retard"};
    float rating = (float)12.10;
    String isbn = "12546278468234";
    int pages = 1000;


    StringBuilder output = new StringBuilder();

        output.append(title).append("\nby ");
                for (String author : authors) {
                output.append(author).append(", ");
                }
                output.deleteCharAt(output.length() - 1).deleteCharAt(output.length() - 1).append("\n");
                output.append("Rating: ").append(String.format("%.2f",rating)).append("\n");
                output.append("ISBN: ").append(isbn).append("\n");
                output.append(pages).append(" pages");


                System.out.println( output.toString());
*/
