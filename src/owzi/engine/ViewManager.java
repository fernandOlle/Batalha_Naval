package owzi.engine;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ViewManager{

    private Map<String, View> views = new HashMap<>();

    public void update(String currentViewKey){
        if(validateKey(currentViewKey))
            this.views.get(currentViewKey).update();
    }

    public void render(String currentViewKey, RenderStrategy renderStrategy){
        if(validateKey(currentViewKey) && renderStrategy != null)
            this.views.get(currentViewKey).render(renderStrategy);
    }

    public ViewManager addView(View view){
        this.views.put(view.getName(), view);
        return this;
    }

    public View getView(String name){
        return views.get(name);
    }

    public Set getViewsKeyList(){
        return views.keySet();
    }

    public boolean validateKey(String key) {
        if(key == null || !views.keySet().contains(key))
            return false;
        return true;
    }

}
