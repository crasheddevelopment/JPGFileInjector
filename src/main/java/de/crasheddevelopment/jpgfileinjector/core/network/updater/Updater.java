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

package de.crasheddevelopment.jpgfileinjector.core.network.updater;

import com.google.common.io.Resources;
import de.crasheddevelopment.jpgfileinjector.core.ui.frame.JFrameUpdate;
import de.crasheddevelopment.jpgfileinjector.utils.Constants;
import de.crasheddevelopment.jpgfileinjector.utils.StringUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Updater
{
    // Search update method.
    public void searchUpdate ()
    {
        try
        {
            // Initialize variables.
            URL url = new URL("https://api.github.com/repos/CrashedDevelopment/JPGFileInjector/releases/latest");
            List<String> websiteContent = Resources.readLines(url, StandardCharsets.UTF_8);

            boolean state = false;

            // Reads every line.
            for (String line : websiteContent)
            {
                // Initialize variables.
                String[] source1 = line.split("\"browser_download_url\":");
                String[] source2 = source1[1].split("}],\"tarball_url\":");
                String source3 = source2[0].replace("\"", "");

                // Checks if the version that found is not equals to this application version.
                if (!source3.contains(Constants.VERSION))
                {
                    // If an update is available it should open a JFrame with the options to download it.
                    StringUtils.sendInformation("Update is available!");
                    state = true;
                    new JFrameUpdate().initJFrame();
                    return;
                }
            }

            if (!state)
            {
                // If no updates are available.
                StringUtils.sendInformation("No updates currently available!");
            }
        }
        catch (IOException ioException)
        {
            StringUtils.sendInformation("IOException while resolving the url!");
            ioException.printStackTrace();
        }
    }

    public void downloadUpdate ()
    {
        try
        {
            // Initialize variables.
            URL url = new URL("https://api.github.com/repos/CrashedDevelopment/JPGFileInjector/releases/latest");
            List<String> websiteContent = Resources.readLines(url, StandardCharsets.UTF_8);

            // Reads every line.
            for (String line : websiteContent)
            {

                // Initialize variables.
                String[] source1 = line.split("\"browser_download_url\":");
                String[] source2 = source1[1].split("}],\"tarball_url\":");
                String source3 = source2[0].replace("\"", "");

                // Checks if the version that found is not equals to this application version.
                if (!source3.contains(Constants.VERSION))
                {
                    // Initialize variables.
                    // Connecting to url that contains the download and downloads the file.
                    URL downloadURL = new URL(source3);
                    ReadableByteChannel readableByteChannel = Channels.newChannel(downloadURL.openStream());
                    FileOutputStream fileOutputStream = new FileOutputStream("JPGFileInjector.zip");
                    fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);

                    StringUtils.sendInformation("Update downloaded successfully!");
                    fileOutputStream.close();
                    System.exit(0);
                    return;
                }
            }

            // If something went wrong it shows here.
            StringUtils.sendInformation("Something went wrong!");
        }
        catch (IOException ioException)
        {
            StringUtils.sendInformation("IOException while resolving the url!");
            ioException.printStackTrace();
        }
    }
}