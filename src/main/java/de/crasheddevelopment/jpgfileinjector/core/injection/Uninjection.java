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

package de.crasheddevelopment.jpgfileinjector.core.injection;

import de.crasheddevelopment.jpgfileinjector.utils.HEXConverter;
import de.crasheddevelopment.jpgfileinjector.utils.StringUtils;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class Uninjection 
{
    // Prepares the uninjection.
    public void prepareUninjection (Path jpgFilePath, Path outputFilePath)
    {
        // Initialize variables.
        Alert alert = new Alert(Alert.AlertType.ERROR);

        // Checks if the jpg file exists.
        if (Files.exists(jpgFilePath))
        {
            // Starts uninjection.
            uninject(jpgFilePath, outputFilePath);
        }
        else
        {
            // Initialize alert and show it then.
            alert.setTitle("Error!");
            alert.setHeaderText("Something went wrong!");
            alert.setContentText("One of the files is not exist!");
            alert.show();
        }
    }

    // Uninject method.
    private void uninject (Path jpgFilePath, Path outputFilePath)
    {
        try
        {
            // Initialize variables.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            String jpgFileInHEX = HEXConverter.TO_HEX(Files.readAllBytes(jpgFilePath));

            // Check if the String contains 496E6A65637465642077697468204A504746696C65496E6A6563746F72.
            if (jpgFileInHEX.contains("496E6A65637465642077697468204A504746696C65496E6A6563746F72"))
            {
                // Initialize variables.
                String[] contentFromHEX = jpgFileInHEX.split("496E6A65637465642077697468204A504746696C65496E6A6563746F72");
                String jpgFileContentInHEX = contentFromHEX[0].trim();
                String otherFileContentInHEX = contentFromHEX[1].trim();

                // Writes the old bytes in the jpg file.
                Files.write(jpgFilePath, Objects.requireNonNull(HEXConverter.FROM_HEX(jpgFileContentInHEX)));

                // If the other file is not exists, its create automatically one.
                if (!Files.exists(outputFilePath))
                    Files.createFile(outputFilePath);

                // Writes the old bytes into the other file.
                Files.write(outputFilePath, Objects.requireNonNull(HEXConverter.FROM_HEX(otherFileContentInHEX)));

                // Initialize alert.
                alert.setTitle("Uninjected!");
                alert.setHeaderText("Successfully uninjected!");
                alert.setContentText("Your file successfully uninjected from the JPG file!");
            }
            else
            {
                // Initialize alert.
                alert.setTitle("Error!");
                alert.setHeaderText("Something went wrong!");
                alert.setContentText("In this JPG nothing injected from this injector!");
            }

            // Shows alert.
            alert.show();
        }
        catch (IOException ioException)
        {
            StringUtils.sendInformation("IOException while reading bytes from the files!");
            ioException.printStackTrace();
        }
    }
}