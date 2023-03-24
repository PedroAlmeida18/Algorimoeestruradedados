public class BST implements EstruturaDeDados{

    private Node root;

    @Override
    public void insert(int key) {
        if (root == null){
            root = new Node(key);
        } else{
            insertNode(root, key);   
        }
    }

    private void insertNode(Node n, int key){
        if (key >= n.getValue()){
            //inserir na direita
            if (n.getRight() == null){
                Node newN = new Node(key);
                n.setRight(newN);
            } else {
                insertNode(n.getRight(), key);
            }
        } else {
            //inserir na esquerda
            if (n.getLeft() == null){
                Node newN = new Node(key);
                n.setLeft(newN);
            } else {
                insertNode(n.getLeft(), key);
            }
        }
    }

    @Override
    public void delete(int chave) {
        
    }

    private void deleteNode(Node n, int key){
        if (key >= n.getValue()){
            Node r = n.getRight();
            if (r.getValue() == key){
                //verificar se r é folha
                if (r.getRight() == null && r.getLeft() == null){
                    //Caso 1
                    n.setRight(null);
                } else if (r.getRight() == null){
                    //Caso 2
                    n.setRight(r.getLeft());
                } else if (r.getLeft() == null){
                    //Caso 2
                    n.setRight(r.getRight());
                } else{
                    // Caso 3
                }
                
            }

        }
    }

    @Override
    public boolean search(int key) {
        if (root == null){
            return false;
        }
        return searchNode(root, key);
    }

    private boolean searchNode(Node n, int key){
        if (n.getValue() == key){
            return true;
        } else if (key > n.getValue()){
            if (n.getRight() == null){
                return false;
            } else {
                return searchNode(n.getRight(),key);
            }
        } else {
            if (n.getLeft() == null){
                return false;
            } else {
                return searchNode(n.getLeft(),key);
            }
        }
    }

    @Override
    public int minimum() {
        if (root == null) {
            return -1;
        } else {
            return compareMinimum(root, root.getValue());
        }
    }

    private int compareMinimum(Node no, int minimum) {
        int min = minimum;

        if (no.getLeft() != null) {
            if (no.getLeft().getValue() < minimum) {
                min = no.getLeft().getValue();
            }
            return compareMinimum(no.getLeft(), min);
        } else {
            return min;
        }
    }

    @Override
    public int maximum() {
        if (root == null) {
            return -1;
        } else {
            return compareMaximum(root, root.getValue());
        }
    }

    private int compareMaximum(Node no, int maximum) {
        int max = maximum;

        if (no.getRight() != null) {
            if (no.getRight().getValue() > maximum) {
                max = no.getRight().getValue();
            }
            return compareMaximum(no.getRight(), max);
        } else {
            return max;
        }
    }

    @Override
    public int sucessor(int chave) {
        return 0;
    }

    @Override
    public int prodessor(int chave) {
        return 0;
    }

    public static void main(String[] args) {
        BST tree = new BST();
        System.out.println(tree.search(7));
        tree.insert(4);
        tree.insert(2);
        tree.insert(5);
        tree.insert(6);
        tree.insert(10);
        tree.insert(99);
        tree.insert(15);
        tree.insert(8);
        tree.insert(1);
        System.out.println(tree.search(5));
        System.out.println(tree.search(7));
        System.out.println(tree.search(1));
        System.out.println(tree.minimum());
        System.out.println(tree.maximum());
    }
}
