package first;
/*>> Instruction <<
 * Control left shoulder: e(up) r(down)
 * Control left elbow: w(up) q(down)
 * Control right shoulder: u(up) i(down)
 * Control right elbow: o(up) p(down)
 * Control left thigh: d(up) f(down)
 * Control left shin: a(up) s(down)
 * Control right thigh: h(up) j(down)
 * Control right shin: k(up) l(down)
 * Control head: g(spin)
 * Control body X: t(spin)
 * Control body Y: y(spin): */

import java.awt.Frame;
import java.io.IOException;
import jgl.GL;
import jgl.GLCanvas;
import jgl.glu.GLUquadricObj;

public class Robot extends GLCanvas {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GLUquadricObj qobj;
	static int leftShoulderX = 0;
	static int leftShoulderZ = 0;
	static int leftElbow = 0;
	static int leftThigh = 0;
	static int leftShin = 0;
	static int rightShoulderX = 0;
	static int rightShoulderZ = 0;
	static int rightElbow = 0;
	static int rightThigh = 0;
	static int rightShin = 0;
	static int bodyX = 0;
	static int bodyY = 0;
	static int head = 0;
	static double temp = 0;
	
	float no_mat[] = { 0.0f, 0.0f, 0.0f, 1.0f };
	float skin_diffuse[] = { 0.37f, 0.27f, 0.18f, 1.0f };
	float hat_diffuse[] = { 0.81f, 0.16f, 0.16f, 1.0f };
	float white_pant_diffuse[] = { 1f, 1f, 1f, 1.0f };
	float red_pant_diffuse[] = { 1f, 0f, 0f, 1.0f };
	float blue_pant_diffuse[] = { 0f, 0f, 1f, 1.0f };
	float muscle_diffuse[] = { 0.30f, 0.23f, 0.17f ,1.0f };
	float face_diffuse[] = { 0.0f, 0.0f, 0.0f ,1.0f };

