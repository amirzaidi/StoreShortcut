package amirz.storeshortcut;

import android.content.Context;
import android.os.Bundle;
import android.os.Process;

abstract class Shortcut {
    abstract String getKey();

    abstract String getIntent(Context context);

    abstract String getLabel(Context context);

    Bundle toBundle(Context context) {
        Bundle bundle = new Bundle();
        bundle.putString("key", getKey());
        bundle.putString("id", getKey());
        bundle.putString("intent", getIntent(context));
        bundle.putString("shortLabel", getLabel(context));
        bundle.putString("longLabel", getLabel(context));
        bundle.putString("disabledMessage", "");
        bundle.putParcelable("userHandle", Process.myUserHandle());
        bundle.putInt("rank", -99);
        bundle.putBoolean("enabled", true);
        return bundle;
    }
}
