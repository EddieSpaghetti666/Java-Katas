package Scramblies;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Scramblies {

    public static boolean scramble(String str1, String str2) {
        if(str1.length() < str2.length()) return false;
        else {
            for(char c: str2.toCharArray()){
                if(!str1.contains(""+c)) return false;
                else str1 = str1.replaceFirst(""+c, "");
            }
            return true;
        }
    }
}