/*
 * Copyright © 2019 CrashedDevelopment
 *
 * This is a non-commercial project.
 * All rights belong to their respective owners.
 * You are allowed to change the software as long as no illegal content is created and / or distributed.
 * All rights and information about the used libraries in the LICENSE_APACHE_2.0.txt / LIBRARIES.txt and at the point "Libraries" and "Used Libraries" here.
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

import de.crasheddevelopment.jpgfileinjector.core.files.FileManager;
import de.crasheddevelopment.jpgfileinjector.core.injection.Inject;
import javafx.scene.control.Alert;

import java.io.File;

public class Uninjector
{
    private final Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    private final FileManager fileManager = new FileManager();

    public Uninjector (String jpgFile, String outputFile)
    {
        uninjecting(jpgFile, outputFile);
    }

    private void uninjecting (String jpgFilePath, String outputFilePath)
    {
        File jpgFile = fileManager.getFile(jpgFilePath);

        if ((jpgFile != null) && (outputFilePath != null))
        {
            new Uninject(jpgFile, outputFilePath);
        }
        else
        {
            errorAlert.setTitle("Error!");
            errorAlert.setHeaderText("One of these files are not exists!");
            errorAlert.show();
        }
    }
}
