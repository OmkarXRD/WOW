package com.live.worldsocialintegrationapp.Fragments.Music;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.live.worldsocialintegrationapp.Adapters.LocalAddedAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.Music;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.room.AppDatabase;


import java.util.ArrayList;

public class LocalAddedFragment extends Fragment  {

    private RecyclerView localAddedRV;
    private ArrayList<Music> audioList = new ArrayList<>();
    private ArrayList<Music> addedMusicList = new ArrayList<>();
    private LocalAddedAdapter localAddedAdapter;
    AppDatabase appDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_local_added, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        appDatabase= AppDatabase.getInstance(requireContext());
        findIds(view);
        clicks(view);
        getMusic();


        localAddedAdapter = new LocalAddedAdapter(audioList, requireContext());
        localAddedRV.setAdapter(localAddedAdapter);

        onBackPressed(view);

    }

    private void findIds(View view) {
        localAddedRV = view.findViewById(R.id.localAddedRV);
    }


    private void clicks(View view) {

        view.findViewById(R.id.localAddedBackImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
                getActivity().findViewById(R.id.callAcitivtyMainRL).setVisibility(View.GONE);
                getActivity().findViewById(R.id.callActivityFrameLayout).setVisibility(View.VISIBLE);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.callActivityFrameLayout, new MusicFragment()).addToBackStack(null).commit();

            }
        });
    }

    private void getMusic() {

        String[] proj = {MediaStore.Audio.Media._ID, MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ALBUM, MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATE_ADDED, MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.ALBUM_ID};// Can include more data for more details and check it.

        Cursor audioCursor = getActivity().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, proj, null, null, null);
        if (audioCursor != null) {
            if (audioCursor.moveToFirst()) {
                do {
                    String titleC = audioCursor.getString(audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                    String idC = audioCursor.getString(audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                    String albumC = audioCursor.getString(audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
                    String artistC = audioCursor.getString(audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                    String pathC = audioCursor.getString(audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                    @SuppressLint("Range") Long durationC = audioCursor.getLong(audioCursor.getColumnIndex(MediaStore.Audio.Media.DURATION));


                    Uri uri = Uri.parse("content://media/external/audio/albumart");
                    String artUriC = Uri.withAppendedPath(uri, albumC).toString();


                    Music music = new Music(idC, titleC, albumC, artistC, pathC, durationC, artUriC);
                    audioList.add(music);

                } while (audioCursor.moveToNext());
            }
        }
        audioCursor.close();

    }

    private void onBackPressed(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if (i == KeyEvent.KEYCODE_BACK) {
                        getActivity().findViewById(R.id.callAcitivtyMainRL).setVisibility(View.GONE);
                        getActivity().findViewById(R.id.callActivityFrameLayout).setVisibility(View.VISIBLE);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.callActivityFrameLayout, new MusicFragment()).addToBackStack(null).commit();
                        return true;
                    }
                }
                return false;
            }
        });

    }

}