import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
    public RocketShip rocket;
    public ArrayList<Projectile> projs;
    public ArrayList<Alien> aliens;
    public Random rand;
    public int score;
    

    
    public ObjectManager(RocketShip r){
        this.rocket = r;
        this.projs = new ArrayList<>();
        this.aliens = new ArrayList<>();
        this.rand = new Random();
        addAlien(new Alien(rand.nextInt(LeagueInvaders.WIDTH),0,50,50));
        this.score = 0;



    }

    @Override
    public void actionPerformed(ActionEvent e){
        addAlien(new Alien(rand.nextInt(LeagueInvaders.WIDTH),0,50,50));
    }

    

    
    public void addProjectile(Projectile p ){
        projs.add(p);
    }

    public void addAlien(Alien a){
        aliens.add(a);
    }

    public void update(){
        for (Alien a :aliens){
            a.update();
        }

        for (Projectile p :projs){
            p.update();   
        }

        checkcollision();
        purge();
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

    public void checkcollision(){
        for (Alien a: aliens){
            for (Projectile p: projs){
                if (a.rect.intersects(p.rect)){
                    a.isactive = false;
                    p.isactive = false;
                    score += 1;
                }
            }
            if (a.rect.intersects(rocket.rect)){
                a.isactive = false;
                rocket.isactive = false;
            }
        }
    }



}
