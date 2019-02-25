import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.oscim.awt.AwtGraphics;
import org.oscim.backend.GLAdapter;
import org.oscim.core.Tile;
import org.oscim.gdx.GdxAssets;
import org.oscim.gdx.GdxMap;
import org.oscim.gdx.LwjglGL20;
import org.oscim.utils.FastMath;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.utils.SharedLibraryLoader;

public class MyMapApp extends GdxMap
{
    public static void run(GdxMap map) {
        run(map, null, Tile.SIZE);
    }
	
    public static void run(GdxMap map, LwjglApplicationConfiguration config, int tileSize) {
        Tile.SIZE = FastMath.clamp(tileSize, 128, 512);

//        new LwjglApplication(map, (config == null ? getConfig(map.getClass().getSimpleName()) : config));
        LwjglAWTCanvas mapC = new LwjglAWTCanvas(map, getConfig(map.getClass().getSimpleName()));
        JFrame f = new JFrame("Kartentest");
        f.getContentPane().add(mapC.getCanvas(), BorderLayout.CENTER);
        f.getContentPane().add(new JButton("my button"), BorderLayout.SOUTH);
        f.getContentPane().add(new JButton("my button"), BorderLayout.NORTH);
        f.setSize(600, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    
    protected static LwjglApplicationConfiguration getConfig(String title) {
        LwjglApplicationConfiguration.disableAudio = true;
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = title != null ? title : "vtm-gdx";

//        int[] sizes = new int[]{128, 64, 32, 16};
//        for (int size : sizes) {
//            String path = "res/ic_vtm_" + size + ".png";
//            cfg.addIcon(path, Files.FileType.Internal);
//        }

        cfg.width = 800;
        cfg.height = 600;
        cfg.stencil = 8;
        //cfg.samples = 2;
        cfg.foregroundFPS = 30;
        cfg.backgroundFPS = 10;
        cfg.forceExit = false;
        return cfg;
    }


    public static void init() {
        // load native library
        new SharedLibraryLoader().load("vtm-jni");
        // init globals
        AwtGraphics.init();
        GdxAssets.init("assets/");
        GLAdapter.init(new LwjglGL20());
    }
}
