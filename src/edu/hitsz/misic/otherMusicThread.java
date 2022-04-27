package edu.hitsz.misic;

import edu.hitsz.application.Game;
import edu.hitsz.application.Main;

import javax.sound.sampled.*;
import java.io.*;

public class otherMusicThread extends MusicThread {

    public otherMusicThread(String filename) {
        super(filename);
    }

    @Override
    public boolean isContinue() {
        return true;
    }

    @Override
    public boolean isLoop() {
        return false;
    }
}



