/*
 * Max Tjen
 * mkt38
 * eecs233 assignment 4
 */

public class HashEntry {
  
  private HashEntry next = null;;
  private int frequency = 0;
  private String key = "";
  
  /*
   * constructor to create an entry 
   * @param key, the key of the entry
   * @param frequency, the frequency of the entry
   */
  public HashEntry (String key, int frequency) 
  { 
    this.next = null;
    this.frequency = frequency;
    this.key = key; 
  }
  
  
  //////////////////////////////////////////////////////////
  //
  //           getter / setter methods for fields
  //
  //////////////////////////////////////////////////////////
  
  public String getKey()
  {
    return key;
  }
  
  public void setKey (String key)
  {
    this.key = key;
  }
  
  public HashEntry getNext()
  {
    return next;
  }
  
  public void setNext(String key)
  {
    this.next = new HashEntry(key, 1);
  }
  
  public int getFrequency() 
  {
    return frequency;
  }
  
  public void setFrequency (int frequency)
  {
    this.frequency = frequency;
  }
  
  /*
   * method to increment the frequency of a word occurence
   */
  public void adjustFrequency()
  {
    frequency++;
  }
  
}