package com.worked.money_movement.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;

import com.worked.money_movement.view_model.RowModel;
import com.worked.money_movement.view_model_controller.HubRow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Utils : Parse Json
 */
public class ParseUtils {
    private static final String TAG = ParseUtils.class.getSimpleName();

    /**
     * Parse Json
     */
    public static void parseJsonData(Context context, String json) {
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(context, json));

            JSONObject money_movement = obj.getJSONObject("money_movement");

            JSONArray rows = money_movement.getJSONArray("rows");

            HubRow hubRow = HubRow.getInstance();

            for (int i = 0; i < rows.length(); i++) {
                // row data
                RowModel rowModel = new RowModel();

                JSONObject row = rows.optJSONObject(i).optJSONObject("row");

                rowModel.setId(row.optString("id"));

                rowModel.setIndex(row.optInt("index"));

                // row views
                JSONObject views = row.optJSONObject("views");

                if (views != null) {
                    rowModel.setTitle(views.optString("title"), true);

                    rowModel.setSubtitle(views.optString("subtitle"), true);

                    rowModel.setHint(views.optString("hint"));

                    rowModel.setIcon(findDrawableByName(context, views.optString("icon")));

                    rowModel.setIconDisabled(findDrawableByName(context, views.optString("icon_disabled")));
                }

                // row attributes
                JSONObject attributes = row.optJSONObject("attributes");

                if (attributes != null) {
                    rowModel.setEnabled(attributes.optBoolean("enabled"), true);

                    rowModel.setLocked(attributes.optBoolean("locked", true), true);

                    rowModel.setOptional(attributes.optBoolean("optional"), true);

                    rowModel.setVisible(attributes.optBoolean("visible", true), true);

                    rowModel.setStyle(attributes.optString("style"));
                }

                if (rowModel == null) {
                    return;
                }

                hubRow.addRow(rowModel);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper : Get drawable resource by name
     *
     * @param name resource
     * @return drawable
     */
    private static Drawable findDrawableByName(Context context, String name) {
        try {
            Resources resources = context.getResources();

            return ResourcesCompat.getDrawable(resources, resources.getIdentifier(name, "drawable", context.getPackageName()), null);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Helper : Loads Json asset
     *
     * @return json string
     */
    private static String loadJSONFromAsset(Context context, @NonNull String json) {
        try {
            InputStream is = context.getAssets().open(json);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
