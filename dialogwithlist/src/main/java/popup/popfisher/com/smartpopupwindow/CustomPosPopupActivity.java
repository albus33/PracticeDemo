package popup.popfisher.com.smartpopupwindow;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class CustomPosPopupActivity extends Activity {

    private PopupWindow mPopupWindow;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_pos_window);
        initView();
    }

    private View getPopupWindowContentView() {
        // 一个自定义的布局，作为显示的内容
        int layoutId = R.layout.popup_content_layout;   // 布局ID
        View contentView = LayoutInflater.from(this).inflate(layoutId, null);
        View.OnClickListener menuItemOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Click " + ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        };
        contentView.findViewById(R.id.menu_item1).setOnClickListener(menuItemOnClickListener);
        contentView.findViewById(R.id.menu_item2).setOnClickListener(menuItemOnClickListener);
        contentView.findViewById(R.id.menu_item3).setOnClickListener(menuItemOnClickListener);
        contentView.findViewById(R.id.menu_item4).setOnClickListener(menuItemOnClickListener);
        contentView.findViewById(R.id.menu_item5).setOnClickListener(menuItemOnClickListener);
        return contentView;
    }

    private void showPopupWindow(View anchorView) {
        View contentView = getPopupWindowContentView();
        mPopupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        // 设置好参数之后再show
        int windowPos[] = PopupWindowUtil.calculatePopWindowPos(anchorView, contentView);
        int xOff = 20; // 可以自己调整偏移
        windowPos[0] -= xOff;
        mPopupWindow.showAtLocation(anchorView, Gravity.TOP | Gravity.START, windowPos[0], windowPos[1]);
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listview);
        mListView.setAdapter(new CustomAdapter());
    }

    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getBaseContext()).inflate(R.layout.listview_item, null);
                viewHolder = new ViewHolder();
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.moreRoot = convertView.findViewById(R.id.more_root);
            viewHolder.moreImageView = convertView.findViewById(R.id.more_imageView);
            viewHolder.moreRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopupWindow(viewHolder.moreImageView);//弹出pop
                    showDialog(viewHolder.moreImageView);//弹出dialog
                }
            });
            return convertView;
        }
    }

    private void showDialog(View moreImageView) {
        View contentView = getPopupWindowContentView();
        final int 锚点坐标[] = new int[2];
        // 获取锚点View在屏幕上的左上角坐标位置
        moreImageView.getLocationOnScreen(锚点坐标);
        Log.i("X轴的位置:", 锚点坐标[0] + "");
        Log.i("Y轴的位置:", 锚点坐标[1] + "");

        int screenWidth = ScreenUtils.getScreenWidth(this);
        int screenHeight = ScreenUtils.getScreenHeight(this);

        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int measuredWidth = contentView.getMeasuredWidth();
        int measuredHeight = contentView.getMeasuredHeight();

        int X轴的偏移量 = (screenWidth / 2 - measuredWidth / 2 - (screenWidth - 锚点坐标[0]));

        int Y轴的偏移量 = -(screenHeight / 2 - measuredHeight / 2 - 锚点坐标[1] + ScreenUtils.getStatusBarHeight(this) / 2);
        Log.i("X轴的偏移量", +X轴的偏移量 + "");
        Log.i("Y轴的偏移量", +Y轴的偏移量 + "");
        InListDialog inListDialog = new InListDialog(this, contentView, X轴的偏移量, Y轴的偏移量);
        inListDialog.show();
    }

    class ViewHolder {
        View moreRoot;
        View moreImageView;
    }
}
