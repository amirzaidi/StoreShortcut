package amirz.storeshortcut;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ShortcutCreator {
    private static final String STORE = "com.android.vending";

    private final Context mContext;
    private final PackageManager mPm;

    ShortcutCreator(Context context) {
        mContext = context;
        mPm = context.getPackageManager();
    }

    List<Bundle> getForActivity(String packageName, ComponentName activity) {
        List<Bundle> out = new ArrayList<>();
        if (packageName != null) {
            String installer = mPm.getInstallerPackageName(packageName);
            if (STORE.equals(installer)) {
                try {
                    CharSequence name = mPm.getApplicationLabel(mPm.getApplicationInfo(packageName, 0));
                    String link = "https://play.google.com/store/apps/details?id=" + packageName;
                    out.add(new StoreShortcut(link).toBundle(mContext));
                    out.add(new ShareShortcut(name, link).toBundle(mContext));
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return out;
    }

    Bitmap getIcon(String key, int density) {
        if ("store".equals(key) || "share".equals(key)) {
            Drawable icon = mContext.getDrawable("store".equals(key)
                    ? R.drawable.ic_store
                    : R.drawable.ic_share);
            Bitmap bitmap = Bitmap.createBitmap(
                    icon.getIntrinsicWidth(), icon.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            icon.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            icon.draw(canvas);
            return bitmap;
        }
        return null;
    }
}
