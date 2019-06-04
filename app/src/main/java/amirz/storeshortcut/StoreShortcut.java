package amirz.storeshortcut;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

class StoreShortcut extends Shortcut {
    private final Intent mIntent;

    StoreShortcut(String link) {
        mIntent = new Intent(Intent.ACTION_VIEW);
        mIntent.setData(Uri.parse(link));
    }

    @Override
    String getKey() {
        return "store";
    }

    @Override
    String getIntent(Context context) {
        return mIntent.toUri(Intent.URI_INTENT_SCHEME);
    }

    @Override
    String getLabel(Context context) {
        return context.getString(R.string.shortcut_store);
    }

    @Override
    Bundle toBundle(Context context) {
        Bundle bundle = super.toBundle(context);
        bundle.putString("package", "com.android.vending");
        bundle.putInt("rank", -98);
        return bundle;
    }
}
