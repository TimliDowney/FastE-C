package swing.downey.fastec.example;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import swing.downey.latte.app.Latte;
import swing.downey.latte.delegates.LatteDelegate;
import swing.downey.latte.net.CallBack.IError;
import swing.downey.latte.net.CallBack.IFailure;
import swing.downey.latte.net.CallBack.ISuccess;
import swing.downey.latte.net.RestClient;

/**
 * Created by Paul on 2017/8/15.
 */

public class ExampleDelegate extends LatteDelegate {
    private static final String TAG = "ExampleDelegate" + Latte.TAG;

    @Override
    public Object setLayout() {
        return R.layout.dalegate_example;
    }

    @Override
    public void onBinderView(Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient() {
        Log.d(TAG, "tes1t");
        RestClient.builder()
                .url("http://127.0.0.1/index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(), "Failure", Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .get();
    }
}
