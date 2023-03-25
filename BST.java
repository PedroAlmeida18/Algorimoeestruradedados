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
        remover(chave);
    }

    private boolean remover(int key) {
        Node atual = this.root;
        Node paiatual = null;
        while(atual != null ){
            if (atual.getValue() == key){
                break;
            } else if (key < atual.getValue() ){
                paiatual = atual;
                atual= atual.getLeft();

            } else {
                paiatual = atual;
                atual= atual.getRight();
            }

        }  if ( atual != null ){
            if(atual.getRight() != null){
                Node substitiNode = atual.getRight();
                 Node substitipai = atual ;
                 while ( substitiNode.getLeft()!= null){
                    substitipai = substitiNode;
                    substitiNode = substitiNode.getLeft();
                 } if (paiatual!= null){
                    if ( atual.getValue()<paiatual.getValue()){
                        paiatual.setLeft(substitiNode);
                    }
                    else {
                        paiatual.setRight(substitiNode);
                    }
                 }
                 else {
                    this.root = substitiNode;
                 } 
                 

                 if ( substitiNode.getValue()<substitipai.getValue()){
                    substitipai.setLeft(null);
                }
                else {
                    substitipai.setRight(null);
                }

          

            } else if ( atual.getLeft() != null){
                 Node substitiNode = atual.getLeft();
                 Node substitipai = atual ;
                 while ( substitiNode.getRight()!= null){
                    substitipai = substitiNode;
                    substitiNode = substitiNode.getRight();
                
                 } 
                 if (paiatual != null ){
                    if ( atual.getValue()<paiatual.getValue()){
                        paiatual.setLeft(substitiNode);
                    }
                    else {
                        paiatual.setRight(substitiNode);
                    }

                 }
                 else {
                    this.root = substitiNode;
                 }
                 

                 if ( substitiNode.getValue()<substitipai.getValue()){
                    substitipai.setLeft(null);
                }
                else {
                    substitipai.setRight(null);
                }

            }else {
                if (paiatual!= null){
                    if ( atual.getValue()<paiatual.getValue()){
                        paiatual.setLeft(null);
                    }
                    else {
                        paiatual.setRight(null);
                    }

                } else {
                    this.root = null;
                }
               
            }
              return true ;
        }
         else {
            return false ;
         }
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
        int[] sucessor = new int[1];
        int max = compareMaximum(root, root.getValue());
        sucessor[0] = max;

        if (chave == max) {
            return -1;
        } else {
            searchSucessor(root, chave, sucessor);
            return sucessor[0];
        }
    }

    private void searchSucessor(Node no, int key, int[] sucessor) {
        if (no != null) {
            searchSucessor(no.getLeft(), key, sucessor);
            if (no.getValue() > key && no.getValue() < sucessor[0]) {
                sucessor[0] = no.getValue();
            }
            searchSucessor(no.getRight(), key, sucessor);
        }
    }

    @Override
    public int prodessor(int chave) {
        int [] prodessor = new int [1];
        int min = compareMinimum(root, root.getValue());
        prodessor[0] = min;

        if (chave == min) {
            return -1;
        } else {
            searchProdessor(root, chave, prodessor);
            return prodessor[0];
        }
    }

    private void searchProdessor(Node no, int key, int[] prodessor) {
        if (no != null) {
            searchProdessor(no.getRight(), key, prodessor);
            if (no.getValue() < key && no.getValue() >prodessor[0]) {
                prodessor[0] = no.getValue();
            }
            searchProdessor(no.getLeft(), key, prodessor);
        }
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
        System.out.println(tree.sucessor(1));
        System.out.println(tree.sucessor(2));
        System.out.println(tree.sucessor(4));
        System.out.println(tree.sucessor(5));
        System.out.println(tree.sucessor(6));
        System.out.println(tree.sucessor(8));
        System.out.println(tree.sucessor(10));
        System.out.println(tree.sucessor(15));
        System.out.println(tree.sucessor(99));
        System.out.println(tree.prodessor(10));
        System.out.println(tree.prodessor(99));
        System.out.println(tree.prodessor(1));
        tree.delete(10);
        System.out.println(tree.search(10));
        tree.delete(99);
        System.out.println(tree.search(99));
    }
}