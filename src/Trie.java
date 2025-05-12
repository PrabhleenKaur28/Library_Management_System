import java.util.*;
public class Trie {
    private TrieNode root = new TrieNode();

    public void insert(String title, Book book) {
        TrieNode node = root;
        for (char c : title.toLowerCase().toCharArray()) {
            node = node.children.computeIfAbsent(c, k -> new TrieNode());
        }
        node.isEndOfWord = true;
        node.books.add(book);
    }

    public void delete(String title, Book book) {
        deleteHelper(root, title.toLowerCase(), 0, book);
    }

    private boolean deleteHelper(TrieNode node, String title, int index, Book book) {
        if (index == title.length()) {
            if (!node.isEndOfWord) return false;
            node.books.remove(book);
            // Clean up if needed
            if (node.books.isEmpty()) node.isEndOfWord = false;
            return node.children.isEmpty() && !node.isEndOfWord;
        }

        char ch = title.charAt(index);
        TrieNode child = node.children.get(ch);
        if (child == null) return false;

        boolean shouldDeleteChild = deleteHelper(child, title, index + 1, book);

        if (shouldDeleteChild) {
            node.children.remove(ch);
            return node.children.isEmpty() && !node.isEndOfWord;
        }
        return false;
    }

    public List<Book> searchByPrefix(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toLowerCase().toCharArray()) {
            node = node.children.get(c);
            if (node == null) return new ArrayList<>();
        }

        List<Book> result = new ArrayList<>();
        collectBooks(node, result);
        return result;
    }

    private void collectBooks(TrieNode node, List<Book> result) {
        if (node == null) return;
        if (node.isEndOfWord) result.addAll(node.books);
        for (TrieNode child : node.children.values()) {
            collectBooks(child, result);
        }
    }
}