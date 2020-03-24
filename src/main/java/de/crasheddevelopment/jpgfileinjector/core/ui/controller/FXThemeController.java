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
 */

package de.crasheddevelopment.jpgfileinjector.core.ui.controller;

import com.jfoenix.controls.JFXTextField;
import de.crasheddevelopment.jpgfileinjector.core.files.FileManager;
import de.crasheddevelopment.jpgfileinjector.core.ui.application.FXMenu;
import de.crasheddevelopment.jpgfileinjector.utils.StringUtils;
import de.crasheddevelopment.jpgfileinjector.utils.ThemeUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FXThemeController
{
    // Initialize variables.
    @FXML private final JFXTextField customThemePathTextField = new JFXTextField();
    private final FileManager fileManager = new FileManager();
    private final Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
    private final Alert errorAlert = new Alert(Alert.AlertType.ERROR);

    // Change the theme to old.
    @FXML private void changeThemeToOld ()
    {
        // Initialize variables.
        final ArrayList<String> tempThemeConfig = new ArrayList<>();

        // Writes stuff into the array list.
        tempThemeConfig.add("// If you want to change something here in the config do it before the ;\n");
        tempThemeConfig.add("theme:OLD;\n");
        tempThemeConfig.add("custom-theme-path:;");

        try
        {
            // Save the theme.
            fileManager.save(fileManager.themeSettingPath, tempThemeConfig);
            ThemeUtils.USED_THEMES = "/fxml/themes/fx_menu_old_theme.fxml";
            StringUtils.sendInformation("Old theme saved and loaded!");
            informationAlert.setTitle("Success!");
            informationAlert.setContentText("Theme saved and loaded!");
            informationAlert.show();
        }
        catch (Exception exception)
        {
            StringUtils.sendInformation("Exception!");
            exception.printStackTrace();
        }
    }

    @FXML private void changeThemeToNew ()
    {
        // Initialize variables.
        final ArrayList<String> tempThemeConfig = new ArrayList<>();

        // Writes stuff into the array list.
        tempThemeConfig.add("// If you want to change something here in the config do it before the ;\n");
        tempThemeConfig.add("theme:NEW;\n");
        tempThemeConfig.add("custom-theme-path:;");

        try
        {
            // Save the theme.
            fileManager.save(fileManager.themeSettingPath, tempThemeConfig);
            ThemeUtils.USED_THEMES = "/fxml/themes/fx_menu_new_theme.fxml";
            StringUtils.sendInformation("New theme saved and loaded!");
            informationAlert.setTitle("Success!");
            informationAlert.setContentText("Theme saved and loaded!");
            informationAlert.show();
        }
        catch (Exception exception)
        {
            StringUtils.sendInformation("Exception!");
            exception.printStackTrace();
        }
    }

    @FXML private void changeThemeToCustom ()
    {
        if (!customThemePathTextField.getText().isEmpty())
        {
            // Initialize variables.
            final ArrayList<String> tempThemeConfig = new ArrayList<>();

            // Writes stuff into the array list.
            tempThemeConfig.add("// If you want to change something here in the config do it before the ;\n");
            tempThemeConfig.add("theme:CUSTOM;\n");
            tempThemeConfig.add("custom-theme-path:" + customThemePathTextField.getText().trim() + ";");

            try
            {
                // Save the theme.
                fileManager.save(fileManager.themeSettingPath, tempThemeConfig);
                ThemeUtils.USED_THEMES = customThemePathTextField.getText().trim();
                StringUtils.sendInformation("Custom theme saved and loaded!");
                informationAlert.setTitle("Success!");
                informationAlert.setContentText("Theme saved and loaded!");
                informationAlert.show();
            }
            catch (Exception exception)
            {
                StringUtils.sendInformation("Exception!");
                exception.printStackTrace();
            }
        }
        else
        {
            errorAlert.setTitle("Error!");
            errorAlert.setContentText("You need to select an file!");
            errorAlert.show();
        }
    }

    @FXML private void browseCustomTheme (ActionEvent actionEvent)
    {
        // Initialize the variables.
        final Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Initialize file chooser.
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Browse custom theme file");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("FXML file (*.fxml)", "*.FXML");
        fileChooser.getExtensionFilters().add(extensionFilter);

        // Shows file chooser.
        final File file = fileChooser.showOpenDialog(stage);

        // Checks that the file is not null.
        if (file != null)
        {
            // Sets the file path to the customThemePathTextField.
            customThemePathTextField.setText(file.getPath().trim());
        }
    }

    @FXML private void goBack (ActionEvent actionEvent)
    {
        // Initialize the variables.
        final Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        try
        {
            // Opens menu window.
            new FXMenu().initGUI(stage);
        }
        catch (IOException ioException)
        {
            StringUtils.sendInformation("IOException!");
            ioException.printStackTrace();
        }
    }
}