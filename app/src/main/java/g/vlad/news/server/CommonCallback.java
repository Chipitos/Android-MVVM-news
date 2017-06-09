package g.vlad.news.server;

import g.vlad.news.server.response.BaseResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class CommonCallback<T extends BaseResponse> implements Callback<T> {
    private IRequestCallback callback;

    CommonCallback(IRequestCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            callback.onSuccessResult(response.body());
        } else {
            callback.onErrorResult(response.errorBody());

        }
        callback.hideProgress();
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        callback.onErrorResult(null);
        callback.hideProgress();
    }
}