using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ElderNecklace
{
    class Sequence
    {
        internal bool isEnemy;
        internal int attacker;

        internal Sequence(bool isEnemy, int attacker)
        {
            this.isEnemy = isEnemy;
            this.attacker = attacker;
        }
    }
}
