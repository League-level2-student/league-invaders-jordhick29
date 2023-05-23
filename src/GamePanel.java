import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
public class GamePanel extends JPanel{
   final int MENU = 0;
   final int GAME = 1;
   final int END = 2;
   public Font titleFont;
   public GamePanel(){
      this.titleFont = new Font("Arial",Font.PLAIN,48);
   }

   int currentState = MENU;
   public void updateMenuState() { 

   }
   public void updateGameState() { 

   }
   public void updateEndState()  {

   }

   public void drawMenuState(Graphics g) {
      System.out.println("asdasd");
      g.setColor(Color.BLUE);
      g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
      g.setFont(titleFont);
      g.setColor(Color.YELLOW);
      g.drawString("menu state text", 0, 0);

   }
   public void drawGameState(Graphics g) {  
      g.setColor(Color.BLACK);
      g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);

   }
   public void drawEndState(Graphics g)  { 
      g.setColor(Color.RED);
      g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
      g.setFont(titleFont);
      g.setColor(Color.YELLOW);
      g.drawString("end state text", 0, 0);

   }


   @Override
   public void paintComponent(Graphics g){
   if(currentState == MENU){
      System.out.println("ooyioyiy");
       drawMenuState(g);
   }else if(currentState == GAME){
       drawGameState(g);
   }else if(currentState == END){
       drawEndState(g);
   }

   }
}
