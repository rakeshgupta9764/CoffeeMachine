/** Author : Rakesh Gupta
 *  Date : May 01, 2020
 */

package machine;

import java.util.Scanner;

enum State {
    // represents state of the coffee machine
    CHOOSE_ACTION,
    CHOOSE_VARIANT_TO_BUY,
    CHOOSE_WHAT_TO_FILL;
}

public class CoffeeMachine {

    // default requirements for the coffee machine
    private static final int ESPRESSO_WATER = 250;
    private static final int ESPRESSO_BEANS = 16;
    private static final int ESPRESSO_COST = 4;
    private static final int LATTE_WATER = 350;
    private static final int LATTE_MILK = 75;
    private static final int LATTE_BEANS = 20;
    private static final int LATTE_COST = 7;
    private static final int CAPPUCCINO_WATER = 200;
    private static final int CAPPUCCINO_MILK = 100;
    private static final int CAPPUCCINO_BEANS = 12;
    private static final int CAPPUCCINO_COST = 6;
    
    // starting conditions
    static int water = 400;
    static int milk = 540;
    static int coffeeBeans = 120;
    static int cups = 9;
    static int money = 550;
    private static State state = State.CHOOSE_ACTION;   // default state of the system
    
    // scanner object
    static Scanner sc = new Scanner(System.in);
    
    // single method for all the actions
    private static void doAction(String input) {
        boolean actionComplete = false;
        while (!actionComplete) {
            switch(state) {
                case CHOOSE_ACTION:
                
                    // if the action can be done without taking more inputs, write it here
                    // otherwise change the machine's state
                    if (input.equals("take")) {
                        System.out.println("I gave you $" + money);
                        money = 0;
                        actionComplete = true;
                    } else if (input.equals("fill")) {
                        state = State.CHOOSE_WHAT_TO_FILL;
                    } else if (input.equals("buy")) {
                        state = State.CHOOSE_VARIANT_TO_BUY;
                    } else if (input.equals("remaining")) {
                        System.out.println("The coffee machine has:");
                        System.out.println(water + " of water");
                        System.out.println(milk + " of milk");
                        System.out.println(coffeeBeans + " of coffee beans");
                        System.out.println(cups + " of cups");
                        System.out.println("$" + money + " of money");
                        actionComplete = true;
                    }
                    break;

                case CHOOSE_WHAT_TO_FILL:
                
                    // change the machine state to default after this process
                    System.out.println("Write how many ml of water do you want to add:");
                    water += sc.nextInt();
                    System.out.println("Write how many ml of milk do you want to add:");
                    milk += sc.nextInt();
                    System.out.println("Write how many grams of coffee beans do you want to add:");
                    coffeeBeans += sc.nextInt();
                    System.out.println("Write how many disposable cups of coffee do you want to add:");
                    cups += sc.nextInt();
                    state = State.CHOOSE_ACTION;
                    actionComplete = true;
                    break;

                case CHOOSE_VARIANT_TO_BUY:
                
                    // change the machine's state to default after this process
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to CoffeeMachine menu:");
                    switch(sc.next()) {
                        case "1":
                            if (water >= ESPRESSO_WATER && coffeeBeans >= ESPRESSO_BEANS) {
                                System.out.println("I have enough resources, making you a coffee!");
                                
                                // changes in the system
                                water -= ESPRESSO_WATER;
                                coffeeBeans -= ESPRESSO_BEANS;
                                money += ESPRESSO_COST;
                                cups--;
                            } else {
                                System.out.print("Not enough resources. Amount of resources required for espresso: ");
                                
                                // the requirements
                                if (water < ESPRESSO_WATER) {
                                    System.out.print((ESPRESSO_WATER - water) + " of water, ");
                                }
                                if (coffeeBeans < ESPRESSO_BEANS) {
                                    System.out.print((ESPRESSO_BEANS - coffeeBeans) + " of coffee beans");
                                }
                                System.out.println();
                            }
                            break;
                        case "2":
                            if (water >= LATTE_WATER && milk >= LATTE_MILK && coffeeBeans >= LATTE_BEANS) {
                                System.out.println("I have enough resources, making you a coffee!");
                                
                                // changes in the system
                                water -= LATTE_WATER;
                                milk -= LATTE_MILK;
                                coffeeBeans -= LATTE_BEANS;
                                money += LATTE_COST;
                                cups--;
                            } else {
                                System.out.print("Not enough resources. Amount of resources required for latte: ");
                                
                                // the requirements
                                if (water < LATTE_WATER) {
                                    System.out.print((LATTE_WATER - water) + " of water, ");
                                }
                                if (milk < LATTE_MILK) {
                                    System.out.print((LATTE_MILK - milk) + " of milk, ");
                                }
                                if (coffeeBeans < LATTE_BEANS) {
                                    System.out.print((LATTE_BEANS - coffeeBeans) + " of coffee beans");
                                }
                                System.out.println();
                            }
                            break;

                        case "3":
                            if (water >= CAPPUCCINO_WATER && milk >= CAPPUCCINO_MILK && coffeeBeans >= CAPPUCCINO_BEANS) {
                                
                                // changes in the system
                                System.out.println("I have enough resources, making you a coffee!");
                                water -= CAPPUCCINO_WATER;
                                milk -= CAPPUCCINO_MILK;
                                coffeeBeans -= CAPPUCCINO_BEANS;
                                money += CAPPUCCINO_COST;
                                cups--;
                            } else {
                                System.out.print("Not enough resources. Amount of resources required for latte: ");
                                
                                // the requirements
                                if (water < CAPPUCCINO_WATER) {
                                    System.out.print((CAPPUCCINO_WATER - water) + " of water, ");
                                }
                                if (milk < CAPPUCCINO_MILK) {
                                    System.out.print((CAPPUCCINO_MILK - milk) + " of milk, ");
                                }
                                if (coffeeBeans < CAPPUCCINO_BEANS) {
                                    System.out.print((CAPPUCCINO_BEANS - coffeeBeans) + " of coffee beans");
                                }
                                System.out.println();
                            }
                            break;

                        case "back":
                            // doesn't do anything except completing this process, marked by changing the state
                            // to default
                            break;

                        default:
                            // for syntax clarity

                    }
                    
                    // process done, change the state to default
                    state = State.CHOOSE_ACTION;
                    actionComplete = true;
                    break;
                    
                default:
                    // for syntax clarity

            }
        }
    }

    public static void main(String[] args) {
        String input;
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        input = sc.next();
        while (!input.equals("exit")) {
            doAction(input);
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            input = sc.next();
        }
    }
}
