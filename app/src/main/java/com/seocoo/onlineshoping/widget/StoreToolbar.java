package com.seocoo.onlineshoping.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.media.Image;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.seocoo.onlineshoping.R;
import com.seocoo.onlineshoping.utils.StatusBarUtils;
import com.seocoo.onlineshoping.utils.StringUtils;

/**
 * desc:    自定义通用标题栏
 *
 * @author Bian
 * create at 2018/12/17
 */
public class StoreToolbar extends ConstraintLayout implements View.OnClickListener {

    private Context context;
    private String midText;
    private TextView tvLeft;

    public StoreToolbar(Context context) {
        super(context, null);
    }

    public StoreToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    public StoreToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.layout_store_toolbar, this);
        initView();
    }

    private void initView() {
        tvLeft = findViewById(R.id.tv_toolbar_left);
        ImageView ivLeft = findViewById(R.id.iv_toolbar_city);
        View topView = findViewById(R.id.view_toolbar_top);
        EditText etStore = findViewById(R.id.et_store_edit);
        ImageView ivSearch = findViewById(R.id.iv_store_search);
        topView.setVisibility(VISIBLE);
        setViewStatusHeight(context, topView);
        ivSearch.setOnClickListener(this);
        tvLeft.setOnClickListener(this);
        ivLeft.setOnClickListener(this);
        etStore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (textChangeListener != null) {
                    textChangeListener.textChange(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setViewStatusHeight(Context context, View topView) {
        //安卓4.4以上设置
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ViewGroup.LayoutParams params = topView.getLayoutParams();
            params.height = StatusBarUtils.getStatusBarHeight(context);
            topView.setLayoutParams(params);
        } else {
            topView.setVisibility(View.GONE);
        }
    }

    public void setTvLeftText(String text) {
        tvLeft.setText(text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_toolbar_left:
            case R.id.iv_toolbar_left:
                if (leftClickListener != null) {
                    leftClickListener.leftClickListener();
                }
                break;
            case R.id.iv_toolbar_city:
                if (searchClickListener != null) {
                    searchClickListener.searchClickListener();
                }
                break;
            default:
                break;
        }
    }


    private ToolbarLeftClickListener leftClickListener;
    private ToolbarRightClickListener searchClickListener;
    private ToolbarTextChangeListener textChangeListener;

    public void setLeftClickListener(ToolbarLeftClickListener leftClickListener) {
        this.leftClickListener = leftClickListener;
    }

    public void setSearchClickListener(ToolbarRightClickListener searchClickListener) {
        this.searchClickListener = searchClickListener;
    }

    public void setTextChangeListener(ToolbarTextChangeListener textChangeListener) {
        this.textChangeListener = textChangeListener;
    }


    public interface ToolbarLeftClickListener {
        /**
         * 左边点击事件
         */
        void leftClickListener();
    }


    public interface ToolbarRightClickListener {
        /**
         * 右边点击事件
         */
        void searchClickListener();
    }

    public interface ToolbarTextChangeListener {
        /**
         * 右边点击事件
         */
        void textChange(String text);
    }
}
