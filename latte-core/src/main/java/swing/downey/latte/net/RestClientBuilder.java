package swing.downey.latte.net;

import android.content.Context;
import android.util.Log;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import swing.downey.latte.app.Latte;
import swing.downey.latte.net.CallBack.IError;
import swing.downey.latte.net.CallBack.IFailure;
import swing.downey.latte.net.CallBack.IRequest;
import swing.downey.latte.net.CallBack.ISuccess;
import swing.downey.latte.ui.loader.LoaderStyle;

//import java.lang.ref.PhantomReference;
//import java.lang.reflect.Type;

/**
 * Created by Paul on 2017/8/15.
 */

public class RestClientBuilder {

    private static final String TAG ="RestClientBuilder" +  Latte.TAG;
    private String mUrl = null;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mIRequest = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;
    private RequestBody mBody = null;
    private Context mContext = null;
    private LoaderStyle mLoadStyle = null;
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;

    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }


    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    public final RestClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }

    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;chartset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        Log.d(TAG, "success: tes1t");
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }


    public final RestClientBuilder loader(Context context, LoaderStyle style){
        this.mContext = context;
        this.mLoadStyle = style;
        return this;
    }

    public final RestClientBuilder loader(Context context){
        Log.d(TAG, "loader: tes1t");
        this.mContext = context;
        this.mLoadStyle = LoaderStyle.LineScaleIndicator;
        return this;
    }
//    private Map<String, Object> checkParams() {
//        if (mParams == null) {
//            mParams = new WeakHashMap<>();
//        }
//        return mParams;
//    }

    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mIRequest, mISuccess, mIFailure, mIError, mBody,mContext,mLoadStyle,mDownloadDir,mExtension,mName);
    }

}
