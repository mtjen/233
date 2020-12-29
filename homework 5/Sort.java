/*
 * Max Tjen
 * mkt38
 * eecs233 assignment 5
 */

import java.lang.System;
import java.util.Scanner;

public class Sort {
  
  /*
   * method to sort a given array using merge sort
   * @param array, the input array
   */
  public void mergeSort (int[] array)
  {
    int[] temp = new int[array.length];
    mergeSortHelper (array, temp, 0, array.length - 1);
  }
  
  /*
   * method to sort a given array using quick sort
   * @param array, the input array
   */
  public void quickSort (int[] array)
  {
    quickSortHelper (array, 0, array.length - 1);
  }
  
  /*
   * method to sort a given array using insertion sort
   * @param array, the input array
   */
  public void insertionSort (int[] array)
  {
    for (int i = 1; i < array.length; i++)
    {      
      for (int j = i; j > 0; j--)
      {
        if (array[j - 1] > array[j])
        {
          int toSwitch = array[j];
          array[j] = array[j - 1];
          array[j - 1] = toSwitch;
        }
      }
    }
  }
  
  /*
   * method to create a random array
   * @param arraySize, the number of integers in the array
   * @param minimum, the minimum value of an integer
   * @param maximum, the maximum value of an integer
   * @return int[], an array of integers
   */
  public int[] randomArray (int arraySize, int minimum, int maximum)
  {
    int[] result = new int[arraySize];
    int randomNumber = 0;
    
    for (int i = 0; i < arraySize ; i++)
    {
      randomNumber = createRandomNumber (minimum, maximum);
      result[i] = randomNumber;
    }
    return result;
  }
  
  
  //////////////////////////////////////////////////////////
  //
  //           helper methods
  //
  //////////////////////////////////////////////////////////
  
  /* 
   * method to create a random number
   * @param minumum, the lowest desired number that may be returned
   * @param maximum, the highest desired number that may be returned
   * @return int, the random number
   */
  public int createRandomNumber (int minumum, int maximum)
  {
    int randomNumber = 0;
    
    randomNumber = (int) ((Math.random() * (maximum - minumum)) + minumum);
    return randomNumber;
  }
  
  /*
   * method to print an array
   * @param array the input array
   */
  public void printArray (int[] array)
  {
    for (int i = 0; i < array.length; i++)
    {
      System.out.print (array[i] + "    ");
    }
    System.out.println();
  }
  
  /*
   * method to create a random array for testing
   * @param numElements, the number of elements for the array
   * @return int[], the random array
   */
  public int[] createRandomTestArray (int numElements)
  {
    int minimum = -10000;
    int maximum = 10000;
    
    int[] array = randomArray (numElements, minimum, maximum);
    return array;
  }
  
  /*
   * method to recursively merge and sort two arrays
   * @param array, the array to sort and merge
   * @param temp, the temporary array to hold the sorted elements
   * @param start, the start index
   * @param end, the end index
   */
  public void mergeSortHelper (int[] array, int[] temp, int start, int end)
  {
    if (start >= end)
    {
      return;
    }
    int middle = ((start + end) / 2);
    
    mergeSortHelper (array, temp, start, middle);
    mergeSortHelper (array, temp, middle + 1, end);
    
    merge (array, temp, start, middle, middle + 1, end);
  }
  
  /*
   * method to merge and sort two arrays
   * @param array, the array to sort and merge
   * @param temp, the temporary array to hold the sorted elements
   * @param leftStart, the start index for the first array
   * @param leftEnd, the end index for the first array
   * @param rightStart, the start index for the second array
   * @param rightEnd, the end index for the second array
   */
  public void merge (int[] array, int[] temp, int leftStart, int leftEnd, int rightStart, int rightEnd)
  {
    int i = leftStart;
    int j = rightStart;
    int k = leftStart;
    
    while (i <= leftEnd && j <= rightEnd)
    {
      if (array[i] <= array[j])
      {
        temp[k] = array[i];
        i++;
      }
      else 
      {
        temp[k] = array[j];
        j++;
      }
      k++;
    }
    
    while (i <= leftEnd)
    {
      temp[k] = array[i];
      i++;
      k++;
    }
    
    while (j <= rightEnd)
    {
      temp[k] = array[j];
      j++;
      k++;
    }
    
    for (i = leftStart; i <= rightEnd; i++)
    {
      array[i] = temp[i];
    }
  }
  
