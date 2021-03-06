package cjx.com.diary.presenter.impl;

import java.util.List;

import cjx.com.diary.api.ApiService;
import cjx.com.diary.common.Mock;
import cjx.com.diary.common.MyObserver;
import cjx.com.diary.mode.ImagesResult;
import cjx.com.diary.presenter.FindPresenter;
import cjx.com.diary.util.GsonUtils;
import cjx.com.diary.util.Utils;
import cjx.com.diary.view.fragment.FindFragment;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by bear on 2017/4/27.
 */

public class FindPresenterImp extends MyPresenterImpl implements FindPresenter {
    @Override
    public void getImageList() {
        FindFragment findFragment= (FindFragment) mView;
        ApiService.getApiService().getImages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(imagesResult ->
                        imagesResult.data.images
                )
                .subscribeWith(new MyObserver<List<String>>() {
            @Override
            public void onSuccess(List<String> strings) {
                            findFragment.onRefresh(strings);
            }

            @Override
            public void onError(String msg) {
                ImagesResult bean= GsonUtils.jsonToClass(Mock.getImageList(),ImagesResult.class);
                findFragment.onRefresh(bean.data.images);
            }
        });
    }
}
