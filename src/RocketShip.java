import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
public class RocketShip extends GameObject{
    public static BufferedImage image;
    public static boolean needImage = true;
    public static boolean gotImage = false;	
    public static final int KILLAMMO = 3;
    public static final int STARTAMMO = 10;
    public static final int MAXAMMO = 20;
    public static final int HITAMMO = 1;
    public int ammo;
    
    public RocketShip(int x, int y, int width, int height){
        super(x,y,width,height);
        this.speed = 30;
        this.ammo = STARTAMMO;
        if (needImage) {
            loadImage ("rocket.png");
        }
    }

    public void draw(Graphics g){
        super.update();

        if (gotImage) {
            g.drawImage(image, x, y, width, height, null);
        } else {
            g.setColor(Color.BLUE);
            g.fillRect(x, y, width, height);
        }
    }

    public Projectile getpProjectile(){
        return new Projectile(x+width/2, y, 10, 10);

    }

    public void up(){
        y -= speed;

    }

    public void down(){
        y += speed;
    }

    public void left(){
        x -= speed;
    }

    public void right(){
        x += speed;
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
