package nl.codefox.gilmore.command;

import net.dv8tion.jda.events.message.MessageReceivedEvent;
import nl.codefox.gilmore.command.dice.Dice;

public class DiceCommand extends GilmoreCommand 
{

    public DiceCommand() 
    {
        super("Rolls dice based on input", "Usage: ![roll|dice] [expression]\n"
                + "for example: !roll 1d20 + 5 [to hit]"
                + "[comment]: this is ignored"
                + "2d20khX: keep the X highest dice"
                + "2d20klX: keep the X lowest dice"
                + "4d6r<X: reroll every die lower than X"
                + "4d6ro<X: reroll every die lower than X, but only once"
                + "1d10!: exploding die - every time you roll a crit, add an extra die", 2, null, "!roll", "!dice");
    }

    @Override
    public void run(String[] args, MessageReceivedEvent event) 
    {
        
        String expression = "";
        for (int i = 1; i < args.length; i ++) {
            expression += " " + args[i];
        }
        expression = expression.substring(1);
        
        Dice dice = new Dice(expression);
        int result = dice.roll();
                
        event.getChannel().sendMessage(String.format("[%s] `You rolled '%d' %s with the dice notation '%s'`", event.getAuthor().getAsMention(), result, dice.getBreakdown(), expression));
        
    }

}
