package offer2020.jd;

import java.io.BufferedInputStream;
import java.io.PrintStream;
import java.util.*;

public class offer2020 {
    public static void main(String[] args) {
        // 1 - n
        // n+1 - 2n
        // 记录每个人的关系数目
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        PrintStream out = new PrintStream(System.out);
        int n = sc.nextInt();
        int friendsNum = sc.nextInt();
        Node[] nodes = new Node[2 * n + 1];
        for (int i = 1; i <= 2 * n; i++) {
            nodes[i] = new Node(i);
        }
        TreeSet<Node> treeSet = new TreeSet<>((o1, o2) -> {
            int ans = Integer.compare(o2.counter, o1.counter);
            if (ans == 0) {
                return Integer.compare(o1.id, o2.id);
            }
            return ans;
        });
        HashMap<Node, List<Node>> hashMap = new HashMap<>();
        for (int i = 0; i < friendsNum; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            nodes[a].counter++;
            nodes[b].counter++;
            addNode(hashMap, nodes, a, b);
            addNode(hashMap, nodes, b, a);
        }
        for (int i = 1; i <= 2 * n; i++) {
            treeSet.add(nodes[i]);
        }
        int counter = 0;
        ArrayList<Node> output = new ArrayList<>(2 * n);
        while (true) {
            Node node = treeSet.first();
            if (node.counter == 0) {
                break;
            }
            treeSet.remove(node);
            output.add(node);
            counter++;
            for (Node friend : hashMap.get(node)) {
                friend.counter--;
            }
        }
//        output.sort(Comparator.comparingInt(o -> o.id));
        out.println(counter);
        for (Node node : output) {
            out.println(node.id);
        }
        out.close();
        sc.close();
    }

    private static void addNode(HashMap<Node, List<Node>> hashMap, Node[] nodes, int a, int b) {
        List<Node> list;
        if (!hashMap.containsKey(nodes[a])) {
            list = new LinkedList<>();
        } else {
            list = hashMap.get(nodes[a]);
        }
        list.add(nodes[b]);
        hashMap.put(nodes[a], list);
    }

    /**
     * 2 2
     * 1 3
     * 1 4
     * <p>
     * 1
     * 1
     */

    private static class Node {
        int id;
        int counter;

        public Node(int id) {
            this.id = id;
        }

        public Node(int id, int counter) {
            this.id = id;
            this.counter = counter;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return id == node.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
}
