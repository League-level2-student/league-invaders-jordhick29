public class BasicAlien extends AbstractAlien{
    public static final int HP = 3;
    public static final int SPEED = 3;
    public static final int WEIGHT = 1;
    public static final Integer ID = 1;

    public BasicAlien(int x, int y, int width, int height){
        super(x,y,width,height, HP);
    }

    public void update(){
        y += SPEED;
        super.update();
    }

    public static BasicAlien spawn(){
        return new BasicAlien(rand.nextInt(LeagueInvaders.WIDTH),0,50,50);
    }

}
