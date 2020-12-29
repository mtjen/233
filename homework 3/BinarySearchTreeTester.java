/*
 * Max Tjen
 * mkt38
 * eecs233 assignment 3
 */


import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class BinarySearchTreeTester {
  
  BinarySearchTree tree = new BinarySearchTree();
  boolean isFound = false;
  
  
  /*
   * test of the insert and search methods
   * testing to see if method can successfully insert a node into the tree
   * testing to see if method can successfully search for a node in the tree
   */
  @Test
  public void testInsertAndSearch() 
  {
    
    // test 0
    isFound = tree.search(1);
    assertEquals ("failed", false, isFound);
    
    // test 1
    tree.insert(3);
    isFound = tree.search(3);
    assertEquals ("failed", true, isFound);
    
    isFound = tree.search(10);
    assertEquals ("failed", false, isFound);
    
    // test many
    tree.insert(5);
    tree.insert(6);
    tree.insert(7);
    tree.insert(8);
    tree.insert(9);
    
    isFound = tree.search(5);
    assertEquals ("failed", true, isFound);
    isFound = tree.search(6);
    assertEquals ("failed", true, isFound);
    isFound = tree.search(7);
    assertEquals ("failed", true, isFound);
    isFound = tree.search(8);
    assertEquals ("failed", true, isFound);
    isFound = tree.search(9);
    assertEquals ("failed", true, isFound);
    
    isFound = tree.search(15);
    assertEquals ("failed", false, isFound);
    isFound = tree.search(20);
    assertEquals ("failed", false, isFound);
    isFound = tree.search(25);
    assertEquals ("failed", false, isFound);
    
  }
  
  
  /*
   * test of the delete and search methods
   * testing to see if method can successfully delete a node from the tree
   * testing to see if method can successfully search for a node in the tree
   */
  @Test
  public void testDeleteAndSearch() 
  {
    
    tree.insert(1);
    tree.insert(2);
    tree.insert(3);
    tree.insert(4);
    tree.insert(5);
    
    // before deletion
    isFound = tree.search(1);
    assertEquals ("failed", true, isFound);
    isFound = tree.search(2);
    assertEquals ("failed", true, isFound);
    isFound = tree.search(3);
    assertEquals ("failed", true, isFound);
    isFound = tree.search(4);
    assertEquals ("failed", true, isFound);
    isFound = tree.search(5);
    assertEquals ("failed", true, isFound);
    
    // delete 1 node
    tree.delete(3);
    isFound = tree.search(1);
    assertEquals ("failed", true, isFound);
    isFound = tree.search(2);
    assertEquals ("failed", true, isFound);
    isFound = tree.search(3);
    assertEquals ("failed", false, isFound);
    isFound = tree.search(4);
    assertEquals ("failed", true, isFound);
    isFound = tree.search(5);
    assertEquals ("failed", true, isFound);
    
    // delete many nodes
    tree.delete(1);
    tree.delete(2);
    tree.delete(5);
    
    isFound = tree.search(1);
    assertEquals ("failed", false, isFound);
    isFound = tree.search(2);
    assertEquals ("failed", false, isFound);
    isFound = tree.search(3);
    assertEquals ("failed", false, isFound);
    isFound = tree.search(4);
    assertEquals ("failed", true, isFound);
    isFound = tree.search(5);
    assertEquals ("failed", false, isFound);
    
  }
  
  
  /*
   * test of the kthSmallest method
   * testing to see if method can successfully find the kth smallest node key in the tree
   */
  @Test
  public void testKthSmallest() 
  {
    tree.insert(1);
    tree.insert(3);
    tree.insert(5);
    tree.insert(7);
    tree.insert(9);
    
    assertEquals ("failed", 1, tree.kthSmallest(1));
    assertEquals ("failed", 3, tree.kthSmallest(2));
    assertEquals ("failed", 5, tree.kthSmallest(3));
    assertEquals ("failed", 7, tree.kthSmallest(4));
    assertEquals ("failed", 9, tree.kthSmallest(5));
  }
  
}