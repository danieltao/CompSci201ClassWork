import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Body {
	double myXPos;
	double myYPos;
	double myXVel;
	double myYVel;
	double myMass;
	String myFileName;

	public Body(double xp, double yp, double xv, double yv, double mass, String filename) {
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
	}

	public Body(Body p) {
		myXPos = p.myXPos;
		myYPos = p.myYPos;
		myXVel = p.myXVel;
		myYVel = p.myYVel;
		myMass = p.myMass;
		myFileName = p.myFileName;
	}

	public double calcDistance(Body p) {
		double r = Math.sqrt((myXPos - p.myXPos) * (myXPos - p.myXPos) + (myYPos - p.myYPos) * (myYPos - p.myYPos));
		return r;
	}

	public double calcForceExertedBy(Body p) {
		double f = 6.67 * Math.pow(10, -11) * p.myMass * myMass / Math.pow(calcDistance(p), 2);
		return f;
	}

	public double calcForceExertedByX(Body p) {
		double f = calcForceExertedBy(p) * (-myXPos + p.myXPos) / calcDistance(p);
		return f;
	}

	public double calcForceExertedByY(Body p) {
		double f = calcForceExertedBy(p) * (-myYPos + p.myYPos) / calcDistance(p);
		return f;
	}

	public double calcNetForceExertedByX(Body[] ps) {
		double net = 0.0;
		for (Body p : ps) {
			if (!p.equals(this)) {
				net += calcForceExertedByX(p);
			}
		}
		return net;
	}

	public double calcNetForceExertedByY(Body[] ps) {
		double net = 0.0;
		for (Body p : ps) {
			if (!p.equals(this)) {
				net += calcForceExertedByY(p);
			}
		}
		return net;
	}

	public void update(double seconds, double xforce, double yforce) {
		double ax = xforce / myMass;
		double ay = yforce / myMass;
		myXVel += ax * seconds;
		myYVel += ay * seconds;
		myXPos += myXVel * seconds;
		myYPos += myYVel * seconds;
	}

	public void Draw() {
		StdDraw.picture(myXPos, myYPos, "images/" + myFileName);
	}
}
