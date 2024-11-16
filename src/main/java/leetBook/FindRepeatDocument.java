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

    public int findRepeatDocument2(int[] documents) {
        int i = 0;
        while (i < documents.length) {
            if (documents[i] == i) {
                i++;
                continue;
            }

            if (documents[documents[i]] == documents[i]) return documents[i];

            int temp = documents[i];
            documents[i] = documents[temp];
            documents[temp] = temp;
        }

        return -1;
    }

    public static void main(String[] args) {
        FindRepeatDocument f = new FindRepeatDocument();
        int[] documents = new int[]{3, 4, 2, 1, 1, 0};
        System.out.println(f.findRepeatDocument2(documents));
    }
}
