using System;
using System.Collections;

namespace ElderNecklace
{
    internal class Characters
    {
        internal static int level = 1;
        internal String name = "Enemy";
        internal String role;
        internal float maxhealth = 0f;
        internal float health;
        internal float attack;
        internal float defence;
        internal float critRate = 0f;
        internal float critDamage = 0f;
        internal String skill;
        internal bool isCD = true;

        internal Characters(String name, String role)
        {
            this.name = name;
            this.role = role;

            if(role == "Warrior")
            {
                this.maxhealth = 100f * Characters.level;
                this.health = 100f * Characters.level;
                this.attack = 50f * Characters.level;
                this.defence = 50f * Characters.level;
                this.critRate = 0.5f * Characters.level;
                this.critDamage = 1f * Characters.level;
                this.skill = "PowerUp";
            }
            else if (role == "Mage")
            {
                this.maxhealth = 80f * Characters.level;
                this.health = 80f * Characters.level;
                this.attack = 60f * Characters.level;
                this.defence = 40f * Characters.level;
                this.critRate = 0.6f * Characters.level;
                this.critDamage = 1f * Characters.level;
                this.skill = "Meteor";
            }
            else if (role == "Archer")
            {
                this.maxhealth = 70f * Characters.level;
                this.health = 70f * Characters.level;
                this.attack = 70f * Characters.level;
                this.defence = 30f * Characters.level;
                this.critRate = 0.7f * Characters.level;
                this.critDamage = 1.2f * Characters.level;
                this.skill = "HeadShot";
            }
            else if (role == "Tank")
            {
                this.maxhealth = 120f * Characters.level;
                this.health = 120f * Characters.level;
                this.attack = 20f * Characters.level;
                this.defence = 70f * Characters.level;
                this.critRate = 0.3f * Characters.level;
                this.critDamage = 1f * Characters.level;
                this.skill = "Heal";
            }
            else
            {
                Console.WriteLine("Invalid Class");
            }
        }

        internal Characters(String role)
        {
            this.role = role;

            if (role == "Warrior")
            {
                this.health = 50f * Characters.level;
                this.attack = 25f * Characters.level;
                this.defence = 25f * Characters.level;
            }
            else if (role == "Mage")
            {
                this.health = 40f * Characters.level;
                this.attack = 30f * Characters.level;
                this.defence = 20f * Characters.level;
            }
            else if (role == "Archer")
            {
                this.health = 35f * Characters.level;
                this.attack = 35f * Characters.level;
                this.defence = 15f * Characters.level;
            }
            else if (role == "Tank")
            {
                this.health = 60f * Characters.level;
                this.attack = 10f * Characters.level;
                this.defence = 35f * Characters.level;
            }
            else
            {
                Console.WriteLine("Invalid Class");
            }
        }

        internal bool isAlive()
        {
            if (this.health <= 0)
            {
                return false;
            }
            else
            {
                return true;
            }
        }

        internal void LevelUP()
        {
            Characters.level++;
            if (isAlive())
            {
                this.maxhealth += this.maxhealth;
            }

            this.health = this.maxhealth;
            this.attack += this.attack;
            this.attack -= (10 * Characters.level);
            this.defence += this.defence;
            this.defence -= (10 * Characters.level);
        }
    }
}
