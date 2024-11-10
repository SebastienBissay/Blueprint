import processing.core.PApplet;

import java.util.ArrayList;

import static parameters.Parameters.*;
import static save.SaveUtil.saveSketch;

public class Blueprint extends PApplet {

    public boolean[][] grid;
    private ArrayList<Walker> walkers;


    public static void main(String[] args) {
        PApplet.main(Blueprint.class);
    }

    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
        randomSeed(SEED);
        noiseSeed(floor(random(MAX_INT)));
    }

    @Override
    public void setup() {
        background(BACKGROUND_COLOR.red(), BACKGROUND_COLOR.green(), BACKGROUND_COLOR.blue());
        // Just go as fast as possible
        frameRate(-1);
        blendMode(NORMAL);

        grid = new boolean[width][height];

        walkers = new ArrayList<>();
        for (int k = 0; k < STARTING_WALKER_NUMBER; k++) {
            createWalker();
        }
    }

    @Override
    public void draw() {

        if (walkers.isEmpty()) {
            noLoop();
            saveSketch(this);
        }
        for (int i = walkers.size() - 1; i > -1; i--) {
            Walker w = walkers.get(i);
            w.move();
            if (!w.isAlive()) {
                walkers.remove(w);
            } else {
                w.update();
            }
        }
        createWalker();
    }

    private void createWalker() {
        int x = floor(random(MARGIN, width - MARGIN));
        int y = floor(random(MARGIN, height - MARGIN));
        if (!grid[x][y]) {
            walkers.add(new Walker(x, y, this));
            grid[x][y] = true;
        }
    }
}
