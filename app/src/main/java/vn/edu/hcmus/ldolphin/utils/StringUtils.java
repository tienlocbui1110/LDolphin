package vn.edu.hcmus.ldolphin.utils;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Patterns;

public final class StringUtils {
    public static boolean isEmpty(@Nullable String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
