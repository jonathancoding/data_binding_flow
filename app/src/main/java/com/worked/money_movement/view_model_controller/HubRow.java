package com.worked.money_movement.view_model_controller;

import android.support.annotation.NonNull;

import com.worked.money_movement.utils.shared.Spokes;
import com.worked.money_movement.view_model.RowModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Hub Row Data Object - Singleton
 * Represents data in each row
 * Helper methods to manipulate the {@link RowModel} data
 * <p/>
 * List containing {@link RowModel} representing each row in the {@link HubRow}
 */
public class HubRow {
    private static final HubRow INSTANCE = new HubRow();

    private static List<RowModel> data = new ArrayList();

    private static RowModel previous, current, next;

    private HubRow() {
        if (INSTANCE != null) {
            throw new IllegalStateException("Already instantiated...");
        }
    }

    public static HubRow getInstance() {
        return INSTANCE;
    }

    /**
     * Get model data
     *
     * @return data
     */
    public static List<RowModel> getRows() {
        return data;
    }

    /**
     * Add model data
     *
     * @param rowData row data
     */
    public static void addRow(@NonNull RowModel rowData) {
        data.add(rowData);
    }

    //-- Helpers

    /**
     * Reset hub
     */
    public static void resetHub() {
        for (RowModel row : data) {
            row.resetSubtitle();

            row.resetVisible();

            row.resetEnabled();

            row.resetLocked();
        }
    }

    /**
     * Reset hub from spoke index on
     *
     * @param spokeIndex index
     */
    public static void resetHub(@NonNull int spokeIndex) {
        for (int i = spokeIndex; i < data.size(); i++) {
            data.get(i).resetSubtitle();

            data.get(i).resetVisible();

            data.get(i).resetEnabled();

            data.get(i).resetLocked();
        }
    }

    /**
     * Unlock row
     *
     * @param row        row
     * @param exceptions ignore certain spokes
     */
    public static void unlock(@NonNull RowModel row, @NonNull Spokes... exceptions) {
        // ignore
        for (Spokes spoke : exceptions) {
            if (row.getId().equals(spoke.name())) {
                return;
            }
        }

        // apply
        if (row.isLocked() || !row.isEnabled() || !row.isVisible()) {
            row.setLocked(false);

            row.setEnabled(true);

            row.setVisible(true);
        }
    }

    /**
     * Unlock current row
     *
     * @param exceptions ignore certain spokes
     */
    public static void unlockCurrent(@NonNull Spokes... exceptions) {
        unlock(current, exceptions);
    }

    /**
     * Unlock next row
     *
     * @param exceptions ignore certain spokes
     */
    public static void unlockNext(@NonNull Spokes... exceptions) {
        unlock(next, exceptions);
    }

    /**
     * Unlock previous row
     *
     * @param exceptions ignore certain rows
     */
    public static void unlockPrevious(@NonNull Spokes... exceptions) {
        unlock(previous, exceptions);
    }

    /**
     * Show hidden row
     *
     * @param row row
     */
    public static void show(@NonNull RowModel row) {
        if (!row.isVisible()) {
            row.setVisible(true);
        }
    }

    /**
     * Show current hidden row
     */
    public static void showCurrent() {
        show(current);
    }

    /**
     * Show next hidden row
     */
    public static void showNext() {
        show(next);
    }

    /**
     * Show previous hidden row
     */
    public static void showPrevious() {
        show(previous);
    }

    /**
     * Enable row
     *
     * @param row row
     */
    public static void enable(@NonNull RowModel row) {
        if (!row.isEnabled()) {
            row.setEnabled(true);
        }
    }

    /**
     * Enable current row
     */
    public static void enableCurrent() {
        enable(current);
    }

    /**
     * Enable next row
     */
    public static void enableNext() {
        enable(next);
    }

    /**
     * Enable previous row
     */
    public static void enablePrevious() {
        enable(previous);
    }

    //-- Navigation

    /**
     * Set next row
     */
    private static void setNext() {
        int index = current.getIndex() + 1;

        next = index < data.size() ? data.get(index) : current;
    }

    /**
     * Get next row
     *
     * @return next row
     */
    public static RowModel getNext() {
        return next;
    }

    /**
     * Set previous row
     */
    private static void setPrevious() {
        int index = current.getIndex() - 1;

        previous = index > 0 ? data.get(index) : current;
    }

    /**
     * Get previous row
     *
     * @return previous row
     */
    public static RowModel getPrevious() {
        return previous;
    }

    /**
     * Set current row
     * Also sets next & previous if possible
     *
     * @param currentRow row
     */
    public static void setCurrent(@NonNull RowModel currentRow) {
        current = currentRow;

        setNext();

        setPrevious();
    }

    /**
     * Get current row
     *
     * @return current row
     */
    public static RowModel getCurrent() {
        return current;
    }
}