	private void myinit() {
		myGL.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		myGL.glShadeModel(GL.GL_FLAT);
		float ambient[] = { 0.0f, 0.0f, 0.0f, 1.0f };
		float diffuse[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		float position[] = { 0.0f, 3.0f, 2.0f, 0.0f };
		float lmodel_ambient[] = { 0.4f, 0.4f, 0.4f, 1.0f };
		float local_view[] = { 0.0f };

		myGL.glEnable(GL.GL_DEPTH_TEST);
		myGL.glDepthFunc(GL.GL_LESS);

		myGL.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, ambient);
		myGL.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, diffuse);
		myGL.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, position);
		myGL.glLightModelfv(GL.GL_LIGHT_MODEL_AMBIENT, lmodel_ambient);
		myGL.glLightModelfv(GL.GL_LIGHT_MODEL_LOCAL_VIEWER, local_view);

		myGL.glEnable(GL.GL_LIGHTING);
		myGL.glEnable(GL.GL_LIGHT0);
	}

	public void myReshape(int w, int h) {
		myGL.glViewport(0, 0, w, h);
		myGL.glMatrixMode(GL.GL_PROJECTION);
		myGL.glLoadIdentity();
		myGLU.gluPerspective(60.0, (double) w / (double) h, 1.0, 20.0);
		myGL.glMatrixMode(GL.GL_MODELVIEW);
		myGL.glLoadIdentity();
		myGL.glTranslatef(0.0f, 0.0f, -5.0f);
	}

	public void init() {
		myUT.glutInitWindowSize(480, 480);
		myUT.glutInitWindowPosition(0, 0);
		myUT.glutCreateWindow(this);
		myinit();
		myUT.glutDisplayFunc("display");
		myUT.glutReshapeFunc("myReshape");
		myUT.glutKeyboardFunc("keyboard");
		myUT.glutMainLoop();
	}

	public void display() {
		// Clear screen
		myGL.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

		// Put all body parts in to stack
		myGL.glPushMatrix();

		drawBody();
		drawRightArm();
		drawLeftArm();
		drawRightLeg();
		drawLeftLeg();
		drawNeck();
		drawHead();

		/* Ricardo Part */
		drawChest();
		drawPant();
		drawHat();
		drawMuscle();
		drawFace();

		// Take all body parts out of stack
		myGL.glPopMatrix();
		myGL.glFlush();
	}

	private void drawBody() {
		myGL.glColor3f(0.37f, 0.27f, 0.18f);
		myGL.glTranslatef(0.0f, 0.4f, 0.0f);
//		myGLU.gluLookAt(0, 0, temp, 0, 0, 0, 0, 1, 0);
		myGL.glRotatef((float) bodyX, 1.0f, 0.0f, 0.0f);
		myGL.glRotatef((float) bodyY, 0.0f, 1.0f, 0.0f);
		myGL.glPushMatrix();
		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, skin_diffuse);
		myGL.glScalef(1.0f, 2f, 0.5f);
		myUT.glutSolidCube(1.0);
		myGL.glPopMatrix();
		myGL.glFlush();
	}

	private void drawRightArm() {
		myGL.glPushMatrix();

		// Draw Right Shoulder
		myGL.glTranslatef(0.4f, 0.83f, 0.0f);
		myGL.glRotatef((float) rightShoulderX, 1.0f, 0.0f, 0.0f);
		myGL.glRotatef((float) rightShoulderZ, 0.0f, 0.0f, 1.0f);
		myGL.glTranslatef(0.5f, 0.0f, 0.0f);
		myGL.glPushMatrix();
		myGL.glScalef(0.9f, 0.4f, 0.2f);
		myUT.glutSolidCube(1.0);
		myGL.glPopMatrix();
		// Draw Right Elbow
		myGL.glTranslatef(0.5f, 0.0f, 0.1f);
		myGL.glRotatef((float) rightElbow, 0.0f, 0.0f, 1.0f);
		myGL.glPushMatrix();
		qobj = myGLU.gluNewQuadric();
		myGL.glRotatef(100f, 0.0f, 1.0f, 0.0f);
		myGL.glTranslatef(0.1f, 0.0f, 0.0f);
		myGLU.gluCylinder(qobj, 0.2, 0.15, 1.0, 15, 5);
		myGL.glPopMatrix();

		myGL.glPopMatrix();
		myGL.glFlush();
	}

	private void drawLeftArm() {
		myGL.glPushMatrix();

		// Draw Left Shoulder
		myGL.glTranslatef(-0.4f, 0.83f, 0.0f);
		myGL.glRotatef((float) leftShoulderX, 1.0f, 0.0f, 0.0f);
		myGL.glRotatef((float) leftShoulderZ, 0.0f, 0.0f, 1.0f);
		myGL.glTranslatef(-0.5f, 0.0f, 0.0f);
		myGL.glPushMatrix();
		myGL.glScalef(0.9f, 0.4f, 0.2f);
		myUT.glutSolidCube(1.0);
		myGL.glPopMatrix();
		// Draw Left Elbow
		myGL.glTranslatef(-0.5f, 0.0f, 0.5f);
		myGL.glRotatef((float) leftElbow, 0.0f, 0.0f, 1.0f);
		myGL.glPushMatrix();
		myGL.glRotatef(-100f, 0.0f, 1.0f, 0.0f);
		myGL.glTranslatef(-0.5f, 0.0f, 0.0f);
		qobj = myGLU.gluNewQuadric();
		myGLU.gluCylinder(qobj, 0.2, 0.15, 1.0, 15, 5);
		myGL.glPopMatrix();

		myGL.glPopMatrix();
		myGL.glFlush();
	}

	private void drawRightLeg() {
		myGL.glPushMatrix();

		// Draw Right Thigh
		myGL.glTranslatef(0.3f, -1f, 0.0f);
		myGL.glRotatef((float) rightThigh, 1.0f, 0.0f, 0.0f);
		myGL.glTranslatef(0f, -0.5f, 0.0f);
		myGL.glPushMatrix();
		myGL.glScalef(0.5f, 1.0f, 0.5f);
		myUT.glutSolidCube(1.0);
		myGL.glPopMatrix();
		// Draw Right Shin
		myGL.glTranslatef(0.0f, -0.5f, 0.0f);
		myGL.glRotatef((float) rightShin, 1.0f, 0.0f, 0.0f);
		myGL.glPushMatrix();
		qobj = myGLU.gluNewQuadric();
		myGL.glRotatef(100f, 1.0f, 0.0f, 0.0f);
		// myGL.glTranslatef(0f, -0.5f, 0.0f);
		myGLU.gluCylinder(qobj, 0.25, 0.15, 1.0, 15, 5);
		myGL.glPopMatrix();

		myGL.glPopMatrix();
		myGL.glFlush();
	}

	private void drawLeftLeg() {
		myGL.glPushMatrix();

		// Draw Left Thigh
		myGL.glTranslatef(-0.3f, -1f, 0.0f);
		myGL.glRotatef((float) leftThigh, 1.0f, 0.0f, 0.0f);
		myGL.glTranslatef(0f, -0.5f, 0.0f);
		myGL.glPushMatrix();
		myGL.glScalef(0.5f, 1.0f, 0.5f);
		myUT.glutSolidCube(1.0);
		myGL.glPopMatrix();
		// Draw Left Shin
		myGL.glTranslatef(0.0f, -0.5f, -0.05f);
		myGL.glRotatef((float) leftShin, 1.0f, 0.0f, 0.0f);
		myGL.glPushMatrix();
		qobj = myGLU.gluNewQuadric();
		myGL.glRotatef(100f, 1.0f, 0.0f, 0.0f);
		// myGL.glTranslatef(0f, -0.5f, 0.0f);
		myGLU.gluCylinder(qobj, 0.25, 0.15, 1.0, 15, 5);
		myGL.glPopMatrix();

		myGL.glPopMatrix();
		myGL.glFlush();
	}

	private void drawNeck() {
		myGL.glPushMatrix();

		myGL.glTranslatef(0f, 1.1f, 0.0f);
		myGL.glPushMatrix();
		myGL.glScalef(0.3f, 0.3f, 0.3f);
		myUT.glutSolidCube(1.0);
		myGL.glPopMatrix();

		myGL.glPopMatrix();
		myGL.glFlush();
	}

	private void drawHead() {
		myGL.glPushMatrix();

		myGL.glTranslatef(0f, 1.6f, 0.0f);
		myGL.glRotatef((float) head, 0.0f, 1.0f, 0.0f);
		myGL.glPushMatrix();
		myGL.glScalef(0.8f, 0.8f, 0.6f);
		myUT.glutSolidCube(1.0);
		myGL.glPopMatrix();

		myGL.glPopMatrix();
		myGL.glFlush();
	}

	private void drawChest() {
		myGL.glPushMatrix();

		myGL.glTranslatef(0f, 0.55f, 0.0f);
		myGL.glPushMatrix();
		myGL.glScalef(1.3f, 0.8f, 0.8f);
		myUT.glutSolidCube(1.0);
		myGL.glPopMatrix();

		myGL.glPopMatrix();
		myGL.glFlush();
	}

	private void drawHat() {
		myGL.glColor3f(0.81f, 0.16f, 0.16f);
		myGL.glPushMatrix();

		myGL.glTranslatef(0f, 1.8f, 0.0f);
		myGL.glRotatef((float) head, 0.0f, 1.0f, 0.0f);
		myGL.glPushMatrix();
		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, hat_diffuse);
		myGL.glScalef(0.9f, 0.4f, 0.8f);
		myUT.glutSolidCube(1.0);
		myGL.glPopMatrix();

		myGL.glPopMatrix();
		myGL.glFlush();
	}

	private void drawPant() {
		// White
		// //Stack in
		myGL.glColor3f(1f, 1f, 1f);
		myGL.glPushMatrix();
		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, white_pant_diffuse);

		myGL.glTranslatef(0f, -0.75f, 0.0f);
		myGL.glPushMatrix();
		myGL.glScalef(1.03f, 0.05f, 0.6f);
		myUT.glutSolidCube(1.0);
		myGL.glPopMatrix();

		myGL.glTranslatef(0f, -0.1f, 0.0f);
		myGL.glPushMatrix();
		myGL.glScalef(1.05f, 0.05f, 0.7f);
		myUT.glutSolidCube(1.0);
		myGL.glPopMatrix();

		myGL.glTranslatef(0f, -0.1f, 0.0f);
		myGL.glPushMatrix();
		myGL.glScalef(1.05f, 0.05f, 0.7f);
		myUT.glutSolidCube(1.0);
		myGL.glPopMatrix();

		// Stack out
		myGL.glPopMatrix();

		// Red
		// //Stack in
		myGL.glColor3f(1f, 0.0f, 0.0f);
		myGL.glPushMatrix();
		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, red_pant_diffuse);
		
		myGL.glTranslatef(0f, -0.8f, 0.0f);
		myGL.glPushMatrix();
		myGL.glScalef(1.03f, 0.05f, 0.6f);
		myUT.glutSolidCube(1.0);
		myGL.glPopMatrix();

		myGL.glTranslatef(0f, -0.1f, 0.0f);
		myGL.glPushMatrix();
		myGL.glScalef(1.05f, 0.05f, 0.7f);
		myUT.glutSolidCube(1.0);
		myGL.glPopMatrix();

		myGL.glTranslatef(0f, -0.12f, 0.0f);
		myGL.glPushMatrix();
		myGL.glScalef(1.05f, 0.05f, 0.7f);
		myUT.glutSolidCube(1.0);
		myGL.glPopMatrix();

		// Stack out
		myGL.glPopMatrix();

		// Blue
		// //Stack in
		myGL.glColor3f(0f, 0f, 1f);
		myGL.glPushMatrix();
		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, blue_pant_diffuse);
		
		myGL.glTranslatef(-0.25f, -0.75f, 0.0f);
		myGL.glPushMatrix();
		myGL.glScalef(0.55f, 0.05f, 0.7f);
		myUT.glutSolidCube(1.0);
		myGL.glPopMatrix();

		myGL.glTranslatef(0f, -0.05f, 0.0f);
		myGL.glPushMatrix();
		myGL.glScalef(0.55f, 0.05f, 0.7f);
		myUT.glutSolidCube(1.0);
		myGL.glPopMatrix();

		// Stack out
		myGL.glPopMatrix();
		myGL.glFlush();
	}

	private void drawMuscle() {
		myGL.glColor3f(0.30f, 0.23f, 0.17f);
		myGL.glPushMatrix();
		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, muscle_diffuse);

		// Vertical
		myGL.glTranslatef(0f, -0.3f, 0.3f);
		myGL.glPushMatrix();
		myGL.glScalef(0.05f, 0.5f, 0.05f);
		myUT.glutSolidCube(1.0);
		myGL.glPopMatrix();

		// Horizon top
		myGL.glTranslatef(0f, 0.1f, 0.0f);
		myGL.glPushMatrix();
		myGL.glScalef(0.5f, 0.02f, 0.05f);
		myUT.glutSolidCube(1.0);
		myGL.glPopMatrix();

		//Horizon bottom
		myGL.glTranslatef(0f, -0.2f, 0.0f);
		myGL.glPushMatrix();
		myGL.glScalef(0.5f, 0.02f, 0.05f);
		myUT.glutSolidCube(1.0);
		myGL.glPopMatrix();

		// Right tit
		myGL.glTranslatef(0.5f, 0.8f, 0.1f);
		myGL.glPushMatrix();
		myGL.glScalef(0.03f, 0.03f, 0.03f);
		myUT.glutSolidCube(1.0);
		myGL.glPopMatrix();

		// Left tit
		myGL.glTranslatef(-1f, 0f, 0.0f);
		myGL.glPushMatrix();
		myGL.glScalef(0.03f, 0.03f, 0.03f);
		myUT.glutSolidCube(1.0);
		myGL.glPopMatrix();

		// Stack out
		myGL.glPopMatrix();
		myGL.glFlush();
	}

	private void drawFace() {
		myGL.glColor3f(0.0f, 0.0f, 0.0f);
		myGL.glPushMatrix();
		myGL.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, face_diffuse);

		// Right eye
		myGL.glRotatef((float) head, 0.0f, 1.0f, 0.0f);
		myGL.glTranslatef(0.2f, 1.5f, 0.3f);
		myGL.glPushMatrix();
		myGL.glScalef(0.1f, 0.1f, 0.1f);
		myUT.glutSolidCube(1.0);
		myGL.glPopMatrix();

		// Left eye
		myGL.glTranslatef(-0.4f, 0.0f, 0.0f);
		myGL.glPushMatrix();
		myGL.glScalef(0.1f, 0.1f, 0.1f);
		myUT.glutSolidCube(1.0);
		myGL.glPopMatrix();

		// Mouth
		myGL.glTranslatef(0.2f, -0.2f, 0.0f);
		myGL.glPushMatrix();
		myGL.glScalef(0.5f, 0.03f, 0.03f);
		myUT.glutSolidCube(1.0);
		myGL.glPopMatrix();

		// Stack out
		myGL.glPopMatrix();
		myGL.glFlush();
	}

	// Controller
	public void keyboard(char key, int x, int y) {
		switch (key) {
		case '[':
			temp = (temp + 0.01) % 1000000;
			myUT.glutPostRedisplay();
			break;
		case ']':
			temp = (temp - 0.01) % 1000000;
			myUT.glutPostRedisplay();
			break;
		case 'w':
			leftShoulderZ = (leftShoulderZ + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'W':
			leftShoulderZ = (leftShoulderZ - 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'q':
			leftElbow = (leftElbow + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'Q':
			leftElbow = (leftElbow - 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'e':
			rightShoulderZ = (rightShoulderZ + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'E':
			rightShoulderZ = (rightShoulderZ - 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'r':
			rightElbow = (rightElbow + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'R':
			rightElbow = (rightElbow - 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 's':
			leftThigh = (leftThigh + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'S':
			leftThigh = (leftThigh - 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'a':
			leftShin = (leftShin + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'A':
			leftShin = (leftShin - 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'd':
			rightThigh = (rightThigh + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'D':
			rightThigh = (rightThigh - 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'f':
			rightShin = (rightShin + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'F':
			rightShin = (rightShin - 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'u':
			bodyX = (bodyX + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'i':
			bodyY = (bodyY + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'o':
			head = (head + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 'y':
			rightShoulderX = (rightShoulderX + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 't':
			leftShoulderX = (leftShoulderX + 5) % 360;
			myUT.glutPostRedisplay();
			break;
		case 27:
			System.exit(0);
		default:
			break;
		}
	}

	static public void main(String args[]) throws IOException {
		Frame mainFrame = new Frame();
		mainFrame.setSize(500, 500);
		Robot mainCanvas = new Robot();
		mainCanvas.init();
		mainFrame.add(mainCanvas);
		mainFrame.setVisible(true);
	}
}
