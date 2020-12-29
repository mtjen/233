/*
 * Max Tjen
 * eecs233
 */

public class CourseList {
  
  // field for the course list
  private final Course[] listOfCourses;
  private int size = 0;
  
  /*
   * constructor to create a course list
   * @param length, the desired array length
   */
  public CourseList (int length)
  {
    if (length != 0)
    {
      listOfCourses = new Course[length];
    }
    else
    {
      throw new IllegalArgumentException ("Invalid parameter length");
    }
  }
  
  /*
   * method to return the number of courses in the array
   * @return size, the number of courses in the array
   */
  public int size()
  {
    return size;
  }
  
  
  /*
   * method to add a course to the list of courses
   * @param index, the index for where the course should be added
   * @param course, the course to be added
   */
  public void addCourse (int index, Course course)
  {
    if (index < 0) 
    {
      throw new ArrayIndexOutOfBoundsException ("Invalid value. Value should be zero or greater");
    }
    
    if (size != listOfCourses.length)
    {
      if (index >= size)
      {
        listOfCourses[size] = course;
      }
      else 
      {
        for (int i = size; i > index; i--) 
        {
          listOfCourses[i] = listOfCourses[i - 1];
        }
        listOfCourses[index] = course;
      }
      size++;
    }
  }
  
  /*
   * method to remove a course from the list of courses
   * @param index, the index of the course that should be removed
   * @return isRemoved, returns true if the course is deleted
   */
  public boolean removeCourse (int index)
  {
    boolean isRemoved = false;
    if (index >= 0 && index < size)
    {
      for (int i = index; i < size; i++) 
      {
        listOfCourses[i] = listOfCourses[i + 1];
      }
      size--;
      isRemoved = true;
    }
    return isRemoved;
  }
  
  /*
   * method to change the capacity of a course if the course is in the list of courses
   * @param courseID, the course whose capacity should be changed
   * @param capacity, the new capacity of the course
   * @return isChanged, returns true if the capacity is changed
   */
  public boolean changeCapacity (String courseID, int capacity)
  {
    boolean isChanged = false;
    for (int i = 0; i < size; i++)
    {
      if (courseID.equals(listOfCourses[i].getCourseID()))
      {
        listOfCourses[i].setCapacity(capacity);
        isChanged = true;
      }
    }
    return isChanged;
  }
  
  /*
   * method to return the course at the given index of the list of courses
   * @param index, the index of the course to return
   * @return course, the course at the given index
   */
  public Course getCourseWithIndex (int index)
  {
    Course result = null;
    if (index >= 0 && index < size)
    {
      result = listOfCourses[index];
    }
    return result;
  }
  
  /* 
   * method to return the index of a course with the given course ID
   * @param courseID, the ID of the course to find
   * @return index, the index of the course
   */
  public int searchCourseID (String courseID)
  {
    int index = -1;
    for (int i = 0; i < size; i++)
    {
      if (courseID.equals(listOfCourses[i].getCourseID()))
      {
        index = i;
        break;
      }
    }
    return index;
  }
  
  /* 
   * method to return the index of a course with the given course name
   * @param courseName, the name of the course to find
   * @return index, the index of the course
   */
  public int searchCourseName (String courseName)
  {
    int index = -1;
    for (int i = 0; i < size; i++)
    {
      if (courseName.equals(listOfCourses[i].getCourseName()))
      {
        index = i;
        break;
      }
    }
    return index;
  }
  
  /*
   * method to print a course at given index
   * @param index, the index to return
   * @return courseFound, the course at the given index
   */
  public String print (int index)
  {
    Course course = getCourseWithIndex(index);
    String courseFound = new String("");
    
    if (course != null)
    {
      courseFound = ("Course Name: " + course.getCourseName() + 
                     "; Course ID: " + course.getCourseID() + 
                     "; Capacity: " + course.getCapacity());
    }
    
    else
    {
      courseFound = ("index is empty");
    }
    return courseFound;
  }

}