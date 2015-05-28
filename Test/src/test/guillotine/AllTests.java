package test.guillotine;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.guillotine.db.SQLiteConnectionTest;
import test.guillotine.db.accessory.AccesoryDAOInsertTest;
import test.guillotine.db.accessory.AccessoryDAODeleteTest;
import test.guillotine.db.accessory.AccessoryDAOGetAllTest;
import test.guillotine.db.accessory.AccessoryDAOUpdateTest;
import test.guillotine.entity.element.TestElement;
import test.guillotine.entity.element.TestElementList;
import test.guillotine.entity.element.TestFinalElement;
import test.guillotine.entity.element.TestGeneralElement;

/*

	ok 1p of
	ok 1p design ( Singleton SQLiteConnection , Facade : FrontInterfaceGUI , Decorator : Component, 
					Composite : Element , Data Access Object : AccessoryDAO)
	ok 2p 50 teste (51)
 	ok 2p suita ( 3 : aici , AccessoryDAOAllTests si EntityElementAllTests )
 	ok 1p before si after ( b & a -> AccessoryDAOUpdateTest / bc & ac -> TestFinalElement )
 	ok 1p unit test 
 	ok 2p limite : TestElement / TestElementList / Error : SQLiteConnectionTest / TestGeneralElement

 */



@RunWith(Suite.class)
@SuiteClasses({ TestCut.class, AccesoryDAOInsertTest.class,
		AccessoryDAOUpdateTest.class, AccessoryDAODeleteTest.class,
		AccessoryDAOGetAllTest.class, TestElement.class, TestElementList.class,
		TestFinalElement.class, TestGeneralElement.class, SQLiteConnectionTest.class })
public class AllTests {

}
