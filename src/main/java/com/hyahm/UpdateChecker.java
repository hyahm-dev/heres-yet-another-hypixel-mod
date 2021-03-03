package com.hyahm;

import com.google.gson.JsonElement;

import java.io.*;
import java.net.URL;

public class UpdateChecker {
    public static class Version {
        int major;
        int minor;
        int patch;

        public void fromString(String str) {
            String[] tmp = str.split("\\.");
            if(tmp.length == 2) {
                major = Integer.parseInt(tmp[0]);
                minor = Integer.parseInt(tmp[1]);
                patch = 0;
            }
            else if(tmp.length == 3) {
                major = Integer.parseInt(tmp[0]);
                minor = Integer.parseInt(tmp[1]);
                patch = Integer.parseInt(tmp[2]);
            }
        }

        public String toString() {
            return Integer.toString(major) + '.' + minor + (patch > 0 ? '.' + patch : "");
        }
    }
    public static class ModUpdate {
        Version version;
    }

    static ModUpdate getUpdate() {
        ModUpdate update = new ModUpdate();

        try(InputStream stream = new URL(HyahmMain.MOD_UPDATE).openStream()) {
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder builder = new StringBuilder();

            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                builder.append(currentLine);
            }

            JsonElement elem = HyahmMain.gson.fromJson(builder.toString(), JsonElement.class);
            update.version.fromString(elem.getAsJsonObject().getAsJsonPrimitive("version").getAsString());
        }
        catch (Exception e) {
            HyahmMain.logger.error("Unable to download update json", e);
            return null;
        }

        return update;
    }
}
