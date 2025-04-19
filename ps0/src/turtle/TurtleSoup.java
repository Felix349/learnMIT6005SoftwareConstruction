/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package turtle;

import java.util.List;
import java.util.ArrayList;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
    	// TODO 
    	double degrees = 90.0;
    	turtle.forward(sideLength);
    	turtle.turn(degrees);
    	turtle.forward(sideLength);
    	turtle.turn(degrees);
    	turtle.forward(sideLength);
    	turtle.turn(degrees);
    	turtle.forward(sideLength);
    	turtle.turn(degrees);
    	
        //throw new RuntimeException("implement me!");
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
    	// TODO
    	double angle;
    	if (sides <= 2) {
    		// error: sides <= 2
    		throw new RuntimeException("Sides must be > 2!");
    	}else {
    		angle = 180.0 * (sides - 2) / sides;
    		return angle;
    	}
        //throw new RuntimeException("implement me!");
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
    	// TODO
    	int sides;
    	if (angle <= 0 || angle >= 180) {
    		throw new RuntimeException("0 < angle < 180!");
    	}else {
    		sides = (int)Math.round(2 * 180 / (180 - angle));
    		return sides;
    	}
        
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
    	// TODO
    	double angles = calculateRegularPolygonAngle(sides);// calculate the angle of each turn
    	// repeat (forward and turn) for 'sides' times 
    	for (int i = 0; i < sides; i++) {
    		turtle.forward(sideLength);
        	turtle.turn(180.0 - angles);
    	}
        //throw new RuntimeException("implement me!");
    }

    /**
     * Given the current direction, current location, and a target location, calculate the heading
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentHeading. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentHeading current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to heading (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY,
                                                 int targetX, int targetY) {
    	// TODO
    	double headingVectorAngle;// the degree of angle of headingVector(from current pos to target pos)
    	double turningAngle;// the degree of angle of headingVector - currentHeading
    	
    	headingVectorAngle = Math.atan2(targetX - currentX, targetY - currentY) * 180 / Math.PI;// calculate degree of headingVector
    	if (headingVectorAngle < 0) {// make it positive
    		headingVectorAngle += 360;
    	}
    	turningAngle = headingVectorAngle -currentHeading;
    	if (turningAngle < 0) {// make it positive
    		turningAngle += 360;
    	}
    	return turningAngle;
        //throw new RuntimeException("implement me!");
    }

    /**
     * Given a sequence of points, calculate the heading adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateHeadingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of heading adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {
    	// TODO
    	List<Double> turningAnglesList = new ArrayList<Double>();
    	double currentHeading = 0; // initialize currentHeading angle to 0 (e.g. up direction)
    	double turningAngle; // the degree of the angle to turn clockwise from current pos to target pos
    	
    	if (xCoords.size() != yCoords.size()) {
    		throw new RuntimeException("xCoords and yCoords must have the same size!");
    	}else {
        	for (int i = 0; i < xCoords.size() - 1; i++) {// if xCoords.size() = n, then add n-1 turningAngles to the list
        		turningAngle = calculateHeadingToPoint(currentHeading, xCoords.get(i), yCoords.get(i),xCoords.get(i+1), yCoords.get(i+1));
        		currentHeading += turningAngle; // update the currentHeading angle
        		turningAnglesList.add(turningAngle);// add the turningAngle to the list
        	}
        	return turningAnglesList;
    	}
    	
        
    }

    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
    	// TODO
    	// I would like to draw a 5-pointed symmetrical star!
    	double turningAngle;
    	int units = 80;
    	double spike = 144;
    	double valley = -72 + 360;
    	PenColor[] colors = PenColor.values();
    	
    	turningAngle = 162;
    	turtle.turn(turningAngle); // initialize headingAngle
    	for (int i = 0; i < 5; i++) {
    		turtle.forward(units);
    		turningAngle = valley;
    		turtle.turn(turningAngle);
    		turtle.color(colors[(i) % colors.length]);
    		
    		turtle.forward(units);
    		turningAngle = spike;
    		turtle.turn(turningAngle);
    		turtle.color(colors[(i+1) % colors.length]);
    	}
    	
    	
        //throw new RuntimeException("implement me!");
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

        //drawSquare(turtle, 40);
        //drawRegularPolygon(turtle, 5, 80);
        drawPersonalArt(turtle);
        // draw the window
        turtle.draw();
    }

}
