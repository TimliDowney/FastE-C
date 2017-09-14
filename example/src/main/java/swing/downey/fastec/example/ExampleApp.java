package swing.downey.fastec.example;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import swing.downey.latte.app.Latte;
import swing.downey.latte.ec.database.DatabaseManager;
import swing.downey.latte.ec.icon.FontEcModule;
import swing.downey.latte.net.interceptors.DebugInterceptor;
//import android.support.v7.app.AppCompatActivity;

/**
 * Created by Paul on 2017/8/14.
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withLoaderDelay(1000)
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                //.withWeChatAppId("你的微信AppKey")
                //.withWeChatAppSecret("你的微信AppSecret")
                .configure();
        initStetho();
        DatabaseManager.getInstance().init(this);
    }

        @SuppressWarnings("SpellCheckingInspection")
        private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
