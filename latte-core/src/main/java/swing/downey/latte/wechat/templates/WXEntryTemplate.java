package swing.downey.latte.wechat.templates;

import swing.downey.latte.wechat.BaseWXEntryActivity;
import swing.downey.latte.wechat.LattleWeChat;

/**
 * Created by Paul on 2017/8/30.
 */

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0,0);
    }//悄悄的消失

    @Override
    protected void onSignInSuccess(String userInfo) {
        LattleWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}
