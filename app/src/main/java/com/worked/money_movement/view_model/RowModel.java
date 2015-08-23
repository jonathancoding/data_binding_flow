package com.worked.money_movement.view_model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.drawable.Drawable;

import com.worked.money_movement.view.Hub_Activity;

/**
 * Row Model Object
 * Represents each row in the hub {@link Hub_Activity}
 */
public class RowModel extends BaseObservable {

    // base attributes
    private String id;
    private int index;

    // row attributes
    private boolean locked, enabled, optional, visible;
    private String style;

    // views
    private String title, subtitle, hint;
    private Drawable icon, icon_disabled;

    // original values - unbound data
    private String subtitle_orig, title_orig;
    private boolean visible_orig, enabled_orig, locked_orig, optional_orig;

    //-- views

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title, boolean... setOriginal) {
        this.title = title;

        if (setOriginal.length > 0 && setOriginal[0]) title_orig = title;

        notifyChange();
    }

    public void resetTitle() {
        setTitle(title_orig);
    }

    @Bindable
    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle, boolean... setOriginal) {
        this.subtitle = subtitle;

        if (setOriginal.length > 0 && setOriginal[0]) subtitle_orig = subtitle;

        notifyChange();
    }

    public void resetSubtitle() {
        setSubtitle(subtitle_orig);
    }

    @Bindable
    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    @Bindable
    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;

        notifyChange();
    }

    @Bindable
    public Drawable getIconDisabled() {
        return icon_disabled;
    }

    public void setIconDisabled(Drawable icon_disabled) {
        this.icon_disabled = icon_disabled;

        notifyChange();
    }

    // -- row styles

    @Bindable
    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    // -- attributes

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Bindable
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Bindable
    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked, boolean... setOriginal) {
        this.locked = locked;

        if (setOriginal.length > 0 && setOriginal[0]) locked_orig = locked;

        notifyChange();
    }

    public void resetLocked() {
        setLocked(locked_orig);
    }

    @Bindable
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled, boolean... setOriginal) {
        this.enabled = enabled;

        if (setOriginal.length > 0 && setOriginal[0]) enabled_orig = enabled;

        notifyChange();
    }

    public void resetEnabled() {
        setEnabled(enabled_orig);
    }

    @Bindable
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible, boolean... setOriginal) {
        this.visible = visible;

        if (setOriginal.length > 0 && setOriginal[0]) visible_orig = visible;

        notifyChange();
    }

    public void resetVisible() {
        setVisible(visible_orig);
    }

    @Bindable
    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional, boolean... setOriginal) {
        this.optional = optional;

        if (setOriginal.length > 0 && setOriginal[0]) optional_orig = optional;

        notifyChange();
    }

    public void resetOptional() {
        setOptional(optional_orig);
    }
}