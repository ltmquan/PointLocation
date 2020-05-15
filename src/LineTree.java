/* Name: Quan Luu
 * Student ID: 31529099
 * NetID: qluu2
 * Lab section: MW 6h15 - 7h30
 * Project: 2
 * Description: the class containing the main function, had some introduction, then creates steps
 * and guidelines for user to follow and use the program. Basically, this is a guidebook and reference point
 * to other classes, like graphics.
 */
import java.util.Scanner;

public class LineTree {
	
	//tree, lines are for the LineGraph parameter
	static UR_BST tree = new UR_BST();
	static Line[] lines;
	static LineGraph graph;
	
	public static void main(String[] args) {
		
		//--INTRO--//
		System.out.println("This is a program to solve the point-location problem");
		System.out.println("You will be asked to input the "
				+ "number of lines you want to create, their coordinates, "
				+ "and the points' coordinates used for testing");
		System.out.println("The result will then show up");
		//--INTRO--//
		
		//--LINE INPUT--//
		while (true) {
			Scanner s = new Scanner(System.in);
			System.out.println("Enter number of lines: ");
			int num = s.nextInt();
			lines = new Line[num];
			System.out.println("When you enter the lines' coordinates, make sure that "
					+ "each pair of points has at least one point as 0 or 1");
			System.out.println("And also, the numbers always have to be between 0 and 1");
			for (int i = 0; i < num; i++) {
				double x1, x2, y1, y2;
				while (true) {
					System.out.println("Enter line " + (i+1) + "'s coordinates(separated by a 'space'): ");
					x1 = s.nextDouble();
					y1 = s.nextDouble();
					x2 = s.nextDouble();
					y2 = s.nextDouble();
					
					//just some error catching
					if ((x1 != 0 && y1 != 0 && x1 != 1 && y1 != 1)
							|| (x2 != 0 && y2 != 0 && x2 != 1 && y2 != 1)) {
						System.out.println("Remember!");
						System.out.println("When you enter the lines' coordinates, make sure that "
								+ "each pair of points has at least one point as 0 or 1");
						continue;
					}
					
					//another error catching
					if (x1 < 0 || x2 < 0 || y1 < 0 || y2 < 0
							|| x1 > 1 || x2 > 1 || y1 > 1 || y2 > 1) {
						System.out.println("Remember!");
						System.out.println("The numbers always have to be between 0 and 1");
						continue;
					}
					
					//and another error catching
					if (x1 == x2 && y1 == y2) {
						System.out.println("If you want to create a line, it's best you use two seperate points");
						continue;
					}
						
					break;
				}
				tree.insert(new Line(new Point(x1, y1), new Point(x2, y2), i));
				lines[i] = new Line(new Point(x1, y1), new Point(x2, y2), i);
			}
			//--LINE INPUT--//
			
			//--CALCULATION--//
			int exNode = tree.countExNode();
			double avPathLen = ((double) tree.getExPathLen())/tree.countExNode();
			//--CALCULATION--//
			
			//--GRAPHICS--//
			graph = new LineGraph(lines, tree);
			System.out.println("Do you wish to test out the points on a graphical interface?");
			System.out.println("Enter 'y' to change to graphics or 'n' to continue on console");
			String options = s.next();
			while (!options.equals("y") && !options.equals("n")) {
				System.out.println("Yo!");
				System.out.println("Enter 'y' to change to graphics or 'n' to continue on console");
				options = s.next();
			}
			if (options.equals("y")) {
				graph.setVisible(true);
				System.out.println("Switch to graphics");
				break;
			} else {
				System.out.println("Ok then");
			}
			//--GRAPHICS--//
			
			//--POINT INPUT--//
			boolean changeLine = false;
			
			while (!changeLine) {
				System.out.println("Enter two points to begin test");
				System.out.println("Enter first point's coordinate(separated by a 'space'): ");
				double x1 = s.nextDouble();
				double y1 = s.nextDouble();
				System.out.println("Enter second point's coordinate(separated by a 'space'): ");
				double x2 = s.nextDouble();
				double y2 = s.nextDouble();
				
				//create a Line of the two given points to test it out
				Line comp = new Line(new Point(x1, y1), new Point(x2, y2), -1);
				Line result = tree.test(comp);
				if (result == null) {
					System.out.println("There are no lines that cross the two given points");
				} else {
					System.out.println("Line " + (result.name+1) + " crosses the two given points");
				}
				System.out.println("Number of external nodes: " + exNode);
				System.out.println("Average path length: " + avPathLen);
				System.out.println("Do you wish to continue testing?");
				System.out.println("Enter 'y' to continue or 'n' to quit");
				System.out.println("Enter 'change' to change the original lines");
				String answer = s.next();
				while (!answer.equals("y") && !answer.equals("n") && !answer.equals("change")) {
					System.out.println("Please!");
					System.out.println("Enter 'y' to continue or 'n' to quit");
					System.out.println("Enter 'change' to change the original lines");
					answer = s.next();
				} 
				if (answer.equals("y")) {
					continue;
				} else if (answer.equals("n")){
					System.out.println("Have a nice life!");
					break;
				} else {
					changeLine = true;
				}
			}
			if (changeLine == false) {
				break;
			}
			//--POINT INPUT--//
		}
	}
}
