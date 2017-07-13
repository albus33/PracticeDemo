package popup.popfisher.com.smartpopupwindow;

import android.app.Dialog;
import android.content.Context;
import android.icu.util.Measure;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Administrator on 2017/5/31.
 */

public class InListDialog extends Dialog {
    private Context context;
    private View contentView;
    private int x;
    private int y;

    public InListDialog(@NonNull Context context, View contentView, int x, int y) {
        super(context,R.style.submit_loading_dialog);
        this.context = context;

        this.contentView = contentView;
        this.x = x;
        this.y = y;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setContentView(contentView);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();

        attributes.x = x;
        attributes.y = y;
        window.setAttributes(attributes);
    }
}
