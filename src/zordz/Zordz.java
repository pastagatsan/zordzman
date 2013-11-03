package zordz;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import zordz.level.Level;
import zordz.state.GameState;
import zordz.state.State;
import zordz.state.TitleState;
import zordz.util.SoundPlayer;
import cjaf.tools.NewGLHandler;

public class Zordz {

	public static final int WIDTH = 640, HEIGHT = 480;
	public static final DisplayMode DISPLAY_MODE = new DisplayMode(WIDTH, HEIGHT);
	public Screen screen;
	public Level level;
	public int hp = 100;
	public State state;
	public TitleState titlestate;
	public GameState gamestate;
	public static Zordz zordz;
	
	public static void main(String[] args) {
		new Zordz().start();
	}

	public Zordz() {
		try {
			Display.setDisplayMode(DISPLAY_MODE);
			Display.setTitle("The Zordzman");
			Display.create();
			AL.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public void start() {
		init(); //Initialize variables, etc.
		run();  //Run the game!
		stop(); //Stop running the game, and close the window.
	}
	
	public void init() {
		NewGLHandler.init2D(WIDTH, HEIGHT);
		screen = new Screen(this);
		level = new Level();
		titlestate = new TitleState(this);
		gamestate = new GameState(this);
		state = titlestate;
		SoundPlayer.init();
		zordz = this;
	}
	
	public void run() {
		while (!Display.isCloseRequested()) {
			NewGLHandler.wipeScreen();
			screen.render();
			screen.update();
		}
	}
	
	public void stop() {
		Display.destroy();
		AL.destroy();
		System.exit(0);
	}
}
