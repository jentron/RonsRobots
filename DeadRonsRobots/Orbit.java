package DeadRonsRobots;

import javax.vecmath.Vector2d;

public class Orbit
{
   private Vector2d center = new Vector2d(200, 200);
   private Vector2d location = new Vector2d(0,0);
   private Vector2d direction = new Vector2d(0,0);
   private Vector2d refAngle = new Vector2d(1,0); // the vector we measure angles from
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
      this.direction.sub(this.location);
      Vector2d foo = new Vector2d(this.direction);
      foo.normalize();
// p1 and p2 are normals to direction in opposite directions
      Vector2d p1 = new Vector2d( -foo.y, foo.x);
      p1.set(p1.x*this.radius, p1.y*this.radius);
      Vector2d p2 = new Vector2d( foo.y, -foo.x);
      p2.set(p2.x*this.radius, p2.y*this.radius);

      this.direction.add(p1);
      //TODO: evaluate both p1 and p2 values for the smallest currentheading change
      return (this.direction.angle(this.refAngle));
   }
}
