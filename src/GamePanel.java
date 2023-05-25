import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.lang.Math;

import javax.swing.Timer;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
   final int MENU = 0;
   final int GAME = 1;
   final int END = 2;
   public Timer alienSpawn;
   public ObjectManager manager;
   public RocketShip rocket;
   public KeyListener kl;
   public Timer frameDraw;
   public Font titleFont;
   public GamePanel(){
      this.titleFont = new Font("Arial",Font.PLAIN,48);
      this.frameDraw = new Timer(10,this);
      this.rocket = new RocketShip(250, 700, 50, 50);
      this.manager = new ObjectManager(rocket);
      frameDraw.start();

   }

   int currentState = MENU;
   public void updateMenuState() { 

   }
   public void updateGameState() { 
      manager.update();
      if (!rocket.isactive){
         currentState = END;
     }

   }
   public void updateEndState()  {


   }

   public void startgame(){
      alienSpawn = new Timer(1000 , manager);
      alienSpawn.start();
   }



   @Override
   public void keyTyped(KeyEvent e) {
       // TODO Auto-generated method stub
       

   }

   @Override
   public void keyReleased(KeyEvent e) {
       // TODO Auto-generated method stub
       
   }

   @Override
   public void keyPressed(KeyEvent e) {
       // TODO Auto-generated method stub
       if (e.getKeyCode()==KeyEvent.VK_ENTER) {
         if (currentState == END) {
             currentState = MENU;
             rocket = new RocketShip(250, 700, 50, 50);
             manager = new ObjectManager(rocket);

         } 
         else if (currentState == MENU) {
            currentState = GAME;
            startgame();
         }
         else if (currentState == GAME){
            currentState = END;
            alienSpawn.stop();
         }
     }   
     else if (e.getKeyCode()==KeyEvent.VK_UP) {
      if (rocket.y - rocket.speed > 0)
      rocket.up();
      }
      else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
         if (rocket.y + rocket.speed + rocket.height < LeagueInvaders.HEIGHT)
         rocket.down();
         }
      else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
         if (rocket.x + rocket.speed + rocket.width < LeagueInvaders.WIDTH)
      rocket.right();
      }
      else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
         if (rocket.x - rocket.speed > 0)
      rocket.left();
      }

      else if (e.getKeyCode() == KeyEvent.VK_SPACE && rocket.ammo > 0){
         rocket.ammo = Math.min(rocket.ammo - 1, RocketShip.MAXAMMO);
         manager.addProjectile(rocket.getpProjectile());

      }


   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      if(currentState == MENU){
         updateMenuState();
     }else if(currentState == GAME){
         updateGameState();
     }else if(currentState == END){
         updateEndState();
     }
     repaint();      
   }

   public void drawMenuState(Graphics g) {
      g.setColor(Color.BLUE);
      g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
      g.setFont(titleFont);
      g.setColor(Color.YELLOW);
      g.drawString("ENTER to play", 100, 300);
      g.drawString("LEAGUE INVADERS", 30, 150);

   }

   public void drawGameState(Graphics g) {  
      g.setColor(Color.BLACK);
      g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
      manager.draw(g);
   }

   public void drawEndState(Graphics g)  { 
      g.setColor(Color.RED);
      g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
      g.setFont(titleFont);
      g.setColor(Color.BLUE);
      g.drawString("LEAGUE INVADERS", 30, 150);
      g.drawString("score = " + manager.score, 100, 450);
      g.drawString("ENTER to continue", 25, 600);

   }


   @Override
   public void paintComponent(Graphics g){
   if(currentState == MENU){
       drawMenuState(g);
   }else if(currentState == GAME){
       drawGameState(g);
   }else if(currentState == END){
       drawEndState(g);
   }

   }
}
