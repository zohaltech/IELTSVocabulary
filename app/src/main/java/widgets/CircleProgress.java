package widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.zohaltech.app.ieltsvocabulary.R;

public class CircleProgress extends ImageView {

    private static final int START_ANGLE = 90;
    private static final int SWEEP_ANGLE = 360;
    Context context;

    RectF rect;

    private Paint arcForegroundPaint;
    private Paint arcBackgroundPaint;
    private Paint textPaint;
    private int progress = 0;

    public CircleProgress(Context context) {
        super(context);
        this.context = context;
        initialize();
    }


    public CircleProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initialize();
    }


    public CircleProgress(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initialize();
    }

    public void setProgress(int progress) {
        this.progress = progress;
        //this.progress = 85;
        postInvalidate();
    }

    private void initialize() {

        rect = new RectF();

        arcBackgroundPaint = new Paint();
        //arcBackgroundPaint.setColor(context.getResources().getColor(R.color.primary_light));
        arcBackgroundPaint.setColor(context.getResources().getColor(R.color.green_light));
        arcBackgroundPaint.setAntiAlias(true);
        arcBackgroundPaint.setStyle(Style.STROKE);

        arcForegroundPaint = new Paint();
        arcForegroundPaint.setColor(context.getResources().getColor(R.color.green));
        arcForegroundPaint.setAntiAlias(true);
        arcForegroundPaint.setStyle(Style.STROKE);

        textPaint = new Paint();
        textPaint.setColor(context.getResources().getColor(R.color.green));
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Align.CENTER);
        textPaint.setStyle(Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int baseSize = getWidth() < getHeight() ? getWidth() : getHeight();

        int textSize = baseSize / 3;
        float strokeWidth = (float) baseSize / 15;

        rect.set(strokeWidth, strokeWidth, baseSize - strokeWidth, baseSize - strokeWidth);

        arcForegroundPaint.setStrokeWidth(strokeWidth);
        arcBackgroundPaint.setStrokeWidth(strokeWidth);

        canvas.drawArc(rect, START_ANGLE, SWEEP_ANGLE, false, arcBackgroundPaint);


        int sweepAngle = progress * SWEEP_ANGLE / 100;
        canvas.drawArc(rect, START_ANGLE, sweepAngle, false, arcForegroundPaint);

        textPaint.setTextSize(textSize - (textSize / 4));
        //canvas.drawText(progress + "%", baseSize / 2, getHeight() / 2, textPaint);
        canvas.drawText(progress + "%", baseSize / 2, (getHeight() / 2) + (baseSize / 10), textPaint);
    }
}
