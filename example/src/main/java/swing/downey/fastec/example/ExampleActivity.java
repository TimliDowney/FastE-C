package swing.downey.fastec.example;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.service.notification.StatusBarNotification;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import qiu.niorgai.StatusBarCompat;
import swing.downey.latte.activities.ProxyActivity;
import swing.downey.latte.app.Latte;
import swing.downey.latte.delegates.LatteDelegate;
import swing.downey.latte.ec.launcher.LauncherDelegate;
import swing.downey.latte.ec.main.EcBottomDelegate;
import swing.downey.latte.ec.sign.ISignListener;
import swing.downey.latte.ec.sign.SignInDelegate;
import swing.downey.latte.ui.launcher.ILauncherListener;
import swing.downey.latte.ui.launcher.OnLauncherFinishTag;

//import swing.downey.latte.app.Latte;

public class ExampleActivity extends ProxyActivity implements
        ISignListener,
        ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this,true);
    }


    @Override
    public LatteDelegate setRootDelegate() {
        return new EcBottomDelegate();
//        return new LauncherDelegate();
//        return new SignInDelegate();
//        return new SignUpDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登入成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this,"启动结束，用户登入了",Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this,"启动结束，用户没登入",Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
