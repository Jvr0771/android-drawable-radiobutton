package com.xiaoai.drawableRadioButton;

import com.xiaoai.drawableRadioButton.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.RadioButton;

public class DrawableRadioButton extends RadioButton {

	private Drawable buttonDrawable;

	private int buttonDrawableWidth, buttonDrawableHeight;

	private int buttonPaddingLeft;

	private Drawable contentDrawable;

	private int contentDrawableWidth, contentDrawableHeight;

	private int contentPaddingLeft;

	private Context context;

	public DrawableRadioButton(Context context) {
		this(context, null);
	}

	public DrawableRadioButton(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
		setFocusable(true);
		setClickable(true);
	}

	public DrawableRadioButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;

		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.PaymentButton);

		buttonPaddingLeft = array.getDimensionPixelSize(
				R.styleable.PaymentButton_buttonPaddingLeft, 0);

		Drawable drawable = array.getDrawable(R.styleable.PaymentButton_button);
		if (drawable != null) {
			setRadioButtonDrawable(drawable);

			int defWidth = drawable.getIntrinsicWidth();
			int defHeight = drawable.getIntrinsicHeight();
			buttonDrawableWidth = array.getDimensionPixelSize(
					R.styleable.PaymentButton_buttonWidth, defWidth);
			buttonDrawableHeight = array.getDimensionPixelSize(
					R.styleable.PaymentButton_buttonHeight, defHeight);
		}

		boolean checked = array.getBoolean(R.styleable.PaymentButton_checked,
				false);
		setChecked(checked);

		Drawable content = array
				.getDrawable(R.styleable.PaymentButton_drawable);

		if (content != null) {
			int defWidth = content.getIntrinsicWidth();
			int defHeight = content.getIntrinsicHeight();
			contentDrawableWidth = array.getDimensionPixelSize(
					R.styleable.PaymentButton_drawableWidth, defWidth);
			contentDrawableHeight = array.getDimensionPixelSize(
					R.styleable.PaymentButton_drawableHeight, defHeight);
		}

		contentPaddingLeft = array.getDimensionPixelSize(
				R.styleable.PaymentButton_drawablePadding, 0);

		setContentDrawable(content);
		setCompoundDrawablePadding(contentPaddingLeft);

		array.recycle();
	}

	public void setRadioButtonDrawable(Drawable d) {
		if (d != null) {
			if (buttonDrawable != null) {
				buttonDrawable.setCallback(null);
				unscheduleDrawable(buttonDrawable);
			}
			d.setCallback(this);
			d.setState(getDrawableState());
			d.setVisible(getVisibility() == VISIBLE, false);
			buttonDrawable = d;
			buttonDrawable.setState(null);
			setMinHeight(buttonDrawable.getIntrinsicHeight());
		}

		refreshDrawableState();
	}

	@Override
	protected void drawableStateChanged() {
		super.drawableStateChanged();

		if (buttonDrawable != null) {
			int[] myDrawableState = getDrawableState();

			// Set the state of the Drawable
			buttonDrawable.setState(myDrawableState);

			invalidate();
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		final Drawable button = buttonDrawable;
		if (button != null) {
			final int verticalGravity = getGravity()
					& Gravity.VERTICAL_GRAVITY_MASK;
			final int height = buttonDrawableHeight;

			int y = 0;

			switch (verticalGravity) {
			case Gravity.BOTTOM:
				y = getHeight() - height;
				break;
			case Gravity.CENTER_VERTICAL:
				y = (getHeight() - height) / 2;
				break;
			}

			button.setBounds(0 + buttonPaddingLeft, y, buttonDrawableWidth
					+ buttonPaddingLeft, y + height);
			button.draw(canvas);
		}
	}

	public void setRadioButton(int resId) {
		setRadioButton(context.getResources().getDrawable(resId));
	}

	public void setRadioButton(Drawable drawable) {
		setRadioButtonDrawable(drawable);

		invalidate();
		requestLayout();
	}

	public void setRadioButtonDimensions(int width, int height) {
		this.buttonDrawableWidth = width;
		this.buttonDrawableHeight = height;

		invalidate();
		requestLayout();
	}

	public void setRadioButtonPadddingLefe(int paddingLeft) {
		this.buttonPaddingLeft = paddingLeft;

		invalidate();
		requestLayout();
	}

	public void setContentDrawable(int resId) {
		setContentDrawable(context.getResources().getDrawable(resId));
	}

	public void setContentDrawable(Drawable drawable) {
		this.contentDrawable = drawable;
		if (drawable != null) {
			drawable.setBounds(contentPaddingLeft, 0, contentPaddingLeft
					+ contentDrawableWidth, contentDrawableHeight);
		}
		setCompoundDrawables(drawable, null, null, null);
	}

	public void setContentDrawableDimensions(int width, int height) {
		this.contentDrawableWidth = width;
		this.contentDrawableHeight = height;
		setContentDrawable(contentDrawable);

		invalidate();
		requestLayout();
	}

	public void setContentPaddingLeft(int paddingLeft) {
		this.contentPaddingLeft = paddingLeft;
		setContentDrawable(contentDrawable);

		invalidate();
		requestLayout();
	}

}
