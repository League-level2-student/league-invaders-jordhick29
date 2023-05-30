import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Random;

public class AbstractAlien extends GameObject{
    public static BufferedImage image;
    public static boolean needImage = true;
    public static boolean gotImage = false;
    public static final Random rand = new Random();
    public int health;
    public int speed;


    public AbstractAlien(int x, int y, int width, int height, int health){
        super(x, y, width, height);
        this.health = health;
        if (needImage) {
            loadImage("alien.png");
        }
    }

    public void draw(Graphics g){
        if (gotImage) {
            g.drawImage(image, x, y, width, height, null);
        } else {
            g.setColor(Color.YELLOW);
            g.fillRect(x, y, width, height);
        }
    }

    public void update(){
        super.update();
    }

    public void loadImage(String imageFile) {
        if (needImage) {
            try {
                image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
            gotImage = true;
            } catch (Exception e) {
                
            }
            needImage = false;
        }
    }

}
