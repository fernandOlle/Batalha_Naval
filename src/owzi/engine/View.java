package owzi.engine;

public abstract class View {

    private String name;
    private Game game;

    public View (String name, Game game) {
        this.name = name;
        this.game = game;
    }

    public abstract void render(RenderStrategy renderStrategy);

    public abstract void update();

    public String getName() {
        return name;
    }
    public Game getGame() {
        return game;
    }
}
