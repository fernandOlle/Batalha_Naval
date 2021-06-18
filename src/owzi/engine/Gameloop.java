package owzi.engine;

public class Gameloop {

    private Game game;
    private Thread gameloop;
    private EventHandler eventHandler;
    private boolean running;

    public Gameloop(Game game){
        this.game = game;
        gameloop = new Thread(loop);
        eventHandler = new EventHandler(game);
    }

    public void start(){
        gameloop.start();
        this.running = true;
    }

    public void close() {
        this.running = false;
    }

    private Runnable loop = () -> {
        long excess = 0;
        long noDelays = 0;
        final long DESIRED_UPDATE_TIME = 40;
        final long NO_DELAYS_PER_YIELD = 16;

        long overSleepTime = 0;
        try{
            Thread.sleep(100);
            while(running) {

                long beforeTime = System.currentTimeMillis();

                while (excess > DESIRED_UPDATE_TIME) {
                    game.update();
                    excess -= DESIRED_UPDATE_TIME;
                }

                game.update();
                game.render();

                long afterTime = System.currentTimeMillis();
                long totalTime = afterTime - beforeTime;

                long sleepTime = DESIRED_UPDATE_TIME - totalTime - overSleepTime;
                if (sleepTime >= 0) {
                    Thread.sleep(sleepTime);
                    long afterSleepTime = System.currentTimeMillis();
                    overSleepTime = afterSleepTime - afterTime;
                    noDelays = 0;
                }
                else {
                    overSleepTime = 0;
                    excess += totalTime - DESIRED_UPDATE_TIME;
                    if (++noDelays == NO_DELAYS_PER_YIELD)
                        Thread.yield();
                }

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    };


    private Runnable loop2 = () -> {
        try{

            int FPS = 40;
            int frame_time = 1000/FPS;

            long time_target, now, Dt = 0, time_base;

            while(true){

                time_base = System.currentTimeMillis();
                time_target = time_base + frame_time - Dt;

                while((now = System.currentTimeMillis()) < time_target);

                if(time_target > time_base){
                    game.update();
                    game.render();

                    Dt = System.currentTimeMillis() - now;
                }
                else Dt = 0;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    };
}
