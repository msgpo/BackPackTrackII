package eu.faircode.backpacktrack2;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class ActivityTypeAdapter extends CursorAdapter {
    private int colTime;
    private int colActivity;
    private int colCondidence;

    public ActivityTypeAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
        colTime = cursor.getColumnIndex("time");
        colActivity = cursor.getColumnIndex("activity");
        colCondidence = cursor.getColumnIndex("confidence");
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.activity, parent, false);
    }

    @Override
    public void bindView(final View view, final Context context, final Cursor cursor) {
        // Get values
        long time = cursor.getLong(colTime);
        int activity = cursor.getInt(colActivity);
        int confidence = cursor.getInt(colCondidence);

        // Get views
        TextView tvTime = (TextView) view.findViewById(R.id.tvTime);
        TextView tvActivity = (TextView) view.findViewById(R.id.tvActivity);
        TextView tvConfidence = (TextView) view.findViewById(R.id.tvConfidence);

        // Set values
        tvTime.setText(SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.SHORT, SimpleDateFormat.MEDIUM).format(time));
        tvActivity.setText(BackgroundService.getActivityName(activity, context));
        tvConfidence.setText(confidence + "%");
    }
}
