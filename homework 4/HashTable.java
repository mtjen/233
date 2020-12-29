/*
 * Max Tjen
 * mkt38
 * eecs233 assignment 4
 */

public class HashTable {
  
  private HashEntry[] bucket;
  private final double loadFactor = 1.00;
  private int numEntries = 0;
  private int hashKey = 0;
  private int oldSize = 0;
  private int tableSize = 0;
  
  /*
   * constructor to create a hash table
   */
  public HashTable ()
  {
    this.tableSize = 11;
    bucket = new HashEntry[tableSize];
    
    for (int i = 0; i < tableSize; i++)
    {
      bucket[i] = null;
    }
  }
  
  /*
   * method to take a string of words and prints each individual word and its frequency in the input
   * @param input, the input string
   * @return String, the individual words and their frequencies
   */
  public void wordCount (String input)
  {
    String lowerCaseInput = input.toLowerCase();
    String[] inputString = lowerCaseInput.split("\\P{Alpha}+");
    HashTable hashTable = new HashTable();
    
    for (int i = 0; i < inputString.length; i++)
    {
      if (inputString[i].length() > 0)
      {
        hashTable.add(inputString[i]);
      }
    }
    System.out.println("size of hashtable: " + hashTable.getTableSize());
    System.out.println();
    hashTable.print();
  }
  
 
  //////////////////////////////////////////////////////////
  //
  //           helper methods
  //
  //////////////////////////////////////////////////////////
  
  /*
   * method to find the hash key of the string
   * @param key, the string input
   * @return int, the hash key
   */
  public int findHashKey (String key)
  {
    hashKey = Math.abs (key.hashCode() % tableSize);
    return hashKey;
  }
  
  /*
   * method to find the next prime number
   * @param input, the reference number to look for the next prime number after it
   * @return int, the next prime number
   */
  public int nextPrime (int input)
  {
    boolean isPrime = false;
    while (isPrime == false)
    {
      isPrime = true;
      input++;
      for (int i = 2; i < input; i++)
      {
        if (input % i == 0)
        {
          isPrime = false;
          break;
        }
      }
    }
    return input;
  }
  
  /*
   * method to change the table size for rehashing
   * @param tableSize, the old table size
   * @return int, the new table size
   */
  public int changeTableSize (int tableSize)
  {
    int oldSize = tableSize;
    tableSize = nextPrime (2 * oldSize);
    return tableSize;
  }
  
  /*
   * method to return the size of the hash table
   * @return int, the size
   */
  public int getTableSize()
  {
    return tableSize;
  }
  
  /*
   * method to rehash the table and reassign key values
   * 
   * ////////////////////////////////////////////////
   * 
   * in some instances frequency of a word is lost
   */
  public void rehash()
  {
    oldSize = tableSize;
    tableSize = changeTableSize(oldSize);
    HashEntry[] newTable = new HashEntry[tableSize];
    
    for (int i = 0; i < oldSize - 1; i++)
    {
      HashEntry current = bucket[i];
      
      while (current != null)
      {
        HashEntry rehashedEntry = new HashEntry (current.getKey(), current.getFrequency());
        int changedKey = findHashKey (rehashedEntry.getKey());
        
        // add to new bucket of key
        if (newTable[changedKey] == null)
        {
          newTable[changedKey] = rehashedEntry;
        }
        
        else 
        {      
          HashEntry filledEntry = newTable[changedKey];
          
          while (filledEntry.getNext() != null)
          {
            filledEntry = filledEntry.getNext();
          }
          filledEntry.setNext(rehashedEntry.getKey());
        }
        current = current.getNext();
      }
    }
    bucket = newTable;
  }
  
  /*
   * method to check the load factor of the hash table
   * @param numEntries, the number of hash entries in the table
   * @return boolean, whether or not the load factor is ok
   */
  public boolean checkLoadFactor (int numEntries)
  {
    boolean isOk = true;
    double load = (numEntries / tableSize);
    
    if (load >= loadFactor)
    {
      isOk = false;
    }
    return isOk;
  }
  
  /*
   * method to add a hash entry to the table
   * @param key, the key of the entry
   */
  public void add (String key)
  {  
    int hashKey = findHashKey(key);
    
    // find the location / index to add the word
    if (bucket[hashKey] == null)
    {
      bucket[hashKey] = new HashEntry(key, 1);
    }
    else
    {
      HashEntry entry = bucket[hashKey];

      while ((!(entry.getKey()).equals(key)) && (entry.getNext() != null))
      {
        entry = entry.getNext();
      }
      
      if (entry.getKey().equals(key)) 
      {
        entry.adjustFrequency();
      }
      else
      {
        entry.setNext(key);
      }
    }
    numEntries++;
    boolean isChecked = checkLoadFactor(numEntries);
    if (isChecked == false)
    {
      rehash();
    }
  }
  
  /*
   * method to print out the hash table
   */
  public void print() 
  {
    int count = 1;
    System.out.println ("format: word number | word | frequency");
    
    for (int i = 0; i < tableSize; i++) 
    {
      HashEntry current = bucket[i];
      
      while (current != null)
      {
        System.out.println ((count) + ") "
                             + current.getKey() 
                             + " (" + current.getFrequency() + ")");
        count++;
        current = current.getNext();
      }
    }
  }
  
}