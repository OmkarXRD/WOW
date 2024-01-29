package com.live.worldsocialintegrationapp.Fragments.Music;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.telecom.Call;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.Activites.MainActivity;
import com.live.worldsocialintegrationapp.Adapters.LocalAddedAdapter;
import com.live.worldsocialintegrationapp.Adapters.MusicRVAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.Image;
import com.live.worldsocialintegrationapp.ModelClasses.Music;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.agora.openvcall.ui.CallActivity;
import com.live.worldsocialintegrationapp.room.AppDatabase;
import com.live.worldsocialintegrationapp.room.MusicTable;
import com.live.worldsocialintegrationapp.sqlite.DatabaseHandler;
import com.live.worldsocialintegrationapp.utils.App;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.agora.rtc.IAudioEffectManager;
import io.agora.rtc.RtcEngine;


public class MusicFragment extends Fragment implements Runnable, MusicRVAdapter.Callback {

    MusicRVAdapter musicRVAdapter;
    private LinearLayout noMusicLayout;
    private RelativeLayout musicPlayRL;
    public static ArrayList<Music> addedMusicList = new ArrayList<>();
    private RecyclerView musicRv;
    private TextView musicRescanTv, musicFragSongName, musicStartTimeTv, musicEndTimeTv;
    private MediaPlayer mediaPlayer;
    private ImageView playMusicFragmentImg, volumeMusicFragmentImg, musicBackImg;
    private SeekBar seekbar;
    int musicPlayStatus;
    boolean wasPlaying = false;
    public String audioPath="";

    IAudioEffectManager audioEffectManager;
    RtcEngine mRtcEngine;
    List<MusicTable> musicList= new ArrayList<>();

    AppDatabase appDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_music, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        appDatabase= AppDatabase.getInstance(requireContext());

        findIds(view);
        clicks(view);
        getMuisc();
        onBackPressed(view);


