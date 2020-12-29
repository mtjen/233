/*
 * Max Tjen
 * eecs233
 */

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class CourseListTester {
  
  // fields for testing
  CourseList list = new CourseList(10);
  Course data = new Course ("eecs233", "Intro to Data", 10);
  Course calc = new Course ("math121", "Calculus I", 20);
  Course chem = new Course ("chem111", "Intro to Chem", 30);
  Course java = new Course ("eecs132", "Intro to Java", 40);
  Course phys = new Course ("phys121", "Physics I", 50);
  

  
  /*
   * test of the addCourse method
   * testing to see if method can add a course to the array
   */
  @Test
  public void testAddCourse() 
  {
    list.addCourse(0, data);
    list.addCourse(1, calc);
    list.addCourse(2, chem);
    list.addCourse(1, java);
    list.addCourse(6, phys);
    
    assertEquals("index 0 failed", "Course Name: Intro to Data; Course ID: eecs233; Capacity: 10", list.print(0));
    assertEquals("index 1 failed", "Course Name: Intro to Java; Course ID: eecs132; Capacity: 40", list.print(1));
    assertEquals("index 2 failed", "Course Name: Calculus I; Course ID: math121; Capacity: 20", list.print(2));
    assertEquals("index 3 failed", "Course Name: Intro to Chem; Course ID: chem111; Capacity: 30", list.print(3));
    assertEquals("index 4 failed", "Course Name: Physics I; Course ID: phys121; Capacity: 50", list.print(4));
  }
  
  /*
   * test of the removeCourse method
   * testing to see if method can remove a course from the array
   */
  @Test
  public void testRemoveCourse()
  {
    list.addCourse(0, data);
    list.addCourse(1, calc);
    list.addCourse(2, chem);
    list.addCourse(3, java);
    list.addCourse(4, phys);
    
    assertEquals("remove index 1 failed", true, list.removeCourse(1));
    assertEquals("remove empty index failed", false, list.removeCourse(8));
    
    assertEquals("index 0 failed", "Course Name: Intro to Data; Course ID: eecs233; Capacity: 10", list.print(0));
    assertEquals("index 1 failed", "Course Name: Intro to Chem; Course ID: chem111; Capacity: 30", list.print(1));
    assertEquals("index 2 failed", "Course Name: Intro to Java; Course ID: eecs132; Capacity: 40", list.print(2));
    assertEquals("index 3 failed", "Course Name: Physics I; Course ID: phys121; Capacity: 50", list.print(3));
    assertEquals("index 4 failed", "index is empty", list.print(4));
                 
  }
  
  /*
   * test of size method
   * testing to see if method can return the correct amount of courses in the array
   */
  @Test
  public void testSize()
  {
    list.addCourse(0, data);
    assertEquals("size of 1 failed", 1, list.size());
    
    list.addCourse(1, calc);
    assertEquals("size of 2 failed", 2, list.size());
    
    list.addCourse(2, chem);
    assertEquals("size of 3 failed", 3, list.size());
    
    list.addCourse(3, java);
    assertEquals("size of 4 failed", 4, list.size());
    
    list.addCourse(4, phys);
    assertEquals("size of 5 failed", 5, list.size());
    
    list.removeCourse(4);
    assertEquals("size of 4 failed", 4, list.size());
    
    list.removeCourse(3);
    assertEquals("size of 3 failed", 3, list.size());
  }
  
  /*
   * test of changeCapacity method
   * testing to see if courses in the array can have their capacity correctly changed
   */
  @Test
  public void testChangeCapacity() 
  {
    list.addCourse(0, data);
    list.addCourse(1, calc);
    list.addCourse(2, chem);
    

    assertEquals("index 0 failed", true, list.changeCapacity("eecs233", 60));
    assertEquals("index 1 failed", true, list.changeCapacity("math121", 70));
    assertEquals("index 2 failed", true, list.changeCapacity("chem111", 80));
    assertEquals("unexistant course failed", false, list.changeCapacity("engr800", 60));
    
    list.changeCapacity("eecs233", 60);
    list.changeCapacity("math121", 70);
    list.changeCapacity("chem111", 80);
    
    assertEquals("index 0 failed", "Course Name: Intro to Data; Course ID: eecs233; Capacity: 60", list.print(0));
    assertEquals("index 1 failed", "Course Name: Calculus I; Course ID: math121; Capacity: 70", list.print(1));
    assertEquals("index 2 failed", "Course Name: Intro to Chem; Course ID: chem111; Capacity: 80", list.print(2)); 
  }
  
  /*
   * test of getCourseWithIndex method
   * testing to see if the found course is the same as the course at that index
   */
  @Test
  public void testGetCourseWithIndex()
  {
    list.addCourse(0, data);
    list.addCourse(1, calc);
    list.addCourse(2, chem);
    list.addCourse(3, java);
    list.addCourse(4, phys);
    
    Course courseOne = list.getCourseWithIndex(0);
    Course courseTwo = list.getCourseWithIndex(1);
    Course courseThree = list.getCourseWithIndex(2);
    Course courseFour = list.getCourseWithIndex(3);
    Course courseFive = list.getCourseWithIndex(4);
    
    assertEquals("index 0 failed", "Course Name: Intro to Data; Course ID: eecs233; Capacity: 10", courseOne.print());
    assertEquals("index 1 failed", "Course Name: Calculus I; Course ID: math121; Capacity: 20", courseTwo.print());
    assertEquals("index 2 failed", "Course Name: Intro to Chem; Course ID: chem111; Capacity: 30", courseThree.print());
    assertEquals("index 3 failed", "Course Name: Intro to Java; Course ID: eecs132; Capacity: 40", courseFour.print());
    assertEquals("index 4 failed", "Course Name: Physics I; Course ID: phys121; Capacity: 50", courseFive.print());
  }
  
  /*
   * test of searchCourseID  method
   * testing to see if the returned index is where the course is in the array after searching for the course ID
   */
  @Test
  public void testSearchCourseID()
  {
    list.addCourse(0, data);
    list.addCourse(1, calc);
    list.addCourse(2, chem);
    list.addCourse(3, java);
    list.addCourse(4, phys);
    
    assertEquals("index 0 failed", 0, list.searchCourseID("eecs233"));
    assertEquals("index 1 failed", 1, list.searchCourseID("math121"));
    assertEquals("index 2 failed", 2, list.searchCourseID("chem111"));
    assertEquals("index 3 failed", 3, list.searchCourseID("eecs132"));
    assertEquals("index 4 failed", 4, list.searchCourseID("phys121"));
    assertEquals("nonexistant course failed", -1, list.searchCourseID("engr800"));
  }
  
  /*
   * test of searchCourseName method
   * testing to see if the returned index is where the course is in the array after searching for the course name
   */
  @Test
  public void testSearchCourseName()
  {
    list.addCourse(0, data);
    list.addCourse(1, calc);
    list.addCourse(2, chem);
    list.addCourse(3, java);
    list.addCourse(4, phys);
    
    assertEquals("index 0 failed", 0, list.searchCourseName("Intro to Data"));
    assertEquals("index 1 failed", 1, list.searchCourseName("Calculus I"));
    assertEquals("index 2 failed", 2, list.searchCourseName("Intro to Chem"));
    assertEquals("index 3 failed", 3, list.searchCourseName("Intro to Java"));
    assertEquals("index 4 failed", 4, list.searchCourseName("Physics I"));
    assertEquals("nonexistant course failed", -1, list.searchCourseName("Engineering"));
  }
  
}
