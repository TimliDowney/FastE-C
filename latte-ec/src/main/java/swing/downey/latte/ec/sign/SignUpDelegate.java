package swing.downey.latte.ec.sign;

import android.app.Activity;
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

/**
 * Created by Paul on 2017/8/24.
 */

public class SignUpDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sing_up_name)
    TextInputEditText mName = null;
    @BindView(R2.id.edit_sing_up_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sing_up_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_sing_up_password)
    TextInputEditText mPassword = null;
    @BindView(R2.id.edit_sing_up_re_password)
    TextInputEditText mRePassword = null;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClickLink() {
        if (checkForm()) {
            start(new SignInDelegate());
        }
    }

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
        if (checkForm()){
            RestClient.builder()
                    .url("http://116.196.95.67/RestServer/api/user_profile.php")
                    .params("name", mName.getText().toString())
                    .params("email", mEmail.getText().toString())
                    .params("phone", mPhone.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LatteLogger.json("USER_PROFILE",response);
                            SignHandler.onSignUp(response,mISignListener);
                        }
                    })
                    .build()
                    .post();
            Toast.makeText(getContext(),"checkForm pass",Toast.LENGTH_LONG).show();
        }
    }


    private boolean checkForm() {
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();

        boolean isPass = true;

        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("错误的号码");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() <= 6) {
            mPassword.setError("错误的密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() <= 6 || !rePassword.equals(password)) {
            mRePassword.setError("错误的密码");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.layout_sign_up;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
