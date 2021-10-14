import java.awt.Canvas;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.awt.image.DataBufferInt;
import java.awt.Graphics;
import javax.swing.JFrame;


public class Display extends Canvas implements Runnable{
    public static int WIDTH = 800;
    public static int HEIGHT = 600;
    public static String title = "Minecraft clone!";

    private Thread thread;
    private boolean running = false;
    private Screen screen;
    private BufferedImage img;
    private int[] pixels;

    public Display(){
        this.screen = new Screen(WIDTH, HEIGHT);
        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
    }
    private void start(){
        if(running){
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();

        System.out.println("Working!");
    }
    private void stop() {
        if(!running){
            return;
        }
        running = false;
        try{
            thread.join();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }
    private void tick(){

    }
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        screen.render();

        for(int i = 0; i <WIDTH * HEIGHT; i++){
            pixels[i] =screen.pixels[i];
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
        g.dispose();
        bs.show();
    }

    
    
    public void run() {
        int frames = 0;
        double unprocessedSeconds = 0;
        long previouseTime = System.nanoTime();
        double secondsPerTick = 1 / 60.0;
        int tickCount = 0;
        boolean ticked = false;
        while (running){
            long currentTime = System.nanoTime();
            long passedTime = currentTime - previouseTime;
            previouseTime = currentTime;
            unprocessedSeconds += passedTime / 1000000000.0;
            while(unprocessedSeconds > secondsPerTick){
                tick();
                unprocessedSeconds -= secondsPerTick;
                ticked = true;
                tickCount++;
                if(tickCount % 60 == 0){
                    System.out.println(frames + "fps");
                    previouseTime += 1000;
                    frames = 0;
                }
            }
            if(ticked){
                render();
                frames++;
            }
            render();
            frames++;
        }
    }
    public static void main(String args[]){
        Display game = new Display();
        JFrame frame = new JFrame();
        frame.add(game);
        frame.pack();
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setTitle(title);

        game.start();
    }
}
