package net.voiasis.commands.prefix.tools;

import java.awt.Color;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.voiasis.tools.BotLog;

public class prefixUptime {
    public static void uptime(Message message) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        embed.addField("Vez Bot's Uptime", "<a:typing:995510544813543545> " + message(), false);
        message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue(m -> {
            int amount = 10;
            while (amount > 0) {
                try {
                    Thread.sleep(5000);
                    embed.clear();
                    embed.setColor(Color.RED);
                    embed.addField("Vez Bot's Uptime", "<a:typing:995510544813543545> " + message(), false);
                    m.editMessageEmbeds(embed.build()).mentionRepliedUser(false).queue();
                    amount--;
                } catch (InterruptedException e) {
                    BotLog.log(BotLog.getStackTraceString(e, message.getJDA()), "Uptime", 4);
                    break;
                }  
            }
            embed.clear();
            embed.setColor(Color.RED);
            embed.addField("Vez Bot's Uptime", "<:time:994820769282531450> " + message(), false);
            m.editMessageEmbeds(embed.build()).mentionRepliedUser(false).queue();
        });
    }
    private static String message() {
        long second = second();
        long minute = minute();
        long hour = hour();
        long day = day();
        if (minute == 0) { //only seconds
            if (second >= 2) { //with s
                String message = second + " Seconds. ";
                return message;
            } else { //without s
                String message = second + " Second. ";
                return message;
            }
        } else if (hour == 0) { //minutes and seconds
            if (minute >= 2) { //with s
                if (second >= 2) { //with s
                    String message = minute + " Minutes and " + second + " Seconds. ";
                    return message;
                } else { //without s
                    String message = minute + " Minutes and " + second + " Second. ";
                    return message;
                }
            } else { //without s
                if (second >= 2) { //with s
                    String message = minute + " Minute and " + second + " Seconds. ";
                    return message;
                } else { //without s
                    String message = minute + " Minute and " + second + " Second. ";
                    return message;
                }
            }
        } else if (day == 0) { //hours, minutes, and seconds
            if (hour >= 2) { //with s
                if (minute >= 2) { //with s
                    if (second >= 2) { //with s
                        String message = hour + " Hours, " + minute + " Minutes, and " + second + " Seconds. ";
                        return message;
                    } else { //without s
                        String message = hour + " Hours, " + minute + " Minutes, and " + second + " Second. ";
                        return message;
                    }
                } else { //without s
                    if (second >= 2) { //with s
                        String message = hour + " Hours, " + minute + " Minute, and " + second + " Seconds. ";
                        return message;
                    } else { //without s
                        String message = hour + " Hours, " + minute + " Minute, and " + second + " Second. ";
                        return message;
                    }
                }
            } else {
                if (minute >= 2) { //with s
                    if (second >= 2) { //with s
                        String message = hour + " Hour, " + minute + " Minutes, and " + second + " Seconds. ";
                        return message;
                    } else { //without s
                        String message = hour + " Hour, " + minute + " Minutes, and " + second + " Second. ";
                        return message;
                    }
                } else { //without s
                    if (second >= 2) { //with s
                        String message = hour + " Hour, " + minute + " Minute, and " + second + " Seconds. ";
                        return message;
                    } else { //without s
                        String message = hour + " Hour, " + minute + " Minute, and " + second + " Second. ";
                        return message;
                    }
                }
            }
        } else { //days, hours, minutes, and seconds
            if (day >= 2) { //with s
                if (hour >= 2) { //with s
                    if (minute >= 2) { //with s
                        if (second >= 2) { //with s
                            String message = day + " Days, " + hour + " Hours, " + minute + " Minutes, and " + second + " Seconds. ";
                            return message;
                        } else { //without s
                            String message = day + " Days, " + hour + " Hours, " + minute + " Minutes, and " + second + " Second. ";
                            return message;
                        }
                    } else { //without s
                        if (second >= 2) { //with s
                            String message = day + " Days, " + hour + " Hours, " + minute + " Minute, and " + second + " Seconds. ";
                            return message;
                        } else { //without s
                            String message = day + " Days, " + hour + " Hours, " + minute + " Minute, and " + second + " Second. ";
                            return message;
                        }
                    }
                } else { //without s
                    if (minute >= 2) { //with s
                        if (second >= 2) { //with s
                            String message = day + " Days, " + hour + " Hour, " + minute + " Minutes, and " + second + " Seconds. ";
                            return message;
                        } else { //without s
                            String message = day + " Days, " + hour + " Hour, " + minute + " Minutes, and " + second + " Second. ";
                            return message;
                        }
                    } else { //without s
                        if (second >= 2) { //with s
                            String message = day + " Days, " + hour + " Hour, " + minute + " Minute, and " + second + " Seconds. ";
                            return message;
                        } else { //without s
                            String message = day + " Days, " + hour + " Hour, " + minute + " Minute, and " + second + " Second. ";
                            return message;
                        }
                    }
                }
            } else {
                if (hour >= 2) { //with s
                    if (minute >= 2) { //with s
                        if (second >= 2) { //with s
                            String message = day + " Day, " + hour + " Hours, " + minute + " Minutes, and " + second + " Seconds. ";
                            return message;
                        } else { //without s
                            String message = day + " Day, " + hour + " Hours, " + minute + " Minutes, and " + second + " Second. ";
                            return message;
                        }
                    } else {
                        if (second >= 2) { //with s
                            String message = day + " Day, " + hour + " Hours, " + minute + " Minute, and " + second + " Seconds. ";
                            return message;
                        } else { //without s
                            String message = day + " Day, " + hour + " Hours, " + minute + " Minute, and " + second + " Second. ";
                            return message;
                        }
                    }
                } else {
                    if (minute >= 2) { //with s
                        if (second >= 2) { //with s
                            String message = day + " Day, " + hour + " Hour, " + minute + " Minutes, and " + second + " Seconds. ";
                            return message;
                        } else { //without s
                            String message = day + " Day, " + hour + " Hour, " + minute + " Minutes, and " + second + " Second. ";
                            return message;
                        }
                    } else {
                        if (second >= 2) { //with s
                            String message = day + " Day, " + hour + " Hour, " + minute + " Minute, and " + second + " Seconds. ";
                            return message;
                        } else { //without s
                            String message = day + " Day, " + hour + " Hour, " + minute + " Minute, and " + second + " Second. ";
                            return message;
                        }
                    }
                }
            }
        }
    }
    private static long second() {
        RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
        long ut = rb.getUptime();
        long second = (ut / 1000) % 60;
        return second;
    }
    private static long minute() {
        RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
        long ut = rb.getUptime();
        long minute = (ut / (1000 * 60)) % 60;
        return minute;
    }
    private static long hour() {
        RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
        long ut = rb.getUptime();
        long hour = (ut / (1000 * 60 * 60)) % 24;
        return hour;
    }
    private static long day() {
        RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
        long ut = rb.getUptime();
        long day = (ut / (1000 * 60 * 60 * 60)) % 24;
        return day;
    }
}
