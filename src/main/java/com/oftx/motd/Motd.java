package com.oftx.motd;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Motd extends JavaPlugin {

    private static final long MOTD_UPDATE_INTERVAL = 500L; // 毫秒为单位，500毫秒更新一次

    @Override
    public void onEnable() {
        getLogger().info("MotdUpdater 已启用");

        // 开始定时更新 MOTD
        Bukkit.getScheduler().runTaskTimer(this, this::updateMotd, 0L, MOTD_UPDATE_INTERVAL);
    }

    @Override
    public void onDisable() {
        getLogger().info("MotdUpdater 已禁用");
    }

    private void updateMotd() {
        long time = Bukkit.getWorlds().get(0).getTime(); // 获取服务器时间（第一个世界）
        String weather = getWeatherString(Bukkit.getWorlds().get(0).hasStorm(), Bukkit.getWorlds().get(0).isThundering());

        String motd = getTimeStr(time) + " · " + weather;
        Bukkit.getServer().setMotd(motd);
    }

    private String getTimeStr(long time) {
        if (time > 0 && time <= 5500) {
            return "早上";
        } else if (time > 5500 && time <= 7500) {
            return "中午";
        } else if (time > 7500 && time <= 12000) {
            return "下午";
        } else if (time > 12000 && time <= 13000) {
            return "傍晚";
        } else if (time > 13000 && time <= 18000) {
            return "晚上";
        } else if (time > 18000 && time <= 19000) {
            return "午夜";
        } else if (time > 19000 && time <= 21500) {
            return "深夜";
        } else if (time > 21500 && time <= 23000) {
            return "凌晨";
        } else if (time > 23000 && time <= 25000) {
            return "日出";
        } else {
            return "未知";
        }
    }

    private String getWeatherString(boolean hasStorm, boolean isThundering) {
        if (isThundering) {
            return "雷";
        } else if (hasStorm) {
            return "雨";
        } else {
            return "晴";
        }
    }
}
