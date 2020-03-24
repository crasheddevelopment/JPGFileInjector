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

package de.crasheddevelopment.jpgfileinjector;

import de.crasheddevelopment.jpgfileinjector.core.ui.application.FXMenu;
import de.crasheddevelopment.jpgfileinjector.core.ui.frame.JFrameUpdate;
import de.crasheddevelopment.jpgfileinjector.core.updater.JPGFileInjectorUpdater;
import de.crasheddevelopment.jpgfileinjector.utils.Booleans;
import de.crasheddevelopment.jpgfileinjector.utils.StringUtils;
import javafx.application.Application;

public class JPGFileInjector
{
    public static void main (String[] args)
    {
        System.out.println("\n" +
                "                                                                                                                                                                                                                                                                      \n" +
                "                                                                                                                                                                                                                                                                      \n" +
                "          JJJJJJJJJJJPPPPPPPPPPPPPPPPP           GGGGGGGGGGGGG     FFFFFFFFFFFFFFFFFFFFFF  iiii  lllllll                          IIIIIIIIII                 jjjj                                                  tttt                                               \n" +
                "          J:::::::::JP::::::::::::::::P       GGG::::::::::::G     F::::::::::::::::::::F i::::i l:::::l                          I::::::::I                j::::j                                              ttt:::t                                               \n" +
                "          J:::::::::JP::::::PPPPPP:::::P    GG:::::::::::::::G     F::::::::::::::::::::F  iiii  l:::::l                          I::::::::I                 jjjj                                               t:::::t                                               \n" +
                "          JJ:::::::JJPP:::::P     P:::::P  G:::::GGGGGGGG::::G     FF::::::FFFFFFFFF::::F        l:::::l                          II::::::II                                                                    t:::::t                                               \n" +
                "            J:::::J    P::::P     P:::::P G:::::G       GGGGGG       F:::::F       FFFFFFiiiiiii  l::::l     eeeeeeeeeeee           I::::Innnn  nnnnnnnn   jjjjjjj    eeeeeeeeeeee        ccccccccccccccccttttttt:::::ttttttt       ooooooooooo   rrrrr   rrrrrrrrr   \n" +
                "            J:::::J    P::::P     P:::::PG:::::G                     F:::::F             i:::::i  l::::l   ee::::::::::::ee         I::::In:::nn::::::::nn j:::::j  ee::::::::::::ee    cc:::::::::::::::ct:::::::::::::::::t     oo:::::::::::oo r::::rrr:::::::::r  \n" +
                "            J:::::J    P::::PPPPPP:::::P G:::::G                     F::::::FFFFFFFFFF    i::::i  l::::l  e::::::eeeee:::::ee       I::::In::::::::::::::nn j::::j e::::::eeeee:::::ee c:::::::::::::::::ct:::::::::::::::::t    o:::::::::::::::or:::::::::::::::::r \n" +
                "            J:::::j    P:::::::::::::PP  G:::::G    GGGGGGGGGG       F:::::::::::::::F    i::::i  l::::l e::::::e     e:::::e       I::::Inn:::::::::::::::nj::::je::::::e     e:::::ec:::::::cccccc:::::ctttttt:::::::tttttt    o:::::ooooo:::::orr::::::rrrrr::::::r\n" +
                "            J:::::J    P::::PPPPPPPPP    G:::::G    G::::::::G       F:::::::::::::::F    i::::i  l::::l e:::::::eeeee::::::e       I::::I  n:::::nnnn:::::nj::::je:::::::eeeee::::::ec::::::c     ccccccc      t:::::t          o::::o     o::::o r:::::r     r:::::r\n" +
                "JJJJJJJ     J:::::J    P::::P            G:::::G    GGGGG::::G       F::::::FFFFFFFFFF    i::::i  l::::l e:::::::::::::::::e        I::::I  n::::n    n::::nj::::je:::::::::::::::::e c:::::c                   t:::::t          o::::o     o::::o r:::::r     rrrrrrr\n" +
                "J:::::J     J:::::J    P::::P            G:::::G        G::::G       F:::::F              i::::i  l::::l e::::::eeeeeeeeeee         I::::I  n::::n    n::::nj::::je::::::eeeeeeeeeee  c:::::c                   t:::::t          o::::o     o::::o r:::::r            \n" +
                "J::::::J   J::::::J    P::::P             G:::::G       G::::G       F:::::F              i::::i  l::::l e:::::::e                  I::::I  n::::n    n::::nj::::je:::::::e           c::::::c     ccccccc      t:::::t    tttttto::::o     o::::o r:::::r            \n" +
                "J:::::::JJJ:::::::J  PP::::::PP            G:::::GGGGGGGG::::G     FF:::::::FF           i::::::il::::::le::::::::e               II::::::IIn::::n    n::::nj::::je::::::::e          c:::::::cccccc:::::c      t::::::tttt:::::to:::::ooooo:::::o r:::::r            \n" +
                " JJ:::::::::::::JJ   P::::::::P             GG:::::::::::::::G     F::::::::FF           i::::::il::::::l e::::::::eeeeeeee       I::::::::In::::n    n::::nj::::j e::::::::eeeeeeee   c:::::::::::::::::c      tt::::::::::::::to:::::::::::::::o r:::::r            \n" +
                "   JJ:::::::::JJ     P::::::::P               GGG::::::GGG:::G     F::::::::FF           i::::::il::::::l  ee:::::::::::::e       I::::::::In::::n    n::::nj::::j  ee:::::::::::::e    cc:::::::::::::::c        tt:::::::::::tt oo:::::::::::oo  r:::::r            \n" +
                "     JJJJJJJJJ       PPPPPPPPPP                  GGGGGG   GGGG     FFFFFFFFFFF           iiiiiiiillllllll    eeeeeeeeeeeeee       IIIIIIIIIInnnnnn    nnnnnnj::::j    eeeeeeeeeeeeee      cccccccccccccccc          ttttttttttt     ooooooooooo    rrrrrrr            \n" +
                "                                                                                                                                                            j::::j                                                                                                    \n" +
                "                                                                                                                                                  jjjj      j::::j                                                                                                    \n" +
                "                                                                                                                                                 j::::jj   j:::::j                                                                                                    \n" +
                "                                                                                                                                                 j::::::jjj::::::j                                                                                                    \n" +
                "                                                                                                                                                  jj::::::::::::j                                                                                                     \n" +
                "                                                                                                                                                    jjj::::::jjj                                                                                                      \n" +
                "                                                                                                                                                       jjjjjj                                                                                                         " +
                "\n");

        System.out.println("***************************************************");
        StringUtils.sendInformation("Copyright © 2019 CrashedDevelopment");
        StringUtils.sendInformation("This is an non-commercial project.");
        StringUtils.sendInformation("All rights belong to their respective owners.");
        System.out.println("***************************************************");
        StringUtils.sendInformation("Version: " + StringUtils.VERSION);
        StringUtils.sendInformation("Build version: " + StringUtils.BUILD);

        // Search for an update
        StringUtils.sendInformation("Searching for an valid update...");
        new JPGFileInjectorUpdater();

        // Checks if an update available.
        if (!Booleans.UPDATE_AVAILABLE)
        {
            // If not an update available do this.
            // Sends output.
            StringUtils.sendInformation("No available update found!");
            System.out.println(" ");

            StringUtils.sendInformation("Running application...");
            Application.launch(FXMenu.class, args);
        }
        else
        {
            // If an update is available do this.
            // Initialize variables.
            new JFrameUpdate(args);
        }
    }
}
