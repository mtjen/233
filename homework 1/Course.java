/*
 * Max Tjen
 * eecs233
 */

public class Course {
  
  // fields for courses
  private String courseID = "";
  private String courseName = "";
  private int capacity = 0;
  
  /*
   * constructor to create a course
   * @param courseID, the course ID
   * @param courseName, the name of the course
   * @param capacity, the maximum capacity
   */
  public Course (String courseID, String courseName, int capacity)
  {
    if ((courseID != null) && (courseID.length() > 0) &&
        (courseName != null) && (courseName.length() > 0) &&
        (capacity >= 0))
    {
      this.courseID = courseID;
      this.courseName = courseName;
      this.capacity = capacity;
    }
    else 
    {
      throw new IllegalArgumentException ("One or more invalid parameters. Course cannot be instantiated");
    }
  }
  
  // getter setter methods for course ID
  public void setCourseID (String courseID) 
  {
    this.courseID = courseID;
  }
  
  public String getCourseID() 
  {
    return courseID;
  }
  
  // getter setter methods for course name
  public void setCourseName (String courseName) 
  {
    this.courseName = courseName;
  }
  
  public String getCourseName() 
  {
    return courseName;
  }
  
  // getter setter methods for course capacity
  public void setCapacity (int capacity) 
  {
    this.capacity = capacity;
  }
  
  public int getCapacity() 
  {
    return capacity;
  }
  
  /*
   * method to print a given course 
   * @param course, the course to return
   * @return courseFound, the given course
   */
  public String print()
  {
    String courseFound = new String("");
    courseFound = ("Course Name: " + getCourseName() + "; Course ID: " + getCourseID() + "; Capacity: " + getCapacity());
    return courseFound;
  }
 
}