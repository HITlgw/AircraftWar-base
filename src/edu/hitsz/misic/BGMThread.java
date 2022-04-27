package edu.hitsz.misic;

import edu.hitsz.application.Game;
import edu.hitsz.application.Main;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.DataLine.Info;

public class BGMThread extends MusicThread {

    private Game game;

    public BGMThread(String filename,Game game) {
        super(filename);
        this.game=game;
    }

    @Override
    public boolean isContinue() {
        return !game.isGameOver();
    }

    @Override
    public boolean isLoop() {
        return true;
    }



}


