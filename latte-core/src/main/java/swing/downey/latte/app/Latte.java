package swing.downey.latte.app;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;
//import java.util.WeakHashMap;

/**
 * Created by Paul on 2017/8/14.
 */

@SuppressWarnings("SpellCheckingInspection")
public final class Latte {
    public static final String TAG = "DG";

    public static Configurator init(Context context) {
        getConfigurations().put(ConfigKeys.APPLICATION_CONTEXT,context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static HashMap<Object,Object> getConfigurations(){
        return Configurator.getInstance().getLatteConfigs();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static Context getApplication(){
        return (Context) getConfigurations().get(ConfigKeys.APPLICATION_CONTEXT.name());
    }

    public static Handler getHandler(){
        return getConfiguration(ConfigKeys.HANDLER);
    }

}
