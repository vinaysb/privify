package se.joscarsson.privify.models;

import java.util.List;


public interface OnChangeListener {
    void onSelectionChanged(List<PrivifyFile> selectedFiles);
}
