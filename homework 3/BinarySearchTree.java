/*
 * Max Tjen
 * mkt38
 * eecs233 assignment 3
 */


public class BinarySearchTree {
  
  
  
  //////////////////////////////////////////////////////////
  //
  //           Node class
  //
  //////////////////////////////////////////////////////////
  
  /*
   * class to create a node of the tree
   */
  public static class Node
  {
    private int key = 0;
    private Node leftChild = null;
    private Node rightChild = null;
    
    /*
     * constructor to create a node of the tree
     * @param key, the key of the node
     */
    public Node (int key)
    {
      if (key < 0)
      {
        throw new IllegalArgumentException ("Invalid parameter - Node cannot be instantiated");
      }
      else
      {
        this.key = key;
      }
    }
    
    /*
     * methods to get a node's key or left / right child
     */
    public int getKey()
    {
      return key;
    }
    
    
    public Node getLeftChild()
    {
      return leftChild;
    }
    
    
    public Node getRightChild()
    {
      return rightChild;
    }
  }
  
  
  
  //////////////////////////////////////////////////////////
  //
  //           BinarySearchTree class
  //
  //////////////////////////////////////////////////////////
  
  private Node root = null;
  private Node parent = null;
  private int size = 0;
  
  
  /*
   * method to recursively add a node into the binary search tree
   * @param root, the root of the tree
   * @param key, the key of the node to be added
   * 
   * worst case runtime --> O(n)
   */
  private void insert (Node root, int key)
  {
    Node currentNode = root;
    
    if (root == null || key < 0)
    {
      throw new IllegalArgumentException ("One or more invalid parameters");
    }
    
    // loop to find where to add the node
    while (currentNode != null)
    {
      parent = currentNode;
      
      if (key < currentNode.getKey())
      {
        currentNode = currentNode.getLeftChild();
      }
      else
      {
        currentNode = currentNode.getRightChild();
      }
    }
    
    // adding the node
    if (parent == null)
    {
      root = new Node (key);
    }
    else if (key < parent.getKey())
    {
      parent.leftChild = new Node (key);
    }
    else
    {
      parent.rightChild = new Node (key);
    }
  }
 
  
  /*
   * method to search for a node with a given key
   * @param root, the root of the tree
   * @param key, the key of the node to search for
   * @return Node, the node of the key searched
   * 
   * worst case runtime --> O(n)
   */
  public Node search (Node root, int key) 
  {     
    if (key < 0)
    {
      throw new IllegalArgumentException ("Invalid parameter");
    }
    
    // if the root is null or if the node to delete is the root
    if (root == null || root.getKey() == key) {
      return root; 
    }

    else if (root.getKey() > key) 
    {
      return search (root.getLeftChild(), key); 
    }
    
    else
    {
      return search (root.getRightChild(), key); 
    }
  } 
  
  
  /*
   * method to delete a given node
   * @param root, the root of the tree
   * @param key, the key of the node to delete
   * @return Node, the node that is deleted
   * 
   * worst case runtime --> O(n)
   */
  public Node delete (Node root, int key) 
  {       
    if (key < 0)
    {
      throw new IllegalArgumentException ("Invalid parameter");
    }
    
    if (root == null) 
    {
      return root;
    }
    
    // traverse until the desired key is found
    if (key < root.getKey()) 
    {
      root.leftChild = delete (root.getLeftChild(), key); 
    }
    else if (key > root.key) 
    {
      root.rightChild = delete (root.getRightChild(), key); 
    }
    
    // once desired key is found
    else
    { 
      // if node has no children
      if (root.getLeftChild() == null) 
      {
        return root.getRightChild();
      }
      
      // if node has one child
      else if (root.getRightChild() == null) 
      {
        return root.getLeftChild();
      }
      
      // if the node has two children
      else
      {
        root.key = smallestKey (root.getRightChild()); 
        root.rightChild = delete (root.getRightChild(), root.getKey()); 
      }
    }
    return root;
  }
  
  
  /*
   * method to print the node keys through an in order traversal
   * @param root, the root of the tree
   * 
   * worst case runtime --> O(n)
   */
  public void inOrderRec (Node root) 
  {
    if (root == null)
    {
      throw new IllegalArgumentException ("Invalid parameter");
    }
    
    if (root.leftChild != null)
    {
      inOrderRec (root.getLeftChild());
    }
    
    System.out.print(root.getKey() + "   ");
   
    if (root.rightChild != null)
    {
      inOrderRec (root.getRightChild());
    }
  }
  
  
  /*
   * method to find the 'kth' smallest node key in the tree
   * @param root, the root of the tree
   * @param k, the 'kth' smallest node key
   * @return Node, the 'kth' smallest node
   * 
   * worst case runtime --> O(n)
   */
  public Node kthSmallest (Node root, int k)
  {
    Node result = null;
    Node currentNode = root;
    int count = 0; 
    
    if (k > size || k <= 0)
    {
      throw new IllegalArgumentException ("Invalid parameter");
    }
    
    // while the root / current node is not null
    while (currentNode != null)  
    {  
      if (currentNode.getLeftChild() != null)  
      {  
        Node nextSmallest = currentNode.getLeftChild();
        
        while (nextSmallest.getRightChild() != null && 
               nextSmallest.getRightChild() != currentNode)
        {
          nextSmallest = nextSmallest.getRightChild(); 
        }
        
        
        if (nextSmallest.getRightChild() != null)  
        {  
          nextSmallest.rightChild = null;  
          count++;   
          
          if (count == k)  
          {
            result = currentNode;
          }
          currentNode = currentNode.getRightChild(); 
        }  
        else
        {         
          nextSmallest.rightChild = currentNode;  
          currentNode = currentNode.getLeftChild();
        }
      } 
      
      // if root / current node is null
      else
      {
        count++;  
        
        if (count != k) 
        {
          currentNode = currentNode.getRightChild(); 
        }
        else
        {
          result = currentNode;  
        }
      }  
    }  
    return result;
    
  }
  
  
  
