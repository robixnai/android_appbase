package br.unip.myapplication.views.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import br.unip.myapplication.R;
import br.unip.myapplication.managers.LocationType;
import br.unip.myapplication.managers.MyLocation;
import br.unip.myapplication.models.Location;
import br.unip.myapplication.views.adapters.ListingAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class SubwayFragment extends Fragment {

    public static final String ANDROID_APPS_MAPS = "com.google.android.apps.maps";

    private RecyclerView mRecyclerView;

    public SubwayFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_subway, container, false);
        return mRecyclerView;
    }

    @Override
    public void onResume() {
        super.onResume();
        setupRecyclerView(mRecyclerView);
    }

    public void setupRecyclerView(RecyclerView recyclerView) {
        final MyLocation myLocation = new MyLocation();
        final Location location = new Location();
        final List<Location> locations = location.getAll(LocationType.SUBWAY);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ListingAdapter listingAdapter = new ListingAdapter(locations, new ListingAdapter.OnLocationClickListener() {
            @Override
            public void onLocationClick() {
                Uri gmmIntentUri;
                final String uriCordinatesSubway = "geo:" + myLocation.getLatitude(getActivity()) + "," + myLocation.getLongitude(getActivity()) + "?q=" + getString(R.string.str_subway);
                gmmIntentUri = Uri.parse(uriCordinatesSubway);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage(ANDROID_APPS_MAPS);
                startActivity(mapIntent);
            }
        }, new ListingAdapter.OnLongLocationClickListener() {
            @Override
            public void onCardClick() {
                Toast.makeText(getActivity(), "Click long. Implementar aqui a chamada para os detalhes", Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(listingAdapter);
        listingAdapter.notifyDataSetChanged();
    }

}
