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

package de.crasheddevelopment.jpgfileinjector.core.themes;

import de.crasheddevelopment.jpgfileinjector.core.files.FileManager;
import de.crasheddevelopment.jpgfileinjector.utils.StringUtils;
import de.crasheddevelopment.jpgfileinjector.utils.ThemeUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ThemeSettings
{
    // Initialize variables.
    private final FileManager fileManager = new FileManager();
    private String usedTheme;
    private String customThemePath;

    // Check and loads the settings.
    public void checkSettings ()
    {
        try
        {
            // Load config.
            loadConfig();

            if (usedTheme != null)
            {
                switch (usedTheme)
                {
                    // Old theme.
                    case "OLD":
                        ThemeUtils.USED_THEMES = "/fxml/themes/fx_menu_old_theme.fxml";
                        StringUtils.sendInformation("Old theme loaded!");
                        break;

                    // New theme.
                    case "NEW":
                        ThemeUtils.USED_THEMES = "/fxml/themes/fx_menu_new_theme.fxml";
                        StringUtils.sendInformation("New theme loaded!");
                        break;

                    // Custom theme.
                    case "CUSTOM":
                        if (customThemePath != null)
                        {
                            if (Files.exists(Paths.get(customThemePath)))
                            {
                                ThemeUtils.USED_THEMES = customThemePath;
                                StringUtils.sendInformation("Custom theme loaded!");
                            }
                            else
                            {
                                StringUtils.sendInformation("Custom theme not found! Use new theme!");
                            }
                        }
                        else
                        {
                            StringUtils.sendInformation("Custom theme not configured! Use new theme!");
                        }
                        break;
                }
            }
            else
            {
                StringUtils.sendInformation("No theme configured! Use new theme!");
            }
        }
        catch (Exception exception)
        {
            StringUtils.sendInformation("Exception!");
            exception.printStackTrace();
        }
    }

    // Create the theme file.
    public void createThemeFile ()
    {
        // Initialize variables.
        final ArrayList<String> tempThemeConfig = new ArrayList<>();

        // Writes stuff into the array list.
        tempThemeConfig.add("// If you want to change something here in the config do it before the ;\n");
        tempThemeConfig.add("theme:NEW;\n");
        tempThemeConfig.add("custom-theme-path:;");

        try
        {
            // Saves the file.
            fileManager.save(fileManager.themeSettingPath, tempThemeConfig);
        }
        catch (Exception exception)
        {
            StringUtils.sendInformation("Exception!");
            exception.printStackTrace();
        }
    }

    // Load the config.
    private void loadConfig () throws IOException
    {
        // Initialize variables.
        final ArrayList<String> temp = fileManager.read(fileManager.themeSettingPath);
        String content = null;

        for (String line : temp)
        {
            if (line != null)
            {
                if (content != null)
                {
                    content = content + line + "\n";
                }
                else
                {
                    content = line + "\n";
                }
            }
        }

        if (content != null)
        {
            // Initialize variables.
            String[] s1 = content.split(";");
            String usedThemeSetting = s1[1].replace("theme:", "").trim();
            String customThemePath = s1[2].replace("custom-theme-path:", "").trim();

            if ((usedThemeSetting != null || !usedThemeSetting.isEmpty() || !usedThemeSetting.equalsIgnoreCase("")) && ((customThemePath != null || !customThemePath.isEmpty() || !customThemePath.equalsIgnoreCase(""))))
            {
                // Initialize variables.
                this.usedTheme = usedThemeSetting;
                this.customThemePath = customThemePath;
                StringUtils.sendInformation("Theme settings loaded!");
            }
            else
            {
                StringUtils.sendInformation("Please configure the Theme settings in " + fileManager.themeSettingPath.toString() + " to use other themes!");
            }
        }
        else
        {
            StringUtils.sendInformation("Please configure the Theme settings in " + fileManager.themeSettingPath.toString() + " to use other themes!");
        }
    }
}