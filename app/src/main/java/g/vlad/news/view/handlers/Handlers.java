package g.vlad.news.view.handlers;

import android.view.View;

public interface Handlers {
    interface ListNewsHandlers {
        void onFavoriteClick(View v);

        void onItemClick(View v);
    }

    interface FrgFavoritesHandlers {
        void onBackPressed(View v);
    }

    interface FrgNewsContainerHandlers {
        void onFavoritesClick(View v);
    }
}
