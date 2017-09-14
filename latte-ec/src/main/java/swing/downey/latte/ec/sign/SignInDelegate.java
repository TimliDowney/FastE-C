package swing.downey.latte.ec.sign;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import swing.downey.latte.delegates.LatteDelegate;
import swing.downey.latte.ec.R;
import swing.downey.latte.ec.R2;
import swing.downey.latte.net.CallBack.ISuccess;
import swing.downey.latte.net.RestClient;
import swing.downey.latte.util.log.LatteLogger;
import swing.downey.latte.wechat.LattleWeChat;
import swing.downey.latte.wechat.callbacks.IWeChatSignInCallback;

/**
 * Created by Paul on 2017/8/24.
 */

public class SignInDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sing_in_email)
    TextInputEditText mEmail = null;

    @BindView(R2.id.edit_sing_in_password)
    TextInputEditText mPassword = null;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.ic_sign_in_wechat)
    void onClickSignInWeChat() {
        LattleWeChat.getInstance().onSignSuccess(new IWeChatSignInCallback() {
            @Override
            public void onSignInSuccess(String userInfo) {
                Toast.makeText(getContext(),userInfo,Toast.LENGTH_LONG).show();
            }
        }).signIn();
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {
        if (checkForm()) {
            RestClient.builder()
                    .url("http://116.196.95.67/RestServer/api")
                    .params("email", mEmail.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LatteLogger.json("USER_PROFILE", response);
                            SignHandler.onSignIn(response, mISignListener);
                        }
                    })
                    .build()
                    .post();
            Toast.makeText(getContext(), "checkForm pass", Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink() {
            start(new SignUpDelegate());
    }

    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() <= 6) {
            mPassword.setError("错误的号码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
