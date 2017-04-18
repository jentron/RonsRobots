package utilities;

public class Vector3D {
	public double x, y, z;
	
	public Vector3D(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Vector3D(double x, double y)
	{
		this.x = x;
		this.y = y;
		this.z = 0;
	}
	public Vector3D(Vector3D that)
	{
		this.x = that.x;
		this.y = that.y;
		this.z = that.z;
	}
	public void set(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public void set(double x, double y)
	{
		this.x = x;
		this.y = y;
		this.z = 0;
	}
	public void set(Vector3D that)
	{
		this.x = that.x;
		this.y = that.y;
		this.z = that.z;
	}
	public double angle() // x/y angle
	{
		return Math.atan2(this.y, this.x);
	}
	public double pitch() // z/(x/y) angle
	{
		double foo = x>y ? x : y;
		return Math.atan2(this.z, foo);
	}
	public void normalize()
	{
		double mag = this.magnitude();
		this.x/= mag;
		this.y/= mag;
		this.z/= mag;
	}
	public double magnitude()
	{ // FIXME use a more robust method
		return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
	}
	public double dot(Vector3D that)
	{
		return(
				this.x * that.x +
				this.y * that.y +
				this.z * that.z);
	}
	public Vector3D cross(Vector3D that)
	{// FIXME not implemented
		return new Vector3D(this.y*that.z - this.z*that.y,
				this.z*that.x - this.x*that.z,
				this.x*that.y - this.y*that.x);
	}
	public void add(Vector3D that)
	{
		this.x += that.x;
		this.y += that.y;
		this.z += that.z;
	}
	public void subtract(Vector3D that)
	{
		this.x -= that.x;
		this.y -= that.y;
		this.z -= that.z;
	}
	public void multi(double s)
	{
		this.x *= s;
		this.y *= s;
		this.z *= s;
	}
}