  /*
   * method to split and sort an array
   * @param array, the input array 
   * @param start, the start index
   * @param end, the end index
   * @return int, the integer value
   */
  public int partition (int[] array, int start, int end)
  {
    int i = start - 1;
    int j = end + 1;
    int pivotValue = array [(start + end) / 2];
    
    while (true)
    {
      do
      {
        i++;
      }
      while (array[i] < pivotValue);
      
      do 
      {
        j--;
      }
      while (array[j] > pivotValue);
      
      if (i < j)
      {
        int toSwap = array[i];
        array[i] = array[j];
        array[j] = toSwap;
      }
      else {
        return j;
      }
    }
  }
  
  /*
   * method to recursively split and sort the array
   * @param array, the input array
   * @param start, the index of where to start
   * @param end, the index of where to end
   */
  public void quickSortHelper (int[] array, int start, int end)
  {
    if (start < end)
    {
      int split = partition (array, start, end);
      
      quickSortHelper (array, start, split);
      quickSortHelper (array, split + 1, end);
    }
  }
  

  //////////////////////////////////////////////////////////
  //
  //           elapsed time methods
  //
  //////////////////////////////////////////////////////////
  
  /*
   * method to find how long it takes for Merge sort
   * @param numElements, the number of elements for the array
   * @param minimum, the minimum value of an integer
   * @param maximum, the maximum value of an integer
   */
  public void findMergeSortTime (int numElements)
  { 
    int[] array = createRandomTestArray (numElements);
    
    long startTimeMilli = System.currentTimeMillis();
    long startTimeNano = System.nanoTime();
    
    mergeSort (array);
    
    long endTimeMilli = System.currentTimeMillis();
    long endTimeNano = System.nanoTime();
    
    long timeElapsedMilli = endTimeMilli - startTimeMilli;
    long timeElapsedNano = endTimeNano - startTimeNano;
    
    System.out.println("Merge Sort Time Elapsed");
    System.out.println("milliseconds --> " + timeElapsedMilli);
    System.out.println("nanoseconds  --> " + timeElapsedNano);
  }
  
  /*
   * method to find how long it takes for quick sort
   * @param numElements, the number of elements for the array
   * @param minimum, the minimum value of an integer
   * @param maximum, the maximum value of an integer
   */
  public void findQuickSortTime (int numElements)
  { 
    int[] array = createRandomTestArray (numElements);
    
    long startTimeMilli = System.currentTimeMillis();
    long startTimeNano = System.nanoTime();
    
    quickSort (array);
    
    long endTimeMilli = System.currentTimeMillis();
    long endTimeNano = System.nanoTime();
    
    long timeElapsedMilli = endTimeMilli - startTimeMilli;
    long timeElapsedNano = endTimeNano - startTimeNano;
    
    System.out.println("Quick Sort Time Elapsed");
    System.out.println("milliseconds --> " + timeElapsedMilli);
    System.out.println("nanoseconds  --> " + timeElapsedNano);
  }
  
  /*
   * method to find how long it takes for insertion sort
   * @param numElements, the number of elements for the array
   * @param minimum, the minimum value of an integer
   * @param maximum, the maximum value of an integer
   */
  public void findInsertionSortTime (int numElements)
  { 
    int[] array = createRandomTestArray (numElements);
    
    long startTimeMilli = System.currentTimeMillis();
    long startTimeNano = System.nanoTime();
    
    insertionSort (array);
    
    long endTimeMilli = System.currentTimeMillis();
    long endTimeNano = System.nanoTime();
    
    long timeElapsedMilli = endTimeMilli - startTimeMilli;
    long timeElapsedNano = endTimeNano - startTimeNano;
    
    System.out.println("Insertion Sort Time Elapsed");
    System.out.println("milliseconds --> " + timeElapsedMilli);
    System.out.println("nanoseconds  --> " + timeElapsedNano);
  }
  

  //////////////////////////////////////////////////////////
  //
  //           main method
  //
  //////////////////////////////////////////////////////////
  
  /*
   * main method
   * @param args, the arguments
   */
  public static void main (String... args)
  {
    Sort test = new Sort(); 
    Scanner scanner = new Scanner (System.in);
    int numElements = scanner.nextInt();
    
    test.findMergeSortTime (numElements);
    System.out.println();
    
    test.findQuickSortTime (numElements);
    System.out.println();
    
    test.findInsertionSortTime (numElements);

    scanner.close();
  }
  
}