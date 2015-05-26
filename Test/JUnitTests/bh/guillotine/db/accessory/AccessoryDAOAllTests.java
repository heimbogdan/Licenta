package bh.guillotine.db.accessory;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AccesoryDAOInsertTest.class, AccessoryDAOUpdateTest.class,
		AccessoryDAODeleteTest.class, AccessoryDAOGetAllTest.class })
public class AccessoryDAOAllTests {

}
