package DirectionReduction;

import java.util.*;

public class DirReduction {

    public static String[] dirReduc(String[] arr) {
        LinkedList<String> reducedDirs = dirReducHelper(new LinkedList<>(Arrays.asList(arr)));
        return reducedDirs.toArray(new String[0]);
    }

    private static LinkedList<String> dirReducHelper(LinkedList<String> dirs){
        for(String s : dirs) System.out.println(s);
        System.out.println();
        for(int i = 0; i < dirs.size() - 1; ++i){
            if(dirs.get(i).equals(oppositeDir(dirs.get(i + 1)))){
                dirs.remove(i);
                dirs.remove(i);
                dirReducHelper(dirs);
            }
        }
        return dirs;
    }

    private static String oppositeDir(String dir){
        switch(dir){
            case "NORTH": return "SOUTH";
            case "SOUTH": return "NORTH";
            case "EAST": return "WEST";
            case "WEST": return "EAST";
            default: throw new RuntimeException("Invalid Direction!");
        }
    }

}