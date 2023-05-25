import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.lang.Math;

public class ObjectManager implements ActionListener{
    public RocketShip rocket;
    public ArrayList<Projectile> projs;
    public ArrayList<Alien> aliens;
    public ArrayList<Alien2> alien2s;
    public final static double ALIEN2PROB = 0.35;

    public Random rand;
    public int score;
    public static final int SECPERAMMO = 3;
    public int progress;

    
    public ObjectManager(RocketShip r){
        this.rocket = r;
        this.projs = new ArrayList<>();
        this.aliens = new ArrayList<>();
        this.alien2s = new ArrayList<>();

        this.rand = new Random();
        addAlien(new Alien(rand.nextInt(LeagueInvaders.WIDTH),0,50,50));
        addAlien2(new Alien2(rand.nextInt(LeagueInvaders.WIDTH),0,50,50));
        this.score = 0;
        this.progress = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        progress += 1;
        if (progress % SECPERAMMO == 0){
            rocket.ammo += 1;
        }
        if (Math.random() > ALIEN2PROB){
            addAlien(new Alien(rand.nextInt(LeagueInvaders.WIDTH),0,50,50));

        }else{
        addAlien2(new Alien2(rand.nextInt(LeagueInvaders.WIDTH),0,50,50));
        }
    }

    

    
    public void addProjectile(Projectile p ){
        projs.add(p);
    }
    public void addAlien(Alien a){
        aliens.add(a);
    }
    public void addAlien2(Alien2 a){
        alien2s.add(a);
    }

    public void update(){
        for (Alien a :aliens){
            a.update();
        }

        for (Projectile p :projs){
            p.update();   
        }

        for (Alien2 a2: alien2s){
            a2.update();
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

        for (Alien2 a2: alien2s){
            a2.draw(g);
        }
        rocket.draw(g);
        g.setColor(Color.BLUE);
        g.drawString("ammo: " + rocket.ammo, 5, LeagueInvaders.HEIGHT - 8);
    }

    public void purge(){
        ArrayList<Alien2> bada2 = new ArrayList<>();
        for (Alien2 a :alien2s){
            if (! a.isactive){
                bada2.add(a);
            }
        }
        alien2s.removeAll(bada2);


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
        for (Alien2 a2: alien2s){
            for (Projectile p: projs){
                if (a2.rect.intersects(p.rect)){
                    p.isactive = false;
                    if (a2.health > 1){
                        a2.health -= 1;    
                        rocket.ammo += RocketShip.HITAMMO;
                    } else {
                        score += 1;
                        a2.isactive = false;
                        rocket.ammo += RocketShip.KILLAMMO;
                    }
                }
            }
            if (a2.rect.intersects(rocket.rect)){
                a2.isactive = false;
                rocket.isactive = false;
            }
        }
    

        for (Alien a: aliens){
            for (Projectile p: projs){
                if (a.rect.intersects(p.rect)){
                    p.isactive = false;
                    if (a.health > 1){
                        a.health -= 1;    
                        rocket.ammo += RocketShip.HITAMMO;
                    } else {
                        score += 1;
                        a.isactive = false;
                        rocket.ammo += RocketShip.KILLAMMO;
                    }
                }
            }
            if (a.rect.intersects(rocket.rect)){
                a.isactive = false;
                rocket.isactive = false;
            }
        }
    }

}
