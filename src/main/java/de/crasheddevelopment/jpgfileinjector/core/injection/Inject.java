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

package de.crasheddevelopment.jpgfileinjector.core.injection;

import de.crasheddevelopment.jpgfileinjector.core.converter.BytesToHEX;
import de.crasheddevelopment.jpgfileinjector.core.converter.HEXToBytes;
import de.crasheddevelopment.jpgfileinjector.core.exception.ShowExceptionDialog;
import de.crasheddevelopment.jpgfileinjector.utils.StringUtils;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Inject
{
    // Initialize variables.
    private final BytesToHEX bytesToHEX = new BytesToHEX();
    private final HEXToBytes hexToBytes = new HEXToBytes();
    private File file;

    // Public method.
    public Inject (File jpgFile, File otherFile)
    {
        // Initialize variables.
        file = jpgFile;

        try
        {
            // Try to start the injection.
            startInjection(jpgFile, otherFile);
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

    // Method to start the injection.
    private void startInjection (File jpgFile, File otherFile) throws IOException
    {
        // Initialize variables.
        final byte[] jpgFileBytes = Files.readAllBytes(jpgFile.toPath());
        final byte[] otherFileBytes = Files.readAllBytes(otherFile.toPath());
        final String otherFileBytesInHex = bytesToHEX.convertBytesToHex(otherFileBytes);
        String jpgFileBytesInHex = bytesToHEX.convertBytesToHex(jpgFileBytes);

        // Check if the string starts with FFD8 and ends with FFD9.
        if ((jpgFileBytesInHex.startsWith("FFD8")) && (jpgFileBytesInHex.endsWith("FFD9")))
        {
            // Creates the injectable string.
            jpgFileBytesInHex = jpgFileBytesInHex + "496E6A65637465642077697468204A504746696C65496E6A6563746F72" + otherFileBytesInHex;

            // Injects the string.
            inject(jpgFileBytesInHex);
        }
        else
        {
            final Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error!");
            errorAlert.setHeaderText("Something went wrong!");
            errorAlert.setContentText("Something went wrong while running the injection!");
            errorAlert.show();
        }
    }

    private void inject (String injectableHEXString) throws IOException
    {
        // Checks if the file is not null.
        if (file != null)
        {
            // Initialize variables.
            final byte[] fileBytes = hexToBytes.convertHEXToBytes(injectableHEXString);
            final Alert alert = new Alert(Alert.AlertType.INFORMATION);

            // Writes the string to the file.
            Files.write(file.toPath(), fileBytes);

            // Shows alert.
            alert.setTitle("Injected!");
            alert.setHeaderText("Successfully injected!");
            alert.setContentText("Your file successfully injected in the JPG file!\nIf you want to uninject the file click on uninject!");
            alert.show();
        }
    }
}