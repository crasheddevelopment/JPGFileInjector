/*
 * Copyright © 2019 CrashedDevelopment
 *
 * This is a non-commercial project.
 * All rights belong to their respective owners.
 * You are allowed to change the software as long as no illegal content is created and / or distributed.
 * All rights and information about the used libraries in the LICENSE_APACHE_2.0 / LIBRARIES and at the point "Libraries" and "Used Libraries" here.
 *
 * You are allowed to use following changes at this software:
 *     - Creates new features
 *     - Modify source code. (Without the identification of "// Do not remove this.")
 *     - Publish your changes. (non-commercial)
 *     - Send changes to us to improve or add new features.
 *
 * You are not allowed to do:
 *     - Publish the software for a commercial use without our permission.
 *     - Infect users with viruses or to do any other illegal content.
 *
 * For news, updates and support:
 * https://discord.gg/CBkXXKa
 *
 * Development Team:
 *
 * CrashedLife | Sören (Developed everything.):
 * Discord: CrashedLife#9417
 * Twitter: https://twitter.com/sqliCrashedLife
 * Github: https://github.com/CrashedLife
 * YouTube: https://www.youtube.com/channel/UC3alimqGC2YcgEK7jc_N4mg
 *
 * Luca (Staff):
 * Discord: Luca.#1337
 * Twitter: https://twitter.com/luca1337_
 *
 * Libraries:
 * We used libraries to complete our project and respect and thanks every single owner that created the library.
 * If some owner has problems with it please contact us.
 *
 * Used Libraries:
 *  JFoeniX © jfoenixadmin
 *  Github: https://github.com/jfoenixadmin/
 *  JFoeniX: https://github.com/jfoenixadmin/JFoenix
 *  License: Apache 2.0 (Under LICENSE_APACHE_2.0)
 */

package de.crasheddevelopment.jpgfileinjector.core.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ShowExceptionDialog
{
    // Exception dialog.
    public Alert exceptionDialog (Exception exception)
    {
       // Initialize variables.
       final Alert alert = new Alert(Alert.AlertType.ERROR);
       StringWriter stringWriter = new StringWriter();
       String text = stringWriter.toString();
       TextArea textArea = new TextArea(text);
       GridPane exceptionGridPane = new GridPane();

       alert.setTitle("Exception!");

       try (PrintWriter printWriter = new PrintWriter(stringWriter))
       {
          exception.printStackTrace(printWriter);
       }

       textArea.setEditable(false);
       textArea.setWrapText(true);

       textArea.setMaxWidth(Double.MAX_VALUE);
       textArea.setMaxHeight(Double.MAX_VALUE);
       GridPane.setVgrow(textArea, Priority.ALWAYS);
       GridPane.setHgrow(textArea, Priority.ALWAYS);

       exceptionGridPane.setMaxWidth(Double.MAX_VALUE);
       exceptionGridPane.add(textArea, 0, 1);

       alert.getDialogPane().setExpandableContent(exceptionGridPane);

       // Returns the alert.
       return alert;
    }
}
