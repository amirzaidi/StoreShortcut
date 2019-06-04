package amirz.storeshortcut;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Process;

class ShareShortcut extends Shortcut {
    private final Intent mIntent;

    ShareShortcut(CharSequence name, String link) {
        mIntent = new Intent(Intent.ACTION_SEND);
        mIntent.setType("text/plain");
        mIntent.putExtra(Intent.EXTRA_SUBJECT, name);
        mIntent.putExtra(Intent.EXTRA_TEXT, link);
    }

    @Override
    String getKey() {
        return "share";
    }

    @Override
    String getIntent(Context context) {
        return mIntent.toUri(Intent.URI_INTENT_SCHEME);
    }

    @Override
    String getLabel(Context context) {
        return context.getString(R.string.shortcut_share);
    }
}
