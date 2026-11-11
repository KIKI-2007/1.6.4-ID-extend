package com.zxl.betterid.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public final class BetterIDConfig {
    private static final String KEY_DIAGNOSTICS = "diagnostics.enabled";
    private boolean diagnosticsEnabled = true;

    public void load(File file) throws IOException {
        Properties properties = new Properties();
        if (file.isFile()) {
            FileInputStream input = new FileInputStream(file);
            try { properties.load(input); } finally { input.close(); }
        }
        diagnosticsEnabled = Boolean.parseBoolean(properties.getProperty(KEY_DIAGNOSTICS, "true"));

        File parent = file.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            throw new IOException("Could not create config directory: " + parent);
        }
        FileOutputStream output = new FileOutputStream(file);
        try {
            properties.setProperty(KEY_DIAGNOSTICS, String.valueOf(diagnosticsEnabled));
            properties.store(output, "Better ID Extender 1.6.4");
        } finally {
            output.close();
        }
    }

    public boolean isDiagnosticsEnabled() { return diagnosticsEnabled; }
}
