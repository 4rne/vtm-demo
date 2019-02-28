package vtmDemo;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import org.oscim.awt.AwtGraphics;
import org.oscim.backend.GLAdapter;
import org.oscim.core.Tile;
import org.oscim.gdx.GdxAssets;
import org.oscim.gdx.GdxMap;
import org.oscim.gdx.LwjglGL20;
import org.oscim.utils.FastMath;

import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.utils.SharedLibraryLoader;

public class MyMapApp extends GdxMap
{
	public static void run(GdxMap map)
	{
		run(map, null, Tile.SIZE);
	}

	public static void run(GdxMap map, LwjglApplicationConfiguration config, int tileSize)
	{
		Tile.SIZE = FastMath.clamp(tileSize, 128, 512);

		LwjglAWTCanvas mapC = new LwjglAWTCanvas(map, getConfig(map.getClass().getSimpleName()));
		JFrame f = new JFrame("Swing map demo");
		f.getContentPane().add(mapC.getCanvas(), BorderLayout.CENTER);
		f.setSize(600, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	protected static LwjglApplicationConfiguration getConfig(String title)
	{
		LwjglApplicationConfiguration.disableAudio = true;
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();

		cfg.stencil = 8;
		// cfg.samples = 2;
		cfg.foregroundFPS = 30;
		cfg.backgroundFPS = 10;
		return cfg;
	}

	public static void init()
	{
		// load native library
		new SharedLibraryLoader().load("vtm-jni");
		// init globals
		AwtGraphics.init();
		GdxAssets.init("assets/");
		GLAdapter.init(new LwjglGL20());
	}
}
