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

package de.crasheddevelopment.jpgfileinjector;

import de.crasheddevelopment.jpgfileinjector.core.network.updater.Updater;
import de.crasheddevelopment.jpgfileinjector.core.ui.application.FXMenu;
import de.crasheddevelopment.jpgfileinjector.core.ui.frame.JFrameUpdate;
import de.crasheddevelopment.jpgfileinjector.utils.Constants;
import de.crasheddevelopment.jpgfileinjector.utils.StringUtils;
import javafx.application.Application;

public class JPGFileInjector
{
    // Main method.
    public static void main (String[] args)
    {
        new JPGFileInjector().initialize(args);
    }

    // Initialize.
    private void initialize (String[] args)
    {
        System.out.println("\n" +
                "      _ ____   ____ _____ _ _      ___        _           _             \n" +
                "     | |  _ \\ / ___|  ___(_) | ___|_ _|_ __  (_) ___  ___| |_ ___  _ __ \n" +
                "  _  | | |_) | |  _| |_  | | |/ _ \\| || '_ \\ | |/ _ \\/ __| __/ _ \\| '__|\n" +
                " | |_| |  __/| |_| |  _| | | |  __/| || | | || |  __/ (__| || (_) | |   \n" +
                "  \\___/|_|    \\____|_|   |_|_|\\___|___|_| |_|/ |\\___|\\___|\\__\\___/|_|   \n" +
                "                                           |__/                         \n");
        StringUtils.sendInformation("Copyright © 2019 - 2020 CrashedDevelopment");
        StringUtils.sendInformation("This is a non-commercial project.");
        StringUtils.sendInformation("All rights belong to their respective owners.");
        StringUtils.sendInformation(" ");
        StringUtils.sendInformation("Version: " + Constants.VERSION);
        StringUtils.sendInformation("Build version: " + Constants.BUILD);
        StringUtils.sendInformation(" ");

        // Search for an update
        StringUtils.sendInformation("Searching for updates...");
        searchUpdate();

        StringUtils.sendInformation(" ");

        // Starting the injector.
        StringUtils.sendInformation("Starting injector...");
        Application.launch(FXMenu.class, args);
    }

    // Searches a project update.
    private void searchUpdate ()
    {
        // Runs updater.
        new Updater().searchUpdate();
    }
}