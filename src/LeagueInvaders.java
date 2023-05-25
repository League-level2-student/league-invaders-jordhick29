import javax.swing.JFrame;

public class LeagueInvaders {    
    public static final int WIDTH = 500;
    public static final int HEIGHT = 800;
    private JFrame member;
    private GamePanel memb;


    public LeagueInvaders(){
        this.member = new JFrame();
        this.memb = new GamePanel();
    }

    public void setup(){
        this.member.setSize(WIDTH + 0, HEIGHT + 27);
        // this.member.setSize(WIDTH + member.getInsets().left, HEIGHT + member.getInsets().top);

        this.member.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.member.add(memb);
        // this.memb.repaint();
        // this.member.pack();
        
        this.member.setVisible(true);
        this.member.addKeyListener(memb);
    }
    public static void main(String[] args) {
        LeagueInvaders le = new LeagueInvaders();
        le.setup();

    }
}
