package com.example.paymentbuttondemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.RadioButton;

public class PaymentButton extends RadioButton {

	private int paddingLeft;

	private Drawable buttonDrawable;

	private int buttonWidth, buttonHeight;

	private Drawable paymentDrawable;

	private int drawableWidth, drawableHeight;

	private int drawablePadding;

	public PaymentButton(Context context) {
		super(context, null);
	}

	public PaymentButton(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
		setFocusable(true);
		setClickable(true);
	}

	public PaymentButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.PaymentButton);

		paddingLeft = array.getDimensionPixelSize(
				R.styleable.PaymentButton_buttonPaddingLeft, 0);

		Drawable drawable = array.getDrawable(R.styleable.PaymentButton_button);
		if (drawable != null) {
			setDrawableButton(drawable);

			int defWidth = drawable.getIntrinsicWidth();
			int defHeight = drawable.getIntrinsicHeight();
			buttonWidth = array.getDimensionPixelSize(
					R.styleable.PaymentButton_buttonWidth, defWidth);
			buttonHeight = array.getDimensionPixelSize(
					R.styleable.PaymentButton_buttonHeight, defHeight);
		}

		boolean checked = array.getBoolean(R.styleable.PaymentButton_checked,
				false);
		setChecked(checked);

		paymentDrawable = array.getDrawable(R.styleable.PaymentButton_drawable);

		if (paymentDrawable != null) {
			int defWidth = paymentDrawable.getIntrinsicWidth();
			int defHeight = paymentDrawable.getIntrinsicHeight();
			drawableWidth = array.getDimensionPixelSize(
					R.styleable.PaymentButton_drawableWidth, defWidth);
			drawableHeight = array.getDimensionPixelSize(
					R.styleable.PaymentButton_drawableHeight, defHeight);
		}

		drawablePadding = array.getDimensionPixelSize(
				R.styleable.PaymentButton_drawablePadding, 0);

		setPaymentDrawable(paymentDrawable);
		setCompoundDrawablePadding(drawablePadding);

		array.recycle();
	}

	public void setDrawableButton(Drawable d) {
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
	
	private void setPaymentDrawable(Drawable drawable) {
		if (drawable != null) {
			drawable.setBounds(drawablePadding, 0, drawablePadding
					+ drawableWidth, drawableHeight);
		}
		setCompoundDrawables(drawable, null, null, null);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		final Drawable button = buttonDrawable;
		if (button != null) {
			final int verticalGravity = getGravity()
					& Gravity.VERTICAL_GRAVITY_MASK;
			final int height = buttonHeight;

			int y = 0;

			switch (verticalGravity) {
			case Gravity.BOTTOM:
				y = getHeight() - height;
				break;
			case Gravity.CENTER_VERTICAL:
				y = (getHeight() - height) / 2;
				break;
			}

			button.setBounds(0 + paddingLeft, y, buttonWidth + paddingLeft, y
					+ height);
			button.draw(canvas);
		}
	}

}
