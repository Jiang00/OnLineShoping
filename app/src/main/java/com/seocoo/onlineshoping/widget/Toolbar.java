package com.seocoo.onlineshoping.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.TransitionManager;
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
import android.widget.TextView;

import com.seocoo.onlineshoping.R;
import com.seocoo.onlineshoping.utils.StatusBarUtils;
import com.seocoo.onlineshoping.utils.StringUtils;

/**
 * desc:    自定义通用标题栏
 *
 * @author Bian
 * create at 2018/12/17
 */
public class Toolbar extends ConstraintLayout implements View.OnClickListener, TextWatcher, View.OnTouchListener {

    private Context context;
    private boolean ivLeftVisible;
    private boolean tvRightVisible;
    private String leftText;
    private String rightText;
    private String midText;
    private boolean isTopVisible;
    private int rightIcon;
    private int background;
    private float backgroundAlpha;
    private int leftColor;
    private int rightColor;
    private int midColor;
    private boolean isEdit;
    private int editHintColor;
    private int editTextColor;
    private String editHintText;
    private int editBackground;
    private int lineColor;
    private TextView tvMiddle;
    private TextView tvLeft;
    private TextView tvRight;
    private EditText etMiddle;
    private View line;
    private View rootView;
    private ConstraintLayout clToolbar;
    private ConstraintSet applyConstraintSet = new ConstraintSet();
    private ConstraintSet resetConstraintSet = new ConstraintSet();

    public Toolbar(Context context) {
        super(context, null);
    }

