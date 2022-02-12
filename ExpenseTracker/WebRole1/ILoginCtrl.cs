using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ExpenseTrackerCore.Interfaces
{
    public interface ILoginCtrl
    {
        bool Insert(Models.User user);
        bool Validate(Models.User user);
    }
}
