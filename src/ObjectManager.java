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
    public ArrayList<AbstractAlien> aliens;
    public Random rand;
    public int score;
    public static final int SECPERAMMO = 3;
    public int progress;
    public ArrayList<Integer> choices;


    public ObjectManager(RocketShip r){
        choices = new ArrayList<>();
        rocket = r;
        projs = new ArrayList<>();
        aliens = new ArrayList<>();
        rand = new Random();
        score = 0;
        progress = 0;
        for (int i = 0; i < JiggleAlien.WEIGHT; i++){
            choices.add(JiggleAlien.ID);
        }
        for (int i = 0; i < BasicAlien.WEIGHT; i++){
            choices.add(BasicAlien.ID);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        progress += 1;
        if (progress == SECPERAMMO){
            rocket.ammo += 1;
            progress = 0;
        }
        spawnAlien();
    }

    

    
    public void addProjectile(Projectile p ){
        projs.add(p);
    }
    public void spawnAlien(){
        Integer classid =  choices.get((int) (Math.random() * choices.size()));
        if (classid == JiggleAlien.ID){
            aliens.add(JiggleAlien.spawn());
        }
        else if (classid == BasicAlien.ID){
            aliens.add(BasicAlien.spawn());
        }
        
    }

    public void update(){
        for (AbstractAlien a :aliens){
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
        for (AbstractAlien a: aliens){
            a.draw(g);
        }

        rocket.draw(g);
        g.setColor(Color.BLUE);
        g.drawString("ammo: " + rocket.ammo, 5, LeagueInvaders.HEIGHT - 8);
    }

    public void purge(){
        ArrayList<AbstractAlien> bada = new ArrayList<>();
        for (AbstractAlien a :aliens){
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
        for (AbstractAlien a: aliens){
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
