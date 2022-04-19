using System;
using System.Collections;

namespace ElderNecklace
{
    class Program
    {
        static void Main(string[] args)
        {
            Characters[] yourTeam = new Characters[3];

            Console.WriteLine("Welcome to Elder Necklace!" +
                "\nIn this game players are forced to battle enemies" +
                "\nin a turn-based style until they defeat the boss," +
                "\nalso they can geto stronger along the way.");
            Console.WriteLine("\nPress enter key to proceed...");
            Console.ReadLine();
            Console.WriteLine("Choose 3 Characters to play!");
            Console.WriteLine("\nPress enter key to proceed...");
            Console.ReadLine();

            selectTeam(ref yourTeam);

            Console.WriteLine("Ready to Play?");
            Console.WriteLine("\nPress enter key to proceed...");
            Console.ReadLine();

            gameplay(ref yourTeam);

            Console.WriteLine("Thank you for playing!");
            Console.ReadKey();
        }

        private static void gameplay(ref Characters[] yourTeam)
        {
            bool isPlaying = true;
            int position = 0;
            Random rand = new Random();
            do
            {
                Console.WriteLine("Rolling Dice...");

                int temp = rand.Next(1, 7);
                position += temp;

                Console.WriteLine($"Rolled {temp} your position is {position}");

                Console.WriteLine("\nPress enter key to proceed...");
                Console.ReadLine();
                Console.Clear();

                foreach (Characters member in yourTeam)
                {
                    member.isCD = false;
                }

                String role = "";
                temp = rand.Next(1, 11);
                if (temp > 7 && position < 20)
                {
                    Console.WriteLine("Nothing happened, it's a peaceful day.");
                    Console.WriteLine("\nPress enter key to proceed...");
                    Console.ReadLine();
                    Console.Clear();
                }
                else
                {
                    Characters[] enemyTeam = new Characters[3];

                    if (position >= 20)
                    {
                        Console.WriteLine("You arrive to the BOSS!");
                        for (int i = 0; i < enemyTeam.Length; i++)
                        {
                            temp = rand.Next(1, 5);
                            switch (temp)
                            {
                                case 1:
                                    role = "Warrior";
                                    break;
                                case 2:
                                    role = "Mage";
                                    break;
                                case 3:
                                    role = "Archer";
                                    break;
                                case 4:
                                    role = "Tank";
                                    break;
                            }

                            enemyTeam[i] = new Characters($"Boss {i+1}", role);
                        }
                    }
                    else
                    {
                        Console.WriteLine("Enemies Incoming!");
                        for (int i = 0; i < enemyTeam.Length; i++)
                        {
                            temp = rand.Next(1, 5);
                            switch (temp)
                            {
                                case 1:
                                    role = "Warrior";
                                    break;
                                case 2:
                                    role = "Mage";
                                    break;
                                case 3:
                                    role = "Archer";
                                    break;
                                case 4:
                                    role = "Tank";
                                    break;
                            }

                            enemyTeam[i] = new Characters(role);
                        }
                    }
                    
                    Console.Beep();

                    bool inBattle = true;
                    Queue sequence = new Queue(5);
                    String order = "";

                    for (int i = 0; i < 5; i++)
                    {
                        createSequence(ref rand, ref sequence, ref order, ref yourTeam, ref enemyTeam);
                    }

                    for (int currentTurn = 1; inBattle; currentTurn++)
                    {
                        order = order.Substring(0, order.Length - 2);

                        Console.WriteLine($"Battle Sequence: {order}");
                        Console.WriteLine("\nYour Team: " + showTeam(ref yourTeam));
                        Console.WriteLine("Enemy Team: " + showTeam(ref enemyTeam) + "\n");

                        Sequence turn = (Sequence)sequence.Dequeue();
                        order += ", ";
                        createSequence(ref rand, ref sequence, ref order, ref yourTeam, ref enemyTeam);

                        if (turn.isEnemy && enemyTeam[turn.attacker].isAlive())
                        {
                            Console.WriteLine($"Turn {currentTurn}");
                            Console.WriteLine("Enemy is attacking!");

                            int target;

                            do
                            {
                                target = rand.Next(0, 3);
                            } while (!yourTeam[target].isAlive());

                            float damage = enemyTeam[turn.attacker].attack;
                            temp = rand.Next(0, 2);

                            if (!enemyTeam[turn.attacker].isCD && temp <= 0)
                            {
                                if (enemyTeam[turn.attacker].skill == "PowerUp")
                                {
                                    enemyTeam[turn.attacker].attack *= 1.5f;
                                    damage *= 2f;
                                    Console.WriteLine($"{enemyTeam[turn.attacker].name} got stronger!");
                                }
                                else if (enemyTeam[turn.attacker].skill == "Meteor")
                                {
                                    foreach (Characters enemy in yourTeam)
                                    {
                                        enemy.health -= enemyTeam[turn.attacker].attack / 3;
                                        if (enemy.health > enemy.maxhealth)
                                        {
                                            enemy.health = enemy.maxhealth;
                                        }
                                    }
                                    Console.WriteLine($"{enemyTeam[turn.attacker].name} delivered AOE damage!");
                                }
                                else if (enemyTeam[turn.attacker].skill == "HeadShot")
                                {
                                    damage *= 1.5f;
                                    Console.WriteLine($"{enemyTeam[turn.attacker].name} is focused!");
                                }
                                else if (yourTeam[turn.attacker].skill == "Heal")
                                {
                                    foreach (Characters member in enemyTeam)
                                    {
                                        if (member.isAlive())
                                        {
                                            member.health += enemyTeam[turn.attacker].maxhealth / 2;
                                        }
                                    }
                                    Console.WriteLine($"{yourTeam[turn.attacker].name} Healed their remaining allies!");
                                }
                            }

                            temp = rand.Next(0, 2);

                            if (temp <= 0)
                            {
                                damage -= yourTeam[target].defence;
                            }

                            if(damage > 0)
                            {
                                Console.WriteLine($"Enemy {enemyTeam[turn.attacker].role} attacks {yourTeam[target].name}");
                                yourTeam[target].health -= damage;
                            }
                            else
                            {
                                Console.WriteLine($"{yourTeam[target].name} blocks the Enemy {enemyTeam[turn.attacker].role}'s attack ");
                            }

                            if (!yourTeam[target].isAlive())
                            {
                                Console.WriteLine($"{yourTeam[target].name} died!");
                            }

                            int dead = 0;
                            foreach (Characters member in yourTeam)
                            {
                                if (!member.isAlive())
                                {
                                    dead++;
                                }
                            }

                            if(dead >= 3)
                            {
                                Console.WriteLine("\n\tEveryone died");
                                inBattle = isPlaying = false;

                            }
                        }
                        else if(!turn.isEnemy && yourTeam[turn.attacker].isAlive())
                        {
                            Console.WriteLine($"Turn {currentTurn}");

                            bool notCorrect;
                            do
                            {
                                notCorrect = false;
                                Console.WriteLine("Defeat the enemy!");
                                Console.WriteLine($"Who will {yourTeam[turn.attacker].name} attack?");

                                for (int i = 0; i < enemyTeam.Length; i++)
                                {
                                    if (enemyTeam[i].isAlive())
                                    {
                                        Console.WriteLine($"\t[{i}] {enemyTeam[i].role} HP: {enemyTeam[i].health}");
                                    }
                                }

                                float damage = yourTeam[turn.attacker].attack;

                                try
                                {
                                    int target = Convert.ToInt32(Console.ReadLine());
                                    if (target < 0 | target > 2)
                                    {
                                        notCorrect = true;
                                        Console.WriteLine("Invalid Input");
                                        Console.WriteLine("\nPress enter key to proceed...");
                                        Console.ReadLine();
                                        Console.Clear();
                                    }
                                    else if (!enemyTeam[target].isAlive())
                                    {
                                        notCorrect = true;
                                        Console.WriteLine("That enemy is already fainted!");
                                        Console.WriteLine("\nPress enter key to proceed...");
                                        Console.ReadLine();
                                        Console.Clear();
                                    }
                                    else
                                    {
                                        if (!yourTeam[turn.attacker].isCD)
                                        {
                                            bool isCorrect;
                                            do
                                            {
                                                isCorrect = true;

                                                Console.WriteLine($"Use {yourTeam[turn.attacker].role}'s skill, {yourTeam[turn.attacker].skill} ( y | n )?");
                                                String skill = Console.ReadLine();

                                                if (skill.ToUpper() == "Y")
                                                {
                                                    if (yourTeam[turn.attacker].skill == "PowerUp")
                                                    {
                                                        yourTeam[turn.attacker].attack *= 1.5f;
                                                        damage *= 2f;
                                                        Console.WriteLine($"{yourTeam[turn.attacker].name} got stronger!");
                                                    }
                                                    else if (yourTeam[turn.attacker].skill == "Meteor")
                                                    {
                                                        foreach (Characters enemy in enemyTeam)
                                                        {
                                                            enemy.health -= yourTeam[turn.attacker].attack / 3;
                                                        }
                                                        Console.WriteLine($"{yourTeam[turn.attacker].name} delivered AOE damage!");
                                                    }
                                                    else if (yourTeam[turn.attacker].skill == "HeadShot")
                                                    {
                                                        damage *= 1.5f;
                                                        Console.WriteLine($"{yourTeam[turn.attacker].name} is focused!");
                                                    }
                                                    else if (yourTeam[turn.attacker].skill == "Heal")
                                                    {
                                                        foreach (Characters member in yourTeam)
                                                        {
                                                            if (member.isAlive())
                                                            {
                                                                member.health += yourTeam[turn.attacker].maxhealth / 2;
                                                                if(member.health > member.maxhealth)
                                                                {
                                                                    member.health = member.maxhealth;
                                                                }
                                                            }
                                                        }
                                                        Console.WriteLine($"{yourTeam[turn.attacker].name} Healed the remaining allies!");
                                                    }
                                                    yourTeam[turn.attacker].isCD = true;
                                                }
                                                else if (skill.ToUpper() != "N")
                                                {
                                                    isCorrect = false;
                                                    Console.WriteLine("y or n only");
                                                    Console.WriteLine("\nPress enter key to proceed...");
                                                    Console.ReadLine();
                                                    Console.Clear();
                                                }
                                            } while (!isCorrect);
                                        }

                                        temp = rand.Next(0, 5);
                                        if(temp <= 0)
                                        {
                                            damage -= enemyTeam[target].defence;
                                            Console.WriteLine($"Enemy {enemyTeam[target].role} saw your attack coming!");
                                        }

                                        if (damage > 0)
                                        {
                                            Console.WriteLine($"{yourTeam[turn.attacker].name} attacks Enemy {enemyTeam[target].role}!");
                                            enemyTeam[target].health -= damage;
                                        }
                                        else
                                        {
                                            Console.WriteLine($"Enemy {enemyTeam[target].role} blocks {yourTeam[turn.attacker].name}'s attack.");
                                        }

                                        if (!enemyTeam[target].isAlive())
                                        {
                                            Console.WriteLine($"Enemy {enemyTeam[target].role} fainted!");
                                        }
                                    }
                                }
                                catch (Exception e)
                                {
                                    notCorrect = true;
                                    Console.WriteLine(e.Message);
                                    Console.WriteLine("\nPress enter key to proceed...");
                                    Console.ReadLine();
                                    Console.Clear();
                                }
                            } while (notCorrect);

                            int dead = 0;
                            foreach (Characters member in enemyTeam)
                            {
                                if (!member.isAlive())
                                {
                                    dead++;
                                }
                            }

                            if (dead >= 3)
                            {
                                Console.WriteLine("Every enemy fainted. You Win");
                                inBattle = false;

                                if(position >= 20)
                                {
                                    isPlaying = false;
                                    Console.WriteLine("\n\t ## You beat the Game! You did great :) ##");
                                }
                                else
                                {
                                    foreach (Characters member in yourTeam)
                                    {
                                        member.LevelUP();
                                    }
                                }
                            }
                        }
                        else
                        {
                            Console.WriteLine("Silence... The attacker already fainted");
                        }

                        order = order.Substring(order.IndexOf(',') + 1);

                        Console.WriteLine("\nPress enter key to proceed...");
                        Console.ReadLine();
                        Console.Clear();
                    }
                } 
            } while (isPlaying);
        }

