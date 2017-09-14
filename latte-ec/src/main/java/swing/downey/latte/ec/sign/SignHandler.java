package swing.downey.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import swing.downey.latte.app.AccountManager;
import swing.downey.latte.ec.database.DatabaseManager;
import swing.downey.latte.ec.database.UserProfile;

/**
 * Created by Paul on 2017/8/25.
 */

public class SignHandler {

    public static void onSignIn(String response,ISignListener signListener){
        final JSONObject profileJson= JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");;
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile  profile = new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getDao().insert(profile);

        //已经登入成功了。
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }

    public static void onSignUp(String response,ISignListener signListener){
        final JSONObject profileJson= JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");;
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile  profile = new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册并登入成功了。
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }
}
