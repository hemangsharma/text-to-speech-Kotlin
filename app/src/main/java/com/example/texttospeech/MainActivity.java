package com.example.texttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    EditText input_text;
    Button speak_btn;
    TextToSpeech TTS;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input_text= findViewById(R.id.input);
        TTS = new TextToSpeech( this,this);
        speak_btn = findViewById(R.id.speak_button);
        speak_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speakIT();

            }
        });

    }

    @Override
    public void onInit(int i) {
        if (i==TextToSpeech.SUCCESS){
            int result=  TTS.setLanguage(Locale.getDefault());
            TTS.setSpeechRate(10);
            TTS.setPitch(-10);
            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
            {
               Log.e("TTS_Lang_Error","Language not supported");
            }else{
                speak_btn.setEnabled(true);
                speakIT();
            }


        } else{
            Log.e("TTS","Initialization Error");
        }



    }

    public void speakIT()
    {
        String text = input_text.getText().toString();
        TTS.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);
    }

}
