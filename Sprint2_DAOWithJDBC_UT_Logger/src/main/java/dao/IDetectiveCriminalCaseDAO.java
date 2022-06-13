package dao;

import dao.IDAO;
import model.CriminalCase;
import model.Detective;

import java.util.List;

public interface IDetectiveCriminalCaseDAO  {
    List<CriminalCase> findListCriminalCaseOfADetective(Detective detective);
    List<Detective> findListDetectiveInACriminalCase(CriminalCase criminalCase);

}
