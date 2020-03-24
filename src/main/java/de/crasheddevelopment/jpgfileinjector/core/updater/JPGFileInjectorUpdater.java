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

package de.crasheddevelopment.jpgfileinjector.core.updater;

import de.crasheddevelopment.jpgfileinjector.utils.Booleans;
import de.crasheddevelopment.jpgfileinjector.utils.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class JPGFileInjectorUpdater
{
    // Public method.
    public JPGFileInjectorUpdater ()
    {
        searchUpdate();
    }

    // Search update method.
    private void searchUpdate ()
    {
        try
        {
            // Initialize variables.
            // Connect to the URL.
            final URL url = new URL("https://api.github.com/repos/CrashedDevelopment/JPGFileInjector/releases/latest");
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            final StringBuilder stringBuilder = new StringBuilder();
            String source;

            // Read website content.
            while ((source = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(source);
            }

            // Splits website content.
            final String[] source1 = stringBuilder.toString().split("\"browser_download_url\":");
            final String[] source2 = source1[1].split("}],\"tarball_url\":");
            final String source3 = source2[0].replace("\"", "");

            // Checks if the latest version not contains with the JPGFileInjector version.
            if (!source3.contains(StringUtils.VERSION))
            {
                // Sends output.
                StringUtils.sendInformation("Update found!");

                // Change the update available variable.
                Booleans.UPDATE_AVAILABLE = true;
            }

            // Closes buffer reader.
            bufferedReader.close();
        }
        catch (Exception exception)
        {
            // Prints the exception.
            StringUtils.sendInformation("Exception!");
            exception.printStackTrace();
        }
    }
}