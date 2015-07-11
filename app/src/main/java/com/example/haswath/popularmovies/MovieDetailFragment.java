package com.example.haswath.popularmovies;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haswath.popularmovies.dummy.DummyContent;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A fragment representing a single Movie detail screen.
 * This fragment is either contained in a {@link MovieListActivity}
 * in two-pane mode (on tablets) or a {@link MovieDetailActivity}
 * on handsets.
 */
public class MovieDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MovieDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        Log.i("detail", "loading image "+ mItem.content);
        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            JSONObject jsonObject = mItem.content;
            ImageView iv = ((ImageView) container.findViewById(R.id.imageView));
            try {
                String url = "http://image.tmdb.org/t/p/w185" + jsonObject.getString("poster_path");
                Picasso.with(getActivity()).load(url).into(iv);
                TextView tv = ((TextView) container.findViewById(R.id.textView));
                tv.setText(jsonObject.getString("original_title"));
                tv = ((TextView) container.findViewById(R.id.textView2));
                tv.setText(jsonObject.getString("release_date").substring(0, 4));
                tv = ((TextView) container.findViewById(R.id.tvOverview));
                tv.setText(jsonObject.getString("overview"));
                tv = ((TextView) container.findViewById(R.id.tvRating));
                tv.setText(jsonObject.getString("vote_average"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return rootView;
    }
}