        if (musicList.isEmpty()) {
            noMusicLayout.setVisibility(View.VISIBLE);
            musicRv.setVisibility(View.GONE);
            musicRescanTv.setVisibility(View.GONE);
            musicPlayRL.setVisibility(View.GONE);

        } else {
            noMusicLayout.setVisibility(View.GONE);
            musicRv.setVisibility(View.VISIBLE);
            musicRescanTv.setVisibility(View.VISIBLE);
            musicRVAdapter = new MusicRVAdapter(musicList, requireContext(), MusicFragment.this);
            musicRv.setAdapter(musicRVAdapter);
            musicRVAdapter.notifyDataSetChanged();
        }


    }

    private void findIds(View view) {
        noMusicLayout = view.findViewById(R.id.noMusicLayout);
        musicRv = view.findViewById(R.id.musicRv);
        musicRescanTv = view.findViewById(R.id.musicRescanTv);
        musicPlayRL = view.findViewById(R.id.musicPlayRL);
        playMusicFragmentImg = (ImageView) view.findViewById(R.id.playMusicFragmentImg);
        musicFragSongName = (TextView) view.findViewById(R.id.musicFragSongName);
        musicStartTimeTv = (TextView) view.findViewById(R.id.musicStartTimeTv);
        musicEndTimeTv = (TextView) view.findViewById(R.id.musicEndTimeTv);
        seekbar = (SeekBar) view.findViewById(R.id.seekbar);
        volumeMusicFragmentImg = (ImageView) view.findViewById(R.id.volumeMusicFragmentImg);
        musicBackImg = (ImageView) view.findViewById(R.id.musicBackImg);

    }

    private void clicks(View view) {

        view.findViewById(R.id.scanAndAddMusicBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().findViewById(R.id.callAcitivtyMainRL).setVisibility(View.GONE);
                getActivity().findViewById(R.id.callActivityFrameLayout).setVisibility(View.VISIBLE);
                LocalAddedFragment localAddedFragment = new LocalAddedFragment();
                CallActivity.backDialogShow = 1;
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.callActivityFrameLayout, localAddedFragment).addToBackStack(null).commit();

            }
        });

        musicBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().findViewById(R.id.callAcitivtyMainRL).setVisibility(View.VISIBLE);
            }
        });
        musicRescanTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalAddedFragment localAddedFragment = new LocalAddedFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.callActivityFrameLayout, localAddedFragment).addToBackStack(null).commit();
            }
        });

        playMusicFragmentImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pausePlaying();
            }
        });
    }


    @Override
    public void playMusic(MusicTable musicDetail, int musicPlayStatus, ImageView imageView, boolean playOrNot, int position) {
        if (musicDetail != null) {



            startPlaying(musicDetail, imageView, playOrNot, position);

            seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (fromUser) {
                        mediaPlayer.seekTo(seekBar.getProgress());
                    }
                    musicStartTimeTv.setText(formatDuration(progress));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }
    }

    @Override
    public void deleteMusic(MusicTable musicTable,int posn) {

     //   appDatabase.userDao().deleteById(Integer.parseInt(String.valueOf(musicTable.getId());

        //Toast.makeText(getApplicationContext(), "fef"+musicTable.getId(), Toast.LENGTH_SHORT).show();
        appDatabase.userDao().deleteById(musicTable.getId());
    }

    private void startPlaying(MusicTable musicDetails, ImageView imageView, boolean playOrNot, int position) {

        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            playMusicFragmentImg.setImageResource(R.drawable.ic_baseline_pause_circle_24);
            mediaPlayer.pause();
            mediaPlayer.release();
            Log.d("CALLACTIVITY", "isPlaying" + musicDetails.getTitle() + "  ---" + playOrNot + " Postion : " + position);

            mediaPlayer = new MediaPlayer();
            try {
                CallActivity.audioPath = musicDetails.getPath().toString();
                mediaPlayer.setDataSource(musicDetails.getPath());
                mediaPlayer.prepare();
                mediaPlayer.start();
                playMusicFragmentImg.setImageResource(R.drawable.ic_baseline_pause_circle_24);
                new Thread(this).start();
                musicFragSongName.setText(musicDetails.getTitle());
                musicPlayRL.setVisibility(View.VISIBLE);
                seekbar.setProgress(0);

                seekbar.setMax(mediaPlayer.getDuration());

                musicStartTimeTv.setText(formatDuration(mediaPlayer.getCurrentPosition()));
                musicEndTimeTv.setText(formatDuration(musicDetails.getDuration()));
            } catch (Exception e) {
            }
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    playMusicFragmentImg.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
                }
            });

        } else if (mediaPlayer != null) {
            mediaPlayer.start();
            new Thread(this).start();
            Log.d("CALLACTIVITY", "mediaPlayerNotNull " + musicDetails.getTitle() + "  ---" + playOrNot);
        } else {
            mediaPlayer = new MediaPlayer();
            try {
                CallActivity.audioPath = musicDetails.getPath().toString();
                CallActivity.check=1;

                audioPath = musicDetails.getPath().toString();

                mediaPlayer.setDataSource(musicDetails.getPath());
                mediaPlayer.prepare();
                mediaPlayer.start();
                playMusicFragmentImg.setImageResource(R.drawable.ic_baseline_pause_circle_24);
                Log.d("CALLACTIVITY", "mediaPlayerStart " + musicDetails.getTitle() + "  ---" + playOrNot + "Postion : " + position);

                new Thread(this).start();

                musicFragSongName.setText(musicDetails.getTitle());
                musicPlayRL.setVisibility(View.VISIBLE);
                seekbar.setProgress(0);
                seekbar.setMax(mediaPlayer.getDuration());
                musicStartTimeTv.setText(formatDuration(mediaPlayer.getCurrentPosition()));
                musicEndTimeTv.setText(formatDuration(musicDetails.getDuration()));
            } catch (Exception e) {
            }

        }
    }

    private void stopPlaying() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    private void pausePlaying() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                musicStartTimeTv.setText(formatDuration(seekbar.getProgress()));
                Log.d("MUSICFRAGMENT", "pause ");
                playMusicFragmentImg.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
            } else {
                mediaPlayer.start();
                musicStartTimeTv.setText(formatDuration(seekbar.getProgress()));
                new Thread(this).start();
                Log.d("MUSICFRAGMENT", "play ");
                playMusicFragmentImg.setImageResource(R.drawable.ic_baseline_pause_circle_24);
            }
        } else {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.release();
            mediaPlayer.stop();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        stopPlaying();
    }

    public void run() {

        int currentPosition = mediaPlayer.getCurrentPosition();
        int total = mediaPlayer.getDuration();

        while (mediaPlayer != null && mediaPlayer.isPlaying() && currentPosition < total) {
            try {
                Thread.sleep(1000);
                currentPosition = mediaPlayer.getCurrentPosition();

            } catch (InterruptedException e) {
                return;
            } catch (Exception e) {
                return;
            }
            seekbar.setProgress(currentPosition);


        }
    }

    //this method duration of song covert into mintues
    private String formatDuration(long duration) {
        long minutes = TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS);
        long seconds = TimeUnit.SECONDS.convert(duration, TimeUnit.MILLISECONDS)
                - minutes * TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES);
        return String.format("%02d:%02d", minutes, seconds);
    }

    private void onBackPressed(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if (i == KeyEvent.KEYCODE_BACK) {

                        getActivity().findViewById(R.id.callAcitivtyMainRL).setVisibility(View.VISIBLE);
                        getActivity().findViewById(R.id.callActivityFrameLayout).setVisibility(View.GONE);
                        CallActivity.backDialogShow = 1;

                        return true;
                    }
                }
                return false;
            }
        });

    }


    private void getMuisc(){

        musicList =  appDatabase.userDao().getAllSongs();

        for (int i=0;i<musicList.size();i++){
            Log.d("MUSICFRAGMENT","songs :"+musicList.get(i).getPath());
        }
    }

}