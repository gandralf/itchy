package itchy;

import static itchy.ItchySuite.browser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import itchy.testsupport.FakeApp;

import org.junit.Before;
import org.junit.Test;

public class FormTest {
	@Before
	public void visitExamplePage() {
		browser.visit(FakeApp.EXAMPLE_URL);
	}

	@Test
	public void fillsFieldById() {
		browser.fill("secondTFId", "Value written by id");
		assertThat(browser.findById("secondTFId").value(), equalTo("Value written by id"));
	}
	
	@Test
	public void fillsFieldByName() {
		browser.fill("secondTextField", "Value written by name");
		assertThat(browser.findById("secondTFId").value(), equalTo("Value written by name"));
	}
	
	@Test
	public void fillsFieldByLabel() {
		browser.fill("Second", "Value written by label");
		assertThat(browser.findById("secondTFId").value(), equalTo("Value written by label"));
	}
	
	@Test(expected=ElementNotFoundException.class)
	public void raisesExceptionWhenElementIsNotFound() {
		browser.fill("UNEXISTING", "a dummy value");
	}
	
	@Test
	public void choosesRadioButtonByNameAndValue() {
		assertThat(browser.findById("hb").isChosen(), is(false));
		browser.choose("sport", "handball");
		assertThat(browser.findById("hb").isChosen(), is(true));
	}
	
	@Test
	public void choosesRadioButtonByLabel() {
		assertThat(browser.findById("vb").isChosen(), is(false));
		browser.choose("Volleyball");
		assertThat(browser.findById("vb").isChosen(), is(true));
	}
}