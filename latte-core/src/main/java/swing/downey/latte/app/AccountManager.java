package swing.downey.latte.app;

import swing.downey.latte.util.storage.LattePreference;

/**
 * Created by Paul on 2017/8/25.
 */

public class AccountManager {
    private enum SignTag{
        SIGN_TAG
    }
    //保存用户登入状态，登入后使用。
    public static void setSignState(boolean state){
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }

    private static boolean isSignIn(){
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker){
        if (isSignIn()){
            checker.onSignIn();
        }else {
            checker.onNotSignIn();
        }
    }
}
