package com.example.arjun_mu.mvptestingcodelab;

import java.io.IOException;

/**
 * Created by arjun_mu on 4/30/2018.
 */

public class FakeImageFileImpl extends ImageFileImpl {
    @Override
    public void create(String name, String extension) throws IOException {
        // Do nothing
    }

    @Override
    public String getPath() {
        return "file:///android_asset/atsl-logo.png";
    }

    @Override
    public boolean exists() {
        return true;
    }
}
