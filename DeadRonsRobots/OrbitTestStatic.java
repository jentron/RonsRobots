package DeadRonsRobots;

public class OrbitTestStatic
{
  public static void main(String[] args)
  {
     OrbitTest myTest = new OrbitTest();
     myTest.burnin_test();
     myTest.arc_test();
  }
}

class OrbitTest
{
  public void burnin_test()
  {
     System.out.println("Burn-In");
     Orbit myOrbit = new Orbit();
     myOrbit.setRadius(200);
     System.out.println("Course Heading "+ (myOrbit.getCourseHeading()*180.0/3.14159));
  
  }
  
  public void arc_test()
  {
     System.out.println("Arc Test");
     Orbit myOrbit = new Orbit();
     myOrbit.setRadius(0);
     for(double t=0.0; t < Math.PI/2 + 0.01 ; t += Math.PI/8)
     {
        myOrbit.setCenter( Math.cos(t)*100,Math.sin(t)*100);
        System.out.println("Angle to center: " + (t*180.0/Math.PI));
        System.out.println("Course Heading "+ (myOrbit.getCourseHeading()*180.0/3.14159));
     }
  
  }
}