package net.voiasis.commands.slash.tools;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.voiasis.auto.Grammar;
import net.voiasis.commands.slash.handler.CommandBuilder;
import net.voiasis.tools.math.Algebra;
import net.voiasis.tools.math.Rational;

public class slashLinear extends CommandBuilder {
    public slashLinear() {
        setCommandData(Commands.slash("linear", "Solves linear equations.")
        .addOption(OptionType.STRING, "equation", "Enter the equation to solve. Surround negative numbers with (). Separate equations with commas.", true)
        .addOption(OptionType.STRING, "amount", "Set the amount of unknown characters. Default is 1.", false)
        .addOption(OptionType.STRING, "list", "List of unknown characters separated with commas. Default is x.", false));
    }
    @Override
    public void executeCommand(@NotNull SlashCommandInteractionEvent event) {
        OptionMapping equationOpt = event.getOption("equation");
        String equation = equationOpt == null ? null : equationOpt.getAsString();

        OptionMapping amountOpt = event.getOption("amount");
        String amountString = amountOpt == null ? null : amountOpt.getAsString();
        if (amountString == null) {
            int unknowns = 1;
            list(event, equation, unknowns);
        } else {
            int unknowns = Integer.parseInt(amountString);
            list(event, equation, unknowns);
        }
    }
    private static void list(SlashCommandInteractionEvent event, String equation, int unknowns) {
        OptionMapping listOpt = event.getOption("list");
        String list = listOpt == null ? null : listOpt.getAsString();
        if (list == null) {
            String[] ss = "x".split(",");
            reply(event, equation, unknowns, ss);
        } else {
            String[] ss = list.split(",");
            reply(event, equation, unknowns, ss);
        }
    }
    private static void reply(SlashCommandInteractionEvent event, String equation, int unknowns, String[] ss) {
        char[] uks = new char[ss.length];
        for (int i = 0; i < ss.length; i++) {
            uks[i] = ss[i].trim().charAt(0);
        }
        Rational[] r = Algebra.solve(unknowns, uks, equation.split(","));
        String s = "";
        for (int i = 0; i < r.length; i++) {
            s += uks[i] + " = " + r[i] + (i != r.length - 1 ? "\n" : "");
        }
        int charCount = ss.length;
        String characters = "";
        while (charCount > 0) {
            characters = ss[charCount - 1] + ", " + characters;
            charCount--;
        }
        event.reply(Grammar.grammar("Equation", equation.split(",").length) + " entered:\n" + equation.replaceAll(",", "\n") + "\n\nAmount of unknown characters:\n" + unknowns + "\n\nList of unknown characters:\n" + characters.substring(0, characters.length() - 2) + "\n\nAnswers:\n" + s).mentionRepliedUser(false).queue();
    }
}
