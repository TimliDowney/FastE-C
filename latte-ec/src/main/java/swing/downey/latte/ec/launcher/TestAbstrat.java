package swing.downey.latte.ec.launcher;

import android.app.Activity;

import swing.downey.latte.activities.ProxyActivity;
import swing.downey.latte.delegates.LatteDelegate;

/**
 * Created by Paul on 2017/8/25.
 */

public class TestAbstrat extends ProxyActivity {
    @Override
    public LatteDelegate setRootDelegate() {
        return null;
    }
}
