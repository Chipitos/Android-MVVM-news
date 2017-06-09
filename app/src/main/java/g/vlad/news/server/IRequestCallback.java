package g.vlad.news.server;

import g.vlad.news.server.response.BaseResponse;
import okhttp3.ResponseBody;

public interface IRequestCallback {

    void onSuccessResult(BaseResponse response);

    void onErrorResult(ResponseBody error);

    void hideProgress();

    void showProgress();
}
