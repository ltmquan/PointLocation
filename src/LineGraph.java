/* Name: Quan Luu
 * Student ID: 31529099
 * NetID: qluu2
 * Lab section: MW 6h15 - 7h30
 * Project: 2
 * Description: graphics
 * Note: one thing I need to clarify is that graphics and coordinates don't go too well with each other (at least
 * for my program it doesn't), that's why you see some weird math regarding coordinates, but it works (or so I thought)
 */
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.*;

public class LineGraph extends JFrame implements MouseListener, ActionListener{
	
	Graph graph;
	UR_BST tree;
	Line[] lines;
	Point[] points;
	JPanel message, menu;
	JButton retest;
	JLabel messageLabel, p1coordLabel, p2coordLabel, exNodeLabel, avPathLenLabel, continueLabel;
	String p1coord, p2coord;
	Line crossingLine;
	
	public LineGraph(Line[] lines, UR_BST tree) {
		this.lines = lines;
		this.tree = tree;
		
		setTitle("Point-Location");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		//basically the canvas
		graph = new Graph(this.lines);
		add(graph, BorderLayout.CENTER);
		
		//contains the two test points
		points = new Point[2];
		
		//labels
		messageLabel = new JLabel("Click on the graph to get the 2 points");
		p1coordLabel = new JLabel("");
		p2coordLabel = new JLabel("");
		exNodeLabel = new JLabel("Number of external nodes: " + tree.countExNode());
		avPathLenLabel = new JLabel("Average path length: " + ((double) tree.getExPathLen())/tree.countExNode());
		continueLabel = new JLabel("Continue?");
		
		//retest button
		retest = new JButton("Retest");
		retest.addActionListener(this);
		
		//panels
		message = new JPanel();
		message.add(messageLabel);
		add(message, BorderLayout.SOUTH);
		
		menu = new JPanel();
		menu.setLayout(new GridLayout(2, 2));
		menu.add(continueLabel);
		menu.add(retest);
		menu.add(exNodeLabel);
		menu.add(avPathLenLabel);
		add(menu, BorderLayout.NORTH);
		
		addMouseListener(this);
	}
	
	//basically the canvas class
	public class Graph extends JPanel{
		
		Line[] lines;
		
		public Graph(Line[] lines) {
			this.lines = lines;
			
			//the board I draw is 400 times bigger than 0 and 1 so
			for (int i = 0 ; i < lines.length; i++) {
				lines[i].p1.x = lines[i].p1.x*400;
				lines[i].p1.y = lines[i].p1.y*400;
				lines[i].p2.x = lines[i].p2.x*400;
				lines[i].p2.y = lines[i].p2.y*400;
			}
		}
		
		public void paintComponent(Graphics g) {
			
			//drawing the board
			g.setColor(Color.BLACK);
			g.drawLine(50, 50, 450, 50);
			g.drawLine(50, 50, 50, 450);
			g.drawLine(450, 450, 450, 50);
			g.drawLine(450, 450, 50, 450);
			
			//drawing lines
			for (int i = 0; i < lines.length; i++) {
				g.drawLine(50 + (int) lines[i].p1.x, 450 - (int) lines[i].p1.y, 50 + (int) lines[i].p2.x, 450 - (int) lines[i].p2.y);
				if (lines[i].p1.x == 0) {
					g.drawString(String.valueOf(lines[i].name+1), 40, 450 - (int) lines[i].p1.y);
				} else if (lines[i].p1.x == 400) {
					g.drawString(String.valueOf(lines[i].name+1), 460, 450 - (int) lines[i].p1.y);
				} else if (lines[i].p1.y == 0) {
					g.drawString(String.valueOf(lines[i].name+1), 50 + (int) lines[i].p1.x, 460);
				} else if (lines[i].p1.y == 400) {
					g.drawString(String.valueOf(lines[i].name+1), 50 + (int) lines[i].p1.x, 40);
				}
			}
			
			//drawing two test points
			g.setColor(Color.CYAN);
			if (points[0] != null) {
				g.fillOval((int) points[0].x-8, (int) points[0].y-83, 4, 4);
			}
			if (points[1] != null) {
				g.fillOval((int) points[1].x-8, (int) points[1].y-83, 4, 4);
			}
		}
	}

	
	//getting the two points
	@Override
	public void mouseClicked(MouseEvent e) {
		if (points[1] == null) {
			int x = e.getX();
			int y = e.getY();
			
			if (x < 58 || x > 458 || y < 133 || y > 533) {
				message.removeAll();
				message.setLayout(new BorderLayout());
				message.add(messageLabel, BorderLayout.CENTER);
				messageLabel.setText("Please click inside the graph");
			} else {
				if (points[0] == null) {
					points[0] = new Point(x, y);
					message.removeAll();
					message.setLayout(new GridLayout(2, 2));
					message.add(p1coordLabel);
					message.add(p2coordLabel);
					p1coord = "Point 1: " + ((x-58)/400.0) + ", " + (1.0-(y-133)/400.0);
					p1coordLabel.setText(p1coord);
				} else {
					points[1] = new Point(x, y);
					message.removeAll();
					message.setLayout(new GridLayout(2, 2));
					message.add(p1coordLabel);
					message.add(p2coordLabel);
					p2coord = "Point 2: " + ((x-58)/400.0) + ", " + (1.0-(y-133)/400.0);
					p2coordLabel.setText(p2coord);
					message.add(messageLabel);
					crossingLine = test(points[0], points[1]);
					if (crossingLine == null) {
						messageLabel.setText("There are no lines that cross the two given points");
					} else {
						messageLabel.setText("Line " + (test(points[0], points[1]).name+1) + " crosses the two given points");
					}
				}
			}
		}
		
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	public Line test(Point p1, Point p2) {
		Line comp = new Line(new Point((p1.x - 58)/400, 1-(p1.y - 133)/400), new Point((p2.x - 58)/400, 1-(p2.y - 133)/400), -1);
		return tree.test(comp);
	}
	
	//retest function for the retest button
	public void retest() {
		points = new Point[2];
		message.removeAll();
		messageLabel = new JLabel("Click on the graph to get the 2 points");
		p1coordLabel = new JLabel("");
		p2coordLabel = new JLabel("");
		message.add(messageLabel);
		
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == retest) {
			retest();
		}
	}
}
