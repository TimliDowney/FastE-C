package swing.downey.latte.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import swing.downey.latte.app.Latte;

/**
 * Created by Paul on 2017/8/18.
 */

public class DimenUtil {

    public static int getScreenWidth(){
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
