/**
 * 
 */
package br.com.dsouza;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author rodrigo.pires
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	ClienteServiceTest.class,
	ClienteDAOTest.class,
	ProdutoServiceTest.class,
	ProdutoDAOTest.class,
	EstoqueDAOTest.class,
	VendaDAOTest.class
})
public class AllTests {

}