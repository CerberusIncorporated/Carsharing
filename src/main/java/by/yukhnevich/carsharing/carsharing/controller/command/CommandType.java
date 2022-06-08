package by.yukhnevich.carsharing.carsharing.controller.command;

import by.yukhnevich.carsharing.carsharing.controller.command.impl.*;
import by.yukhnevich.carsharing.carsharing.controller.command.impl.car.AddCarCommand;
import by.yukhnevich.carsharing.carsharing.controller.command.impl.car.DeleteCarCommand;
import by.yukhnevich.carsharing.carsharing.controller.command.impl.car.EditCarCommand;
import by.yukhnevich.carsharing.carsharing.controller.command.impl.car.GoToCarPage;
import by.yukhnevich.carsharing.carsharing.controller.command.impl.comment.DeleteComment;
import by.yukhnevich.carsharing.carsharing.controller.command.impl.comment.GoToCarComment;
import by.yukhnevich.carsharing.carsharing.controller.command.impl.comment.LeaveComment;
import by.yukhnevich.carsharing.carsharing.controller.command.impl.news.AddNewsCommand;
import by.yukhnevich.carsharing.carsharing.controller.command.impl.news.DeleteNewsCommand;
import by.yukhnevich.carsharing.carsharing.controller.command.impl.news.EditNewsCommand;
import by.yukhnevich.carsharing.carsharing.controller.command.impl.news.GoToNewsPage;
import by.yukhnevich.carsharing.carsharing.controller.command.impl.order.ChangeOrderStatusCommand;
import by.yukhnevich.carsharing.carsharing.controller.command.impl.order.GoToOrderPage;
import by.yukhnevich.carsharing.carsharing.controller.command.impl.order.GoToUserOrderPage;
import by.yukhnevich.carsharing.carsharing.controller.command.impl.order.MakeOrderCommand;
import by.yukhnevich.carsharing.carsharing.controller.command.impl.payment.GoToPaymentPage;
import by.yukhnevich.carsharing.carsharing.controller.command.impl.payment.MakePayment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * The enum Command type.
 */
public enum CommandType {
    LOGIN(new LoginCommand()),
    GOTOLOGINPAGE(new GoToLoginPage()),
    GOTONEWSPAGE(new GoToNewsPage()),
    GOTOREGISTERPAGE(new GoToRegisterPage()),
    REGISTER(new RegisterCommand()),
    SIGNOUT(new SignOutCommand()),
    GOTOCARSPAGE(new GoToCarPage()),
    GOTOCARCOMMENTSPAGE(new GoToCarComment()),
    CHANGELANG(new ChangeLanguageCommand()),
    GOTONEWSEDITPAGE(new GoToEditPage()),
    GOTOCAREDITPAGE(new GoToEditPage()),
    ADDCAR(new AddCarCommand()),
    EDITCAR(new EditCarCommand()),
    DELETECAR(new DeleteCarCommand()),
    ADDNEWS(new AddNewsCommand()),
    DELETENEWS(new DeleteNewsCommand()),
    EDITNEWS(new EditNewsCommand()),
    GOTOORDERPAGE(new GoToOrderPage()),
    GOTOORDERSPAGE(new GoToUserOrderPage()),
    MAKEORDER(new MakeOrderCommand()),
    CHANGEORDERSTATUS(new ChangeOrderStatusCommand()),
    GOTOPAYMENTPAGE(new GoToPaymentPage()),
    MAKEPAYMENT(new MakePayment()),
    LEAVECOMMENT(new LeaveComment()),
    DELETECOMMENT9(new DeleteComment()),
    DEFAULT(new Default());

    private final Command command;
    private static final Logger LOGGER = LogManager.getLogger();

    CommandType(Command command) {
        this.command = command;
    }

    /**
     * Gets command.
     *
     * @return the command
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Define command command.
     *
     * @param commandType the command type
     * @return the command
     */
    public static Command defineCommand(String commandType) {
        if (commandType == null || commandType.isEmpty()) {
            return DEFAULT.getCommand();
        }
        try {
            return CommandType.valueOf(commandType.toUpperCase()).getCommand();
        } catch (IllegalArgumentException exception) {
            LOGGER.error("Error has occurred while defining command: " + exception);
            return DEFAULT.getCommand();
        }
    }
}
