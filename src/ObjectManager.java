import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
    public RocketShip rocket;
    public ArrayList<Projectile> projs;
    public ArrayList<Alien> aliens;
    public Random rand;
    

    
    public ObjectManager(RocketShip r){
        this.rocket = r;
        this.projs = new ArrayList<>();
        this.aliens = new ArrayList<>();
        this.rand = new Random();
        aliens.add(new Alien(rand.nextInt(LeagueInvaders.WIDTH),0,50,50));



    }

    // @Override
    public void actionPerformed(){
        Alien a = new Alien(20,20 ,50 , 50);
        addAlien(a);
    }

    

    
    public void addProjectile(Projectile p ){
        projs.add(p);
    }

    public void addAlien(Alien a){
        aliens.add(a);
    }

    public void update(){
        for (Alien a :aliens){
            if (a.y < LeagueInvaders.HEIGHT){
                a.update();
            }
            else{
                a.isactive = false;
            }
        }

        for (Projectile p :projs){
            if (p.y < LeagueInvaders.HEIGHT){
                p.update();
            }
            else{
                p.isactive = false;
            }
        }
    }

    public void draw(Graphics g){
        for (Projectile p: projs){
            p.draw(g);
        }
        for (Alien a: aliens){
            a.draw(g);
        }
        rocket.draw(g);
    }

    public void purge(){
        ArrayList<Alien> bada = new ArrayList<>();
        for (Alien a :aliens){
            if (! a.isactive){
                bada.add(a);
            }
        }
        aliens.removeAll(bada);

        ArrayList<Projectile> badp = new ArrayList<>();
        for (Projectile p :projs){
            if (! p.isactive){
                badp.add(p);
            }
        }
        projs.removeAll(badp);
    }


}
