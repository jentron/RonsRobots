package utilities;

import utilities.Vector3D;

public class Orbit
{
   private Vector3D center = new Vector3D(200, 200);
   private Vector3D location = new Vector3D(0,0);
   private Vector3D direction = new Vector3D(0,0);
//   private Vector3D refAngle = new Vector3D(1,0); // the vector we measure angles from
   private double radius = 50;
   private double currentHeading = 0;
   
   public void setLocation(double x, double y)
   {
      this.location.set(x, y);
   }

    public void setCenter(double x, double y)
   {
      this.center.set(x, y);
   }

   public void setRadius(double r)
   {
      this.radius = r;
   }

   public void setCurrentHeading(double r)
   {
      this.currentHeading = r;
   }

   public double getCourseHeading()
   {// TODO: normalize the angles using heading
      this.direction.set(this.center);
      this.direction.subtract(this.location);
      Vector3D foo = new Vector3D(this.direction);
      foo.normalize();
// p1 and p2 are normals to direction in opposite directions
      Vector3D p1 = new Vector3D( -foo.y, foo.x);
      p1.set(p1.x*this.radius, p1.y*this.radius);
      Vector3D p2 = new Vector3D( foo.y, -foo.x);
      p2.set(p2.x*this.radius, p2.y*this.radius);

      this.direction.add(p1);
      //TODO: evaluate both p1 and p2 values for the smallest currentheading change
//      return (this.direction.angle(this.refAngle));
      return (this.direction.angle());
   }
}
