public class HuffmanCode {
    public static String[] getCode(Tree.Node root) {
        if (root == null) {
            return null;
        }
        String[] codes = new String[2 * 128];
        assignCode(root, codes);
        return codes;
    }

    public static void assignCode(Tree.Node root, String[] codes) {
        if (root.left != null) {
            root.left.code = root.code + "0";
            assignCode(root.left, codes);

            root.right.code = root.code + "1";
            assignCode(root.right, codes);
        }
        else {
            codes[(int)root.element] = root.code;
        }
    }

    public static Tree getHuffmanTree(int[] counts) {
        Heap<Tree> heap = new Heap<>();
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                heap.add(new Tree(counts[i], (char)i));
            }
        }

        while (heap.getSize() > 1) {
            Tree t1 = heap.remove();
            Tree t2 = heap.remove();
            heap.add(new Tree(t1, t2));
        }

        return heap.remove();
    }

    public static int[] getCharacterFrequency(String text) {
        int[] counts = new int[256];

        for (int i = 0; i < text.length(); i++) {
            counts[(int)text.charAt(i)]++;
        }

        return counts;
    }

    public static class Tree implements Comparable<Tree> {
        Node root;

        public Tree(Tree t1, Tree t2) {
            root = new Node();
            root.left = t1.root;
            root.right = t2.root;
            root.weight = t1.root.weight + t2.root.weight;
        }

        public Tree(int weight, char element) {
            root = new Node(weight, element);
        }

        @Override
        public int compareTo(Tree t) {
            if (root.weight < t.root.weight) {
                return 1;
            }
            else if (root.weight == t.root.weight) {
                return 0;
            }
            else {
                return -1;
            }
        }

        public class Node {
            char element;
            int weight;
            Node left;
            Node right;
            String code = "";

            public Node() {
            }

            public Node(int weight, char element) {
                this.weight = weight;
                this.element = element;
            }
        }
    }
}

class Heap <E extends Comparable<E>> {
    private java.util.ArrayList<E> list = new java.util.ArrayList<>();

    public Heap(){
    }

    public Heap(E[] objects) {
        for (int i=0; i < objects.length; i++) {
            add(objects[i]);
        }
    }

    public void add(E newObject) {
        list.add(newObject);
        int currentIndex = list.size() - 1;

        while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2;
            if (list.get(currentIndex).compareTo(list.get(parentIndex)) > 0){
                E temp = list.get(currentIndex);
                list.set(currentIndex, list.get(parentIndex));
                list.set(parentIndex, temp);
            }
            else {
                break;
            }

            currentIndex = parentIndex;
        }
    }

    public E remove() {
        if (list.size() == 0) {
            return null;
        }

        E removeObject = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);

        int currentIndex = 0;
        while (currentIndex < list.size()) {
            int leftChildIndex = 2 * currentIndex +1;
            int rightChildIndex = 2 * currentIndex +2;

            if (leftChildIndex >= list.size()) {
                break;
            }
            int maxIndex = leftChildIndex;
            if (rightChildIndex < list.size()) {
                if (list.get(maxIndex).compareTo(list.get(rightChildIndex)) < 0) {
                    maxIndex = rightChildIndex;
                }
            }

            if (list.get(currentIndex).compareTo(list.get(maxIndex)) < 0) {
                E temp = list.get(maxIndex);
                list.set(maxIndex, list.get(currentIndex));
                list.set(currentIndex, temp);
                currentIndex = maxIndex;
            }
            else {
                break;
            }
        }

        return removeObject;
    }

    public int getSize() {
        return list.size();
    }
}