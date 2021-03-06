package tests.sequentialAssemblerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import configuratorEngine.FullConfig;
import configuratorEngine.Storage;
import dataSource.StorageDao;
import sequentialAssembler.ComponentAssembly;
import sequentialAssembler.StorageAssembly;

class StorageAssemblyTest {

	ComponentAssembly<Storage> storageAssembly;
	StorageDao storageDao;
	FullConfig f1;

	@BeforeEach
	@AfterEach
	void init() throws JAXBException {
		storageAssembly = new StorageAssembly();
		storageDao = new StorageDao();
		storageDao.setEmptyComponents();
		f1 = new FullConfig();
	}

	@Test
	void getComponentsByIndex() throws JAXBException {
		Storage storage0 = new Storage("Prova0", 50, 3, 1, false);
		Storage storage1 = new Storage("Prova1", 50, 26, 2, false);
		Storage storage2 = new Storage("Prova2", 50, 3, 3, false);
		Storage storage3 = new Storage("Prova3", 50, 26, 4, false);

		ArrayList<Storage> storageList = new ArrayList<>();
		storageList.add(storage0);
		storageList.add(storage1);
		storageList.add(storage2);
		storageList.add(storage3);

		storageDao.addComponents(storageList);
		storageAssembly.InputBasedBehavior(f1, "0");
		assertEquals(f1.getStorage(), storage0,
				"Comparing the expected component with the one obtained through the InputBasedBehavior method");
		assertNotEquals(f1.getStorage(), storage1,
				"Comparing the NOT expected component with the one obtained through the InputBasedBehavior method");

	}

	@AfterAll
	static void clean() throws JAXBException {
		StorageDao storageDao1 = new StorageDao();
		storageDao1.setDefaultComponents();
	}

}
