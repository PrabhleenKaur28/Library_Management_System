import java.util.*;
class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Insert book title into Trie
    public void insert(String title, Book book) {
        TrieNode node = root;
        for (char ch : title.toLowerCase().toCharArray()) {
            node = node.children.computeIfAbsent(ch, c -> new TrieNode());
        }
        node.isEndOfWord = true;
        node.books.add(book);
    }

    // Search all books starting with prefix
    public List<Book> searchByPrefix(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toLowerCase().toCharArray()) {
            node = node.children.get(ch);
            if (node == null) return new ArrayList<>();
        }
        return collectBooks(node);
    }

    private List<Book> collectBooks(TrieNode node) {
        List<Book> result = new ArrayList<>(node.books);
        for (TrieNode child : node.children.values()) {
            result.addAll(collectBooks(child));
        }
        return result;
    }
}
