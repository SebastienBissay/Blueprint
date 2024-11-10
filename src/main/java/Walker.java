import processing.core.PVector;

import static parameters.Parameters.*;
import static processing.core.PApplet.*;
import static processing.core.PConstants.TWO_PI;

public class Walker {
    int x;
    int y;
    float blue;
    PVector direction;
    Blueprint blueprint;

    Walker(int x, int y, Blueprint blueprint) {
        this.blueprint = blueprint;
        this.x = x;
        this.y = y;
        direction = PVector.fromAngle(this.blueprint.random(TWO_PI));
        blue = constrain((MIN_BLUE + MAX_BLUE) * (.5f + .1f * this.blueprint.randomGaussian()),
                MIN_BLUE,
                MAX_BLUE);
    }

    void move() {
        PVector noise = PVector.fromAngle(blueprint.noise(x / NOISE_SCALE, y / NOISE_SCALE) * TWO_PI);
        direction.add(noise.mult(MAGNITUDE_MULTIPLIER * blueprint.noise(x / NOISE_SCALE, y / NOISE_SCALE)));
        if (direction.magSq() > 1) {
            blue = min(blue + BLUE_SHIFT_AMOUNT, MAX_BLUE);
        } else if (direction.magSq() < 1) {
            blue = max(blue - BLUE_SHIFT_AMOUNT, MIN_BLUE);
        }
        direction.normalize();
        x = (int) (x + direction.x);
        y = (int) (y + direction.y);
        blueprint.stroke(0, 0, blue);
        blueprint.point(x, y);
    }

    boolean isAlive() {
        if (x < MARGIN
                || x >= WIDTH - MARGIN
                || y < MARGIN
                || y >= HEIGHT - MARGIN) {
            return false;
        }
        return !blueprint.grid[x][y];
    }

    void update() {
        blueprint.grid[x][y] = true;
    }
}
