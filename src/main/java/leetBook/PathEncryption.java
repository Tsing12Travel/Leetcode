package leetBook;

public class PathEncryption {
    public String pathEncryption(String path) {
        StringBuilder res = new StringBuilder();

        for (Character c : path.toCharArray()) {
            if (c == '.') res.append(' ');
            else res.append(c);
        }

        return res.toString();
    }
}
