package com.subwindow.project;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.subwindow.project.MyAppWidgetset")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        final Tree filesTree = new Tree();

        Button button = new Button("Open Sub Window");
        final FileListSubWindow fileListWindow = new FileListSubWindow(new FileListSubWindow.Callback(){
                                                    public void updateTree(String fileName) {
                                                          switch (fileName) {
                                                              case "FileB":
                                                                  filesTree.addItem("File B");
                                                                  filesTree.setParent("File B", "Files");
                                                                  filesTree.expandItem("File B");
                                                                  break;
                                                              case "FileC":
                                                                  filesTree.addItem("File C");
                                                                  filesTree.setParent("File C", "Files");
                                                                  filesTree.expandItem("File C");
                                                                  break;
                                                              default:
                                                                  break;

                                                          }

                                                    }
                                                });

        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                if (fileListWindow.getParent() == null) {
                    fileListWindow.setHeight("100px");
                    fileListWindow.setWidth("350px");
                    fileListWindow.setPositionX(350);
                    fileListWindow.setPositionY(150);
                    UI.getCurrent().addWindow(fileListWindow);
                }
            }
        });


        filesTree.addItem("Files");
        filesTree.expandItem("Files");

        filesTree.addItem("File A");
        filesTree.setParent("File A", "Files");
        filesTree.expandItem("File A");

        layout.addComponent(button);
        layout.addComponent(filesTree);

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
