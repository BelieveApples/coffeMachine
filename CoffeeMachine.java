package machine;

import javafx.css.Match;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CoffeeMachine {
    static int water = 400;
    static int milk = 540;
    static int beans = 120;
    static int cups = 9;
    static int money = 550;
    static boolean work = true;
    static Action action = Action.ACTION;
    static int count = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (work) {
            switch (action) {
                case ACTION:
                    System.out.println("Write action (buy, fill, take, remaining, exit) :");
                    break;
                case BAY:
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                    break;
                case FILL:
                    if (count == 0) {
                        System.out.println("Write how many ml of water do you want to add:");
                    } else if (count == 1) {
                        System.out.println("Write how many ml of milk do you want to add:");
                    } else if (count == 2) {
                        System.out.println("Write how many grams of coffee beans do you want to add:");
                    } else if (count == 3) {
                        System.out.println("Write how many disposable cups of coffee do you want to add:");
                    }
                    break;
                default:
                    System.out.println("Unknown command");
                    break;
            }
            String s = scanner.nextLine();
            makeCoffee(s, action);
        }
    }

    private enum Action {
        ACTION,
        BAY,
        FILL
    }


    public static void makeCoffee(String selection, Action action) {
        switch (action) {
            case ACTION:
                //STRING
                change(selection, action);
                break;
            case BAY:
                //STRING
                bay(selection, action);
                break;
            case FILL:
                //INTEGER
                fill(selection);
                break;
            default:
                System.out.println("Unknown command");
                break;
        }
    }

    private static void change(String selection, Action action) {
        switch (selection) {
            case "buy":
                CoffeeMachine.action = Action.BAY;
                break;
            case "fill":
                CoffeeMachine.action = Action.FILL;
                break;
            case "take":
                System.out.println("I gave you " + money);
                money = 0;
                break;
            case "remaining":
                System.out.println(water + " of water");
                System.out.println(milk + " of milk");
                System.out.println(beans + " of coffee beans");
                System.out.println(cups + " of disposable cups");
                System.out.println(money + " of money");
                break;
            case "exit":
                work = false;
                break;
            default:
                System.out.println("Unknown command");
                break;
        }
    }

    private static Coffee bay(String coffee, Action action) {
        Coffee coffee1 = null;
        switch (coffee) {
            case "1":
                if (water < Coffee.ESPRESSO.water) {
                    System.out.println("Sorry, not enough water!");
                } else if (beans < Coffee.ESPRESSO.beans) {
                    System.out.println("Sorry, not enough coffee beans!");
                } else if (cups < 1) {
                    System.out.println("Sorry, not enough disposable cups!");
                } else {
                    System.out.println("I have enough resources, making you a coffee!");
                    water -= Coffee.ESPRESSO.water;
                    beans -= Coffee.ESPRESSO.beans;
                    cups -= 1;
                    money += Coffee.ESPRESSO.money;
                    coffee1 = Coffee.ESPRESSO;
                }
                CoffeeMachine.action = Action.ACTION;
                break;
            case "2":
                if (water < Coffee.LATTE.water) {
                    System.out.println("Sorry, not enough water!");
                } else if (milk < Coffee.LATTE.milk) {
                    System.out.println("Sorry, not enough milk!");
                } else if (beans < Coffee.LATTE.beans) {
                    System.out.println("Sorry, not enough coffee beans!");
                } else if (cups < 1) {
                    System.out.println("Sorry, not enough disposable cups!");
                } else {
                    System.out.println("I have enough resources, making you a coffee!");
                    water -= Coffee.LATTE.water;
                    milk -= Coffee.LATTE.milk;
                    beans -= Coffee.LATTE.beans;
                    cups -= 1;
                    money += Coffee.LATTE.money;
                    coffee1 = Coffee.LATTE;
                }
                CoffeeMachine.action = Action.ACTION;
                break;
            case "3":
                if (water < Coffee.CAPPUCCINO.water) {
                    System.out.println("Sorry, not enough water!");
                } else if (milk < Coffee.CAPPUCCINO.milk) {
                    System.out.println("Sorry, not enough milk!");
                } else if (beans < Coffee.CAPPUCCINO.beans) {
                    System.out.println("Sorry, not enough coffee beans!");
                } else if (cups < 1) {
                    System.out.println("Sorry, not enough disposable cups!");
                } else {
                    System.out.println("I have enough resources, making you a coffee!");
                    water -= Coffee.CAPPUCCINO.water;
                    milk -= Coffee.CAPPUCCINO.milk;
                    beans -= Coffee.CAPPUCCINO.beans;
                    cups -= 1;
                    money += Coffee.CAPPUCCINO.money;
                    coffee1 = Coffee.CAPPUCCINO;
                }
                CoffeeMachine.action = Action.ACTION;
                break;
            case "back":
                CoffeeMachine.action = Action.ACTION;
                break;
            default:
                System.out.println("Unknown command");
                break;
        }
        return coffee1;
    }
    private static void fill(String selection) {
        if (count == 0) {
            water += Integer.parseInt(selection);
            count = 1;
        } else if (count == 1) {
            milk += Integer.parseInt(selection);
            count = 2;
        } else if (count == 2) {
            beans += Integer.parseInt(selection);
            count = 3;
        } else if (count == 3) {
            cups += Integer.parseInt(selection);
            count = 0;
            CoffeeMachine.action = Action.ACTION;
        }
    }
}
