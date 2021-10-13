import java.util.Random;
public class Screen extends Render{

    private Render test;

    public Screen(int width, int height){
        super(width, height);
        Random rand = new Random();
        test = new Render(512, 512);

        for(int i = 0; i <512 * 512; i++){
            test.pixels[i] = rand.nextInt();
        }
    }

    public void render(){
        draw(test, 0, 0);
    }
}
