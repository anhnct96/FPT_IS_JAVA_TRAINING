package dao;

import model.CriminalCase;

public interface ICriminalCaseDAO extends IDAO<CriminalCase> {
    public void delete (long id);
}
