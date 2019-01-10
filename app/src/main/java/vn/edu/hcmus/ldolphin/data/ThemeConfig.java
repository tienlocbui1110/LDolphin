package vn.edu.hcmus.ldolphin.data;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.IntDef;
import android.support.v4.content.ContextCompat;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import vn.edu.hcmus.ldolphin.R;

public final class ThemeConfig {
    // Constants
    public static final int DEFAULT = 0;
    public static final int MORDERN = 1;

    // Declare the @IntDef for these constants
    @IntDef({DEFAULT, MORDERN})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Theme {
    }

    @Theme
    private static int sTheme;
    private static int bottomBackgroundColor;
    private static int bottomAccentColor;
    private static int bottomInactiveColor;
    private static int bottomActiveSize;
    private static int bottomInactiveSize;
    private static boolean init;

    private static void switchToDefaultTheme(Context context) {
        bottomBackgroundColor = ContextCompat.getColor(context, R.color.blue);
        bottomAccentColor = ContextCompat.getColor(context, R.color.white);
        bottomInactiveColor = ContextCompat.getColor(context, R.color.gray);
        bottomActiveSize = 35;
        bottomInactiveSize = 25;
    }

    private static void switchToModernTheme(Context context) {
        bottomBackgroundColor = ContextCompat.getColor(context, R.color.blue);
        bottomAccentColor = ContextCompat.getColor(context, R.color.white);
        bottomInactiveColor = ContextCompat.getColor(context, R.color.gray);
        bottomActiveSize = 35;
        bottomInactiveSize = 25;
    }

    static {
        init = false;
    }

    public static void setTheme(@Theme int theme, Context context) {
        if (init && sTheme == theme)
            return;
        init = true;
        switch (theme) {
            case DEFAULT:
                switchToDefaultTheme(context);
                break;
            case MORDERN:
                switchToModernTheme(context);
                break;
            default:
                init = false;
        }
    }

    @ColorInt
    public static int BOTTOM_BACKGROUND_COLOR() {
        return bottomBackgroundColor;
    }

    @ColorInt
    public static int BOTTOM_ACCENT_COLOR() {
        return bottomAccentColor;
    }

    @ColorInt
    public static int BOTTOM_INACTIVE_COLOR() {
        return bottomInactiveColor;
    }

    public static int BOTTOM_ACTIVE_SIZE() {
        return bottomActiveSize;
    }

    public static int BOTTOM_INACTIVE_SIZE() {
        return bottomInactiveSize;
    }
}
