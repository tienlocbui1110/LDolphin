package vn.edu.hcmus.ldolphin.views.base;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

public interface Presenter<V extends MVPView> {
    void setView(V view);
}