  //////////////////////////////////////////////////////////
  //
  //           helper methods primarily used
  //
  //////////////////////////////////////////////////////////
  
  /*
   * method to easily call the insert method using only the node key
   * @param key, the key of the node
   * 
   * worst case runtime --> O(n)
   */
  public void insert (int key)
  {
    if (key < 0)
    {
      throw new IllegalArgumentException ("Invalid parameter");
    }
    
    if (root == null)
    {
      root = new Node (key);
    }
    else
    {
      insert (root, key);
    }
    size++;
  }
  
  
  /*
   * method to easily call the search method using only the node key
   * @param key, the key of the node
   * @return boolean, whether or not the key is found
   * 
   * worst case runtime --> O(n)
   */
  public boolean search (int key)
  { 
    if (key < 0)
    {
      throw new IllegalArgumentException ("Invalid parameter");
    }
    
    boolean isFound = (search(root, key) != null);
    return isFound;
  }
  
  
  /*
   * method to easily call the delete method using only the node key
   * @param key, the key of the node
   * 
   * worst case runtime --> O(n)
   */
  public void delete (int key) 
  { 
    if (key < 0)
    {
      throw new IllegalArgumentException ("Invalid parameter");
    }
    
    root = delete (root, key);
    size--;
  } 
  
  
  /*
   * method to find the smallest key in the tree
   * @param root, the root of the tree
   * @return int, the key of the smallest node
   * 
   * worst case runtime --> O(n)
   */
  public int smallestKey (Node root) 
  { 
    if (root == null)
    {
      throw new IllegalArgumentException ("Invalid parameter");
    }
    
    int result = root.getKey(); 
    
    // traverse to find the last node with no smaller node keys
    while (root.getLeftChild() != null) 
    { 
      result = root.getLeftChild().getKey(); 
      root = root.getLeftChild(); 
    } 
    return result; 
  }  
  
  
  /*
   * method to easily call the inOrderRec method
   * 
   * worst case runtime --> O(n)
   */
  public void inOrderRec()
  {
    if (root == null)
    {
      System.out.println ("cannot traverse - tree is empty");
    }
    else 
    {
      inOrderRec (root);
    }
  }
  
  
  /*
   * method to easily call the kthSmallest method
   * @param k, the 'kth' smallest node key to look for
   * @return int, the key of the node found
   * 
   * worst case runtime --> O(n)
   */
  public int kthSmallest (int k)
  {
    int result = (kthSmallest(root, k)).getKey();
    return result;
  }
 
  
  
  //////////////////////////////////////////////////////////
  //
  //           main method
  //
  //////////////////////////////////////////////////////////
  
  /*
   * main method to run the BinarySearchTree program
   * @param args, the arguments of the program
   */
  public static void main (String... args)
  {
    BinarySearchTree tree = new BinarySearchTree();
    
    System.out.println(" --> insert elements:");
    tree.insert(2);
    tree.insert(1);
    tree.insert(4);
    tree.insert(5);
    tree.insert(9);
    tree.insert(3);
    tree.insert(6);
    tree.insert(7);
    tree.insert(10);
    tree.insert(12);
    tree.insert(11);
    
    tree.inOrderRec();
    System.out.println();
    System.out.println();
    
    System.out.println(" --> delete 4:");
    tree.delete(4);
    tree.inOrderRec();
    System.out.println();
    System.out.println();
    
    System.out.println(" --> delete 9:");
    tree.delete(9);
    tree.inOrderRec();
    System.out.println();
    System.out.println();
    
    System.out.println(" --> search if 12 is in the tree:");
    System.out.println(tree.search(12));
    System.out.println();
    
    System.out.println(" --> search if 4 is in the tree:");
    System.out.println(tree.search(4));
    System.out.println();
    
    System.out.println(" --> find key of 3rd smallest node in the tree:");
    System.out.println(tree.kthSmallest(3));
    
  }
  
}