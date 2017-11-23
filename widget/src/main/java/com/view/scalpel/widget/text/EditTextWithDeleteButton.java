package com.view.scalpel.widget.text;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.view.scalpel.widget.R;

/**
 * Created by liuxingxing on 2015/3/3.
 */
public class EditTextWithDeleteButton extends LinearLayout {
    protected EditText editText;
    protected ImageButton clearTextButton;
    protected boolean isHaveDelBtn = true;
    public interface TextChangedListener extends TextWatcher {
    }
    TextChangedListener editTextListener = null;
    public void addTextChangedListener(TextChangedListener listener) {
        this.editTextListener = listener;
    }
    public EditTextWithDeleteButton(Context context) {
        super(context);
    }

    public EditTextWithDeleteButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs);
    }

    public EditTextWithDeleteButton(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
        initViews(context, attrs);
    }

    private void initViews(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.EditTextWithDeleteButton, 0, 0);
        String hintText;
        int deleteButtonRes;
        try {
            // get the text and colors specified using the names in attrs.xml
            hintText = a.getString(R.styleable.EditTextWithDeleteButton_hintText);
            deleteButtonRes = a.getResourceId(
                    R.styleable.EditTextWithDeleteButton_deleteButtonRes,
                    R.drawable.text_field_clear_btn);
            isHaveDelBtn = a.getBoolean(R.styleable.EditTextWithDeleteButton_show_delete_btn,true);
        } finally {
            a.recycle();
        }
        editText = createEditText(context, hintText);

        clearTextButton = createImageButton(context, deleteButtonRes);

        this.addView(editText);
        this.addView(clearTextButton);

        //clearTextButton.setVisibility(isHaveDelBtn?VISIBLE:INVISIBLE);
        editText.addTextChangedListener(makeTextWatcher());
        editText.setOnFocusChangeListener(new OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && editText.getText().toString().length() > 0){
                    if(isHaveDelBtn)clearTextButton.setVisibility(View.VISIBLE);
                }else clearTextButton.setVisibility(View.INVISIBLE);

            }
        });
        clearTextButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
    }

    public TextWatcher makeTextWatcher() {
        return new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (editTextListener != null)
                    editTextListener.onTextChanged(s, start, before, count);

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editTextListener != null)
                    editTextListener.afterTextChanged(s);
                if (editText.getText().toString().length() > 0)
                    if(isHaveDelBtn)clearTextButton.setVisibility(View.VISIBLE);
                    else
                        clearTextButton.setVisibility(View.INVISIBLE);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                if (editTextListener != null)
                    editTextListener.beforeTextChanged(s, start, count, after);

            }

        };
    }

    private EditText createEditText(Context context, String hintText) {
        editText = new EditText(context);
        editText.setLayoutParams(new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, 1f));
        editText.setGravity(Gravity.LEFT);

        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, 1f);
        params.gravity = Gravity.LEFT;
        editText.setLayoutParams(params);
        editText.setGravity(Gravity.CENTER_VERTICAL);
        if(Build.VERSION.SDK_INT > 15){
            editText.setBackground(null);
        }else {
            editText.setBackgroundDrawable(null);
        }
        editText.setHint(hintText);
        return editText;
    }

    private ImageButton createImageButton(Context context, int deleteButtonRes) {
        clearTextButton = new ImageButton(context);
        LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        params.gravity = Gravity.CENTER_VERTICAL;
        clearTextButton.setLayoutParams(params);
        clearTextButton.setBackgroundResource(deleteButtonRes);
        clearTextButton.setVisibility(View.INVISIBLE);
        return clearTextButton;
    }

    public void hideClearBtn(){
        if(null != clearTextButton)clearTextButton.setVisibility(GONE);
    }

    public EditText getEditText() {
        return editText;
    }
}
