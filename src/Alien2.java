import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.lang.Math;
public class Alien2 extends Alien{
    public static BufferedImage image;
    public static boolean needImage = true;
    public static boolean gotImage = false;
    public int health;
    public int horizspeed;
    
    public Alien2(int x, int y, int width, int height){
        super(x,y,width,height);
        this.speed = 1;
        this.health = 2;
        this.horizspeed = 10;

    }

    public void update(){
        if (Math.random() > 0.5){
            x += horizspeed;
        } else {
            x -= horizspeed;
        }
        super.update();
    }


}
