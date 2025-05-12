import java.util.*;

public class TrieNode {
    Map<Character, TrieNode> children=new HashMap<>();
    List<Book> books=new ArrayList<>();
    boolean isEndOfWord;
}
