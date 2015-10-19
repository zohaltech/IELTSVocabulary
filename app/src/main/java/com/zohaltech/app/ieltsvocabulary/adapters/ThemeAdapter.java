package com.zohaltech.app.ieltsvocabulary.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zohaltech.app.ieltsvocabulary.R;
import com.zohaltech.app.ieltsvocabulary.activities.MainActivity;
import com.zohaltech.app.ieltsvocabulary.activities.VocabulariesActivity;
import com.zohaltech.app.ieltsvocabulary.classes.App;
import com.zohaltech.app.ieltsvocabulary.classes.LearningStatus;
import com.zohaltech.app.ieltsvocabulary.data.SystemSettings;
import com.zohaltech.app.ieltsvocabulary.entities.SystemSetting;
import com.zohaltech.app.ieltsvocabulary.entities.Theme;

import java.util.ArrayList;

import widgets.CircleProgress;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> {

    private static final long DURATION = 300;

    Activity                        activity;
    ArrayList<Theme>                themes;
    ArrayList<ProgressDetailStatus> progressDetailStatuses;
    //ImageLoader                     imageLoader;

    public ThemeAdapter(Activity activity, ArrayList<Theme> themes) {
        this.activity = activity;
        this.themes = themes;
        this.progressDetailStatuses = new ArrayList<>();
        for (int i = 0; i < themes.size(); i++) {
            progressDetailStatuses.add(new ProgressDetailStatus(i, false));
        }
        //imageLoader = ImageLoader.getInstance();
        //imageLoader.init(ImageLoaderConfiguration.createDefault(activity));
    }

    public static void expand(final View v) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                                             ? ViewGroup.LayoutParams.WRAP_CONTENT
                                             : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        //        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        a.setDuration(DURATION);
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        //        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        a.setDuration(DURATION);
        v.startAnimation(a);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.adapter_theme, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Theme theme = themes.get(position);
        holder.imgTheme.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, App.screenHeight / 3));

        //final int imageId = activity.getResources().getIdentifier(theme.getIconName(), "drawable", activity.getPackageName());
        //Picasso.with(activity).load(imageId).into(holder.imgTheme);

        if (position == 0) {
            Picasso.with(activity).load(R.drawable.education).into(holder.imgTheme);
        } else if (position == 1) {
            Picasso.with(activity).load(R.drawable.job).into(holder.imgTheme);
        } else if (position == 2) {
            Picasso.with(activity).load(R.drawable.media).into(holder.imgTheme);
        } else if (position == 3) {
            Picasso.with(activity).load(R.drawable.health).into(holder.imgTheme);
        } else if (position == 4) {
            Picasso.with(activity).load(R.drawable.environment).into(holder.imgTheme);
        } else if (position == 5) {
            Picasso.with(activity).load(R.drawable.advertising).into(holder.imgTheme);
        } else if (position == 6) {
            Picasso.with(activity).load(R.drawable.foreign_language).into(holder.imgTheme);
        } else if (position == 7) {
            Picasso.with(activity).load(R.drawable.urbanisation).into(holder.imgTheme);
        } else if (position == 8) {
            Picasso.with(activity).load(R.drawable.law).into(holder.imgTheme);
        } else if (position == 9) {
            Picasso.with(activity).load(R.drawable.sport).into(holder.imgTheme);
        } else if (position == 10) {
            Picasso.with(activity).load(R.drawable.space).into(holder.imgTheme);
        } else if (position == 11) {
            Picasso.with(activity).load(R.drawable.science).into(holder.imgTheme);
        } else if (position == 12) {
            Picasso.with(activity).load(R.drawable.causes).into(holder.imgTheme);
        }

        //new ImageLoaderTask(holder.imgTheme).execute(imageId);

        holder.txtTheme.setText(theme.getName());

        SystemSetting setting = SystemSettings.getCurrentSettings();
        if (position < 2 || setting.isPremium()) {
            holder.imgPremium.setVisibility(View.GONE);
            holder.layoutRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(App.currentActivity, VocabulariesActivity.class);
                    intent.putExtra("THEME", theme);
                    App.currentActivity.startActivity(intent);
                }
            });
        } else {
            holder.imgPremium.setVisibility(View.VISIBLE);
            holder.layoutRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //MySnackbar.show(v, "Please buy premium version", Snackbar.LENGTH_SHORT);
                    ((MainActivity)activity).showPaymentDialog();
                }
            });
        }
        //holder.layoutProgressDetail.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
        holder.layoutProgressDetail.setVisibility(View.GONE);
        //collapse(holder.layoutProgressDetail);

        LearningStatus status = LearningStatus.getLearningStatusByTheme(theme.getId());
        if (status != null) {
            holder.layoutDivider.setVisibility(View.VISIBLE);
            holder.layoutCircleProgress.setVisibility(View.VISIBLE);
            holder.circleProgress.setProgress(status.getProgress());

            if (progressDetailStatuses.get(position).visible) {
                holder.layoutProgressDetail.setVisibility(View.VISIBLE);
            } else {
                holder.layoutProgressDetail.setVisibility(View.GONE);
            }

            holder.txtDayProgress.setText(String.format("Day %d/%d", status.getDayIndex(), status.getDayCount()));
            holder.txtVocabProgress.setText(String.format("Vocab %d/%d", status.getVocabIndex(), status.getVocabCount()));

            holder.layoutCircleProgress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ProgressDetailStatus status = progressDetailStatuses.get(position);
                    if (status.visible) {
                        collapse(holder.layoutProgressDetail);
                        App.handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                holder.layoutProgressDetail.setVisibility(View.GONE);
                            }
                        }, DURATION);
                        status.visible = false;
                    } else {
                        holder.layoutProgressDetail.setVisibility(View.VISIBLE);
                        //ViewCompat.animate(holder.layoutProgressDetail).scaleYBy(12).setDuration(1000).start();
                        expand(holder.layoutProgressDetail);
                        status.visible = true;
                    }
                }
            });
        } else {
            holder.layoutDivider.setVisibility(View.GONE);
            holder.layoutCircleProgress.setVisibility(View.GONE);
            holder.layoutProgressDetail.setVisibility(View.GONE);
            progressDetailStatuses.set(position, new ProgressDetailStatus(position, false));
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public int getItemCount() {
        return themes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout   layoutRoot;
        public ImageView      imgTheme;
        public ImageView      imgPremium;
        public TextView       txtTheme;
        public CircleProgress circleProgress;
        public LinearLayout   layoutDivider;
        public LinearLayout   layoutCircleProgress;
        public LinearLayout   layoutProgressDetail;
        public TextView       txtDayProgress;
        public TextView       txtVocabProgress;

        public ViewHolder(View view) {
            super(view);
            layoutRoot = (LinearLayout) view.findViewById(R.id.layoutRoot);
            imgTheme = (ImageView) view.findViewById(R.id.imgTheme);
            imgPremium = (ImageView) view.findViewById(R.id.imgPremium);
            txtTheme = (TextView) view.findViewById(R.id.txtTheme);
            layoutDivider = (LinearLayout) view.findViewById(R.id.layoutDivider);
            layoutCircleProgress = (LinearLayout) view.findViewById(R.id.layoutCircleProgress);
            circleProgress = (CircleProgress) view.findViewById(R.id.circleProgress);
            layoutProgressDetail = (LinearLayout) view.findViewById(R.id.layoutProgressDetail);
            txtDayProgress = (TextView) view.findViewById(R.id.txtDayProgress);
            txtVocabProgress = (TextView) view.findViewById(R.id.txtVocabProgress);
        }
    }

    private class ProgressDetailStatus {
        public int     position;
        public boolean visible;

        public ProgressDetailStatus(int position, boolean visible) {
            this.position = position;
            this.visible = visible;
        }
    }

    //private class ImageLoaderTask extends AsyncTask<Integer, Void, Bitmap> {
    //
    //    ImageView imageView;
    //
    //    public ImageLoaderTask(ImageView imageView) {
    //        this.imageView = imageView;
    //    }
    //
    //    @Override
    //    protected void onPreExecute() {
    //        imageView.setImageBitmap(null);
    //    }
    //
    //    @Override
    //    protected Bitmap doInBackground(Integer... params) {
    //        //return decodeSampledBitmapFromResource(activity.getResources(), params[0], App.screenWidth, App.screenHeight / 3);
    //        return BitmapFactory.decodeResource(activity.getResources(), params[0]);
    //    }
    //
    //    @Override
    //    protected void onPostExecute(Bitmap bitmap) {
    //        //super.onPostExecute(bitmap);
    //        imageView.setImageBitmap(bitmap);
    //    }
    //}
}
