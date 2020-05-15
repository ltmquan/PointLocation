/* Name: Quan Luu
 * Student ID: 31529099
 * NetID: qluu2
 * Lab section: MW 6h15 - 7h30
 * Project: 2
 * Description: a Line class, which has the pointTest (similar to ccw fucntion given) function and intersect function
 */
public class Line implements Comparable<Line>{
	
	Point p1, p2;
	int name;
	double a, b;
	
	public Line(Point p1, Point p2, int name) {
		this.p1 = p1;
		this.p2 = p2;
		this.name = name;
		
		//calculating stuff for the intersect function
		a = (p2.y - p1.y)/(p2.x - p1.x);
		b = p1.y - a*p1.x;
	}
	
	//test whether a point is on the left or right or just on a line
	public int pointTest(Point p0) {
		double dx1 = p1.x - p0.x;
		double dy1 = p1.y - p0.y;
		double dx2 = p2.x - p0.x;
		double dy2 = p2.y - p0.y;
		if (dx1*dy2 < dy1*dx2) {
			return 1;
		} else if (dx1*dy2 > dy1*dx2) {
			return -1;
		} else {
			return 0;
		}
	}
	
	//compareTo for 2 lines
	@Override
	public int compareTo(Line o) {
		if (o.pointTest(p1) == 0 && o.pointTest(p2) == 0) {
			return 0;
		} else if (o.pointTest(p1) != 1 && o.pointTest(p2) != 1) {
			return -1;
		} else if (o.pointTest(p1) != -1 && o.pointTest(p2) != -1) {
			return 1;
		} else {
			if (o.pointTest(p1) == 1) {
				return 2;
			} else {
				return -2;
			}
		}
	}
	
	//returns the intersection point of two lines
	public Point intersect(Line o) {
		double x = (o.b - b)/(a - o.a);
		double y = x*a + b;
		return new Point(x, y);
	}
	
	public String toString() {
		return "Line " + name + ":" + p1 + ", " + p2;
	}
}
