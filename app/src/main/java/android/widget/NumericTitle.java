package android.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Html;
import android.util.AttributeSet;
import android.view.Gravity;

import com.alorma.github.R;

/**
 * Created by Bernat on 13/07/2014.
 */
public class NumericTitle extends TextView {

    private int number = 0;
    private int text = 0;
    private int rgb;
    private Rect rect;
    private float size;
    private Paint paint;

    public NumericTitle(Context context) {
        super(context);
        init();
    }

    public NumericTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NumericTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        isInEditMode();

        rgb = getResources().getColor(R.color.accent);

        rect = new Rect();

        size = 5 * getResources().getDisplayMetrics().density;

        paint = new Paint();

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    public void setCustomNumber(int number) {
        this.number = number;
        setCustom();
    }

    public void setCustomText(int text) {
        this.text = text;
        setCustom();
    }

    private void setCustom() {
        String textS;
        if (text != 0) {
            textS = getResources().getString(text);
        } else if (isInEditMode()) {
            textS = "Demo";
        } else {
            textS = "--";
        }

        setGravity(Gravity.CENTER);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<b>");
        stringBuilder.append(number);
        stringBuilder.append("</b>");

        stringBuilder.append("<br />");
        stringBuilder.append(textS);

        setText(Html.fromHtml(stringBuilder.toString()));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(rgb);
        rect.left = canvas.getClipBounds().left;
        rect.right = canvas.getClipBounds().right;
        rect.bottom = canvas.getClipBounds().bottom;
        if (isSelected()) {
            rect.top = (int) (canvas.getClipBounds().bottom - size);
        } else {
            rect.top = (int) (canvas.getClipBounds().bottom - size / 3 );
        }
        canvas.drawRect(rect, paint);
    }

    public void setRgb(int rgb) {
        this.rgb = rgb;
        invalidate();
    }
}
