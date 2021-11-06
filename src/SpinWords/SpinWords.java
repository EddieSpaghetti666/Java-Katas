package SpinWords;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

class SpinWords {

    String spinWords(String sentence) {
        String[] words = sentence.split(" ");
        return Arrays.stream(words)
                .map(s -> s.length() >= 5 ? new StringBuilder(s).reverse() : s)
                .collect(Collectors.joining(" "));
    }
}