package net.wyvest.mod.utils;

import ga.matthewtgm.json.objects.JsonObject;
import ga.matthewtgm.json.parsing.JsonParser;
import net.wyvest.mod.Constants;
import net.wyvest.nod.WyfoldMod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/*/
    By MatthewTGM
    Will not work unless versFileUrl is defined to a valid json
 */

public class VersionChecker {

    public String verJson;
    public JsonObject verOBJ;

    {
        try {
            String versFileUrl = "JSON FILE HERE";
            BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(versFileUrl).openStream()));
            verJson = reader.readLine();
            verOBJ = JsonParser.parseObj(verJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getVersion() {
        try {
            JsonObject obj = JsonParser.parseObj(verJson);
            return String.valueOf(obj.get("latest"));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    public boolean getEmergencyStatus() {

        try {

            JsonObject obj = JsonParser.parseObj(verJson);

            boolean emergency;
            if (WyfoldMod.getInstance().isNull(obj.get("emergency_update_" + Constants.VER)))
                emergency = false;
            else emergency = (boolean) obj.get("emergency_update_" + Constants.VER);

            return emergency;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

    }


}
