/*
 * Max Tjen
 * mkt38
 * eecs233 assignment 2
 */


import java.util.Scanner;

public class ReverseLinkedList { 
  

  //////////////////////////////////////////////////////////
  //
  //           nested class to create a node 
  //
  //////////////////////////////////////////////////////////

  private static class Node { 
    private int value = 0;  
    private Node next = null;
    
    /*
     * constructor to create a node of the linked list
     * @param value, the value to be stored in the node
     */
    public Node (int value) 
    { 
      this.value = value; 
    } 
    
    public int getValue() 
    { 
      return value; 
    } 
 
    public Node getNext() 
    { 
      return next; 
    } 
  }
  
  
  //////////////////////////////////////////////////////////
  //
  //           class to reverse a linked list
  //
  //////////////////////////////////////////////////////////

  private Node front = null;
  
  /*
   * method to add a node to the back of the linked list
   * @param node, the node to be added
   */
  public void addToBack (Node node)
  {
    boolean isEmpty = false;
    // check if the linked list is empty
    if (front == null) 
    {
      isEmpty = true;
    } 

    if (isEmpty == false) 
    {
      Node nodeptr = front;
      while (nodeptr.getNext() != null)
      {
        nodeptr = nodeptr.getNext();
      }
      nodeptr.next = node;
    }
    else
    {
      front = node;
    }
  }
  
  
  /*
   * method to reverse the linked list
   * @return previousNode, the previous node
   */
  public Node reverse () 
  { 
    Node currentNode = front; 
    Node previousNode = null;
    Node nextNode = currentNode.getNext(); 
    
    // loop through linked list until current node is null
    while (currentNode != null) 
    { 
      currentNode.next = previousNode;
      previousNode = currentNode;
      currentNode = nextNode;
      
      if (nextNode != null)
      {
        nextNode = nextNode.getNext();
      }
    }
    return previousNode;
  } 
  
  
  /*
   * method to print the linked list in a single line
   */
  public void print () 
  { 
    Node currentNode = front;
    while (currentNode != null) 
    { 
      System.out.print(currentNode.getValue() + "   "); 
      currentNode = currentNode.next; 
    } 
  }
  
  /* 
   * method to create a random number
   * @param maximum, the highest desired maximum number that may be returned
   * @return randomNumber, the random number
   */
  public int createRandomNumber (int maximum)
  {
    int randomNumber = 0;
    randomNumber = (int)(Math.random() * (maximum + 1));
    return randomNumber;
  }
  
  
  /*
   * main method to run the program and print the linked list and its reverse
   * @param args, arguments for the main method
   */
  public static void main (String... args)
  {
    ReverseLinkedList test = new ReverseLinkedList(); 
    Scanner scanner = new Scanner (System.in);
    
    int numNodes = scanner.nextInt();
    int count = 0;
    
    for (count = 0; count < numNodes; count++)
    {
      int random = test.createRandomNumber(100);
      test.addToBack(new Node(random));
    }
    
    System.out.println("Original Linked List: ");  
    test.print(); 
    
    System.out.println("\n");
    test.front = test.reverse();
    
    System.out.println("Reversed Linked List: "); 
    test.print();
  }
  
}

