package com.example.dam_actividad3_anacarton;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {



    //Hay que recordar que el MainActivity es una extensión del AppCompatActivity
    // este elemento tiene el onCreate y el onDestroy.


    //DEFINICIÓN DE VARIABLES

    //La clase MediaPlayer se usa para reproducir archivos de audio de larga duración
    //Para poder escuchar una pista, hace falta crear un objeto de esta clase
    private MediaPlayer mediaPlayer, mp;
    ProgressBar progressBar;
    private MediaObserver observer = null;
    Button playButton, pauseButton, stopButton, rewindButton, forwardButton;

    WebView myVideoview;
    TextView metadataTV;
    //ImageButton btnPlayStop;
    VideoView videoView;
    MediaController mediaController;
    Spinner sourcesSpinner;
    Boolean salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Se inicializan las variables asociándolas a elementos del layout
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        //btnPlayStop = findViewById(R.id.button_play_stop);
        playButton = findViewById(R.id.buttonPlay);
        pauseButton = findViewById(R.id.buttonPause);
        stopButton = findViewById(R.id.buttonStop);
        rewindButton = findViewById(R.id.buttonRewind);
        forwardButton = findViewById(R.id.buttonForward);
        metadataTV = findViewById(R.id.metadata);
        salir = false;

        sourcesSpinner = findViewById(R.id.spinnerSources);

        //Definimos el array con las opciones que va a mostrar el spinner
        String[] options = {"CREATE MP3", "URI SETDATASOURCE MP3", "VIDEO MP4"};

        //Se declara el adaptador para el spinner y se le setea
        ArrayAdapter<String> optionsAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, options);
        sourcesSpinner.setAdapter(optionsAdapter);









            sourcesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    //Si está seleccionada la primera opción del spinner
                    if (sourcesSpinner.getSelectedItemPosition() == 0) {

                        playButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                play(view);

                            }
                        });

                        pauseButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                pause(view);
                            }
                        });

                        stopButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                stop(view);
                            }
                        });

                        forwardButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                forward(view);
                            }
                        });

                        rewindButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                rewind(view);
                            }
                        });
                    }

                    if(sourcesSpinner.getSelectedItemPosition() == 1){
                        playButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                play2(view);

                            }
                        });

                        pauseButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                pause(view);

                            }
                        });
                        stopButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                stop(view);
                            }
                        });
                        forwardButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                forward(view);

                            }
                        });

                        rewindButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                rewind(view);

                            }
                        });
                    }

                    if(sourcesSpinner.getSelectedItemPosition() == 2){
                        playButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                play(view);

                            }
                        });

                        pauseButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });

                        stopButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                stop(view);
                            }
                        });

                        forwardButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                forward(view);

                            }
                        });

                        rewindButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                rewind(view);

                            }
                        });

                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });






/*

observer = new MediaObserver();
                        new Thread(observer).start();
                }
                    pauseButton.setOnClickListener(new View.OnClickListener() {

                        //si después de darle a play, damos a pause
                        @Override
                        public void onClick(View view) {
                            if(mediaPlayer.isPlaying())
                            mediaPlayer.stop();
                            else mediaPlayer.
                        }
                    });

                    //si después de darle a play, damos a stop

                    stopButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(mediaPlayer.isPlaying())
                            mediaPlayer.stop();
                            else
                        }
                    });*/


            //Si está seleccionada la segunda opción del spinner

            if (sourcesSpinner.getSelectedItemPosition() == 1) {
                //USANDO URI Y SETDATASOURCE Y PREPARE
                //el nombre del archivo ha de estar en minúsculas
                Uri myUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.renaissance);


                mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mp.setDataSource(getApplicationContext(), myUri);
                    mp.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mp.start();

                observer = new MediaObserver();
                new Thread(observer).start();

                pauseButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mp.stop();
                    }
                });

                stopButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mp.stop();
                    }
                });


            }
            //Si está seleccionada la tercera opción del spinner

            if (sourcesSpinner.getSelectedItemPosition() == 2) {
                //Parte del vídeo
                //asociamos la variable con el elemento en el layout
                videoView = findViewById(R.id.video_view);
                //Asociamos la variable con el vídeo que está en Raw
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pinkfloyd));
                //El método start() inicia el vídeo
                videoView.start();
                //CONTROLES DEL VÍDEO
                mediaController = new MediaController(MainActivity.this);
                //mediaController sirve para añadir controler al vídeo para iniciar y parar
                mediaController.setAnchorView(videoView);
                videoView.setMediaController(mediaController);

                observer = new MediaObserver();
                new Thread(observer).start();

                pauseButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        videoView.pause();

                    }
                });

                stopButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        videoView.resume();
                    }
                });

            }


    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void play(View view) {
        //USANDO CREATE
        //Cuando esté seleccionada la primera opción EN EL SPINNER


        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        //El método create recibe dos parámetros: el contexto y la ruta a la pista de audio, que está en la carpeta raw
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.renaissance);
        //El método start() permite iniciar la reproducción de la pista de audio

        mediaPlayer.start();
    }

    public void play2(View view) {
        Uri myUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.renaissance);

        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mp.setDataSource(getApplicationContext(), myUri);
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.start();
    }

    public void pause(View view) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void continuar(View view) {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    public void forward(View view) {
        if (mediaPlayer != null) {
            int position = mediaPlayer.getCurrentPosition();
            mediaPlayer.seekTo(position + 10000);
        }
    }

    public void rewind(View view) {
        if (mediaPlayer != null) {
            int position = mediaPlayer.getCurrentPosition();
            mediaPlayer.seekTo(position - 10000);
        }
    }

    public void stop(View view){
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mp.stop();
            try {
                mp.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class MediaObserver implements Runnable {
        private AtomicBoolean stop = new AtomicBoolean(false);

        public void stop() {
            stop.set(true);
        }

        @Override
        public void run() {
            while (!stop.get()) {
                progressBar.setProgress((int) ((double) mediaPlayer.getCurrentPosition() / (double) mediaPlayer.getDuration() * 100));
                try {
                    Thread.sleep(200);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    //onDestroy es uno de los métodos de la clase madre AppCompatActivity
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mp.stop();
        salir = true;
    }
    //
/*
        myVideoview =findViewById(R.id.WebViewID);
        myVideoview.getSettings().setJavaScriptEnabled(true);
        myVideoview.loadUrl("https://www.youtube.com/watch?v=nk0BIwZP8RM&list=PLknSwrodgQ706MCpRz-_8dVMDoMhfEQ1X");
        myVideoview.setWebViewClient(new WebViewClient());
        myVideoview.setWebChromeClient(new WebChromeClient());

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try{
        //    mediaPlayer.setDataSource(MainActivity.this, uri);
            mediaPlayer.prepare();
        }
        catch(IOException e){
           e.printStackTrace();
        }
        mediaPlayer.start();


        btnPlayStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
                else{
                    mediaPlayer.start();
                }
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                observer.stop();
                progressBar.setProgress(mp.getCurrentPosition());
                mediaPlayer.stop();
                mediaPlayer.reset();
            }
        });
        }

*/
}
