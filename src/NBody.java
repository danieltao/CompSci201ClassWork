import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {
	public static void main(String[] args) {
		double totalTime = 2.0e6;
		double dt = 25000.0;
		String pfile = "data/planets.txt";
		if (args.length > 2) {
			totalTime = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			pfile = args[2];
		}

		// Read simulation data from file
		Body[] planets = readBodies(pfile); // readBodies(pfile);
		double radius = readRadius(pfile); // readRadius(pfile);
		for (double time = 0.0; time < totalTime; time += dt) {
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for (int i = 0; i < planets.length; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for (int i = 0; i < planets.length; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			// TODO Draw the background
			StdDraw.setScale(-radius, radius);
			StdDraw.picture(0, 0, "images/starfield.jpg");
			// TODO Animate the simulation from time 0 until totalTime
			for (Body body : planets) {
				body.Draw();
			}
			StdDraw.show(10);
		}
		// Print final positions of planets
		System.out.printf("%d\n", planets.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", planets[i].myXPos, planets[i].myYPos,
					planets[i].myXVel, planets[i].myYVel, planets[i].myMass, planets[i].myFileName);
		}
	}

	public static double readRadius(String fname) {
		try {
			Scanner scan = new Scanner(new File(fname));
			double r = 0.0;
			scan.nextInt();
			r = scan.nextDouble();
			scan.close();
			return r;
		} catch (FileNotFoundException e) {
			System.out.println("No Such File!");
			System.exit(0);
			return 0.0;
		}
	}

	public static Body[] readBodies(String fname) {
		try {
			Scanner scan = new Scanner(new File(fname));
			scan.nextInt();
			scan.nextDouble();
			int a = 0;
			Body[] bodys = new Body[2000];
			while (scan.hasNextDouble()) {
				double xp = scan.nextDouble();
				double yp = scan.nextDouble();
				double xv = scan.nextDouble();
				double yv = scan.nextDouble();
				double mass = scan.nextDouble();
				String flname = scan.next();
				Body body = new Body(xp, yp, xv, yv, mass, flname);
				bodys[a] = body;
				a++;
			}
			scan.close();
			Body[] bodies = new Body[a];
			for (int i = 0; i < a; i++) {
				bodies[i] = bodys[i];
			}
			return bodies;
		} catch (FileNotFoundException e) {
			System.out.println("No Such File");
			Body[] bodys = new Body[0];
			return bodys;
		}
	}
}
