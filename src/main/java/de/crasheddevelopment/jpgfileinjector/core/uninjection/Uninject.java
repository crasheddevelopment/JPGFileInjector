/*
 * Copyright © 2019 CrashedDevelopment
 *
 * This is a non-commercial project.
 * All rights belong to their respective owners.
 * You are allowed to change the software as long as no illegal content is created and / or distributed.
 * All rights and information about the used libraries in the LICENSE_APACHE_2.0.data / LIBRARIES and at the point "Libraries" and "Used Libraries" here.
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
 *  License: Apache 2.0 (Under LICENSE_APACHE_2.0.txt)
 */

package de.crasheddevelopment.jpgfileinjector.core.uninjection;

import de.crasheddevelopment.jpgfileinjector.core.converter.BytesToHEX;
import de.crasheddevelopment.jpgfileinjector.core.converter.HEXToBytes;
import de.crasheddevelopment.jpgfileinjector.core.exception.ShowExceptionDialog;
import de.crasheddevelopment.jpgfileinjector.utils.StringUtils;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Uninject
{
    // Initialize variables.
    private final BytesToHEX bytesToHEX = new BytesToHEX();
    private final HEXToBytes hexToBytes = new HEXToBytes();
    private File jpgFile;
    private String outputFilePath;

    // Public method.
    public Uninject (File jpgFile, String outputFilePath)
    {
        // Initialize variables.
        this.jpgFile = jpgFile;
        this.outputFilePath = outputFilePath;

        try
        {
            // Starts the uninjection.
            startUninjection(jpgFile);
        }
        catch (IOException ioException)
        {
            StringUtils.sendInformation("IOException!");
            ioException.printStackTrace();

            // Initialize variables.
            final ShowExceptionDialog showExceptionDialog = new ShowExceptionDialog();
            final Alert exceptionAlert = showExceptionDialog.exceptionDialog(ioException);

            // Shows the exception.
            exceptionAlert.show();
        }
    }

    // Method to starts the uninjection.
    private void startUninjection (File jpgFile) throws IOException
    {
        // Initialize variables.
        final byte[] jpgFileBytes = Files.readAllBytes(jpgFile.toPath());
        final String jpgFileBytesInHex = bytesToHEX.convertBytesToHex(jpgFileBytes);
        String jpgHEX;
        String outputHEX;

        // Check the string if the content contains in it.
        if (jpgFileBytesInHex.contains("496E6A65637465642077697468204A504746696C65496E6A6563746F72"))
        {
            // Initialize variables.
            String[] hex = jpgFileBytesInHex.split("496E6A65637465642077697468204A504746696C65496E6A6563746F72");
            jpgHEX = hex[0];
            outputHEX = hex[1];

            // Uninject
            uninject(jpgHEX, outputHEX);
        }
        else
        {
            // Initialize variables.
            final Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error!");
            errorAlert.setHeaderText("Something went wrong!");
            errorAlert.setContentText("Something went wrong while running the injection!");
            errorAlert.show();
        }
    }

    // Uninject.
    private void uninject (String injectableJPGHEXString, String injectableOutputHEXString) throws IOException
    {
        // Checks if the file is not null.
        if ((jpgFile != null) && (!outputFilePath.isEmpty()))
        {
            // Initialize variables.
            final byte[] jpgFileBytes = hexToBytes.convertHEXToBytes(injectableJPGHEXString);
            final byte[] outputFileBytes = hexToBytes.convertHEXToBytes(injectableOutputHEXString);
            final Alert alert = new Alert(Alert.AlertType.INFORMATION);

            // Writes the string to the file.
            Files.write(jpgFile.toPath(), jpgFileBytes);

            // Checks if the file exists.
            if (!Files.exists(Paths.get(outputFilePath)))
            {
                // Creates the file and write it.
                Files.createFile(Paths.get(outputFilePath));
                Files.write(Paths.get(outputFilePath), outputFileBytes);
            }
            else
            {
                // Writes the string to the file.
                Files.write(Paths.get(outputFilePath), outputFileBytes);
            }


            // Shows alert.
            alert.setTitle("Uninjected!");
            alert.setHeaderText("Successfully Uninjected!");
            alert.setContentText("Your file successfully uninjected from the JPG file!");
            alert.show();
        }
    }
}