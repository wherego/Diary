package cjx.com.diary.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cjx.com.diary.R;
import cjx.com.diary.api.ApiService;
import cjx.com.diary.api.HttpInterface;
import cjx.com.diary.base.BaseActivity;
import cjx.com.diary.presenter.impl.LoginPresenterImpl;

/**
 * Created by bear on 2017/4/17.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.act_account)
    AutoCompleteTextView mAccountAct;
    @BindView(R.id.et_psd)
    EditText mPsdEt;
    @BindView(R.id.btn_login)
    Button mLoginBtn;
    @BindView(R.id.btn_register)
    Button mRegisterBtn;

    private LoginPresenterImpl mLoginPresenter;
    private HttpInterface api;

    public static void action(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initTitleBar("登录");
        mLoginPresenter = new LoginPresenterImpl();
        mLoginPresenter.bindView(LoginActivity.this, null);
        api = ApiService.getApiService();

    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String account = mAccountAct.getText().toString();
                String psd = mPsdEt.getText().toString();
                mLoginPresenter.login(account,psd);
//                api.search(account)
//                        .subscribeOn(Schedulers.newThread())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(roomResult -> {
//                            Utils.showToast(mActivity, roomResult.prompWord);
//                        });
                break;
            case R.id.btn_register:
                mLoginPresenter.jumpToRegister(mActivity);
                break;
        }
    }

}
