package swing.downey.latte.delegates;

import android.app.Activity;

/**
 * Created by Paul on 2017/8/15.
 */

@SuppressWarnings("unchecked")
public abstract class LatteDelegate extends PermissionCheckerDelegate {

    public <T extends LatteDelegate> T getParentDelegate(){
        return (T) getParentFragment();
    }
}