    public Toolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    public Toolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.toolbar, this);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.Toolbar);
        background = typedArray.getResourceId(R.styleable.Toolbar_bar_background, 0);
        backgroundAlpha = typedArray.getFloat(R.styleable.Toolbar_bar_background_alpha, -1);
        ivLeftVisible = typedArray.getBoolean(R.styleable.Toolbar_bar_left_iv_visible, true);
        tvRightVisible = typedArray.getBoolean(R.styleable.Toolbar_bar_right_tv_visible, true);
        isTopVisible = typedArray.getBoolean(R.styleable.Toolbar_bar_top_visible, true);
        leftColor = typedArray.getColor(R.styleable.Toolbar_bar_left_tv_color, 0);
        rightColor = typedArray.getColor(R.styleable.Toolbar_bar_right_tv_color, 0);
        midColor = typedArray.getColor(R.styleable.Toolbar_bar_mid_tv_color, 0);
        isEdit = typedArray.getBoolean(R.styleable.Toolbar_is_edit, true);
        editHintColor = typedArray.getColor(R.styleable.Toolbar_edit_hint_color, 0);
        editTextColor = typedArray.getColor(R.styleable.Toolbar_edit_text_color, 0);
        editBackground = typedArray.getResourceId(R.styleable.Toolbar_edit_background, 0);
        lineColor = typedArray.getColor(R.styleable.Toolbar_line_color, 0);
        if (typedArray.hasValue(R.styleable.Toolbar_bar_left_tv_text)) {
            editHintText = typedArray.getString(R.styleable.Toolbar_edit_hint_text);
        }
        if (typedArray.hasValue(R.styleable.Toolbar_bar_left_tv_text)) {
            leftText = typedArray.getString(R.styleable.Toolbar_bar_left_tv_text);
        }
        if (typedArray.hasValue(R.styleable.Toolbar_bar_right_tv_text)) {
            rightText = typedArray.getString(R.styleable.Toolbar_bar_right_tv_text);
        }
        rightIcon = typedArray.getResourceId(R.styleable.Toolbar_bar_right_iv_resource, 0);

        if (typedArray.hasValue(R.styleable.Toolbar_bar_mid_tv_text)) {
            midText = typedArray.getString(R.styleable.Toolbar_bar_mid_tv_text);
        }
        typedArray.recycle();
        initView();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initView() {
        tvLeft = findViewById(R.id.tv_toolbar_left);
        ImageView ivLeft = findViewById(R.id.iv_toolbar_left);
        etMiddle = findViewById(R.id.et_store_edit);
        tvRight = findViewById(R.id.tv_toolbar_right);
        View topView = findViewById(R.id.view_toolbar_top);
        ConstraintLayout clEdit = findViewById(R.id.cl_edit);
        clToolbar = findViewById(R.id.cl_toolbar);
        tvMiddle = findViewById(R.id.tv_toolbar_middle);
        rootView = findViewById(R.id.ll_toolbar_root);
        line = findViewById(R.id.line);

        if (background != 0) {
            rootView.setBackgroundResource(background);
        }
        if (backgroundAlpha != -1) {
            rootView.setAlpha(backgroundAlpha);
        }
        if (!ivLeftVisible) {
            ivLeft.setVisibility(GONE);
        }
        if (!tvRightVisible) {
            tvRight.setVisibility(GONE);
        }
        if (StringUtils.isNotEmpty(leftText)) {
            tvLeft.setVisibility(VISIBLE);
            tvLeft.setText(leftText);
        }
        if (leftColor != 0) {
            tvLeft.setTextColor(leftColor);
        }
        if (StringUtils.isNotEmpty(midText)) {
            tvMiddle.setText(midText);
        }
        if (midColor != 0) {
            tvMiddle.setTextColor(midColor);
        }
        if (StringUtils.isNotEmpty(rightText)) {
            tvRight.setVisibility(VISIBLE);
            tvRight.setText(rightText);
        }
        if (rightColor != 0) {
            tvRight.setTextColor(rightColor);
        }
        if (lineColor != 0) {
            line.setBackgroundColor(lineColor);
            line.setVisibility(VISIBLE);
        }
        if (isTopVisible) {
            topView.setVisibility(VISIBLE);
            setViewStatusHeight(context, topView);
        }

        if (!isEdit) {
            tvMiddle.setVisibility(VISIBLE);
            clEdit.setVisibility(GONE);
        } else {
            if (StringUtils.isNotEmpty(editHintText)) {
                etMiddle.setHint(editHintText);
            }
            if (editHintColor != 0) {
                etMiddle.setHintTextColor(editHintColor);
            }
            if (editTextColor != 0) {
                etMiddle.setTextColor(editTextColor);
            }
            if (editBackground != 0) {
                etMiddle.setBackgroundResource(editBackground);
            }
            etMiddle.setOnTouchListener(this);
            etMiddle.addTextChangedListener(this);
        }
        applyConstraintSet.clone(clToolbar);
        resetConstraintSet.clone(clToolbar);
        ivLeft.setOnClickListener(this);
        tvLeft.setOnClickListener(this);
        tvRight.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_toolbar_left:
            case R.id.iv_toolbar_left:
                if (leftClickListener != null) {
                    leftClickListener.leftClickListener();
                    break;
                }
                ((Activity) context).onBackPressed();
                break;
            case R.id.tv_toolbar_right:
                if (rightClickListener != null) {
                    rightClickListener.rightClickListener();
                    break;
                }
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(etMiddle.getWindowToken(), 0);
                }
                TransitionManager.beginDelayedTransition(clToolbar);
                resetConstraintSet.applyTo(clToolbar);
                break;
            default:
                break;
        }
    }

    private ToolbarLeftClickListener leftClickListener;
    private ToolbarRightClickListener rightClickListener;
    private ToolbarEditClickListener editClickListener;
    private ToolbarTextChangeListener textChangeListener;

    public void setLeftClickListener(ToolbarLeftClickListener leftClickListener) {
        this.leftClickListener = leftClickListener;
    }

    public void setRightClickListener(ToolbarRightClickListener rightClickListener) {
        this.rightClickListener = rightClickListener;
    }

    public void setEditClickListener(ToolbarEditClickListener editClickListener) {
        this.editClickListener = editClickListener;
    }

    public void setTextChangeListener(ToolbarTextChangeListener textChangeListener) {
        this.textChangeListener = textChangeListener;
    }

    public void setMidText(String midText) {
        this.midText = midText;
        if (StringUtils.isNotEmpty(midText)) {
            tvMiddle.setText(midText);
        }
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
        if (StringUtils.isNotEmpty(rightText)) {
            tvRight.setVisibility(VISIBLE);
            tvRight.setText(rightText);
        }
    }

    public void setRootViewBackground(int resId) {
        rootView.setBackgroundResource(resId);
    }

    public void setRootViewAlpha(float alpha) {
        rootView.setAlpha(alpha);
    }

    public void setClToolbarBackground(int resId) {
        clToolbar.setBackgroundResource(resId);
    }

    public TextView getRightText() {
        return tvRight;
    }

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

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId() == R.id.et_store_edit && event.getAction() == MotionEvent.ACTION_DOWN) {
            if (editClickListener != null) {
                editClickListener.editClickListener();
                return false;
            }
            TransitionManager.beginDelayedTransition(clToolbar);
            applyConstraintSet.setVisibility(R.id.tv_toolbar_left, ConstraintSet.GONE);
            applyConstraintSet.setVisibility(R.id.iv_toolbar_left, ConstraintSet.GONE);
            applyConstraintSet.setVisibility(R.id.tv_toolbar_right, ConstraintSet.VERTICAL);
            applyConstraintSet.applyTo(clToolbar);
        }
        return false;
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
        void rightClickListener();
    }

    public interface ToolbarEditClickListener {
        /**
         * 右边点击事件
         */
        void editClickListener();
    }

    public interface ToolbarTextChangeListener {
        /**
         * 右边点击事件
         *
         * @param text
         */
        void textChange(String text);
    }
}