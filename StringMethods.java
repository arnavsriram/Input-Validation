import java.util.*;
public class StringMethods {
    Scanner console = new Scanner(System.in);
    public StringMethods() {
    }
    public void unusualHello(String name) {
        System.out.println("Hello " + name + ", you dummy!");
    }
    public String stringRipper(String str) {
        return str.substring(0,1)+str.substring(str.length()-1);
    }
    public boolean evenFooBar(String s) {
        String foo = "foo";
        String bar = "bar";
        int numFoo = 0;
        int numBar = 0;
        boolean equal = false;
        for (int i = 0; i <= s.length()-foo.length(); i++) {
            if (s.substring(i, i+foo.length()).equals(foo)) {
                numFoo++;
            }
        }
        for (int i = 0; i <= s.length()-bar.length(); i++) {
            if (s.substring(i, i + bar.length()).equals(bar)) {
                numBar++;
            }
        }
        if (numFoo == numBar) {
            equal = true;
        }
        return equal;

    }
    public int sumString(String str) {
        int sum = 0;
        String[] s = str.split("");
        for (int i = 0; i < s.length; i++) {
            int x = Integer.parseInt(s[i]);
            if (console.hasNextInt(x)) {
                sum+=x;
            }
        }
        return sum;

    }
    public String decode(String key, String code) {
        Scanner chopper = new Scanner(code);
        String hCode = "";
        int place = 0;
        while (chopper.hasNext()) {
            if (chopper.hasNextInt()) {
                place = chopper.nextInt();
                hCode += key.substring(place, place+1);
            }
        }
        return hCode;
    }

}
