package net.wyvest.mod;

import club.sk1er.modcore.ModCoreInstaller;
import club.sk1er.mods.core.gui.notification.Notifications;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.wyvest.mod.utils.VersionChecker;

@Mod(name = Constants.NAME, version = Constants.VER, modid = Constants.ID)
public class ExampleMod {

    @Mod.Instance(Constants.ID)
    private static ExampleMod INSTANCE;
    public VersionChecker VERSION_CHECKER = new VersionChecker();
    private boolean latestVersion;

    public static ExampleMod getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ExampleMod();
        return INSTANCE;
    }

    @Mod.EventHandler
    protected void onPreInit(FMLPreInitializationEvent event) {
        if (this.VERSION_CHECKER.getEmergencyStatus())
            throw new RuntimeException("PLEASE UPDATE TO THE NEW VERSION OF " + Constants.NAME + "\nTHIS IS AN EMERGENCY!");
        this.latestVersion = this.VERSION_CHECKER.getVersion().equals(Constants.VER);
    }

    @Mod.EventHandler
    protected void onInit(FMLInitializationEvent event) {
        ModCoreInstaller.initializeModCore(Minecraft.getMinecraft().mcDataDir);
    }


    @Mod.EventHandler
    protected void onPostInit(FMLPostInitializationEvent event) {
        if (!isLatestVersion()) {
            Notifications.INSTANCE.pushNotification(Constants.NAME, Constants.NAME + " is out of date. Please update to the latest version.");
        }
    }
    public boolean isNull(Object obj) {
        return obj == null;
    }
    public boolean isLatestVersion() {
        return latestVersion;
    }
}