package tests.SequentialAssembler;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ConfiguratorEngine.Case;
import ConfiguratorEngine.FullConfigBuilder;
import ConfiguratorEngine.Motherboard;
import dataSource.CaseDao;
import sequentialAssembler.CaseAssembly;
import sequentialAssembler.ComponentAssembly;

class CaseAssemblyTest {
	
	ComponentAssembly caseAssembly;
	CaseDao caseDao;
	FullConfigBuilder f1 = FullConfigBuilder.getIstance();

	
	@BeforeEach
	@AfterEach
	void init() throws JAXBException {
		caseAssembly = new CaseAssembly();
		caseDao = new CaseDao();
		caseDao.setEmptyComponents();
		f1.setCpu(null);
		f1.setGpu(null);
		f1.setMotherboard(null);
		f1.setCase(null);
		f1.setRam(null);
		f1.setStorage(null);
	}
	
	
	@Test
	void getCompatibleComponentsTest() throws JAXBException {
		Case case0 = new Case("Prova0", 2, 3, 1);
		Case case1 = new Case("Prova1", 15, 26, 2);
		Case case2 = new Case("Prova2", 2, 3, 3);
		Case case3 = new Case("Prova3", 15, 26, 4);
		
		ArrayList<Case> caseList = new ArrayList<>();
		caseList.add(case0);
		caseList.add(case1);
		caseList.add(case2);
		caseList.add(case3);
		
		caseDao.addComponents(caseList);
		Motherboard motherboard = new Motherboard("MotherboardProva", 43, 3, "LGA1150", "Z77", "DDR3", false, 2);

		f1.setMotherboard(motherboard);
		
		ArrayList<Case> compatibleComponents = caseAssembly.getCompatibleComponents(f1);
		
		assertFalse(compatibleComponents.contains(case0), "Comparing a motherboard with uncompatible (smaller size) case");
		assertTrue(compatibleComponents.contains(case1), "Comparing a motherboard with compatible (equal size) case");
		assertTrue(compatibleComponents.contains(case2), "Comparing a motherboard with compatible (bigger size) case");
		assertTrue(compatibleComponents.contains(case3), "Comparing a motherboard with compatible (bigger size) case");
		
		f1.setMotherboard(null);
	}
	
	@Test
	void getComponentsByIndex() throws JAXBException {
		Case case0 = new Case("Prova0", 2, 3, 1);
		Case case1 = new Case("Prova1", 15, 26, 2);
		Case case2 = new Case("Prova2", 2, 3, 3);
		Case case3 = new Case("Prova3", 15, 26, 4);
		
		ArrayList<Case> caseList = new ArrayList<>();
		caseList.add(case0);
		caseList.add(case1);
		caseList.add(case2);
		caseList.add(case3);
		
		caseDao.addComponents(caseList);
		FullConfigBuilder f1 = FullConfigBuilder.getInstance();
		caseAssembly.InputBasedBehavior(caseAssembly, f1 , "0");
		assertEquals(f1.getMyCase1(), case0, "Comparing the Case with right index");
		assertNotEquals(f1.getMyCase1(), case1, "Choosing the Case with wrong index");
		
	}

	
	@AfterAll
	static void  clean() throws JAXBException 
	{
		CaseDao caseDao1 = new CaseDao();
		caseDao1.setDefaultComponents();
	}
	
	
}