        private static String showTeam(ref Characters[] team)
        {
            String temp = "";
            foreach (Characters adventurer in team)
            {
                if (adventurer.isAlive())
                {
                    temp += $"{adventurer.name} ({adventurer.role}) HP: {adventurer.health}, ";
                }
                else
                {
                    temp += $"{adventurer.name} (DEAD), ";
                }
            }
            temp = temp.Substring(0, temp.Length - 2);
            return temp;
        }

        private static void createSequence(ref Random rand, ref Queue sequence, ref String order, ref Characters[] yourTeam, ref Characters[] enemyTeam)
        {
            int temp = rand.Next(1, 4);
            if (temp > 1)
            {
                // Player turn
                bool repeat;
                do
                {
                    repeat = false;
                    temp = rand.Next(0, 3);

                    if(!yourTeam[temp].isAlive())
                    {
                        repeat = true;
                    }
                    else
                    {
                        sequence.Enqueue(new Sequence(false, temp));
                        order += $"{yourTeam[temp].name}, ";
                    }
                } while (repeat);
            }
            else
            {
                // Enemy Turn
                bool repeat;
                do
                {
                    repeat = false;
                    temp = rand.Next(0, 3);

                    if (!enemyTeam[temp].isAlive())
                    {
                        repeat = true;
                    }
                    else
                    {
                        temp = rand.Next(0, 3);
                        sequence.Enqueue(new Sequence(true, temp));
                        order += $"{enemyTeam[temp].name}, ";
                    }
                } while (repeat);
            }
        }

        private static void selectTeam(ref Characters[] yourTeam)
        {
            for (int i = 0; i < yourTeam.Length; i++)
            {
                String role;
                bool isAccepted;
                do
                {
                    isAccepted = true;

                    Console.WriteLine($"{i + 1} of 3 Character:");
                    Console.WriteLine("\t[1] Warrior");
                    Console.WriteLine("\t[2] Archer");
                    Console.WriteLine("\t[3] Mage");
                    Console.WriteLine("\t[4] Tank");

                    role = Console.ReadLine();

                    Console.Clear();

                    if (role == "1")
                    {
                        role = "Warrior";
                    }
                    else if (role == "2")
                    {
                        role = "Archer";
                    }
                    else if (role == "3")
                    {
                        role = "Mage";
                    }
                    else if (role == "4")
                    {
                        role = "Tank";
                    }
                    else
                    {
                        Console.WriteLine("Invalid input!");
                        isAccepted = false;
                    }
                } while (!isAccepted);

                String name;
                do
                {
                    Console.WriteLine($"Name your {role}");
                    name = Console.ReadLine();
                    Console.Clear();
                } while (name == "");

                yourTeam[i] = new Characters(name, role);

                Console.Clear();
            }
        }
    }
}
