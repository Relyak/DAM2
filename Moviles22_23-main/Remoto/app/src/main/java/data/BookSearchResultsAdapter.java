package data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;
import com.example.remoto.R;
import java.util.ArrayList;
import java.util.List;

import data.Volume;

public class BookSearchResultsAdapter extends RecyclerView.Adapter<BookSearchResultsAdapter.BookSearchResultHolder> {
    private List<Volume> results = new ArrayList<>();
    private OnItemClickListener mListener;

    @NonNull
    @Override
    public BookSearchResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout, parent, false);

        return new BookSearchResultHolder(itemView);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull BookSearchResultHolder holder, int position) {
        Volume volume = results.get(position);

        holder.titleTextView.setText(volume.getVolumeInfo().getTitle());
        holder.publishedDateTextView.setText(volume.getVolumeInfo().getPublishedDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mListener.onItemClick(position);
                    }
                }
            }
        });

        if (volume.getVolumeInfo().getImageLinks() != null) {
            String imageUrl = volume.getVolumeInfo().getImageLinks().getSmallThumbnail()
                    .replace("http://", "https://");

            Glide.with(holder.itemView)
                    .load(imageUrl)
                    .into(holder.smallThumbnailImageView);

        }

        if (volume.getVolumeInfo().getAuthors() != null) {
            String autores="";
            for (String a: volume.getVolumeInfo().getAuthors()){
                autores+=a+", ";
            }
            holder.authorsTextView.setText(autores);
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setResults(List<Volume> results) {
        this.results = results;
        notifyDataSetChanged();
    }

    class BookSearchResultHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView authorsTextView;
        private TextView publishedDateTextView;
        private ImageView smallThumbnailImageView;

        public BookSearchResultHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.book_item_title);
            authorsTextView = itemView.findViewById(R.id.book_item_authors);
            publishedDateTextView = itemView.findViewById(R.id.book_item_publishedDate);
            smallThumbnailImageView = itemView.findViewById(R.id.book_item_smallThumbnail);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}