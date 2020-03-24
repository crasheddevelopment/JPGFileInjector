/*
 * Copyright © 2019 - 2020 CrashedDevelopment
 *
 * This is a non-commercial project.
 * All rights belong to their respective owners.
 * You are allowed to change the software as long as no illegal content is created and / or distributed.
 * All rights and information about the used libraries in the LICENSE_APACHE_2.0 / LIBRARIES and at the point "Libraries" and "Used Libraries" here.
 * The license from the project contains in the file LICENSE_PROJECT!
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
 *
 *  Guava © Google
 *  Repository: https://mvnrepository.com/artifact/com.google.guava/guava/28.2-jre/
 *  License: Apache 2.0 (Under LICENSE_APACHE_2.0.txt)
 */

package de.crasheddevelopment.jpgfileinjector.core.ui.controller;

import com.jfoenix.controls.JFXTextField;
import de.crasheddevelopment.jpgfileinjector.core.injection.Injection;
import de.crasheddevelopment.jpgfileinjector.core.injection.Uninjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Paths;

public class FXMenuController
{
    // Initialize variables.
    @FXML private JFXTextField jpgPathTextField;
    @FXML private JFXTextField otherPathTextField;
    private FileChooser fileChooser;
    private Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    private Stage stage;
    private File file;
    private String outputPath;

    // Browse the jpg file.
    @FXML
    private void browseJPGFile (ActionEvent actionEvent)
    {
        // Initialize the variables.
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Initialize file chooser.
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg, *.jpeg)", "*.JPG", ".JPEG");
        fileChooser = new FileChooser();
        fileChooser.setTitle("Browse JPG file");
        fileChooser.getExtensionFilters().add(extensionFilter);

        // Shows file chooser.
        file = fileChooser.showOpenDialog(stage);

        // Checks that the file is not null.
        if (file != null)
        {
            // Sets the file path to the jpgPathTextField.
            this.jpgPathTextField.setText(file.getPath().trim());
        }
    }

    // Browse the other file to inject.
    @FXML
    private void browseOtherFile (ActionEvent actionEvent)
    {
        // Initialize the variables.
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Initialize file chooser.
        fileChooser = new FileChooser();
        fileChooser.setTitle("Browse file");

        // Shows file chooser.
        file = fileChooser.showOpenDialog(stage);

        // Checks that the file is not null.
        if (file != null)
        {
            // Sets the file path to the otherPathTextField.
            this.otherPathTextField.setText(file.getPath().trim());
        }
    }

    // Injects the other file to the jpg file.
    @FXML
    private void inject ()
    {
        // Check the requirements.
        if (!this.jpgPathTextField.getText().isEmpty())
        {
            if ((this.jpgPathTextField.getText().endsWith(".jpg")) || (this.jpgPathTextField.getText().endsWith(".jpeg")))
            {
                if (!this.otherPathTextField.getText().isEmpty())
                {
                    if ((!this.otherPathTextField.getText().endsWith(".jpg")) && (!this.otherPathTextField.getText().endsWith(".jpeg")))
                    {
                        // Prepares the injector.
                        new Injection().prepareInjection(Paths.get(this.jpgPathTextField.getText().trim()), Paths.get(this.otherPathTextField.getText().trim()));
                    }
                    else
                    {
                        // Initialize alert.
                        errorAlert.setTitle("Error!");
                        errorAlert.setHeaderText("Other file path is contains not a valid file extension!");
                        errorAlert.setContentText("");
                    }
                }
                else
                {
                    // Initialize alert.
                    errorAlert.setTitle("Error!");
                    errorAlert.setHeaderText("Other file path is empty!");
                    errorAlert.setContentText("The other file path cannot be empty!");
                }
            }
            else
            {
                // Initialize alert.
                errorAlert.setTitle("Error!");
                errorAlert.setHeaderText("The JPG file path is contains not a valid file extension!");
                errorAlert.setContentText("");
            }
        }
        else
        {
            // Initialize alert.
            errorAlert.setTitle("Error!");
            errorAlert.setHeaderText("JPG path is empty!");
            errorAlert.setContentText("The JPG path cannot be empty!");
        }

        // Shows alert.
        errorAlert.show();
    }

    @FXML
    private void uninject (ActionEvent actionEvent)
    {
        // Check the requirements.
        if (!this.jpgPathTextField.getText().isEmpty())
        {
            if (this.jpgPathTextField.getText().endsWith(".jpg") || this.jpgPathTextField.getText().endsWith(".jpeg"))
            {
                // Opens browse output method.
                browseOutput(actionEvent);

                if (!outputPath.isEmpty())
                {
                    // Prepares the uninjector.
                    new Uninjection().prepareUninjection(Paths.get(this.jpgPathTextField.getText().trim()), Paths.get(outputPath));
                }
                else
                {
                    // Initialize alert.
                    errorAlert.setTitle("Error!");
                    errorAlert.setHeaderText("Please select an output file!");
                    errorAlert.setContentText("");
                }
            }
            else
            {
                // Initialize alert.
                errorAlert.setTitle("Error!");
                errorAlert.setHeaderText("The JPG file path is contains not a valid file extension!");
                errorAlert.setContentText("");
            }
        }
        else
        {
            // Initialize alert.
            errorAlert.setTitle("Error!");
            errorAlert.setHeaderText("JPG path is empty!");
            errorAlert.setContentText("The JPG path cannot be empty!");
        }

        // Shows alert.
        errorAlert.show();
    }

    // Browse the select output method.
    @FXML
    private void browseOutput (ActionEvent actionEvent)
    {
        // Initialize the variables.
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Initialize file chooser.
        fileChooser = new FileChooser();
        fileChooser.setTitle("Browse output");

        // Shows file chooser.
        file = fileChooser.showSaveDialog(stage);

        // Checks that the file is not null.
        if (file != null)
        {
            // Sets the file path to the outputPath.
            outputPath = file.getPath().trim();
        }
    }
}