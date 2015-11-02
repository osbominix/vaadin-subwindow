package com.subwindow.project;

import com.vaadin.ui.*;


public class FileListSubWindow extends Window{

    Callback callback;

    public FileListSubWindow(Callback callback) {


        super("Add Tree Item"); // Set window caption
        center();

        this.callback = callback;

        // Some basic content for the window
        HorizontalLayout content = new HorizontalLayout();
        content.setMargin(true);
        setContent(content);
        setClosable(true);


        // Trivial logic for closing the sub-window
        Button fileB = new Button("Add File B");
        fileB.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                updateUI("FileB");
                close(); // Close the sub-window
            }
        });

        Button fileC = new Button("Add File C");
        fileC.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                updateUI("FileC");
                close(); // Close the sub-window
            }
        });

        Button cancel = new Button("Cancel");
        cancel.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                close(); // Close the sub-window
            }
        });


        content.addComponent(fileB);
        content.addComponent(fileC);
        content.addComponent(cancel);
    }

    public void  updateUI(String fileName) {
        callback.updateTree(fileName); //Also method parameter can be used to transmit selected data
    }

    public interface Callback {
        void updateTree(String fileName);
    }
}

