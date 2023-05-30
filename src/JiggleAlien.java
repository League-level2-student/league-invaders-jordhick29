import java.lang.Math;
public class JiggleAlien extends AbstractAlien{
    public static final int HP = 2;
    public static final int SPEED = 1;
    public static final int HORIZSPEED = 10;
    public static final int WEIGHT = 1;
    public static final Integer ID = 2;
        
    public JiggleAlien(int x, int y, int width, int height){
        super(x,y,width,height, HP);
    }

    public void update(){
        y += SPEED;
        if (Math.random() > 0.5){
            x += HORIZSPEED;
        } else {
            x -= HORIZSPEED;
        }
        super.update();
    }

    public static JiggleAlien spawn(){
        return new JiggleAlien(rand.nextInt(LeagueInvaders.WIDTH),0,50,50);
    }

    


}
