package swing.downey.latte.ui.loader;

import android.content.Context;
import android.util.Log;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

import swing.downey.latte.app.Latte;

/**
 * Created by Paul on 2017/8/18.
 */

final class LoaderCreator {
    private static final String TAG = "LoaderCreator " + Latte.TAG;
    private static final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();
//private static final HashMap<String, Indicator> LOADING_MAP = new HashMap<>();

    static AVLoadingIndicatorView create(String type, Context context) {

        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (LOADING_MAP.get(type) == null) {
//            Log.d(TAG, "create: LOADING_MAP.get(type) == null");
//            Log.d(TAG, "type = " + type);
            final Indicator indicator = getIndicator(type);
//            Log.d(TAG, "indicator = " + indicator);
            LOADING_MAP.put(type, indicator);
//            Log.d(TAG, "LOADING_MAP.get(type) " + LOADING_MAP.get(type));
        }

        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
//        avLoadingIndicatorView.setIndicatorColor(R.color.black);
        return avLoadingIndicatorView;
    }

    private static Indicator getIndicator(String name) {
        if (name == null || name.isEmpty()) {
            Log.d(TAG, "getIndicator: 1");
            return null;
        }
        final StringBuilder drawableClassName = new StringBuilder();
        Log.d(TAG, "getIndicator: 2");
        if (!name.contains(".")) {
            final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(name);
        try {
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
