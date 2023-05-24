import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Projectile extends GameObject{
    public static BufferedImage image;
    public static boolean needImage = true;
    public static boolean gotImage = false;

    public Projectile(int x, int y, int width, int height){
        super(x,y,width,height);
        this.speed = 10;
        if (needImage) {
            loadImage ("bullet.png");
        }
    }

    public void draw(Graphics g){
        System.out.println(y);

        if (gotImage) {
            g.drawImage(image, x, y, width, height, null);
        } else {
            g.setColor(Color.RED);
            g.fillRect(x, y, width, height);
        }
    }

    public void update(){
        System.out.println("asdasdsadasdasd");
        y -= speed;
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
