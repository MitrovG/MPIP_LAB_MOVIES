package mk.ukim.finki.mpip.labmovies.adapters.holders;

import android.support.annotation.NonNull;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import mk.ukim.finki.mpip.labmovies.GlideApp;
import mk.ukim.finki.mpip.labmovies.R;
import mk.ukim.finki.mpip.labmovies.models.Movie;

public class MovieHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView textViewTitle;
    private TextView textViewYear;

    public MovieHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imgMovieCard);
        textViewTitle = itemView.findViewById(R.id.tvMovieTitleCard);
        textViewYear = itemView.findViewById(R.id.tvMovieYearCard);
    }

    public void bind(Movie movie) {
        textViewTitle.setText(movie.getTitle());
        textViewYear.setText(movie.getYear());
        RequestOptions options = new RequestOptions();
        options.transform(new RoundedCorners(20));
        GlideApp.with(itemView.getContext())
                .load(movie.getPhotoUrl())
                .apply(options)
                .placeholder(getPlaceholder())
                .error(R.drawable.ic_error_black_24dp)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    private CircularProgressDrawable getPlaceholder() {

        CircularProgressDrawable cpd = new CircularProgressDrawable(itemView.getContext());
        cpd.setCenterRadius(30f);
        cpd.setStrokeWidth(5f);
        cpd.start();

        return cpd;

    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public TextView getTextViewTitle() {
        return textViewTitle;
    }

    public void setTextViewTitle(TextView textViewTitle) {
        this.textViewTitle = textViewTitle;
    }

    public TextView getTextViewYear() {
        return textViewYear;
    }

    public void setTextViewYear(TextView textViewYear) {
        this.textViewYear = textViewYear;
    }

}
