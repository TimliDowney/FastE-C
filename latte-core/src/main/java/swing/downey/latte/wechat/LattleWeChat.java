package swing.downey.latte.wechat;

import android.app.Activity;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import swing.downey.latte.app.ConfigKeys;
import swing.downey.latte.app.Latte;
import swing.downey.latte.wechat.callbacks.IWeChatSignInCallback;

/**
 * Created by Paul on 2017/8/30.
 */

public class LattleWeChat {
    static final String APP_ID = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
    static final String APP_SECRET = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_SECRET);

    private final IWXAPI WXAPI;
    private IWeChatSignInCallback mSignInCallback = null;

    private static final class Holder{
        private static final LattleWeChat INSTANCE = new LattleWeChat();
    }

    public static LattleWeChat getInstance(){
        return Holder.INSTANCE;
    }

    public IWeChatSignInCallback getSignInCallback(){
        return mSignInCallback;
    }

    public LattleWeChat onSignSuccess(IWeChatSignInCallback callback){
        this.mSignInCallback = callback;
        return this;
    }

    private LattleWeChat() {
        final Activity activity = Latte.getConfiguration(ConfigKeys.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity,APP_ID,true);
        WXAPI.registerApp(APP_ID);
    }

    public final IWXAPI getWXAPI(){
        return WXAPI;
    }

    public final void signIn(){
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }
}
