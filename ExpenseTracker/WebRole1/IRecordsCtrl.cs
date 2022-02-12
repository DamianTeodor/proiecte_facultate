using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ExpenseTrackerCore.Interfaces
{
    public interface IRecordsCtrl
    {
        bool Insert(Models.Record record);
        bool Edit(Models.Record record);
        bool Delete(int id);
        IEnumerable<Models.Record> GetRecords(string username);
    }
}
