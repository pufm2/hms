package puf.m2.hms.view;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import puf.m2.hms.model.Physician;

public class ManagePhysicianTest {

	@Test
	public void testIsValidFields_name_is_blank() {
		ManagePhysician frm = new ManagePhysician();
		frm.getTxtName().setText("");
		Assert.assertEquals("You must put physician name",
				frm.checkValidFields());
	}

	@Test
	public void testIsValidFields_name_valid() {
		ManagePhysician frm = new ManagePhysician();
		frm.getTxtName().setText("Nguyen Huu Phat");
		Assert.assertEquals("True", frm.checkValidFields());
	}

	@Test
	public void testIsDuplicatePhysicianname() {
		ManagePhysician frm = new ManagePhysician();

		Physician mock = EasyMock.createMock(Physician.class);
		String physicianName = "Tran The Hien";
		EasyMock.expect(mock.getPhysicianByName(physicianName)).andReturn(null);

		EasyMock.replay(mock);
		Assert.assertEquals(false, frm.isDuplicatePhysicianname(physicianName));

	}
}
