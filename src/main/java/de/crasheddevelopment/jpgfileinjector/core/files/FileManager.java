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

package de.crasheddevelopment.jpgfileinjector.core.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileManager
{
    // Initialize variables.
    private Path filePath;
    private final Path settingsDirPath = Paths.get(System.getProperty("user.home") + "\\CrashedDevelopment\\JPGFileInjector\\settings\\");
    public final Path themeSettingPath = Paths.get(settingsDirPath + "\\theme.config");

    // Save the file.
    public void save (Path filePath, ArrayList<String> arrayList) throws IOException
    {
        // Initialize variables.
        this.filePath = filePath;
        final BufferedWriter bufferedWriter = Files.newBufferedWriter(this.filePath);

        // Check if settings dir exists.
        if (!Files.exists(settingsDirPath))
        {
            // Create stuff.
            Files.createDirectories(settingsDirPath);
            Files.createFile(themeSettingPath);
        }

        for (String line : arrayList)
        {
            if (line != null)
            {
                // Writes the string into the file.
                bufferedWriter.write(line);
                bufferedWriter.flush();
            }
        }

        // Closes buffered writer.
        bufferedWriter.close();
    }

    // Reads the file and write it into the arraylist.
    public ArrayList<String> read (Path filePath) throws IOException
    {
        // Initialize variables.
        this.filePath = filePath;
        final ArrayList<String> temp = new ArrayList<>();
        final BufferedReader bufferedReader = Files.newBufferedReader(this.filePath);
        String line;

        // Check if settings dir exists.
        if (!Files.exists(settingsDirPath))
        {
            // Create stuff.
            Files.createDirectories(settingsDirPath);
            Files.createFile(themeSettingPath);
        }

        while ((line = bufferedReader.readLine()) != null)
        {
            // Adds the line into the array list.
            temp.add(line);
        }

        // Return the array list.
        return temp;
    }

    // Get the file.
    public File getFile (String filePath)
    {
        // Initialize variables.
        Path path = Paths.get(filePath);

        // Check if the file exists.
        if (Files.exists(path))
        {
            // Return the file.
            return new File(filePath);
        }
        else
        {
            // Return null.
            return null;
        }
    }
}
