package leetBook;

import java.util.HashSet;
import java.util.Set;

public class FindRepeatDocument {
    public int findRepeatDocument(int[] documents) {
        Set<Integer> set = new HashSet<>();

        for (int document : documents) {
            if (set.contains(document)) return document;
            set.add(document);
        }

        return -1;
    }
}
