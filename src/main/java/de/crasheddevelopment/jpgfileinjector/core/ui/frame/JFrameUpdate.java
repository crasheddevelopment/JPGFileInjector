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

package de.crasheddevelopment.jpgfileinjector.core.ui.frame;

import de.crasheddevelopment.jpgfileinjector.core.ui.application.FXMenu;
import de.crasheddevelopment.jpgfileinjector.core.updater.JPGFileInjectorZIPDownloader;
import de.crasheddevelopment.jpgfileinjector.utils.StringUtils;
import javafx.application.Application;

import javax.swing.*;

public class JFrameUpdate extends JFrame
{
    // Public method.
    public JFrameUpdate (String[] args)
    {
        // Initialize variables.
        int inputValue = JOptionPane.showConfirmDialog(null, "The injector got an new update.\nDo you want to download it?");

        // Checks if inputValue got the button yes.
        if (inputValue == 0)
        {
            updateInjector();
        }
        else
        {
            startInjector(args);
        }
    }

    // Update the injector.
    private void updateInjector ()
    {
        StringUtils.sendInformation("Downloading application...");
        new JPGFileInjectorZIPDownloader();
    }

    // Starts the injector.
    private void startInjector (String[] args)
    {
        StringUtils.sendInformation("Running application...");
        Application.launch(FXMenu.class, args);
    }
}
