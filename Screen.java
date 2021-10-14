import java.util.Random;
public class Screen extends Render{

    private Render test;

    public Screen(int width, int height){
        super(width, height);
        Random rand = new Random();
        test = new Render(256, 256);

        for(int i = 0; i <256 * 256; i++){
            test.pixels[i] = rand.nextInt();
        }
    }

    public void render(){
        test2();
    }
    private void test2(){
        for(int i = 0; i < width * height; i++){
            pixels[i] = 0;
        }
        for(int i = 0; i < 100; i++){
            int anim = (int)(Math.sin((System.currentTimeMillis()+i) % 2000.1 / 2000 * Math.PI * 2) * 100);
            int anim2 = (int)(Math.cos((System.currentTimeMillis()+i) % 2000.1 / 2000 * Math.PI * 2) * 100);
            draw(test, (width-256)/2 + anim, (height-256)/2 - anim2);
        }
    }
    private void test1(){
        for(int i = 0; i < width * height; i++){
            pixels[i] = 0;
        }
        int anim = (int)(Math.sin(System.currentTimeMillis() % 2000.1 / 2000 * Math.PI * 2) * 100);
        int anim2 = (int)(Math.cos(System.currentTimeMillis() % 2000.1 / 2000 * Math.PI * 2) * 100);
        draw(test, (width-256)/2 + anim, (height-256)/2 - anim2);
    }
    private void test3(){
        for(int i = 0; i < 100; i++){
            int anim = (int)(Math.sin((System.currentTimeMillis()+i) % 2000.1 / 2000 * Math.PI * 2) * 100);
            int anim2 = (int)(Math.cos((System.currentTimeMillis()+i) % 2000.1 / 2000 * Math.PI * 2) * 100);
            draw(test, (width-256)/2 + anim, (height-256)/2 - anim2);
        }
    }
}
